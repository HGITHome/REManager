package com.dgut.modules.border.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.border.entity.BusinessOrder;
import com.dgut.modules.border.entity.dto.BusinessOrderDto;
import com.dgut.modules.border.entity.support.PriorityEnum;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.border.service.BusinessOrderMng;
import com.dgut.modules.common.utils.PrincipalUtil;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.customer.service.CustomerMng;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.sys.log.service.AdminLogMng;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.service.UserMng;

@Controller
@RequestMapping("border")
public class BusinessOrderAct {
	
	private static final Logger log = LoggerFactory.getLogger(BusinessOrderAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<BusinessOrderDto> getPage(HttpServletRequest request,HttpServletResponse response,PageableWrap pageable){
		BusinessOrderDto dto = new BusinessOrderDto();
		User user = userMng.findByUserName(PrincipalUtil.getPrincipal());
		if(user == null) {
			dto.setEmployeeName(null);
		}else {
		  dto.setEmployeeName(user.getEmployee().getName());
		}
		String salesType = request.getParameter("salesType");
		if(!StringUtils.isBlank(salesType)) {
			dto.setSalesType(SalesTypeEnum.valueOf(request.getParameter("salesType")));
		}
		
		if(request.getParameter("transactionState") != null) {
			dto.setTransactionState(TransactionStateEnum.valueOf(request.getParameter("transactionState")));
		}
		if(request.getParameter("priority") != null) {
			dto.setPriority(PriorityEnum.valueOf(request.getParameter("priority")));
		}
		WebPage<BusinessOrderDto, Long> webPage = businessOrderMng.findAll(BusinessOrderDto.getWhereClause(dto), pageable.getPageable());
		Page<BusinessOrderDto> page = new PageImpl<BusinessOrderDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_update.do")
	public @ResponseBody AjaxMessage updateTransactionState(HttpServletRequest request,HttpServletResponse response,String borderId) {
		if(StringUtils.isBlank(borderId)) {
			return new AjaxMessage(false, "业务单id为空");
		}
		String transactionState = request.getParameter("transactionState");
		if(StringUtils.isBlank(transactionState)) {
			return new AjaxMessage(false, "业务单状态为空");
		}
		BusinessOrder border = businessOrderMng.findById(Integer.parseInt(borderId));
		border.setTransactionState(TransactionStateEnum.valueOf(transactionState));
		try {
			businessOrderMng.update(border);
			adminLogMng.operating(request, "更新业务单状态", "业务单id："+border.getId());
			return new AjaxMessage(true, "更新状态成功");
		}catch(Exception e) {
			log.info("更新业务单状态时:"+e.getMessage());
			return new AjaxMessage(false, "更新状态失败");
		}
	}
	
	@RequestMapping("o_finish.do")
	public @ResponseBody AjaxMessage finish(HttpServletRequest request,HttpServletResponse response,String borderId) {
		if(StringUtils.isBlank(borderId)) {
			return new AjaxMessage(false, "业务单id为空");
		}
		BusinessOrder border = businessOrderMng.findById(Integer.parseInt(borderId));
		if(border == null) {
			return new AjaxMessage(false, "业务单不存在");
		}
		String customerName =request.getParameter("customerName");
		if(StringUtils.isBlank(customerName)) {
			return new AjaxMessage(false, "客户名为空");
		}
		String transactionPrice = request.getParameter("transactionPrice");
		String intermediateFee = request.getParameter("intermediateFee");
		border.setTransactionPrice(Double.parseDouble(transactionPrice));
		border.setIntermediateFee(Double.parseDouble(intermediateFee));
		border.setCustomer(customerMng.findByNameLike(customerName));
		border.setTransactionState(TransactionStateEnum.SOLD);
		border.setFinishTime(new Date());
		try {
		   businessOrderMng.update(border);
		   adminLogMng.operating(request, "完成交易", "业务单id:"+border.getId());
		   return new AjaxMessage(true, "更新成功");
		}catch(Exception e) {
			log.info("更新业务单时：" + e.getMessage());
			return new AjaxMessage(false, "更新失败");
		}
		
	}
	
/*	@RequestMapping("o_save.do")
	public @ResponseBody AjaxMessage save(HttpServletRequest request, HttpServletResponse response,Integer[] roomIds,String[] prioritys,String employeeId){
		if(StringUtils.isBlank(employeeId)) {
			return new AjaxMessage(false, "工作人员id为空");
		}
		try {
			//businessOrderMngImpl.save(roomIds,prioritys,employeeId);
		}catch(Exception e) {
			return new AjaxMessage(false, "添加失败");
		}
		return null;
	}*/
	
	@Autowired
	private EmployeeMng employeeMng;
	
	@Autowired
	private CustomerMng customerMng;
	
	@Autowired
	private BusinessOrderMng businessOrderMng;
	
	@Autowired
	private UserMng userMng;
	
	@Autowired
	private AdminLogMng adminLogMng;
}

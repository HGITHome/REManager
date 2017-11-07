package com.dgut.modules.order.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.dgut.modules.border.entity.support.PriorityEnum;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.border.service.BusinessOrderMng;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.customer.service.CustomerMng;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.order.entity.RoomOrder;
import com.dgut.modules.order.entity.dto.RoomOrderDto;
import com.dgut.modules.order.entity.support.AuditedStateEnum;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.order.service.RoomOrderMng;
import com.dgut.modules.owner.service.OwnerMng;
import com.dgut.modules.sys.log.service.AdminLogMng;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.service.UserMng;

@Controller
@RequestMapping("order")
public class RoomOrderAct {

	private static final Logger log = LoggerFactory.getLogger(RoomOrderAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<RoomOrderDto> getPage(HttpServletRequest request,HttpServletResponse response,RoomOrderDto dto,PageableWrap pageable){
	    String salesType = request.getParameter("salesType");
		if(salesType != null && !salesType.trim().equals("")) {
			dto.setSalesType(SalesTypeEnum.valueOf(request.getParameter("salesType")));
		}
		String auditedState = request.getParameter("auditedState");
		if(!StringUtils.isBlank(auditedState) && auditedState.trim().equals("")) {
			dto.setAuditedState(AuditedStateEnum.valueOf(auditedState));
		}
		String employeeName = (String) request.getSession().getAttribute("name");
		String roleName = (String) request.getSession().getAttribute("roles");
		if(!roleName.equals("ROLE_SUPER") && !roleName.equals("ROLE_MANAGER")) {
			  dto.setAuditorName(employeeName);
		}
		WebPage<RoomOrderDto,Long> webPage = roomOrderMng.findAll(RoomOrderDto.getWhereClause(dto), pageable.getPageable());
		Page<RoomOrderDto> page = new PageImpl<RoomOrderDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_save.do")
	public @ResponseBody AjaxMessage save(HttpServletRequest request,HttpServletResponse response,RoomOrder order){
		String creatorName = request.getParameter("creatorName");
		String roomOwnerName = request.getParameter("roomOwnerName");
		String salesType = request.getParameter("salesType");
		String employeeName = (String) request.getSession().getAttribute("name");
		order.setCreator(employeeMng.findByNameLike(employeeName));
		order.setCreateTime(new Date());
		order.setIsDistribute(0);
		order.setSalesType(SalesTypeEnum.valueOf(salesType));
		order.setAuditedState(AuditedStateEnum.WAIT_AUDIT);
		order.setOwnerName(roomOwnerName);
		try{
			roomOrderMng.save(order);
			adminLogMng.operating(request, "保存订单", "订单id:"+order.getId());
			return new AjaxMessage(true,"保存成功");
		}catch(Exception e){
			log.info("保存订单时:"+e.getMessage());
			return new AjaxMessage(false,"保存失败");
		}
	}
	
	
//	public @ResponseBody AjaxMessage distribute(HttpServletRequest request,HttpServletResponse response,RoomOrder order) {
//		String employeeId = request.getParameter("employeeId");
//		if(StringUtils.isBlank(employeeId)) {
//			return new AjaxMessage(false, "员工id为空");
//		}
//		if(order.getId() == null) {
//			return new AjaxMessage(false, "委托单id为空");
//		}
//		
//	}
	
	@RequestMapping("o_update.do")
	public @ResponseBody AjaxMessage update(HttpServletRequest request,HttpServletResponse response){
		String employeeId = request.getParameter("employeeId");
		String orderId = request.getParameter("orderId");
		if(StringUtils.isBlank(employeeId)) {
			return new AjaxMessage(false, "员工ID为空");
		}
		if(StringUtils.isBlank(orderId)) {
			return new AjaxMessage(false, "订单id为空");
		}
		RoomOrder order = roomOrderMng.findById(Integer.parseInt(orderId));
		order.setAuditor(employeeMng.findById(Integer.parseInt(employeeId)));
		order.setIsDistribute(1);
		order.setAuditedState(AuditedStateEnum.WAIT_AUDIT);
		try{
			roomOrderMng.update(order);
			
			adminLogMng.operating(request, "分配委托单", "委托单id:"+order.getId());
			return new AjaxMessage(true, "分配成功");
		}catch(Exception e){
			log.info("更新委托单信息时:"+e.getMessage());
			return new AjaxMessage(false, "分配失败");
		}
	}
	@RequestMapping("o_waitAudit.do")
	public @ResponseBody Page<RoomOrderDto> getWaitAudit(HttpServletRequest request,HttpServletResponse response,PageableWrap pageable){
		RoomOrderDto dto = new RoomOrderDto();
		dto.setAuditedState(AuditedStateEnum.WAIT_AUDIT);
		WebPage<RoomOrderDto,Long> webPage = roomOrderMng.findAll(RoomOrderDto.getWhereClause(dto), pageable.getPageable());
	    Page<RoomOrderDto> page = new PageImpl<RoomOrderDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_isPass.do")
	public @ResponseBody AjaxMessage updateAudit(HttpServletRequest request,HttpServletResponse response){
		String roomOrderId = request.getParameter("roomOrderId");
		if(StringUtils.isBlank(roomOrderId)) {
			return new AjaxMessage(false, "委托单ID为空");
		}
		String auditStatus = request.getParameter("auditStatus");
		if(StringUtils.isBlank(auditStatus)) {
			return new AjaxMessage(false,"审核状态为空");
		}
		RoomOrder order = roomOrderMng.findById(Integer.parseInt(roomOrderId));
		if(auditStatus.equals("PASS")) {
			BusinessOrder border = new BusinessOrder();
			border.setCreateTime(new Date());
			border.setEmployee(order.getAuditor());
			border.setPriority(PriorityEnum.HIGH);
			border.setRoom(order.getRoom());
			border.setSalesType(order.getSalesType());
			border.setTransactionState(TransactionStateEnum.WAIT);
			border = businessOrderMng.save(border);
			adminLogMng.operating(request, "生成业务单", "业务单id："+border.getId());
		}
		order.setAuditedState(AuditedStateEnum.valueOf(auditStatus));
		try {
			roomOrderMng.update(order);
			return new AjaxMessage(true,  "更新审核状态成功");
		}catch (Exception e) {
			return new AjaxMessage(false, "更新审核状态失败");
		}
	}
	
	@RequestMapping("o_delete.do")
	public @ResponseBody AjaxMessage delete(HttpServletRequest request,HttpServletResponse response,Integer[] orderIds) {
		if(orderIds.length == 0) {
			return new AjaxMessage(false, "订单id为空");
		}
		try {
			roomOrderMng.deleteByIds(orderIds);
			adminLogMng.operating(request, "删除委托单", "订单ids:" + orderIds.toString());
			return new AjaxMessage(true, "删除成功");
		}catch (Exception e) {
			log.info("删除委托单时:" + e.getMessage());
			return new AjaxMessage(false, "删除失败");
			
		}
	}
	
	@Autowired
	private RoomOrderMng roomOrderMng;
	
	@Autowired
	private CustomerMng customerMng;
	
	@Autowired
	private EmployeeMng employeeMng;
	
	@Autowired
	private AdminLogMng adminLogMng;
	
	@Autowired
	private BusinessOrderMng businessOrderMng;
	
	
}

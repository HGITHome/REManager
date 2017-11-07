package com.dgut.modules.customer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.customer.entity.Customer;
import com.dgut.modules.customer.entity.dto.CustomerDto;
import com.dgut.modules.customer.service.CustomerMng;
import com.dgut.modules.sys.log.service.AdminLogMng;


@Controller
@RequestMapping("customer")
public class CustomerAct {

	private static final Logger log = LoggerFactory.getLogger(CustomerAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<Customer> getPage(HttpServletRequest request,HttpServletResponse response,PageableWrap pageable,CustomerDto dto){
		String sex = request.getParameter("sex");
		if(!StringUtils.isBlank(sex)) {
			dto.setSex(SexEnum.valueOf(request.getParameter("sex")));
		}
		Page<Customer> page = customerMng.findAll(CustomerDto.getWhereClause(dto),pageable.getPageable());
		
		return page;
	}
	
	@RequestMapping("o_save.do")
	public @ResponseBody AjaxMessage save(HttpServletRequest request,HttpServletResponse response,Customer customer){
		String sex = request.getParameter("gender");
		if(StringUtils.isBlank(sex)){
			return new AjaxMessage(false, "性别为空");
		}
		customer.setSex(SexEnum.valueOf(sex));
		try{
			customerMng.save(customer);
			adminLogMng.operating(request, "添加客户", "客户id:"+customer.getId());
			return new AjaxMessage(true, "添加成功");
		}catch(Exception e){
			log.info("保存客户信息时:"+e.getMessage());
			return new AjaxMessage(false, "添加失败");
		}
	}
	
	@RequestMapping("o_update.do")
	public @ResponseBody AjaxMessage update(HttpServletRequest request,HttpServletResponse response,Customer customer){
		String sex = request.getParameter("gender");
		if(StringUtils.isBlank(sex)){
			return new AjaxMessage(false, "性别为空");
		}
		if(customer.getId() == null){
			return new AjaxMessage(false, "客户id为空");
		}
		try{
			customerMng.update(customer);
			adminLogMng.operating(request, "更新客户", "客户id:"+customer.getId());
			return new AjaxMessage(true,"更新成功");
		}catch(Exception e){
			log.info("更新用户信息时:"+e.getMessage());
			return new AjaxMessage(false,"更新失败");
		}
	}
	
	@RequestMapping("o_delete.do")
	public @ResponseBody AjaxMessage delete(HttpServletRequest request,HttpServletResponse response,String customerId){
		if(StringUtils.isBlank(customerId)){
			return new AjaxMessage(false, "客户id为空");
		}
		try{
			customerMng.deleteById(Integer.parseInt(customerId));
			adminLogMng.operating(request, "删除客户", "客户id:"+customerId);
			return new AjaxMessage(true, "删除成功");
		}catch(Exception e){
			log.info("删除用户信息时:" +e.getMessage());
			return new AjaxMessage(false, "删除失败");
		}
	}
	@Autowired
	private CustomerMng customerMng;
	
	@Autowired
	private AdminLogMng adminLogMng;
}

package com.dgut.modules.employee.action;

import java.util.List;

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

import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.employee.entity.dto.EmployeeDto;
import com.dgut.modules.employee.entity.dto.ManagerDto;
import com.dgut.modules.employee.entity.support.EducationEnum;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.room.entity.support.EmployeeTypeEnum;
import com.dgut.modules.sys.log.service.AdminLogMng;

@Controller
@RequestMapping("employee")
public class EmployeeAct {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<EmployeeDto> getPage(HttpServletRequest request,HttpServletResponse response,EmployeeDto dto,PageableWrap pageable) {
		String employeeType = request.getParameter("employeeType");
		if(!StringUtils.isBlank(employeeType)) {
			dto.setEmployeeType(EmployeeTypeEnum.valueOf(request.getParameter("employeeType")));
		}
		String sex = request.getParameter("sex");
		if(!StringUtils.isBlank(sex)) {
			dto.setSex(SexEnum.valueOf(sex));
		}
		WebPage<EmployeeDto, Long> webPage = employeeMng.findAll(EmployeeDto.getWhereClause(dto), pageable.getPageable());
		Page<EmployeeDto> page = new PageImpl<EmployeeDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_list.do")
	public @ResponseBody List<ManagerDto> getManagerDtoList(HttpServletRequest request,HttpServletResponse response){
		List<ManagerDto> lists = employeeMng.getManagerDtoList();
		return lists;
	}
	
	@RequestMapping("o_save.do")
	public @ResponseBody AjaxMessage save(HttpServletRequest request,HttpServletResponse response,Employee employee) {
		String sex = request.getParameter("sex");
		if(StringUtils.isBlank(sex)) {
			return new AjaxMessage(false, "性别为空");
		}
		String education = request.getParameter("education");
		if(StringUtils.isBlank(education)) {
			return new AjaxMessage(false, "学历为空");
		}
		String managerId = request.getParameter("managerId");
		if(StringUtils.isBlank(managerId)) {
			return new AjaxMessage(false, "所属经理id为空");
		}
		String employeeType = request.getParameter("employeeType");
		if(StringUtils.isBlank(employeeType)) {
			return new AjaxMessage(false, "职位为空");
		}
		employee.setSex(SexEnum.valueOf(sex));
		employee.setEducation(EducationEnum.valueOf(education));
		employee.setManager(employeeMng.findById(Integer.parseInt(managerId)));
		employee.setEmployeeType(EmployeeTypeEnum.valueOf(employeeType));
		try {
			employeeMng.save(employee);
			adminLogMng.operating(request, "添加员工", "员工姓名:"+employee.getName());
			return new AjaxMessage(false, "添加成功");
		}catch (Exception e) {
			log.info("添加员工时:"+e.getMessage());
			return new AjaxMessage(false, "添加失败");
		}
	}
	
	@RequestMapping("o_delete.do")
	public @ResponseBody AjaxMessage delete(HttpServletRequest request,HttpServletResponse response,String employeeId) {
		if(StringUtils.isBlank(employeeId)) {
			return new AjaxMessage(false, "员工id为空");
		}
		try {
			employeeMng.deleteById(Integer.parseInt(employeeId));
			adminLogMng.operating(request, "删除员工", "员工id="+employeeId);
			return new AjaxMessage(true, "删除成功");
		}catch (Exception e) {
			log.info("删除员工时:"+e.getMessage());
			return new AjaxMessage(false,"删除失败");
		}
	}
	@Autowired
	private EmployeeMng employeeMng;
	
	@Autowired
	private AdminLogMng adminLogMng;
}

package com.dgut.modules.sys.log.action;

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

import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.sys.log.entity.AdminLog;
import com.dgut.modules.sys.log.entity.dto.AdminLogDto;
import com.dgut.modules.sys.log.entity.support.LogType;
import com.dgut.modules.sys.log.service.AdminLogMng;

@Controller
@RequestMapping("log")
public class AdminLogAct {

	private static final Logger log = LoggerFactory.getLogger(AdminLogAct.class);
	
	@RequestMapping("v_list_operating.do")
	public @ResponseBody Page<AdminLogDto> operated(HttpServletRequest request,HttpServletResponse response,AdminLogDto dto,PageableWrap pageable){
		dto.setLogType(LogType.OPERATING);
		WebPage<AdminLogDto, Long> webPage = adminLogMng.findAll(AdminLogDto.getWhereClause(dto), pageable.getPageable());
		Page<AdminLogDto> page = new PageImpl<AdminLogDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_operating_delete.do")
	public @ResponseBody AjaxMessage operatedDelete(HttpServletRequest request,HttpServletResponse response,Integer[] operatedIds){
		if(operatedIds == null || operatedIds.length ==0 ){
			return new AjaxMessage(false, "操作日志id为空");
		}
		try{
			adminLogMng.deleteByIds(operatedIds);
			return new AjaxMessage(true, "删除成功");
		}catch(Exception e){
			log.info("删除操作日志时:" + e.getMessage());
			return new AjaxMessage(false, "删除失败");
		}
	}
	
	@RequestMapping("v_list_login_success.do")
	public @ResponseBody Page<AdminLogDto> logSuccess(HttpServletRequest request,HttpServletResponse response,AdminLogDto dto,PageableWrap pageable){
	    dto.setLogType(LogType.LOGIN_SUCCESS);
	    WebPage<AdminLogDto, Long> webPage = adminLogMng.findAll(AdminLogDto.getWhereClause(dto), pageable.getPageable());
	    Page<AdminLogDto> page = new PageImpl<AdminLogDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_login_success_delete.do")
	public @ResponseBody AjaxMessage logSuccessDelete(HttpServletRequest request,HttpServletResponse response,Integer[] logSuccessIds){
		if(logSuccessIds == null || logSuccessIds.length ==0 ){
			return new AjaxMessage(false, "登录成功日志id为空");
		}
		try{
			adminLogMng.deleteByIds(logSuccessIds);
			return new AjaxMessage(true, "删除成功");
		}catch(Exception e){
			log.info("删除登录成功日志时:" + e.getMessage());
			return new AjaxMessage(false, "删除失败");
		}
	}
	
	@RequestMapping("v_list_login_failure.do")
	public @ResponseBody Page<AdminLogDto> logFailure(HttpServletRequest request,HttpServletResponse response,AdminLogDto dto,PageableWrap pageable){
	    dto.setLogType(LogType.LOGIN_FAILURE);
		WebPage<AdminLogDto,Long> webPage = adminLogMng.findAll(AdminLogDto.getWhereClause(dto), pageable.getPageable());
		Page<AdminLogDto> page = new PageImpl<AdminLogDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_login_failure_delete.do")
	public @ResponseBody AjaxMessage logFailureDelete(HttpServletRequest request,HttpServletResponse response,Integer[] logFailureIds){
		if(logFailureIds == null || logFailureIds.length ==0 ){
			return new AjaxMessage(false, "登录失败日志id为空");
		}
		try{
			adminLogMng.deleteByIds(logFailureIds);
			return new AjaxMessage(true, "删除成功");
		}catch(Exception e){
			log.info("删除登录失败日志时:" + e.getMessage());
			return new AjaxMessage(false, "删除失败");
		}
	}
	
	
	@Autowired
	private AdminLogMng adminLogMng;
	

}

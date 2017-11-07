package com.dgut.modules.sys.log.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UrlPathHelper;

import com.dgut.modules.common.utils.PrincipalUtil;
import com.dgut.modules.common.utils.RequestUtils;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.sys.log.dao.AdminLogDao;
import com.dgut.modules.sys.log.entity.AdminLog;
import com.dgut.modules.sys.log.entity.dto.AdminLogDto;
import com.dgut.modules.sys.log.entity.support.LogType;
import com.dgut.modules.sys.log.service.AdminLogMng;

@Service
@Transactional
public class AdminLogMngImpl implements AdminLogMng {

	@Transactional(readOnly=true)
	public WebPage<AdminLogDto, Long> findAll(Specification<AdminLog> spec,
			Pageable pageable) {
		Page<AdminLog> page = dao.findAll(spec, pageable);;
		List<AdminLog> adminLogList = page.getContent();
		List<AdminLogDto> dtoList = new ArrayList<AdminLogDto>();
		AdminLogDto dto = null;
		for(AdminLog log : adminLogList) {
			dto = new AdminLogDto();
			AdminLogDto.entityToDto(log, dto);
			dtoList.add(dto);
		}
		WebPage<AdminLogDto, Long> webPage = new WebPage<AdminLogDto, Long>(dtoList, page.getTotalElements());
		return webPage;
	}

	@Transactional(readOnly=true)
	public AdminLog findById(Integer id) {
		
		return dao.findOne(id);
	}
	
	public AdminLog operating(HttpServletRequest request, String title,String content){
		//Admin user = CmsUtils.getAdmin(request);
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		String employeeName = (String) request.getSession().getAttribute("name");
		Employee e = empolyeeMng.findByNameLike(employeeName);
		System.out.println("PrincipalUtil.getPrincipal()="+PrincipalUtil.getPrincipal());
		AdminLog log = save(LogType.OPERATING,  e, uri, ip, date,
				 title, content);
		return log;
	}

	
    public AdminLog LoginSuccess(HttpServletRequest request, String title,String content) {
    	String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		Employee e = empolyeeMng.findByNameLike(PrincipalUtil.getPrincipal());
		AdminLog log = save(LogType.LOGIN_SUCCESS,  e, uri, ip, date,
				 title, content);
		return log;
    }
	
	public AdminLog LoginFailure(HttpServletRequest request, String title,String content) {
		String ip = RequestUtils.getIpAddr(request);
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		Date date = new Date();
		Employee e = empolyeeMng.findByNameLike(PrincipalUtil.getPrincipal());
		AdminLog log = save(LogType.LOGIN_FAILURE,  e, uri, ip, date,
				 title, content);
		return log;
	}
	
	public AdminLog save(Integer operating, Employee employee, String uri, String ip,
			Date date, String title, String content) {
		AdminLog log = new AdminLog();
		log.setLogType(operating);
		log.setEmployee(employee);
		log.setUri(uri);
		log.setIp(ip);
		log.setLogTime(date);
		log.setTitle(title);
		log.setContent(content);
		save(log);
		return log;
	}

	public AdminLog save(AdminLog bean) {
		
		return dao.save(bean);
	}

	public AdminLog update(AdminLog bean) {
	
		return dao.save(bean);
	}

	public AdminLog deleteById(Integer id) {
		AdminLog bean = dao.findOne(id);
		if(bean != null){
			dao.delete(bean);
		}
		return bean;
	}

	public List<AdminLog> deleteByIds(Integer[] ids) {
		List<AdminLog> lists = new ArrayList<AdminLog>(ids.length);
		for(int i = 0 ; i < ids.length ; i++){
			AdminLog bean = dao.findOne(ids[i]);
			if(bean != null){
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}
	
	@Autowired
	private AdminLogDao dao;
	
	@Autowired
	private EmployeeMng empolyeeMng;

}

package com.dgut.modules.sys.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.sys.log.entity.AdminLog;
import com.dgut.modules.sys.log.entity.dto.AdminLogDto;

public interface AdminLogMng {

    public WebPage<AdminLogDto, Long> findAll(Specification<AdminLog> spec,Pageable pageable);
	
	public AdminLog findById(Integer id);
	
	public AdminLog operating(HttpServletRequest request, String title,String content);
	
	public AdminLog LoginSuccess(HttpServletRequest request, String title,String content);
	
	public AdminLog LoginFailure(HttpServletRequest request, String title,String content);
	
	public AdminLog save(AdminLog bean);
	
	public AdminLog update(AdminLog bean);
	
	public AdminLog deleteById(Integer id);
	
	public List<AdminLog> deleteByIds(Integer[] ids);
}

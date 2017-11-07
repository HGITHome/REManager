package com.dgut.modules.email.service.impl;

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

import com.dgut.modules.common.utils.PrincipalUtil;
import com.dgut.modules.email.dao.EmailLogDao;
import com.dgut.modules.email.entity.EmailLog;
import com.dgut.modules.email.service.EmailLogMng;

@Service
@Transactional
public class EmailLogMngImpl implements EmailLogMng {

	@Transactional(readOnly=true)
	public Page<EmailLog> findAll(Specification<EmailLog> spec, Pageable pageable) {
		
		return dao.findAll(spec, pageable);
	}

	@Transactional(readOnly=true)
	public EmailLog findById(Integer id) {
		
		return dao.findOne(id);
	}

	public EmailLog saveSendLog(HttpServletRequest request,String receiver,String subject,Integer isSuccess) {
		String employeeName = (String) request.getSession().getAttribute("name");
		EmailLog log = new EmailLog();
		log.setReceiver(receiver);
		log.setSender(employeeName);
		log.setSendTime(new Date());
		log.setSubject(subject);
		log.setIsSuccess(isSuccess);
		save(log);
		return log;
	}
	
	public EmailLog save(EmailLog bean) {
		
		return dao.save(bean);
	}

	public EmailLog update(EmailLog bean) {
		
		return dao.save(bean);
	}

	public EmailLog deleteById(Integer id) {
		EmailLog bean  = dao.findOne(id);
		if(bean != null) {
			dao.delete(bean);
		}
		return bean;
	}

	public List<EmailLog> deleteByIds(Integer[] ids) {
		List<EmailLog> lists = new ArrayList<EmailLog>(ids.length);
		for(int i = 0 ; i < ids.length ; i ++) {
			EmailLog bean = dao.findOne(ids[i]);
			if(bean != null) {
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}
	
	@Autowired
	private EmailLogDao dao;
}

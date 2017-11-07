package com.dgut.modules.email.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.email.entity.EmailLog;

public interface EmailLogMng {

	   public  Page<EmailLog> findAll(Specification<EmailLog> spec,Pageable pageable);
		
		public EmailLog findById(Integer id);
		
		public EmailLog saveSendLog(HttpServletRequest request,String receiver,String subject,Integer isSuccess);
		
		public EmailLog save(EmailLog bean);
		
		public EmailLog update(EmailLog bean);
		
		public EmailLog deleteById(Integer id);
		
		public List<EmailLog> deleteByIds(Integer[] ids);
}

package com.dgut.modules.email.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.email.entity.EmailLog;

public class EmailLogDto implements Serializable {

	/**
	 * 邮箱发发送日志
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String sender;
	private String receiver;
	private String subject;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public static Specification<EmailLog> getWhereClause(final EmailLogDto emailLogDto)
    {
		return new Specification<EmailLog>() {

			public Predicate toPredicate(Root<EmailLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> predicate = new ArrayList<Predicate>();
				 if(emailLogDto.getSender() !=null && !emailLogDto.getSender().trim().equals("")) {
					 predicate.add(cb.like(root.get("sender").as(String.class), "%"+emailLogDto.getSender()+"%"));
				 }
				 if(emailLogDto.getReceiver()!=null && !emailLogDto.getReceiver().trim().equals("")) {
					 predicate.add(cb.like(root.get("receiver").as(String.class),"%"+emailLogDto.getReceiver() +"%"));
				 }
				 if(emailLogDto.getSubject()!=null && !emailLogDto.getSubject().trim().equals("")) {
					 predicate.add(cb.like(root.get("subject").as(String.class),"%"+emailLogDto.getSubject() +"%"));
				 }

				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
	
		};
      
    }
	
	

}

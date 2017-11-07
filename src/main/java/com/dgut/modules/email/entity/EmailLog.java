package com.dgut.modules.email.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="email_log")
public class EmailLog implements Serializable {

	/**
	 * 发送邮箱日志
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String sender;
	private String receiver;
	private String subject;
	private Date sendTime;
	private Integer isSuccess;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Column(name="sender",nullable=false)
	public String getSender() {
		return sender;
	}
	@Column(name="receiver",nullable=false)
	public String getReceiver() {
		return receiver;
	}
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@Column(name="send_time",nullable=false)
	public Date getSendTime() {
		return sendTime;
	}
	@Column(name="isSuccess",nullable=false)
	public Integer getIsSuccess() {
		return isSuccess;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
	

}

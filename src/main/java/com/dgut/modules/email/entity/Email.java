package com.dgut.modules.email.entity;

import java.io.Serializable;

public class Email implements Serializable {

	/**
	 * 邮箱类
	 */
	private static final long serialVersionUID = 1L;
	
	//private String sendMail;
	private String receiveMail;
	private String subject;
	private String emailContent;
//	public String getSendMail() {
//		return sendMail;
//	}
//	public void setSendMail(String sendMail) {
//		this.sendMail = sendMail;
//	}
	public String getReceiveMail() {
		return receiveMail;
	}
	public void setReceiveMail(String receiveMail) {
		this.receiveMail = receiveMail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	
	
	

}

package com.dgut.modules.common.web;

public class AjaxMessage {

	private Boolean isSuccess;
	private String message;
	public AjaxMessage(){}
	public AjaxMessage(Boolean isSuccess,String message){
		this.isSuccess = isSuccess;
		this.message = message;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

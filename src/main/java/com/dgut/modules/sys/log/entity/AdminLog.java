package com.dgut.modules.sys.log.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgut.modules.employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="admin_log")
public class AdminLog implements Serializable {

	/**
	 * 系统操作日志־
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Employee employee;
	private Integer logType;
	private Date logTime;
	private String ip;
	private String uri;
	private String title;
	private String content;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@ManyToOne
	@JoinColumn(name="employee_id")
	public Employee getEmployee() {
		return employee;
	}
	@Column(name="logType")
	public Integer getLogType() {
		return logType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@Column(name="log_time")
	public Date getLogTime() {
		return logTime;
	}
	@Column(name="ip",length=100)
	public String getIp() {
		return ip;
	}
	@Column(name="uri",length=100)
	public String getUri() {
		return uri;
	}
	@Column(name="title",length=100)
	public String getTitle() {
		return title;
	}
	@Column(name="content",length=100)
	public String getContent() {
		return content;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

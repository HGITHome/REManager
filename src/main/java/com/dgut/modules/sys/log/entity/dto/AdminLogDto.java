package com.dgut.modules.sys.log.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.realEstate.entity.dto.RealEstateDto;
import com.dgut.modules.sys.log.entity.AdminLog;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AdminLogDto implements Serializable {
	/**

	 * AdminLogDto��
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String employeeName;
	private Integer logType;
	private Date logTime;
	private String ip;
	private String uri;
	private String title;
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public static void entityToDto(AdminLog log,AdminLogDto dto) {
		BeanUtils.copyProperties(log, dto);
		if(log.getEmployee() != null) {
			dto.setEmployeeName(log.getEmployee().getName());
		}
	}

	 public static Specification<AdminLog> getWhereClause(final AdminLogDto adminLogDto)
	    {
			return new Specification<AdminLog>() {

				public Predicate toPredicate(Root<AdminLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					 List<Predicate> predicate = new ArrayList<Predicate>();
					 if(adminLogDto.getEmployeeName()!=null && !adminLogDto.getEmployeeName().trim().equals("")) {
						 //root.join("employee").get("name").as(String.class),"%"+ businessOrderDto.getEmployeeName()+"%")
						 predicate.add(cb.like(root.join("employee").get("name").as(String.class), "%"+adminLogDto.getEmployeeName()+"%"));
					 }
					 if(adminLogDto.getTitle() != null && !adminLogDto.getTitle().trim().equals("")) {
						 predicate.add(cb.like(root.get("title").as(String.class),"%"+adminLogDto.getTitle()+"%"));
					 }
					 if(adminLogDto.getLogType() != null && !adminLogDto.getLogType().toString().trim().equals("")) {
						 predicate.add(cb.equal(root.get("logType").as(Integer.class), adminLogDto.getLogType()));
					 }
					
					return cb.and(predicate.toArray(new Predicate[predicate.size()]));
				}
		
			};
	      
	    }
}

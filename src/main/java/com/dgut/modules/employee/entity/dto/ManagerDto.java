package com.dgut.modules.employee.entity.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.dgut.modules.employee.entity.Employee;

public class ManagerDto implements Serializable {

	/**
	 * 经理Dto
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static void entityToDto(Employee e,ManagerDto dto) {
		BeanUtils.copyProperties(e, dto);
	}

}

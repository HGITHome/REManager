package com.dgut.modules.sys.statistic.entity;

import java.io.Serializable;

public class Statistic implements Serializable {

	/**
	 * 统计类
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer totalCount;
	
	public Statistic() {}
	
	
	public Statistic(String name, Integer totalCount) {
		super();
		this.name = name;
		this.totalCount = totalCount;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public Integer getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	
}

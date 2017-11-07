package com.dgut.modules.common.web;

import java.io.Serializable;
import java.util.List;

public class WebPage<T,ID extends Serializable> implements Serializable {

	/**
	 * 前段分页临时类
	 */
	private static final long serialVersionUID = 1L;

    private List<T> contentList ;
	
	private ID totalCount;

	public WebPage(){
	}
	


	public WebPage(List<T> contentList, ID totalCount) {
		super();
		this.contentList = contentList;
		this.totalCount = totalCount;
	}


	public List<T> getContentList() {
		return contentList;
	}


	public void setContentList(List<T> contentList) {
		this.contentList = contentList;
	}


	public ID getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(ID totalCount) {
		this.totalCount = totalCount;
	}
}

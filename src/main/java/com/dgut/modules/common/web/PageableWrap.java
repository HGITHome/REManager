package com.dgut.modules.common.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageableWrap {

	private int 	page 	= 1;
	private int 	limit	= 25;
	private String  sort	= "id";
	private String  dir 	= "ASC";
	
	public void setPage(int page) {this.page = page;}
	public void setLimit(int limit) {this.limit = limit;}
	public void setSort(String sort) {this.sort = sort;}
	public void setDir(String dir) {this.dir = dir;}
	public  Pageable getPageable() 
	{
		Pageable pageable = null;
		if(!sort.trim().equals("") && !dir.trim().equals("")) 
		{
			Sort pageSort = new Sort(Direction.ASC,sort);
			if(dir.equals("ASC")) {
				pageSort = new Sort(Direction.DESC,sort);
			}
			pageable = new PageRequest(page-1, limit,pageSort); 
		}else {
			pageable = new PageRequest(page-1, limit);
		}
		return pageable;
	}
}

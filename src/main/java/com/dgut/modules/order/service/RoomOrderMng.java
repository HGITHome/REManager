package com.dgut.modules.order.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.order.entity.RoomOrder;
import com.dgut.modules.order.entity.dto.RoomOrderDto;

public interface RoomOrderMng {

	public WebPage<RoomOrderDto,Long> findAll(Specification<RoomOrder> spec,Pageable pageable);
	
	public RoomOrder findById(Integer id);
	
	public RoomOrder save(RoomOrder bean);
	
	public RoomOrder update(RoomOrder bean);
	
	public RoomOrder deleteById(Integer id);
	
	public List<RoomOrder> deleteByIds(Integer[] ids);
}

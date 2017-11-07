package com.dgut.modules.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.order.dao.RoomOrderDao;
import com.dgut.modules.order.entity.RoomOrder;
import com.dgut.modules.order.entity.dto.RoomOrderDto;
import com.dgut.modules.order.service.RoomOrderMng;

@Service
@Transactional
public class RoomOrderMngImpl implements RoomOrderMng{

	@Transactional(readOnly=true)
	public WebPage<RoomOrderDto,Long> findAll(Specification<RoomOrder> spec,Pageable pageable) {
		Page<RoomOrder> page = dao.findAll(spec, pageable);
		List<RoomOrder> lists = page.getContent();
		List<RoomOrderDto> dtoList = new ArrayList<RoomOrderDto>();
		RoomOrderDto dto = null;
		for(RoomOrder order : lists) {
			dto = new RoomOrderDto();
			RoomOrderDto.entityToDto(order, dto);
			dtoList.add(dto);
		}
		WebPage<RoomOrderDto,Long> webPage = new WebPage<RoomOrderDto,Long>(dtoList,page.getTotalElements());
		return webPage;
	}

	@Transactional(readOnly=true)
	public RoomOrder findById(Integer id) {
		
		return dao.findOne(id);
	}

	public RoomOrder save(RoomOrder bean) {
	
		return dao.save(bean);
	}

	public RoomOrder update(RoomOrder bean) {
		
		return dao.save(bean);
	}

	public RoomOrder deleteById(Integer id) {
		RoomOrder bean = dao.findOne(id);
		if(bean != null){
			dao.delete(bean);
		}
		return bean;
	}

	public List<RoomOrder> deleteByIds(Integer[] ids) {
		List<RoomOrder> lists = new ArrayList<RoomOrder>(ids.length);
		for(int  i = 0 ; i < ids.length ; i++){
			RoomOrder bean = dao.findOne(ids[i]);
			if(bean != null){
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}
	
	@Autowired
	private RoomOrderDao dao;

}

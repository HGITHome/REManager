package com.dgut.modules.room.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.sys.statistic.entity.Statistic;

public interface RoomMng {

	public WebPage<RoomDto, Long> findAll(Specification<Room> spec, Pageable pageable);
	
	public Room findById(Integer id);
	
	public Room save(Room bean);
	
	public Room update(Room bean);
	
	public Room deleteById(Integer id);
	
	public Integer countByRealEstate(RealEstate r);
}

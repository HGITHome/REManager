package com.dgut.modules.owner.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.owner.entity.Owner;
import com.dgut.modules.owner.entity.dto.OwnerDto;
import com.dgut.modules.room.entity.dto.RoomDto;

public interface OwnerMng {

    public WebPage<OwnerDto,Long> findAll(Specification<Owner> spec,Pageable pageable);
	
	public Owner findById(Integer id);
	
	public Owner findByName(String name);
	
	public WebPage<RoomDto, Integer> getRoomByOwnerId(Integer id);
	
	public Owner save(Owner bean);
	
	public Owner update(Owner bean);
	
	public Owner deleteById(Integer id);
	
	public List<Owner> deleteByIds(Integer[] ids);
}

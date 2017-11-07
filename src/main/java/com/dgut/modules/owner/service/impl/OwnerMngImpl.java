package com.dgut.modules.owner.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.owner.dao.OwnerDao;
import com.dgut.modules.owner.entity.Owner;
import com.dgut.modules.owner.entity.dto.OwnerDto;
import com.dgut.modules.owner.service.OwnerMng;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.RoomDto;

@Service
@Transactional
public class OwnerMngImpl implements OwnerMng {

	@Transactional(readOnly=true)
	public WebPage<OwnerDto,Long> findAll(Specification<Owner> spec, Pageable pageable) {
		Page<Owner> page = dao.findAll(spec, pageable);
		List<OwnerDto> ownerDtoList = new ArrayList<OwnerDto>();
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		List<Owner> ownerList = page.getContent();
		OwnerDto ownerDto = null;
		RoomDto roomDto = null;
		for(Owner o : ownerList) {
			ownerDto = new OwnerDto();
			OwnerDto.entityToDto(o, ownerDto);
			List<Room> roomList = new ArrayList<Room>(o.getRoomSet());
			for(Room r : roomList) {
				roomDto = new RoomDto();
				RoomDto.entityToDto(r, roomDto);
				roomDtoList.add(roomDto);
			}
			ownerDto.setRoomSet(new HashSet<RoomDto>(roomDtoList));
			ownerDtoList.add(ownerDto);
		}
		WebPage<OwnerDto,Long> webPage = new WebPage<OwnerDto,Long>(ownerDtoList, page.getTotalElements());
		return webPage;
	}

	public WebPage<RoomDto, Integer> getRoomByOwnerId(Integer id){
		Owner owner = dao.findOne(id);
		if(owner == null) {
			return null;
		}
		if(owner.getRoomSet() == null) {
			return null;
		}
		Set<Room> roomSet = owner.getRoomSet();
		if(roomSet == null) {
			return null;
		}
		RoomDto dto = null;
		List<Room> roomList = new ArrayList<Room>(roomSet);
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		for(Room r : roomList) {
			dto = new RoomDto();
			RoomDto.entityToDto(r, dto);
			roomDtoList.add(dto);
		}
		WebPage<RoomDto, Integer> webPage = new WebPage<RoomDto, Integer>(roomDtoList,roomList.size());
		return webPage;
	}
	
	@Transactional(readOnly=true)
	public Owner findById(Integer id) {
		
		return dao.findOne(id);
	}

	@Transactional(readOnly=true)
	public Owner findByName(String name) {
		return dao.findByNameLike(name);
	}
	
	public Owner save(Owner bean) {
		
		return dao.save(bean);
	}

	public Owner update(Owner bean) {
		
		return dao.save(bean);
	}

	public Owner deleteById(Integer id) {
		Owner bean = dao.findOne(id);
		if(bean != null) {
			dao.delete(bean);
		}
		return bean;
	}

	
	
	public List<Owner> deleteByIds(Integer[] ids) {
		List<Owner> lists = new ArrayList<Owner>(ids.length);
		for(int  i = 0 ; i < ids.length ; i ++) {
			Owner bean = dao.findOne(ids[i]);
			if(bean != null) {
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}
	
	@Autowired
	private OwnerDao dao;

}

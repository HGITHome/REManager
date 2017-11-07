package com.dgut.modules.room.service.impl;


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
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.room.dao.RoomDao;
import com.dgut.modules.room.entity.ImagFile;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.ImagFileDto;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.room.service.RoomMng;

@Service
@Transactional
public class RoomMngImpl implements RoomMng {

	@Transactional(readOnly=true)
	public WebPage<RoomDto, Long> findAll(Specification<Room> spec,Pageable pageable) {
		Page<Room> page = dao.findAll(spec,pageable);
		List<Room> roomList = page.getContent();
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		RoomDto dto = null;
		ImagFileDto fileDto = null;
		Set<ImagFile> fileSet = new HashSet<ImagFile>();
		Set<ImagFileDto> fileDtoSet = new HashSet<ImagFileDto>(); 
		for(Room r : roomList) {
			dto = new RoomDto();
			RoomDto.entityToDto(r, dto);
			fileSet = r.getImagSet();
			for(ImagFile file : fileSet) {
				fileDto = new ImagFileDto();
				ImagFileDto.entityToDto(file, fileDto);
				fileDtoSet.add(fileDto);
			}
			dto.setImagDtoSet(fileDtoSet);
			roomDtoList.add(dto);
		}
		WebPage<RoomDto, Long> webPage = new WebPage<RoomDto, Long>(roomDtoList,page.getTotalElements());
		return webPage;
	}

	@Transactional(readOnly=true)
	public Room findById(Integer id) {
	
		return dao.findOne(id);
	}

	
	public Room save(Room bean) {
		
		return dao.save(bean);
	}

	public Room update(Room bean){
		return dao.save(bean);
	}
	
	public Room deleteById(Integer id) {
		Room bean = dao.findOne(id);
		if(bean != null){
			dao.delete(bean);
		}
		return bean;
	}

	@Transactional(readOnly=true)
	public Integer countByRealEstate(RealEstate r){
		return dao.findNumberByRealEstate(r);
	}
	
	@Autowired
	private RoomDao dao;

}

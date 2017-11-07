package com.dgut.modules.realEstate.service.impl;

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

import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.realEstate.dao.RealEstateDao;
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.realEstate.entity.dto.RealEstateDto;
import com.dgut.modules.realEstate.service.RealEstateMng;
import com.dgut.modules.room.entity.ImagFile;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.ImagFileDto;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.room.service.RoomMng;
import com.dgut.modules.sys.statistic.entity.Statistic;

@Service
@Transactional
public class RealEstateMngImpl implements RealEstateMng{

	@Transactional(readOnly=true)
	public WebPage<RealEstateDto,Long> findAll(Specification<RealEstate> spec,
			Pageable pageable) {
		RoomDto roomDto = null;
		RealEstateDto rDto = null;
		List<RoomDto> dtoLists = new ArrayList<RoomDto>();
		List<RealEstateDto> rDtoLists = new ArrayList<RealEstateDto>();
		Page<RealEstate> page = dao.findAll(spec, pageable);
		
		List<RealEstate> lists = page.getContent();
		ImagFileDto fileDto = null;
		Set<ImagFile> fileSet = new HashSet<ImagFile>();
		Set<ImagFileDto> fileDtoSet = new HashSet<ImagFileDto>(); 
		for(RealEstate r : lists) {
			rDto = new RealEstateDto();
			RealEstateDto.entityToDto(r, rDto);
			List<Room> roomList = new ArrayList<Room>(r.getRoomSet());
			for(Room room : roomList) {
				roomDto = new RoomDto();
				RoomDto.entityToDto(room, roomDto);
				fileSet = room.getImagSet();
				for(ImagFile file : fileSet) {
					fileDto = new ImagFileDto();
					ImagFileDto.entityToDto(file, fileDto);
					fileDtoSet.add(fileDto);
				}
				roomDto.setImagDtoSet(fileDtoSet);
				dtoLists.add(roomDto);
			}
			rDto.setDtoSet(new HashSet<RoomDto>(dtoLists));
			rDtoLists.add(rDto);
		}
		WebPage<RealEstateDto,Long> webPage = new WebPage<RealEstateDto,Long>(rDtoLists,page.getTotalElements());
		return webPage;
	}

	@Transactional(readOnly=true)
	public Page<RealEstate> getAllList(Specification<RealEstate> spec,Pageable pageable){
		return dao.findAll(spec, pageable);
	}
	
	@Transactional(readOnly=true)
	public List<RealEstate> getRealEastaeList(){
		return dao.getRealEastateList();
	}
	@Transactional(readOnly=true)
	public List<RealEstateDto> getAllList(){
		RealEstateDto  dto = null;
		List<RealEstate> list = dao.getRealEastateList();
		List<RealEstateDto> dtoList = new ArrayList<RealEstateDto>();
		for(RealEstate r : list) {
			dto = new RealEstateDto();
			RealEstateDto.entityToDto(r, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Transactional(readOnly=true)
	public RealEstate findById(Integer id) {
		return dao.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public WebPage<RoomDto,Long> findByRealEstateId(Integer id,RoomDto roomdto,PageableWrap pageable) {
		RealEstate realEstate = dao.findOne(id);
		roomdto.setRealEastateName(realEstate.getEstateName());
		WebPage<RoomDto,Long> webPage = roomMng.findAll(RoomDto.getWhereClause(roomdto), pageable.getPageable());
		
		return webPage;
	}

	public RealEstate save(RealEstate bean) {
		
		return dao.save(bean);
	}

	public RealEstate update(RealEstate bean) {
		
		return dao.save(bean);
	}

	public RealEstate deleteById(Integer id) {
		RealEstate bean = dao.findOne(id);
		if(bean != null){
			dao.delete(bean);
		}
		return bean;
	}

	public List<RealEstate> deleteByIds(Integer[] ids) {
		List<RealEstate> lists = new ArrayList<RealEstate>(ids.length);
		for(int i = 0 ; i < ids.length ; i ++){
			RealEstate bean = dao.findOne(ids[i]);
			if(bean != null){
				dao.delete(bean);
			    lists.add(bean);
			}
		}
		return lists;
	}
	
	@Autowired
	private RealEstateDao dao;

	@Autowired
	private RoomMng roomMng;
}

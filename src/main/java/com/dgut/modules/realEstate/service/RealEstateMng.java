package com.dgut.modules.realEstate.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.realEstate.entity.dto.RealEstateDto;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.sys.statistic.entity.Statistic;

public interface RealEstateMng {

	public WebPage<RealEstateDto,Long> findAll(Specification<RealEstate> spec,Pageable pageable);
	
	public Page<RealEstate> getAllList(Specification<RealEstate> spec,Pageable pageable);
	
	public WebPage<RoomDto,Long> findByRealEstateId(Integer id,RoomDto roomdto,PageableWrap pageable);
	
	public RealEstate findById(Integer id);
	
	public RealEstate save(RealEstate bean);
	
	public RealEstate update(RealEstate bean);
	
	public RealEstate deleteById(Integer id);
	
	public List<RealEstate> deleteByIds(Integer[] ids);
	
	public List<RealEstate> getRealEastaeList();
	
	public List<RealEstateDto> getAllList();

}

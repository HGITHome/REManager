package com.dgut.modules.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.report.service.ExportMng;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.room.entity.dto.RoomWebDto;
import com.dgut.modules.room.service.RoomMng;

@Service
@Transactional
public class ExportMngImpl implements ExportMng {

	@Transactional(readOnly=true)
	public List<RoomWebDto> getPage(Specification<Room> spec, Pageable pageable) {
		WebPage<RoomDto, Long> webPage = roomMng.findAll(spec, pageable);
		List<RoomDto> roomDtoList = webPage.getContentList();
		List<RoomWebDto> roomWebDtoList = new ArrayList<RoomWebDto>();
		RoomWebDto webDto = null;
		for(RoomDto rdto : roomDtoList) {
			webDto = new RoomWebDto();
			RoomWebDto.entityToDto(rdto, webDto);
			roomWebDtoList.add(webDto);
		}
		return roomWebDtoList;
	}

	@Autowired
	private RoomMng roomMng;
}

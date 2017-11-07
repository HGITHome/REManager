package com.dgut.modules.report.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.RoomWebDto;

public interface ExportMng {

	public 	List<RoomWebDto> getPage(Specification<Room> spec, Pageable pageable);
}

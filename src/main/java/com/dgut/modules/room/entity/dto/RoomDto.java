package com.dgut.modules.room.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;


import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.room.entity.ImagFile;
import com.dgut.modules.room.entity.Room;

public class RoomDto implements Serializable {

	/**
	 * 房子Dto
	 */

	private Integer id;
	private String roomNo;
	private Integer floor;
	private Integer roofBeam;
	private Double area;
	private Integer roomNumber;
	private Integer living;
	private Integer toilet;
	private Integer layer;
	private Integer isParking;
	private Integer isYard;
	private String ownerName;
	private String realEastateName;
	private SalesTypeEnum salesType;
	private Double preArea;
	private Double afterArea;
	private String imagUrl;
	private String miniUrl;
	private Set<ImagFileDto> imagDtoSet;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getRoofBeam() {
		return roofBeam;
	}
	public void setRoofBeam(Integer roofBeam) {
		this.roofBeam = roofBeam;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Integer getLiving() {
		return living;
	}
	public void setLiving(Integer living) {
		this.living = living;
	}
	public Integer getToilet() {
		return toilet;
	}
	public void setToilet(Integer toilet) {
		this.toilet = toilet;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}


	public Integer getIsParking() {
		return isParking;
	}
	public void setIsParking(Integer isParking) {
		this.isParking = isParking;
	}
	public Integer getIsYard() {
		return isYard;
	}
	public void setIsYard(Integer isYard) {
		this.isYard = isYard;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	

	public String getRealEastateName() {
		return realEastateName;
	}
	public void setRealEastateName(String realEastateName) {
		this.realEastateName = realEastateName;
	}
	public SalesTypeEnum getSalesType() {
		return salesType;
	}
	public void setSalesType(SalesTypeEnum salesType) {
		this.salesType = salesType;
	}
	
	
	public Double getPreArea() {
		return preArea;
	}
	public void setPreArea(Double preArea) {
		this.preArea = preArea;
	}
	public Double getAfterArea() {
		return afterArea;
	}
	public void setAfterArea(Double afterArea) {
		this.afterArea = afterArea;
	}
	
	public Set<ImagFileDto> getImagDtoSet() {
		return imagDtoSet;
	}
	public void setImagDtoSet(Set<ImagFileDto> imagDtoSet) {
		this.imagDtoSet = imagDtoSet;
	}

	
	public String getImagUrl() {
		return imagUrl;
	}
	public void setImagUrl(String imagUrl) {
		this.imagUrl = imagUrl;
	}
	public String getMiniUrl() {
		return miniUrl;
	}
	public void setMiniUrl(String miniUrl) {
		this.miniUrl = miniUrl;
	}
	public static void entityToDto(Room room , RoomDto dto) {
		BeanUtils.copyProperties(room, dto);
		if(room.getOwner() != null) {
			dto.setOwnerName(room.getOwner().getName());
		}
		if(room.getRealEstate() != null) {
			dto.setRealEastateName(room.getRealEstate().getEstateName());
		}
		if(room.getImagSet() != null) {
			Set<ImagFile> imagSet = room.getImagSet();
			Set<ImagFileDto> imagDtoSet = new HashSet<ImagFileDto>();
			ImagFileDto imagDto = null;
			for(ImagFile i : imagSet) {
				imagDto = new ImagFileDto();
				ImagFileDto.entityToDto(i, imagDto);
				imagDtoSet.add(imagDto);
				dto.setImagUrl(i.getImagUrl());
				dto.setMiniUrl(i.getMiniUrl());
			}
			dto.setImagDtoSet(imagDtoSet);
		}
	}
	
	public static Specification<Room> getWhereClause(final RoomDto roomDto)
    {
		return new Specification<Room>() {

			public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> predicate = new ArrayList<Predicate>();
				 if(roomDto.getRealEastateName() != null && !roomDto.getRealEastateName().trim().equals("")) {
					 predicate.add(cb.like(root.join("realEstate").get("estateName").as(String.class), "%"+ roomDto.getRealEastateName()+"%"));
				 }
				 if(roomDto.getSalesType()!=null && !roomDto.getSalesType().toString().trim().equals("")) {
					 predicate.add(cb.equal(root.get("salesType").as(SalesTypeEnum.class), roomDto.getSalesType()));
				 }
				 if(roomDto.getIsParking() != null && !roomDto.getIsParking().toString().trim().equals("")) {
					 predicate.add(cb.equal(root.get("isParking").as(Integer.class), roomDto.getIsParking()));
				 }
				 if(roomDto.getIsYard() != null && !roomDto.getIsYard().toString().trim().equals("")) {
					 predicate.add(cb.equal(root.get("isYard").as(Integer.class), roomDto.getIsYard()));
				 }
				 if(roomDto.getPreArea() != null && !roomDto.getPreArea().toString().equals("") && roomDto.getAfterArea() != null && !roomDto.getAfterArea().toString().trim().equals("")) {
					 predicate.add(cb.between(root.get("area").as(Double.class), roomDto.getPreArea(), roomDto.getAfterArea()));
				 }
				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
	
		};
      
    }

	
}

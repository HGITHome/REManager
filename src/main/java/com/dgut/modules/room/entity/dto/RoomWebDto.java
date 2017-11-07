package com.dgut.modules.room.entity.dto;

import org.springframework.beans.BeanUtils;

import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.room.entity.Room;

public class RoomWebDto {

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
	public static void entityToDto(RoomDto r,RoomWebDto dto) {
		BeanUtils.copyProperties(r, dto);
	}
	
}

package com.dgut.modules.order.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.dgut.modules.customer.entity.Customer;
import com.dgut.modules.customer.entity.dto.CustomerDto;
import com.dgut.modules.order.entity.RoomOrder;
import com.dgut.modules.order.entity.support.AuditedStateEnum;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RoomOrderDto implements Serializable {

	/**
	 * 委托单dto
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date auditTime;
	private AuditedStateEnum auditedState;//审核状态
	
	private String creatorName;
	
	private String auditorName;

	private Double price;
    private SalesTypeEnum salesType;
    private String estateName;
    private String location;
    private Integer isDistribute;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date preTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date afterTime;
    private RoomDto roomDto;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public AuditedStateEnum getAuditedState() {
		return auditedState;
	}
	public void setAuditedState(AuditedStateEnum auditedState) {
		this.auditedState = auditedState;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public SalesTypeEnum getSalesType() {
		return salesType;
	}
	public void setSalesType(SalesTypeEnum salesType) {
		this.salesType = salesType;
	}
	public String getEstateName() {
		return estateName;
	}
	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

	public Integer getIsDistribute() {
		return isDistribute;
	}
	public void setIsDistribute(Integer isDistribute) {
		this.isDistribute = isDistribute;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getAuditorName() {
		return auditorName;
	}
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	
	
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	public Date getPreTime() {
		return preTime;
	}
	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}
	@JsonFormat(pattern = "yyyy/MM/dd")
	public Date getAfterTime() {
		return afterTime;
	}
	public void setAfterTime(Date afterTime) {
		this.afterTime = afterTime;
	}
	

	public RoomDto getRoomDto() {
		return roomDto;
	}
	public void setRoomDto(RoomDto roomDto) {
		this.roomDto = roomDto;
	}
	public static void entityToDto(RoomOrder roomOrder,RoomOrderDto dto) {
		RoomDto roomDto = null;
		BeanUtils.copyProperties(roomOrder, dto);
		if(roomOrder.getCreator() != null) {
		   dto.setCreatorName(roomOrder.getCreator().getName());
		}
		if(roomOrder.getAuditor() != null) {
		  dto.setAuditorName(roomOrder.getAuditor().getName());
		}
		if(roomOrder.getRoom() != null) {
			roomDto = new RoomDto();
			RoomDto.entityToDto(roomOrder.getRoom(), roomDto);
			dto.setRoomDto(roomDto);
		}
	}
	
	
	public static Specification<RoomOrder> getWhereClause(final RoomOrderDto roomOrderDto)
	    {
			return new Specification<RoomOrder>() {
//predicate.add(cb.like(root.join("employee").get("name").as(String.class),"%"+ businessOrderDto.getEmployeeName()+"%"));
				public Predicate toPredicate(Root<RoomOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					 List<Predicate> predicate = new ArrayList<Predicate>();
					 if(roomOrderDto.getCreatorName()!=null && !roomOrderDto.getCreatorName().trim().equals("")) {
						 predicate.add(cb.like(root.join("creator").get("name").as(String.class),"%"+ roomOrderDto.getCreatorName()+"%"));
					 }
					 if(roomOrderDto.getAuditorName()!=null && !roomOrderDto.getAuditorName().trim().equals("")) {
						 predicate.add(cb.like(root.join("auditor").get("name").as(String.class),"%"+ roomOrderDto.getAuditorName()+"%"));
					 }
					 if(roomOrderDto.getSalesType() != null && !roomOrderDto.getSalesType().toString().equals("")) {
						 predicate.add(cb.equal(root.get("salesType").as(SalesTypeEnum.class), roomOrderDto.getSalesType()));
					 }
					 
					 if(roomOrderDto.getAuditedState() != null && !roomOrderDto.getAuditedState().toString().equals("")) {
						 predicate.add(cb.equal(root.get("auditedState").as(AuditedStateEnum.class), roomOrderDto.getAuditedState()));
					 }
					 
					 if(roomOrderDto.getIsDistribute() != null && !roomOrderDto.getIsDistribute().toString().equals("")) {
						 predicate.add(cb.equal(root.get("isDistribute").as(Integer.class),roomOrderDto.getIsDistribute()));
					 }
					 
					 if(roomOrderDto.getPreTime() != null && roomOrderDto.getAfterTime() != null) {
						 predicate.add(cb.between(root.get("createTime").as(Date.class),roomOrderDto.getPreTime(), roomOrderDto.getAfterTime()));
					 }
					 
					return cb.and(predicate.toArray(new Predicate[predicate.size()]));
				}
		
			};
	      
	    }
    
    
}

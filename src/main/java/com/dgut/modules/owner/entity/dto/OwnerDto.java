package com.dgut.modules.owner.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.order.entity.RoomOrder;
import com.dgut.modules.order.entity.dto.RoomOrderDto;
import com.dgut.modules.order.entity.support.AuditedStateEnum;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.owner.entity.Owner;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.RoomDto;

public class OwnerDto implements Serializable {

	/**
	 * 业主Dto
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private SexEnum sex;
	
	private String phone;

    private String IDNumber;
	
	private String address;

	private Set<RoomDto> roomSet;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public Set<RoomDto> getRoomSet() {
		return roomSet;
	}

	public void setRoomSet(Set<RoomDto> roomSet) {
		this.roomSet = roomSet;
	}

	public static void entityToDto(Owner owner,OwnerDto dto) {
		BeanUtils.copyProperties(owner, dto);
	}
	
	public static Specification<Owner> getWhereClause(final OwnerDto ownerDto)
    {
		return new Specification<Owner>() {

			public Predicate toPredicate(Root<Owner> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> predicate = new ArrayList<Predicate>();
				 if(ownerDto.getName()!=null && !ownerDto.getName().trim().equals("")) {
					 predicate.add(cb.like(root.get("name").as(String.class),"%"+ ownerDto.getName() + "%"));
				 }
				 if(ownerDto.getAddress()!=null && !ownerDto.getAddress().trim().equals("")) {
					 predicate.add(cb.like(root.get("address").as(String.class),"%"+ ownerDto.getAddress() + "%"));
				 }
				 if(ownerDto.getSex()!=null && !ownerDto.getSex().toString().equals("")) {
					 predicate.add(cb.equal(root.get("sex").as(SexEnum.class), ownerDto.getSex()));
				 }
				 if(ownerDto.getPhone()!= null && !ownerDto.getPhone().trim().equals("")) {
					 predicate.add(cb.equal(root.get("phone").as(String.class), ownerDto.getPhone()));
				 }
				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
	
		};
      
    }
	

}

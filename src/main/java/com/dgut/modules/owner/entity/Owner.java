package com.dgut.modules.owner.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;

import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.room.entity.Room;

@Entity
@Table(name="owner")
public class Owner implements Serializable {

	/**
	 * 业主类
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private SexEnum sex;
	
	private String phone;
	
	private String IDNumber;
	
	private String address;


	private Set<Room> roomSet;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(name="name",nullable=false)
	public String getName() {
		return name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="sex",nullable=false)
	public SexEnum getSex() {
		return sex;
	}

	@Column(name="phone",nullable=false)
	public String getPhone() {
		return phone;
	}

	@Column(name="IDNumber",length=18,nullable=false)
	public String getIDNumber() {
		return IDNumber;
	}

	@Column(name="address",nullable=false)
	public String getAddress() {
		return address;
	}

	@OneToMany
	@Cascade(value=CascadeType.ALL)
	@JoinColumn(name="owner_id")
	public Set<Room> getRoomSet() {
		return roomSet;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRoomSet(Set<Room> roomSet) {
		this.roomSet = roomSet;
	}
	
	
	
	

}

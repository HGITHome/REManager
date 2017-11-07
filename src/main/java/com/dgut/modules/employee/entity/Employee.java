package com.dgut.modules.employee.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.employee.entity.support.EducationEnum;
import com.dgut.modules.room.entity.support.EmployeeTypeEnum;

@Entity
@Table(name="employee")
public class Employee implements Serializable {

	/**
	 * 员工类
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String phone;
	private SexEnum sex;
	private Date birthday;
	private String QQ;
	private String email;
	private EducationEnum education;
	private String address;
	private Date entry_time;
	private Employee manager;
	private EmployeeTypeEnum employeeType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name",length=100,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="phone",length=11,nullable=false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="sex")
	public SexEnum getSex() {
		return sex;
	}
	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
	

	@Temporal(TemporalType.DATE)
	@Column(name="birthday",nullable=false)
	public Date getBirthday() {
		return birthday;
	}
	@Column(name="QQ",nullable=false)
	public String getQQ() {
		return QQ;
	}
	@Column(name="email",nullable=false)
	public String getEmail() {
		return email;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="education",nullable=false)
	public EducationEnum getEducation() {
		return education;
	}
	@Column(name="address",nullable=false)
	public String getAddress() {
		return address;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="entry_time",nullable=false)
	public Date getEntry_time() {
		return entry_time;
	}
	/**
     * @return 上级领导者
     */
    @ManyToOne
    @JoinColumn(name = "manager_id")
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="employee_type")
	public EmployeeTypeEnum getEmployeeType() {
		return employeeType;
	}
	
	public void setEmployeeType(EmployeeTypeEnum employeeType) {
		this.employeeType = employeeType;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEducation(EducationEnum education) {
		this.education = education;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	
	

	 
	 
}

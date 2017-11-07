package com.dgut.modules.employee.entity.dto;

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

import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.employee.entity.support.EducationEnum;
import com.dgut.modules.room.entity.support.EmployeeTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDto implements Serializable {

	/**
	 * 员工Dto
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
	private EmployeeTypeEnum employeeType;
	private String managerName;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public SexEnum getSex() {
		return sex;
	}
	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
	
	
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public EmployeeTypeEnum getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeTypeEnum employeeType) {
		this.employeeType = employeeType;
	}
	
	
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EducationEnum getEducation() {
		return education;
	}
	public void setEducation(EducationEnum education) {
		this.education = education;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public static void entityToDto(Employee e,EmployeeDto dto) {
		BeanUtils.copyProperties(e, dto);
		if(e.getManager() != null) {
			dto.setManagerName(e.getManager().getName());
		}else {
			dto.setManagerName("");
		}
	}
	
	public static Specification<Employee> getWhereClause(final EmployeeDto employeeDto)
    {
		return new Specification<Employee>() {

			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> predicate = new ArrayList<Predicate>();
				 if(employeeDto.getName()!=null && !employeeDto.getName().trim().equals("")) {
					 predicate.add(cb.like(root.get("name").as(String.class), "%"+employeeDto.getName()+"%"));
				 }
				 if(employeeDto.getSex() !=null && !employeeDto.getSex().toString().trim().equals("")) {
					 predicate.add(cb.equal(root.get("sex").as(SexEnum.class), employeeDto.getSex()));
				 }
				 if(employeeDto.getPhone() != null && !employeeDto.getPhone().trim().equals("")) {
					 predicate.add(cb.like(root.get("phone").as(String.class), "%"+employeeDto.getPhone()+"%"));
				 }
				 if(employeeDto.getEmployeeType()!=null && !employeeDto.getEmployeeType().toString().equals("")) {
					 predicate.add(cb.equal(root.get("employeeType").as(EmployeeTypeEnum.class), employeeDto.getEmployeeType()));
				 }
				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
	
		};
      
    }

}

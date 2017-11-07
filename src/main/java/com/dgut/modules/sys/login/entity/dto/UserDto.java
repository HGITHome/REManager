package com.dgut.modules.sys.login.entity.dto;

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

import com.dgut.modules.sys.authority.entity.UserProfile;
import com.dgut.modules.sys.authority.entity.dto.UserProfileDto;
import com.dgut.modules.sys.login.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDto implements Serializable {

	/**
	 * 用户dto
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String email;
	private String state;
	
	private Date registerTime;
	private String roleName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public static void entityToDto(User user,UserDto dto) {
		BeanUtils.copyProperties(user, dto);
		if(!user.getUserProfiles().isEmpty()) {
			List<UserProfile> roleList = new ArrayList<UserProfile>(user.getUserProfiles());
			for(UserProfile u : roleList) {
				dto.setRoleName(u.getType());
			}
		}
	}
	
	public static Specification<User> getWhereClause(final UserDto userDto)
    {
		return new Specification<User>() {

			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> predicate = new ArrayList<Predicate>();
				 if(userDto.getUsername() != null && !userDto.getUsername().trim().equals("")) {
					 predicate.add(cb.like(root.get("username").as(String.class),"%"+ userDto.getUsername()+"%"));
				 }
				 if(userDto.getEmail() != null && !userDto.getEmail().trim().equals("")) {
					 predicate.add(cb.like(root.get("email").as(String.class), "%"+userDto.getEmail() +"%"));
				 }
				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
	
		};
      
    }
	

}

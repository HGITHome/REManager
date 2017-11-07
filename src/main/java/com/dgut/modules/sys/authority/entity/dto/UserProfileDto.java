package com.dgut.modules.sys.authority.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


import com.dgut.modules.sys.authority.entity.UserProfile;

public class UserProfileDto implements Serializable {

	/**
	 * 角色dto
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String type;
	
	private Integer isabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIsabled() {
		return isabled;
	}

	public void setIsabled(Integer isabled) {
		this.isabled = isabled;
	}
	
	
	public static Specification<UserProfile> getWhereClause(final UserProfileDto userProfileDto)
    {
		return new Specification<UserProfile>() {

			public Predicate toPredicate(Root<UserProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> predicate = new ArrayList<Predicate>();
				 if(userProfileDto.getType() != null && !userProfileDto.getType().trim().equals("")) {
					 predicate.add(cb.like(root.get("type").as(String.class), userProfileDto.getType()));
				 }
				 if(userProfileDto.getIsabled() != null && !userProfileDto.getIsabled().toString().trim().equals("")) {
					 predicate.add(cb.equal(root.get("isabled").as(Integer.class), userProfileDto.getIsabled()));
				 }
				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
	
		};
      
    }

	

}

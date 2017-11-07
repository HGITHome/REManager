package com.dgut.modules.realEstate.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;


import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.room.entity.dto.RoomDto;

public class RealEstateDto implements Serializable {

	/**
	 * ¥��dto
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String estateName;
    private String location;
    private String propertyCompany;
    private Set<RoomDto> dtoSet;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getPropertyCompany() {
		return propertyCompany;
	}
	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}
	
	
	
	public Set<RoomDto> getDtoSet() {
		return dtoSet;
	}
	public void setDtoSet(Set<RoomDto> dtoSet) {
		this.dtoSet = dtoSet;
	}
	public static void entityToDto(RealEstate realEstate,RealEstateDto dto) {
		BeanUtils.copyProperties(realEstate, dto);
	}
    
	 public static Specification<RealEstate> getWhereClause(final RealEstateDto realEstateDto)
	    {
			return new Specification<RealEstate>() {

				public Predicate toPredicate(Root<RealEstate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					 List<Predicate> predicate = new ArrayList<Predicate>();
					 if(realEstateDto.getEstateName()!=null && !realEstateDto.getEstateName().trim().equals("")) {
						 predicate.add(cb.like(root.get("estateName").as(String.class), "%"+realEstateDto.getEstateName()+"%"));
					 }
					 if(realEstateDto.getLocation()!=null && !realEstateDto.getLocation().trim().equals("")) {
						 predicate.add(cb.like(root.get("location").as(String.class), "%"+realEstateDto.getLocation()+"%"));
					 }
					 if(realEstateDto.getPropertyCompany() != null && !realEstateDto.getPropertyCompany().trim().equals("")){
						 predicate.add(cb.like(root.get("propertyCompany").as(String.class), "%"+realEstateDto.getPropertyCompany()+"%"));
					 }
					return cb.and(predicate.toArray(new Predicate[predicate.size()]));
				}
		
			};
	      
	    }

}

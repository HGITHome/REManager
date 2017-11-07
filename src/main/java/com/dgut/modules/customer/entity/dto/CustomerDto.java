package com.dgut.modules.customer.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.customer.entity.Customer;

public class CustomerDto implements Serializable {

	/**
	 * CustomerDto��
	 */
	private static final long serialVersionUID = -3034106150527312116L;
	
	private Integer id;
	private String name;
	private String phone;
	private SexEnum sex;
	private String email;
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
	
	 
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		public static Specification<Customer> getWhereClause(final CustomerDto customerDto)
	    {
			return new Specification<Customer>() {
				public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					 //1.声明Predicate集合
					 List<Predicate> predicate = new ArrayList<Predicate>();
					 //2.根据orderQueryDTO查询条件动态添加Predicate
					 if(customerDto.getName()!=null && !customerDto.getName().trim().equals("")) {
						 predicate.add(cb.like(root.get("name").as(String.class), "%"+customerDto.getName()+"%"));
					 }
					 if(customerDto.getPhone()!=null && !customerDto.getPhone().trim().equals("")) {
						 predicate.add(cb.like(root.get("phone").as(String.class), "%"+customerDto.getPhone()+"%"));
					 }
					 if(customerDto.getEmail()!=null && !customerDto.getEmail().trim().equals("")) {
						 predicate.add(cb.like(root.get("email").as(String.class), "%"+customerDto.getEmail()+"%"));
					 }
					 if(customerDto.getSex() != null && !customerDto.getSex().toString().trim().equals("")) {
						 predicate.add(cb.equal(root.get("sex").as(SexEnum.class), customerDto.getSex()));
					 }
					 //3.根据Predicate集合生成并返回and 连接的 where条件
		             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
				
				}
			};
	      
	    }

}

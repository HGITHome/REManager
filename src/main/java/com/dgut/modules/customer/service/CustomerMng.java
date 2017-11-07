package com.dgut.modules.customer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.customer.entity.Customer;

public interface CustomerMng {

    public  Page<Customer> findAll(Specification<Customer> spec,Pageable pageable);
	
	public Customer findById(Integer id);
	
	public Customer findByNameLike(String name);
	
	public Customer save(Customer bean);
	
	public Customer update(Customer bean);
	
	public Customer deleteById(Integer id);
	
	public List<Customer> deleteByIds(Integer[] ids);
}

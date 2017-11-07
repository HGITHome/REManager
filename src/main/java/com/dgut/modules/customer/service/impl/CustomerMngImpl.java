package com.dgut.modules.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.customer.dao.CustomerDao;
import com.dgut.modules.customer.entity.Customer;
import com.dgut.modules.customer.service.CustomerMng;

@Service
@Transactional
public class CustomerMngImpl implements CustomerMng {

	@Transactional(readOnly=true)
	public Page<Customer> findAll(Specification<Customer> spec,Pageable pageable) {
		
		return dao.findAll(spec,pageable);
	}

	@Transactional(readOnly=true)
	public Customer findById(Integer id) {
		
		return dao.findOne(id);
	}

	@Transactional(readOnly=true)
	public Customer findByNameLike(String name){
		return dao.findByNameLike("%"+name+"%");
	}
	
	public Customer save(Customer bean) {
		dao.save(bean);
		return bean;
	}


	public Customer update(Customer bean) {
		
		return dao.save(bean);
	}

	public Customer deleteById(Integer id) {
		Customer bean = dao.findOne(id);
		if(bean != null){
			dao.delete(bean);
		}
		return bean;
	}

	public List<Customer> deleteByIds(Integer[] ids) {
		List<Customer> lists = new ArrayList<Customer>(ids.length);
		for(int i = 0 ; i < ids.length ; i ++){
			Customer bean = dao.findOne(ids[i]);
			if(bean != null){
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}
	@Autowired
	private CustomerDao dao;

}

package com.dgut.modules.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dgut.modules.customer.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>,JpaSpecificationExecutor<Customer>{

	@Query("select bean from Customer bean where bean.name like:name")
	public Customer findByNameLike(@Param("name") String name);

}

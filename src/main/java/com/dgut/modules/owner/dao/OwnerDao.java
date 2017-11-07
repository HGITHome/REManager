package com.dgut.modules.owner.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dgut.modules.owner.entity.Owner;

@Repository
public interface OwnerDao extends PagingAndSortingRepository<Owner, Integer>,JpaSpecificationExecutor<Owner> {

	@Query("select bean from Owner bean where bean.name like:name")
	public Owner findByNameLike(@Param("name") String name);
}

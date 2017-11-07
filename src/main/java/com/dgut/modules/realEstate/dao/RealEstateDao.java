package com.dgut.modules.realEstate.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.sys.statistic.entity.Statistic;

@Repository
public interface RealEstateDao extends JpaRepository<RealEstate, Integer>,JpaSpecificationExecutor<RealEstate>{

	@Query("select bean from RealEstate bean where 1=1")
	public List<RealEstate> getRealEastateList();
}

package com.dgut.modules.sys.authority.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.modules.sys.authority.entity.UserProfile;

@Repository
public interface AuthorityDao extends PagingAndSortingRepository<UserProfile, Integer>,JpaSpecificationExecutor<UserProfile>{

}

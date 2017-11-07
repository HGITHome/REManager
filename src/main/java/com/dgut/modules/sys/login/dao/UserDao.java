package com.dgut.modules.sys.login.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dgut.modules.sys.login.entity.User;

@Repository
public interface UserDao extends PagingAndSortingRepository<User, Integer>,JpaSpecificationExecutor<User>{

	@Query("select bean from User bean where bean.email = :email")
	public User findByEmail(@Param("email")String email);
	
	@Query("select bean from User bean where bean.username = :username")
	public User findByUserName(@Param("username")String username);
}

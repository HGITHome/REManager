package com.dgut.modules.sys.authority.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.sys.authority.entity.UserProfile;
import com.dgut.modules.sys.login.entity.User;

public interface AuthorityMng {

    public Page<UserProfile> findAll(Specification<UserProfile> spec,Pageable pageable);
	
	public UserProfile findById(Integer id);
	
	public UserProfile save(UserProfile bean);
	
	public UserProfile update(UserProfile bean);
	
	public UserProfile deleteById(Integer id);
	
	public List<UserProfile> deleteByIds(Integer[] ids);

	public User updateAuthority(String userId, Integer[] authorityIds);
}

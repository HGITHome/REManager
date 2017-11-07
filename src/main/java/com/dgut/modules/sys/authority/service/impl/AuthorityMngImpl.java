package com.dgut.modules.sys.authority.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.dgut.modules.sys.authority.dao.AuthorityDao;
import com.dgut.modules.sys.authority.entity.UserProfile;
import com.dgut.modules.sys.authority.service.AuthorityMng;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.service.UserMng;

@Service
@Transactional
public class AuthorityMngImpl implements AuthorityMng {

	@Transactional(readOnly=true)
	public Page<UserProfile> findAll(Specification<UserProfile> spec, Pageable pageable) {
		
		return dao.findAll(spec, pageable);
	}

	@Transactional(readOnly=true)
	public UserProfile findById(Integer id) {
		
		return dao.findOne(id);
	}

	
	public User updateAuthority(String userId, Integer[] authorityIds) {
		User user = userMng.findById(Integer.parseInt(userId));
		Set<UserProfile> profileSet = user.getUserProfiles();
		if(profileSet == null) {
			profileSet = new HashSet<UserProfile>();
		}
		profileSet.clear();
		for(int i = 0 ; i < authorityIds.length ; i ++ ) {
			UserProfile profile = authorityMng.findById(authorityIds[i]);
			profileSet.add(profile);
		}
		user.setUserProfiles(profileSet);
		userMng.update(user);
		return user;
	}
	
	public UserProfile save(UserProfile bean) {
		
		return dao.save(bean);
	}

	public UserProfile update(UserProfile bean) {
		
		return dao.save(bean);
	}

	public UserProfile deleteById(Integer id) {
		UserProfile bean = dao.findOne(id);
		if(bean != null) {
			dao.delete(bean);
		}
		return bean;
	}

	public List<UserProfile> deleteByIds(Integer[] ids) {
		List<UserProfile> lists = new ArrayList<UserProfile>(ids.length);
		for(int i = 0 ; i < ids.length ; i ++) {
			UserProfile bean = dao.findOne(ids[i]);
			if(bean != null) {
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}
	
	@Autowired
	private AuthorityDao dao;
	
	@Autowired
	private UserMng userMng;

	@Autowired
	private AuthorityMng authorityMng;
	

}

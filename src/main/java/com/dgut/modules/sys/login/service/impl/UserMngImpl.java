package com.dgut.modules.sys.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.sys.login.dao.UserDao;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.entity.dto.UserDto;
import com.dgut.modules.sys.login.service.UserMng;


@Service
@Transactional
public class UserMngImpl implements UserMng {

	@Transactional(readOnly=true)
	public WebPage<UserDto, Long> getPage(Specification<User> spec,Pageable pageable){
		Page<User> page = dao.findAll(spec, pageable);
		List<User> userList = page.getContent();
		List<UserDto> dtoList = new ArrayList<UserDto>();
		UserDto dto = null;
		for(User user : userList) {
			dto = new UserDto();
			UserDto.entityToDto(user, dto);
			dtoList.add(dto);
		}
		WebPage<UserDto, Long> webPage = new WebPage<UserDto, Long>(dtoList, page.getTotalElements());
		return webPage;
	}
	
	@Transactional(readOnly=true)
	public User findById(Integer id) {
		
		return dao.findOne(id);
	}
	
	public User save(User user) {
		String encode = passwordEncoder.encode(user.getPassword());
		user.setPassword(encode);
		System.out.println("encode="+encode);
		return dao.save(user);
	}
	
   public User update(User user) {
	   return dao.save(user);
   }
	
   @Transactional(readOnly=true)
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	@Transactional(readOnly=true)
	public User findByUserName(String username) {
		
		return dao.findByUserName(username);
	}
	
	@Autowired
	private UserDao dao;

	 @Autowired  
	 private BCryptPasswordEncoder passwordEncoder;
}

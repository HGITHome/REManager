package com.dgut.modules.sys.login.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.entity.dto.UserDto;

public interface UserMng {

public User findById(Integer id); 

    public WebPage<UserDto, Long> getPage(Specification<User> spec,Pageable pageable);

	public User save(User bean);
    
    public User update(User user);
    
	public User findByEmail(String email);
	
    public  User findByUserName(String username);
}

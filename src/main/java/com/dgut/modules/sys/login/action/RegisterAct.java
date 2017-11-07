package com.dgut.modules.sys.login.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgut.modules.common.exception.ServiceException;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.service.impl.RegisterValidateService;


@Controller
public class RegisterAct {

	 private static final Logger log = LoggerFactory.getLogger(RegisterAct.class);
	 
	  @Autowired
	  private RegisterValidateService service;
	 
	  @RequestMapping("signUp")
	  public String getRegister(HttpServletRequest request,HttpServletResponse response) {
		  return "signUp";
	  }
	  
	    @RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
	    public String  load(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) throws java.text.ParseException, IOException {
	        String action = request.getParameter("action");
	        if("register".equals(action)) {
	            //注册
	            service.processregister(user,request);//发邮箱激活
	            model.addAttribute("text","注册成功");
	            return "register_success";
	        }
	        else{
	            //激活
	            String email = request.getParameter("email");//获取email
	            String validateCode = request.getParameter("validateCode");//激活码
	            try {
	                service.processActivate(email , validateCode);//调用激活方法
	                return "active_success";
	            } catch (ServiceException e) {
	            	log.info("激活账号时:"+e.getMessage());
	                request.setAttribute("message" , e.getMessage());
	                return "activate_failure";
	            }
	        }
	    }
}
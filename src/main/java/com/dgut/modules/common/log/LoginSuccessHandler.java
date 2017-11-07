package com.dgut.modules.common.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.dgut.modules.common.utils.PrincipalUtil;
import com.dgut.modules.sys.log.service.AdminLogMng;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.service.UserMng;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request,
			   HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		UserDetails uds = (UserDetails)authentication.getPrincipal();
//		WebAuthenticationDetails wauth = (WebAuthenticationDetails)authentication.getDetails();
		System.out.println(uds.getUsername());//获得用户名
		System.out.println(this.getIpAddress(request));//获得IP地址
		//使用spring security跳转的方法
		adminLogMng.LoginSuccess(request, "登录成功", "username:"+uds.getUsername());
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();  
		   
        List<String> roles = new ArrayList<String>();  
   
        for (GrantedAuthority a : authorities) {
        	System.out.println("a.getAuthority()="+a.getAuthority());
            roles.add(a.getAuthority());  
        }  
        if(!roles.isEmpty()) {
           request.getSession().setAttribute("roles", roles.get(0));
        }
        User user = userMng.findByUserName(PrincipalUtil.getPrincipal());
        if(user.getEmployee() != null) {
        	request.getSession().setAttribute("id",user.getId());
        	request.getSession().setAttribute("userName",user.getUsername());
        	request.getSession().setAttribute("name",user.getEmployee().getName());
        	request.getSession().setAttribute("miniUrl", user.getMiniUrl());
        	request.getSession().setAttribute("employeeType",user.getEmployee().getEmployeeType().toString());
        	request.getSession().setAttribute("phone",user.getEmployee().getPhone());
        	request.getSession().setAttribute("QQ",user.getEmployee().getQQ());
        	request.getSession().setAttribute("address",user.getEmployee().getAddress());
        	request.getSession().setAttribute("birthday",user.getEmployee().getBirthday());
        	request.getSession().setAttribute("education",user.getEmployee().getEducation());
        	request.getSession().setAttribute("email",user.getEmployee().getEmail());
        	request.getSession().setAttribute("entry_time",user.getEmployee().getEntry_time());
        }
		SavedRequestAwareAuthenticationSuccessHandler ash = new SavedRequestAwareAuthenticationSuccessHandler();
		ash.onAuthenticationSuccess(request, response, authentication);
	}
	
	  public String getIpAddress(HttpServletRequest request){    
	        String ip = request.getHeader("x-forwarded-for");    
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	            ip = request.getHeader("Proxy-Client-IP");    
	        }    
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	            ip = request.getHeader("WL-Proxy-Client-IP");    
	        }    
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	            ip = request.getHeader("HTTP_CLIENT_IP");    
	        }    
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
	        }    
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	            ip = request.getRemoteAddr();    
	        }    
	        return ip;    
	    }
	  
	  @Autowired
	  private AdminLogMng adminLogMng;
	  
	  @Autowired
	  private UserMng userMng;


}

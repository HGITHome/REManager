package com.dgut.modules.sys.login.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.common.exception.ServiceException;
import com.dgut.modules.common.utils.PrincipalUtil;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.service.UserMng;
import com.dgut.modules.sys.login.service.impl.FindPasswordService;

@Controller
public class LoginAct {

	private static final Logger log = LoggerFactory.getLogger(LoginAct.class);
	@Autowired
	private FindPasswordService service;
	
	@RequestMapping("login")
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		model.addAttribute("user", PrincipalUtil.getPrincipal());
		return "signIn";
	}
	
	@RequestMapping(value="/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:login";
	}
	
	@RequestMapping("o_find.do")
	public String getBackPassword(HttpServletRequest request,HttpServletResponse response) {
		return "findPassword";
	}
	
	@RequestMapping("o_send.do")
	public String sendfindPasswordEmail(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		if(StringUtils.isBlank(username)) {
			model.addAttribute("error", "用户名为空");
			return "findPassword";
		}
		if(StringUtils.isBlank(email)) {
			model.addAttribute("error", "邮箱地址为空");
			return "findPassword";
		}
		User user = userMng.findByUserName(username);
		if(user == null) {
			model.addAttribute("error", "用户名不存在");
			return "findPassword";
		}
		user = userMng.findByEmail(email);
		if(user == null) {
			model.addAttribute("error", "输入邮箱不是绑定邮箱地址");
			return "findPassword";
		}
		try {
			service.processFind(user, request);
			return "send_success";
		} catch (IOException e) {
			log.info("发送邮箱时:"+e.getMessage());
			model.addAttribute("error", "发送邮箱失败");
			e.printStackTrace();
			return "findPassword";
		}
		
	}
	
	
    @RequestMapping(value="/find",method={RequestMethod.GET,RequestMethod.POST})
    public String  validate(HttpServletRequest request,HttpServletResponse response,ModelMap model,User user) throws java.text.ParseException, IOException {
        String action = request.getParameter("action");
        if("findPassword".equals(action)) {
            String email = request.getParameter("email");//获取email
            String username = request.getParameter("username");
            String validateCode = request.getParameter("validateCode");//激活码
            try {
                service.processUpdate(email,validateCode,username);//调用激活方法
                return "validate_success";
            } catch (ServiceException e) {
            	log.info("验证激活链接时:"+e.getMessage());
                request.setAttribute("message" , e.getMessage());
                return "activate_failure";
            }
        }else {
        	request.setAttribute("message" ,"请求链接不对");
            return "activate_failure";
        }
    }
    
    @RequestMapping("o_updateForm.do")
    public String updateForm(HttpServletRequest request,HttpServletResponse response) {
    	return "updatePassword";
    }
	
    @RequestMapping("o_update.do")
    public String updatePassword(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
    	String username = request.getParameter("username");
    	User user = userMng.findByUserName(username);
    	if(user == null) {
    		model.addAttribute("error", "用户不存在");
    		return "updatePassword";
    	}
    	String password = request.getParameter("password");
    	user.setPassword(password);
    	try {
    		userMng.save(user);
    		return "update_success";
    	}catch(Exception e) {
    		log.info("重置密码时:"+e.getMessage());
    		return "update_failure";
    	}
    }
    
	@Autowired
	private UserMng userMng;
}


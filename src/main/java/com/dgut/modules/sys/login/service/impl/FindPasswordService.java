package com.dgut.modules.sys.login.service.impl;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.Constant;
import com.dgut.modules.common.email.SendEmail;
import com.dgut.modules.common.encodeByte.MD5Util;
import com.dgut.modules.common.exception.ServiceException;
import com.dgut.modules.common.file.FileReadUtil;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.entity.support.State;
import com.dgut.modules.sys.login.service.UserMng;

@Service
@Transactional
public class FindPasswordService {

	@Autowired
	  private UserMng userMng;
	 
	    /**
	     * 处理注册
	     * @throws IOException 
	    */
	    public void processFind(User user,HttpServletRequest request) throws IOException{
	        user.setRegisterTime(new Date());
	        String subject="找回密码邮件";
	        ///如果处于安全，可以将激活码处理的更复杂点，这里我稍做简单处理
	        //user.setValidateCode(MD5Tool.MD5Encrypt(email));
	        user.setValidateCode(MD5Util.encode2hex(user.getEmail()));
	        URL path = this.getClass().getClassLoader().getResource(Constant.FIND_EMAIL_TEMPLATE_NAME);
	        userMng.update(user);
	        String template= FileReadUtil.readFile(path.getPath());
	        String replacetr = new String();
	        replacetr=template;
	        replacetr=replacetr.replace("{email}",user.getEmail());
	        replacetr=replacetr.replace("{username}",user.getUsername());
	        replacetr=replacetr.replace("{validateCode}",user.getValidateCode());
	        ///邮件的内容
//	        StringBuffer sb=new StringBuffer("点击下面链接激活账号，24小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
//	        sb.append("<a href=\"http://localhost:8080/REManager/register?action=activate&email=");
//	        sb.append(user.getEmail());
//	        sb.append("&validateCode=");
//	        sb.append(user.getValidateCode());
//	        sb.append("\">http://localhost:8080/spring-security/register?action=activate&email=");
//	        sb.append(user.getEmail());
//	        sb.append("&validateCode=");
//	        sb.append(user.getValidateCode());
//	        sb.append("</a>");
	 
	        //发送邮件
	        SendEmail.send(user.getEmail(),subject, replacetr);
	        System.out.println("发送邮件");
	 
	    }
	 
	    /**
	     * 处理激活
	     * @throws ParseException
	     */
	      ///传递激活码和email过来
	    public void processUpdate(String email , String validateCode,String username)throws ServiceException, ParseException{ 
	         //数据访问层，通过email获取用户信息
	        User user=userMng.findByEmail(email);
	        //验证用户是否存在
	           if(user!=null) {
	        	   if(username.equals(user.getUsername())) {
	                Date currentTime = new Date();//获取当前时间 
	                //验证链接是否过期
	                if(currentTime.before(user.getLastActivateTime())) { 
	                    //验证激活码是否正确 
	                    if(validateCode.equals(user.getValidateCode())) { 
	                        
	                    } else { 
	                       throw new ServiceException("激活码不正确"); 
	                    } 
	                } else { 
	                	throw new ServiceException("激活码已过期！"); 
	                }
	        	   }else {
	        		   throw new ServiceException("用户名不匹配！");
	        	   }
	            }else {
	            	throw new ServiceException("用户不存在！");
	            }
	 
	    }
}

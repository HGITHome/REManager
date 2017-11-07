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
public class RegisterValidateService {

	  @Autowired
	  private UserMng userMng;
	 
	    /**
	     * 处理注册
	     * @throws IOException 
	    */
	    public void processregister(User user,HttpServletRequest request) throws IOException{
	        user.setRegisterTime(new Date());
	        String subject="账号激活邮件";
	        ///如果处于安全，可以将激活码处理的更复杂点，这里我稍做简单处理
	        //user.setValidateCode(MD5Tool.MD5Encrypt(email));
	        user.setValidateCode(MD5Util.encode2hex(user.getEmail()));
	        URL path = this.getClass().getClassLoader().getResource(Constant.EMAIL_TEMPLATE_NAME);
	        user.setEmployee(null);
	        userMng.save(user);//保存注册信息/spring-security/src/main/resources/email/activeEmail.template
	        String template= FileReadUtil.readFile(path.getPath());
	        String replacetr = new String();
	        replacetr=template;
	        replacetr=replacetr.replace("{email}",user.getEmail());
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
	    public void processActivate(String email , String validateCode)throws ServiceException, ParseException{ 
	         //数据访问层，通过email获取用户信息
	        User user=userMng.findByEmail(email);
	        //验证用户是否存在
	        if(user!=null) { 
	            //验证用户激活状态 
	        	String status = user.getState();
	            if(status.equals(State.INACTIVE.getState())) {
	                ///没激活
	                Date currentTime = new Date();//获取当前时间 
	                //验证链接是否过期
	                if(currentTime.before(user.getLastActivateTime())) { 
	                    //验证激活码是否正确 
	                    if(validateCode.equals(user.getValidateCode())) { 
	                        //激活成功， //并更新用户的激活状态，为已激活
	                        System.out.println("==sq==="+user.getState());
	                        user.setState(State.ACTIVE.getState());//把状态改为激活
	                        System.out.println("==sh==="+user.getState());
	                        userMng.update(user);
	                    } else { 
	                       throw new ServiceException("激活码不正确"); 
	                    } 
	                } else { throw new ServiceException("激活码已过期！"); 
	                } 
	            } else {
	               throw new ServiceException("邮箱已激活，请登录！"); 
	            } 
	        } else {
	            throw new ServiceException("该邮箱未注册（邮箱地址不存在）！"); 
	        } 
	 
	    }
}

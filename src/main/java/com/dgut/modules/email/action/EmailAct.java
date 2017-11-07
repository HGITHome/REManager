package com.dgut.modules.email.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.common.email.SendEmail;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.customer.entity.Customer;
import com.dgut.modules.customer.service.CustomerMng;
import com.dgut.modules.email.entity.Email;
import com.dgut.modules.email.service.EmailLogMng;

@Controller
@RequestMapping("email")
public class EmailAct {
	private static final Logger log = LoggerFactory.getLogger(EmailAct.class);
	
	@RequestMapping("o_sendEmail.do")
	public @ResponseBody AjaxMessage sendEmail(HttpServletRequest request,HttpServletResponse response,Email email) {
		String customerId = request.getParameter("customerId");
		if(StringUtils.isBlank(customerId)) {
			return new AjaxMessage(false, "客户id为空");
		}
		Customer customer = customerMng.findById(Integer.parseInt(customerId));
		try {
			SendEmail.send(customer.getEmail(),email.getSubject(), email.getEmailContent());
			emailLogMng.saveSendLog(request,customer.getName(), email.getSubject(), 1);
			return new AjaxMessage(true, "发送成功");
		}catch(Exception e) {
			log.debug("发送邮件时:"+e.getMessage());
			emailLogMng.saveSendLog(request,customer.getName(), email.getSubject(), 0);
			return new AjaxMessage(false, "发送失败");
		}
	}
	
	@Autowired
	private CustomerMng customerMng;
	
	@Autowired
	private EmailLogMng emailLogMng;
}

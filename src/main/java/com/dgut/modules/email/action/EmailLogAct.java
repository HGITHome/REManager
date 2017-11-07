package com.dgut.modules.email.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.email.entity.EmailLog;
import com.dgut.modules.email.entity.dto.EmailLogDto;
import com.dgut.modules.email.service.EmailLogMng;

@Controller
@RequestMapping("emailLog")
public class EmailLogAct {

	private static final Logger log = LoggerFactory.getLogger(EmailLogAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<EmailLog> getPage(HttpServletRequest request,HttpServletResponse response,EmailLogDto dto,PageableWrap pageable){
		Page<EmailLog> page = emailLogMng.findAll(EmailLogDto.getWhereClause(dto), pageable.getPageable());
        return page;
	}
	
	@RequestMapping("o_delete.do")
	public @ResponseBody AjaxMessage delete(HttpServletRequest request,HttpServletResponse response,Integer[] ids) {
		if(ids.length == 0) {
			return new AjaxMessage(false, "邮箱日志id为空");
		}
		try {
			emailLogMng.deleteByIds(ids);
			return new AjaxMessage(true, "删除成功");
		}catch (Exception e) {
			log.info("删除邮箱发送日志时:"+ ids.toString());
			return new AjaxMessage(false, "删除失败");
		}
	}
	
	@Autowired
	private EmailLogMng emailLogMng;
}

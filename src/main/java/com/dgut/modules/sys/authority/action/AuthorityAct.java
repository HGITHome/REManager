package com.dgut.modules.sys.authority.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.sys.authority.entity.UserProfile;
import com.dgut.modules.sys.authority.entity.dto.UserProfileDto;
import com.dgut.modules.sys.authority.service.AuthorityMng;
import com.dgut.modules.sys.log.service.AdminLogMng;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.entity.dto.UserDto;
import com.dgut.modules.sys.login.service.UserMng;

@Controller
@RequestMapping("authority")
public class AuthorityAct {

	private static final Logger log = LoggerFactory.getLogger(AuthorityAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<UserProfile> getPage(HttpServletRequest request,HttpServletResponse response,UserProfileDto dto,PageableWrap pageable){
		Page<UserProfile> page = authorityMng.findAll(UserProfileDto.getWhereClause(dto), pageable.getPageable());
	    return page;
	}
	
	@RequestMapping("o_list.do")
	public @ResponseBody Page<UserDto> getUserPage(HttpServletRequest request,HttpServletResponse response,UserDto dto,PageableWrap pageable) {
		WebPage<UserDto, Long> webPage = userMng.getPage(UserDto.getWhereClause(dto), pageable.getPageable());
		Page<UserDto> page = new PageImpl<UserDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_save")
	public @ResponseBody AjaxMessage save(HttpServletRequest request,HttpServletResponse response,UserProfile userProfile) {
		try {
			authorityMng.save(userProfile);
			adminLogMng.operating(request, "添加角色", "角色名:"+userProfile.getType());
			return new AjaxMessage(true, "添加成功");
		}catch (Exception e) {
			log.info("添加角色时:"+e.getMessage());
			return new AjaxMessage(false, "添加失败");
		}
	}
	
	@RequestMapping("o_update.do")
	public @ResponseBody AjaxMessage update(HttpServletRequest request,HttpServletResponse response,UserProfile userProfile) {
		if(userProfile.getId() == null) {
			return new AjaxMessage(false, "角色id为空");
		}
		try {
			authorityMng.update(userProfile);
			adminLogMng.operating(request, "更新角色", "更新角色id:" + userProfile.getId());
			return new AjaxMessage(true, "更新成功");
		}catch (Exception e) {
			log.info("更新角色时:"+e.getMessage());
			return new AjaxMessage(false, "更新失败");
		}
	}
	
	@RequestMapping("o_delete.do")
	public @ResponseBody AjaxMessage delete(HttpServletRequest request,HttpServletResponse response,Integer[] ids) {
		if(ids.length == 0) {
			return new AjaxMessage(false, "删除角色id为空");
		}
		try {
			authorityMng.deleteByIds(ids);
			adminLogMng.operating(request, "删除角色", "角色ids:"+ids.toString());
			return new AjaxMessage(true, "删除成功");
		}catch (Exception e) {
			log.info("删除角色时:" + e.getMessage());
			return new AjaxMessage(false, "删除失败");
		}
	}
	
	@RequestMapping("o_updateAuthority.do")
	public @ResponseBody AjaxMessage updateAuthority(HttpServletRequest request,HttpServletResponse response,String userId,Integer[] authorityIds) {
		if(StringUtils.isBlank(userId)) {
			return new AjaxMessage(false, "员工id为空");
		}
		if(authorityIds.length == 0) {
			return new AjaxMessage(false, "权限id为空");
		}
		try {
			authorityMng.updateAuthority(userId,authorityIds);
			adminLogMng.operating(request, "更改用户权限", "用户id:"+ userId);
			return new AjaxMessage(true, "更改成功");
		}catch (Exception e) {
			log.info("更改员工权限时:" + e.getMessage());
			return new AjaxMessage(false, "更改失败");
		}
	}
	
	@Autowired
	private AuthorityMng authorityMng;
	
	@Autowired
	private AdminLogMng adminLogMng;
	
	@Autowired
	private UserMng userMng;
}

package com.dgut.modules.owner.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.owner.entity.dto.OwnerDto;
import com.dgut.modules.owner.service.OwnerMng;
import com.dgut.modules.room.entity.dto.RoomDto;

@Controller
@RequestMapping("owner")
public class OwnerAct {

	private static final Logger log = LoggerFactory.getLogger(OwnerAct.class); 
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<OwnerDto> getPage(HttpServletRequest request,HttpServletResponse response,OwnerDto dto,PageableWrap pageable){
		WebPage<OwnerDto,Long> webPage = ownerMng.findAll(OwnerDto.getWhereClause(dto), pageable.getPageable());
		Page<OwnerDto> page = new PageImpl<OwnerDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_list.do")
	public @ResponseBody Page<RoomDto> getRoomByOwnerId(HttpServletRequest request,HttpServletResponse response,String ownerId,PageableWrap pageable){
		WebPage<RoomDto, Integer> webPage = ownerMng.getRoomByOwnerId(Integer.parseInt(ownerId));
		if(webPage == null) {
			return null;
		}
		Page<RoomDto> page = new PageImpl<RoomDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@Autowired
	private OwnerMng ownerMng;
}

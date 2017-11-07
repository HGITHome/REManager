package com.dgut.modules.sys.statistic.action;

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

import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.room.service.RoomMng;
@Controller
@RequestMapping("roomstatistic")
public class RoomStatisticAct {
	private static final Logger log = LoggerFactory.getLogger(RoomStatisticAct.class);
	
	@RequestMapping("o_list.do")
	public @ResponseBody Page<RoomDto> getRoomPage(HttpServletRequest request,HttpServletResponse response,RoomDto dto,PageableWrap pageable){
		String salesType = request.getParameter("salesType");
		if(!StringUtils.isBlank(salesType)) {
			dto.setSalesType(SalesTypeEnum.valueOf(salesType));
		}
		WebPage<RoomDto, Long> webPage = roomMng.findAll(RoomDto.getWhereClause(dto), pageable.getPageable());
	    Page<RoomDto> page = new PageImpl<RoomDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
	    return page;
	}
	
	@Autowired
	private RoomMng roomMng;
}

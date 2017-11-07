package com.dgut.modules.realEstate.action;

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
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.realEstate.entity.dto.RealEstateDto;
import com.dgut.modules.realEstate.service.RealEstateMng;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.sys.log.service.AdminLogMng;

@Controller
@RequestMapping("realEstate")
public class RealEstateAct {

	private static final Logger log = LoggerFactory.getLogger(RealEstateAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<RealEstateDto> getPage(HttpServletRequest request,HttpServletResponse response,RealEstateDto dto,PageableWrap pageable){
		WebPage<RealEstateDto,Long> webPage = realEstateMng.findAll(RealEstateDto.getWhereClause(dto), pageable.getPageable());
		Page<RealEstateDto> page = new PageImpl<RealEstateDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_getRoomsByRealEstateMng")
	public @ResponseBody Page<RoomDto> getRoomByRealEstateId(HttpServletRequest request,HttpServletResponse response,RoomDto roomdto,String realEstateId,PageableWrap pageable){

		WebPage<RoomDto,Long> webPage = realEstateMng.findByRealEstateId(Integer.parseInt(realEstateId),roomdto,pageable);
		Page<RoomDto> page = new PageImpl<RoomDto>(webPage.getContentList(),pageable.getPageable(),webPage.getTotalCount());
	    return page;
	}
	@RequestMapping("o_list.do")
	public @ResponseBody List<RealEstateDto> getAllRealEstateList(HttpServletRequest request,HttpServletResponse response){
		
		return realEstateMng.getAllList();
	}
	
	@RequestMapping("o_save.do")
	public @ResponseBody AjaxMessage save(HttpServletRequest request,HttpServletResponse response,RealEstate realEstate){
		try{
			realEstateMng.save(realEstate);
			adminLogMngImpl.operating(request, "保存楼盘", "楼盘名:"+realEstate.getEstateName());
			return new AjaxMessage(true, "保存成功");
		}catch(Exception e){
			log.info("保存楼盘时:"+ e.getMessage());
			return new AjaxMessage(false, "保存失败");
		}
	}
	
	@RequestMapping("o_update.do")
	public @ResponseBody AjaxMessage update(HttpServletRequest request,HttpServletResponse response,RealEstate realEstate){
		if(realEstate.getId() == null){
			return new AjaxMessage(false,"楼盘id为空");
		}
		try{
			realEstateMng.update(realEstate);
			adminLogMngImpl.operating(request, "更新楼盘信息", "楼盘名:"+realEstate.getEstateName());
			return new AjaxMessage(true, "更新成功");
		}catch(Exception e){
			return new AjaxMessage(false, "更新失败");
		}
	}
	
	@Autowired
	private RealEstateMng realEstateMng;
	
	@Autowired
	private AdminLogMng adminLogMngImpl;
}

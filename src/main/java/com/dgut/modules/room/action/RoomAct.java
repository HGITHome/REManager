package com.dgut.modules.room.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dgut.modules.Constant;
import com.dgut.modules.common.enumeration.SexEnum;
import com.dgut.modules.common.image.ImageScale;
import com.dgut.modules.common.upload.FileRepository;
import com.dgut.modules.common.upload.UploadUtils;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.order.entity.RoomOrder;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.order.service.RoomOrderMng;
import com.dgut.modules.owner.entity.Owner;
import com.dgut.modules.owner.service.OwnerMng;
import com.dgut.modules.realEstate.service.RealEstateMng;
import com.dgut.modules.room.entity.ImagFile;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.room.entity.dto.RoomDto;
import com.dgut.modules.room.service.RoomMng;
import com.dgut.modules.sys.log.service.AdminLogMng;

@Controller
@RequestMapping("room")
public class RoomAct {

	private static final Logger log = LoggerFactory.getLogger(RoomAct.class);
	
	@RequestMapping("v_list.do")
	public @ResponseBody Page<RoomDto> getPage(HttpServletRequest request,HttpServletResponse response,RoomDto dto, PageableWrap pageable){
		dto.setSalesType(SalesTypeEnum.valueOf(request.getParameter("salesType")));
		WebPage<RoomDto, Long> webPage = roomMng.findAll(RoomDto.getWhereClause(dto),pageable.getPageable());
		Page<RoomDto> page = new PageImpl<RoomDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
		return page;
	}
	
	@RequestMapping("o_save.do")
	public @ResponseBody AjaxMessage save(HttpServletRequest request,HttpServletResponse response,Room room,Owner owner){
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		MultipartFile photo = multiRequest.getFile("photo");
		String roomOrderId = request.getParameter("roomOrderId");
		String realEstateId = request.getParameter("realEstateId");
		String isPark = request.getParameter("isPark");
		String isYard = request.getParameter("isYard");
		String saleType = request.getParameter("saleType");
		if(StringUtils.isBlank(roomOrderId)){
			return new AjaxMessage(false, "委托单id为空");
		}
		if(StringUtils.isBlank(realEstateId)){
			return new AjaxMessage(false,"楼盘id为空");
		}
		if(StringUtils.isBlank(isPark) || StringUtils.isBlank(isYard)){
			return new AjaxMessage(false, "参数不完整");
		}
		String sex = request.getParameter("sex");
		if(StringUtils.isBlank(sex)) {
			return new AjaxMessage(false, "业主性别为空");
		}
		if(!StringUtils.isBlank(saleType)) {
			room.setSalesType(SalesTypeEnum.valueOf(saleType));
		}
		owner.setSex(SexEnum.valueOf(sex));
		String photoUrl = null,miniUrl=null;
		ImagFile imagFile = null;
		Set<ImagFile> imagSet = new HashSet<ImagFile>();
		Set<Room> roomSet = new HashSet<Room>();
		if(photo != null){
			
			imagFile = new ImagFile();
			String origName = photo.getOriginalFilename();
			String extName = FilenameUtils.getExtension(origName).toLowerCase(Locale.ENGLISH);
			if(!"gif,jpg,jpeg,png,bmp".contains(extName)){
				return new AjaxMessage(false,"请上传后缀名为:gif,jpg,jpeg,png,bmp的照片");
				
			}
			try {
				photoUrl=fileRepository.storeByExt(Constant.IMAG_URL, extName, photo);
				ServletContext  context=request.getSession().getServletContext();
				File fi = new File(context.getRealPath(photoUrl)); //大图文件  

				String miniPath=context.getRealPath(Constant.IMAG_URL);
				String miniName=UploadUtils.generateFilename("mini", extName);
				File fo = new File(miniPath,miniName); //将要转换出的小图文件
				miniUrl=Constant.IMAG_URL+"/"+miniName;
				imageScaleImpl.resizeFix(fi, fo,180,180);
				imagFile.setRoom(room);
				imagFile.setImagUrl(photoUrl);
				imagFile.setMiniUrl(miniUrl);
				imagSet.add(imagFile);
			} catch (IOException e) {
				log.info("上传照片时:"+e.getMessage());
				return new AjaxMessage(false,"上传相片失败");
			} catch (Exception e) {
				log.info("上传照片时:"+e.getMessage());
				return new AjaxMessage(false,"上传相片失败");
		    }
		 }
		room.setImagSet(imagSet);
		room.setOwner(owner);
		RoomOrder order = roomOrderMng.findById(Integer.parseInt(roomOrderId));
		order.setRoom(room);
		room.setRoomOrder(order);
		room.setSalesType(order.getSalesType());
		room.setRealEstate(realEstateMng.findById(Integer.parseInt(realEstateId)));
		room.setIsParking(Integer.parseInt(isPark));
		room.setIsYard(Integer.parseInt(isYard));
		roomSet.add(room);
		owner.setRoomSet(roomSet);
		try{
			ownerMng.save(owner);
			roomOrderMng.update(order);
			roomMng.save(room);
			adminLogMngImpl.operating(request, "添加房子", "业主名:"+order.getOwnerName());
			return new AjaxMessage(true,"保存成功");
		}catch(Exception e){
			log.info("保存房子信息时:"+e.getMessage());
			return new AjaxMessage(false,"保存失败");
		}
		
	}
	
	@RequestMapping("o_update.do")
	public @ResponseBody AjaxMessage update(HttpServletRequest request,HttpServletResponse response,Room room){
		try{
			roomMng.update(room);
			adminLogMngImpl.operating(request, "更新房子信息", "房子id:"+room.getId());
			return new AjaxMessage(true,"更新成功");
		}catch(Exception e){
			log.info("更新房子信息时:"+e.getMessage());
			return new AjaxMessage(false,"更新失败");
		}
	}
	

	@Autowired
	private RoomMng roomMng;
	
	@Autowired
	private RoomOrderMng roomOrderMng;
	
	@Autowired
	private RealEstateMng realEstateMng;
	
	@Autowired
	private AdminLogMng adminLogMngImpl;
	
	@Autowired
	private FileRepository fileRepository;
		
	@Autowired
	private ImageScale imageScaleImpl;
	
	@Autowired
	private OwnerMng ownerMng;
}

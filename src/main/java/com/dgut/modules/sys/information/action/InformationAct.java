package com.dgut.modules.sys.information.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dgut.modules.Constant;
import com.dgut.modules.common.image.ImageScale;
import com.dgut.modules.common.upload.FileRepository;
import com.dgut.modules.common.upload.UploadUtils;
import com.dgut.modules.common.utils.DateUtils;
import com.dgut.modules.common.web.AjaxMessage;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.sys.log.service.AdminLogMng;
import com.dgut.modules.sys.login.entity.User;
import com.dgut.modules.sys.login.service.UserMng;

@Controller
@RequestMapping("information")
public class InformationAct {

	private static final Logger log = LoggerFactory.getLogger(InformationAct.class);
	
	@RequestMapping("o_update.do")
	public @ResponseBody AjaxMessage updatePassword(HttpServletRequest request,HttpServletResponse response,@RequestBody User user) {
		 
		if(user.getId() == null) {
			 return new AjaxMessage(false, "用户id为空");
		 }
		User bean = userMng.findById(user.getId());
		bean.setPassword(user.getPassword());
		try {
			
			userMng.save(bean);
			return new AjaxMessage(true, "更新成功");
		}catch (Exception e) {
			log.info("修改密码时:"+e.getMessage());
			return new AjaxMessage(false, "修改失败");
		}
	}
	
	@RequestMapping("o_updateInformation.do")
	public @ResponseBody AjaxMessage updateInformation(HttpServletRequest request,HttpServletResponse response,Employee employee) {
		if(employee.getId() == null) {
			return new AjaxMessage(false, "员工id为空");
		}
		Employee bean = employeeMng.findById(employee.getId());
		bean.setAddress(employee.getAddress());
		bean.setQQ(employee.getQQ());
		bean.setBirthday(employee.getBirthday());
		bean.setEmail(employee.getEmail());
		bean.setEducation(employee.getEducation());
		bean.setPhone(employee.getPhone());
		bean.setEntry_time(employee.getEntry_time());
		bean.setName(employee.getName());
		try {
			employeeMng.update(bean);
			return new AjaxMessage(true, "更新成功");
		}catch (Exception e) {
			log.info("更新员工个人信息时:"+e.getMessage());
			return new AjaxMessage(false, "更新失败");
		}
	}
	
	@RequestMapping("o_upload.do")
	public @ResponseBody AjaxMessage uploadIcon(HttpServletRequest request,HttpServletResponse response) {
		MultipartHttpServletRequest mutilRequest = (MultipartHttpServletRequest)request;
		String userId = request.getParameter("userId");
		if(StringUtils.isBlank(userId)) {
			return new AjaxMessage(false,"用户id为空");
			
		}
		User user = userMng.findById(Integer.parseInt(userId));
		String photoUrl = null,miniUrl=null;
		MultipartFile photo = mutilRequest.getFile("photo");
       if(photo != null){
			String origName = photo.getOriginalFilename();
			String extName = FilenameUtils.getExtension(origName).toLowerCase(Locale.ENGLISH);
			if(!"gif,jpg,jpeg,png,bmp".contains(extName)){
				return new AjaxMessage(false,"请上传后缀名为:gif,jpg,jpeg,png,bmp的照片");
			}
			try {
				photoUrl=fileRepository.storeByExt(Constant.ICON_URL, extName, photo);
				ServletContext  context=request.getSession().getServletContext();
				File fi = new File(context.getRealPath(photoUrl)); //大图文件  

				String miniPath=context.getRealPath(Constant.ICON_URL);
				String miniName=UploadUtils.generateFilename("mini", extName);
				File fo = new File(miniPath,miniName); //将要转换出的小图文件
				miniUrl=Constant.ICON_URL+"/"+miniName;
				imageScaleImpl.resizeFix(fi, fo,180,180);
				
			} catch (IOException e) {
				log.info("上传照片时:"+e.getMessage());
				return new AjaxMessage(false,"上传相片失败");
			} catch (Exception e) {
				log.info("上传照片时:"+e.getMessage());
				return new AjaxMessage(false,"上传相片失败");
		    }
		 }
	       user.setIconUrl(photoUrl);
	       user.setMiniUrl(miniUrl);
		   userMng.update(user);
		   adminLogMng.operating(request, "上传头像", "用户id="+user.getId());
		return new AjaxMessage(true, "上传成功");
	}
	
	@Autowired
	private UserMng userMng;
	
	@Autowired
	private FileRepository fileRepository;
		
	@Autowired
	private ImageScale imageScaleImpl;
	
	@Autowired
	private AdminLogMng adminLogMng;
	
	@Autowired
	private EmployeeMng employeeMng;
}

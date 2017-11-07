package com.dgut.modules.room.entity.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.dgut.modules.room.entity.ImagFile;

public class ImagFileDto implements Serializable {

	/**
	 * 照片Dto
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String imagUrl;
	private String miniUrl;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImagUrl() {
		return imagUrl;
	}
	public void setImagUrl(String imagUrl) {
		this.imagUrl = imagUrl;
	}
	public String getMiniUrl() {
		return miniUrl;
	}
	public void setMiniUrl(String miniUrl) {
		this.miniUrl = miniUrl;
	}
	
	public static void entityToDto(ImagFile file,ImagFileDto dto) {
		BeanUtils.copyProperties(file, dto);
	}
}

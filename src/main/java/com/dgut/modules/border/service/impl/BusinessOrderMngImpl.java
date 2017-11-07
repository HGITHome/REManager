package com.dgut.modules.border.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.border.dao.BusinessOrderDao;
import com.dgut.modules.border.entity.BusinessOrder;
import com.dgut.modules.border.entity.dto.BusinessOrderDto;
import com.dgut.modules.border.entity.support.PriorityEnum;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.border.service.BusinessOrderMng;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.room.service.RoomMng;

@Service
@Transactional
public class BusinessOrderMngImpl implements BusinessOrderMng{

	@Transactional(readOnly = true)
	public WebPage<BusinessOrderDto, Long> findAll(Specification<BusinessOrder> spec, Pageable pageable) {
	    Page<BusinessOrder> page = dao.findAll(spec, pageable);
	    List<BusinessOrder> lists = page.getContent();
	    List<BusinessOrderDto> dtoList = new ArrayList<BusinessOrderDto>();
	    BusinessOrderDto dto = null;
	    for(BusinessOrder border : lists) {
	    	dto = new BusinessOrderDto();
	    	BusinessOrderDto.entityToDto(border, dto);
	    	dtoList.add(dto);
	    }
	    WebPage<BusinessOrderDto, Long> webPage = new WebPage<BusinessOrderDto, Long>(dtoList, page.getTotalElements());
		return webPage;
	}

	@Transactional(readOnly=true)
	public BusinessOrder findById(Integer id) {
		
		return dao.findOne(id);
	}

	public List<BusinessOrder> save(Integer[] roomIds, String[] prioritys,String employeeId){
//		for(int  i = 0 ; i < roomIds.length; i ++) {
//			BusinessOrder bean = new BusinessOrder();
//			bean.setRoom(roomMngImpl.findById(roomIds[i]));
//			bean.setEmployee(employeeMngImpl.findById(Integer.parseInt(employeeId)));
//			bean.setPriority(PriorityEnum.valueOf(prioritys[i]));
//			bean.set
//		}]
		return null;
	}
	
	public BusinessOrder save(BusinessOrder bean) {
		
		return dao.save(bean);
	}

	public BusinessOrder update(BusinessOrder bean) {
		
		return dao.save(bean);
	}

	public BusinessOrder deleteById(Integer id) {
		BusinessOrder bean = dao.findOne(id);
		if(bean != null) {
			dao.delete(bean);
		}
		return bean;
	}

	public List<BusinessOrder> deleteByIds(Integer[] ids) {
		List<BusinessOrder> lists = new ArrayList<BusinessOrder>(ids.length);
		for(int i = 0 ; i < ids.length ; i ++) {
			BusinessOrder bean = dao.findOne(ids[i]);
			if(bean != null) {
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}

	@Transactional(readOnly=true)
	public List<Object[]> getTransactionStatisticsCount(TransactionStateEnum transactionState){
		return dao.getTransactionStatisticsCount(transactionState);
	}
	
	@Autowired
	private BusinessOrderDao dao;
	
	@Autowired
	private RoomMng roomMngImpl;
	
	@Autowired
	private EmployeeMng employeeMngImpl;
}

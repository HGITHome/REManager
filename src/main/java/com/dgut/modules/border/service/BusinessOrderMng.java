package com.dgut.modules.border.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.border.entity.BusinessOrder;
import com.dgut.modules.border.entity.dto.BusinessOrderDto;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.common.web.WebPage;

public interface BusinessOrderMng {

    public WebPage<BusinessOrderDto, Long> findAll(Specification<BusinessOrder> spec,Pageable pageable);
	
	public BusinessOrder findById(Integer id);
	
	public BusinessOrder save(BusinessOrder bean);
	
	public BusinessOrder update(BusinessOrder bean);
	
	public BusinessOrder deleteById(Integer id);
	
	public List<BusinessOrder> deleteByIds(Integer[] ids);

	public List<BusinessOrder> save(Integer[] roomIds, String[] prioritys, String employeeId);
	
	public List<Object[]> getTransactionStatisticsCount(TransactionStateEnum transactionState);
}

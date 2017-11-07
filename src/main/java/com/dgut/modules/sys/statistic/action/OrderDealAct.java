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

import com.dgut.modules.border.entity.dto.BusinessOrderDto;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.border.service.BusinessOrderMng;
import com.dgut.modules.common.web.PageableWrap;
import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.order.entity.support.SalesTypeEnum;

@Controller
@RequestMapping("orderdeal")
public class OrderDealAct {
    private static final Logger log = LoggerFactory.getLogger(OrderDealAct.class);
    
    @RequestMapping("o_list.do")
    public @ResponseBody Page<BusinessOrderDto> getOrderDealPage(HttpServletRequest request,HttpServletResponse response,PageableWrap pageable){
    	BusinessOrderDto dto = new BusinessOrderDto();
    	String salesType = request.getParameter("salesType");
    	if(!StringUtils.isBlank(salesType)) {
			dto.setSalesType(SalesTypeEnum.valueOf(request.getParameter("salesType")));
		}
    	dto.setTransactionState(TransactionStateEnum.SOLD);
    	WebPage<BusinessOrderDto, Long> webPage = bOrderMng.findAll(BusinessOrderDto.getWhereClause(dto), pageable.getPageable());
        Page<BusinessOrderDto> page = new PageImpl<BusinessOrderDto>(webPage.getContentList(), pageable.getPageable(), webPage.getTotalCount());
        return page;
    }
    
    @Autowired
    private BusinessOrderMng bOrderMng;
}

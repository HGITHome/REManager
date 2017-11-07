package com.dgut.modules.border.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.border.entity.BusinessOrder;
import com.dgut.modules.border.entity.support.PriorityEnum;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BusinessOrderDto implements Serializable{

	/**
	 * 业务单Dto
	 */
	private Integer id;
	private String roomNo;
	private SalesTypeEnum salesType;
	private String employeeName;
	private TransactionStateEnum transactionState;
	private PriorityEnum priority;
	private Double transactionPrice;//Double 若委托类型为RENT单位应为元/月 委托类型为SALE单位应为万元
	private String  customerName;
	private Double intermediateFee;//中介费用
	private Date createTime;
	private Date finishTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SalesTypeEnum getSalesType() {
		return salesType;
	}
	public void setSalesType(SalesTypeEnum salesType) {
		this.salesType = salesType;
	}

	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public TransactionStateEnum getTransactionState() {
		return transactionState;
	}
	public void setTransactionState(TransactionStateEnum transactionState) {
		this.transactionState = transactionState;
	}
	public PriorityEnum getPriority() {
		return priority;
	}
	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}
	 public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public Double getTransactionPrice() {
		return transactionPrice;
	}
	public void setTransactionPrice(Double transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getIntermediateFee() {
		return intermediateFee;
	}
	public void setIntermediateFee(Double intermediateFee) {
		this.intermediateFee = intermediateFee;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	public static void entityToDto(BusinessOrder border,BusinessOrderDto dto) {
		BeanUtils.copyProperties(border, dto);
		if(border.getRoom() != null) {
		    dto.setRoomNo(border.getRoom().getRoomNo());
		}else {
			dto.setRoomNo("");
		}
		if(border.getEmployee() != null) {
			dto.setEmployeeName(border.getEmployee().getName());
		}else {
			dto.setEmployeeName("");
		}
		if(border.getCustomer() != null) {
			dto.setCustomerName(border.getCustomer().getName());
		}else {
			dto.setEmployeeName("");
		}
		
	}
	
	public static Specification<BusinessOrder> getWhereClause(final BusinessOrderDto businessOrderDto)
	    {
			return new Specification<BusinessOrder>() {

				public Predicate toPredicate(Root<BusinessOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					 List<Predicate> predicate = new ArrayList<Predicate>();
					 if(businessOrderDto.getSalesType() != null && !businessOrderDto.getSalesType().toString().trim().equals("")) {
						 predicate.add(cb.equal(root.get("salesType").as(TransactionStateEnum.class), businessOrderDto.getSalesType()));
					 }
					 if(businessOrderDto.getEmployeeName()!=null && !businessOrderDto.getEmployeeName().trim().equals("")) {
						 predicate.add(cb.like(root.join("employee").get("name").as(String.class),"%"+ businessOrderDto.getEmployeeName()+"%"));
					 }
					 if(businessOrderDto.getTransactionState()!=null && !businessOrderDto.getTransactionState().toString().trim().equals("")) {
						 predicate.add(cb.equal(root.get("transactionState").as(TransactionStateEnum.class), businessOrderDto.getTransactionState()));
					 }
					 if(businessOrderDto.getPriority()!=null && !businessOrderDto.getPriority().toString().trim().equals("")) {
						 predicate.add(cb.equal(root.get("priority").as(PriorityEnum.class), businessOrderDto.getPriority()));
					 }
					 if(businessOrderDto.getCustomerName() != null && !businessOrderDto.getCustomerName().trim().equals("")) {
						 predicate.add(cb.like(root.join("customer").get("name").as(String.class),"%"+ businessOrderDto.getCustomerName()+"%"));
					 }
					return cb.and(predicate.toArray(new Predicate[predicate.size()]));
				}
		
			};
	      
	    }
}

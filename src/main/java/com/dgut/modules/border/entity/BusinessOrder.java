package com.dgut.modules.border.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgut.modules.border.entity.support.PriorityEnum;
import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.customer.entity.Customer;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.room.entity.Room;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="business_order")
public class BusinessOrder implements Serializable {

	/**
	 * 业务单
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Room room;
    private SalesTypeEnum salesType;
	private Employee employee;
	private TransactionStateEnum transactionState;
	private PriorityEnum priority;
	private Double transactionPrice;//Double 若委托类型为RENT单位应为元/月 委托类型为SALE单位应为万元
	private Customer customer;
	private Double intermediateFee;//中介费用
	private Date createTime;
	private Date finishTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@OneToOne
	@JoinColumn(name = "room_id")
	public Room getRoom() {
		return room;
	}
	@Enumerated(value = EnumType.STRING)
    @Column(name = "sales_type")
	public SalesTypeEnum getSalesType() {
		return salesType;
	}
	@ManyToOne
	@JoinColumn(name="employee_id")
	public Employee getEmployee() {
		return employee;
	}
	@Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_state")
	public TransactionStateEnum getTransactionState() {
		return transactionState;
	}
	@Enumerated(value = EnumType.STRING)
    @Column(name = "priority")
	public PriorityEnum getPriority() {
		return priority;
	}
	@Column(name = "transaction_price")
	public Double getTransactionPrice() {
		return transactionPrice;
	}
	@OneToOne
	@JoinColumn(name="customer_id")
	public Customer getCustomer() {
		return customer;
	}
	@Column(name="intermediateFee")
	public Double getIntermediateFee() {
		return intermediateFee;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	@Column(name="finish_time")
	public Date getFinishTime() {
		return finishTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public void setSalesType(SalesTypeEnum salesType) {
		this.salesType = salesType;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public void setTransactionState(TransactionStateEnum transactionState) {
		this.transactionState = transactionState;
	}
	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}
	public void setTransactionPrice(Double transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setIntermediateFee(Double intermediateFee) {
		this.intermediateFee = intermediateFee;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	
}

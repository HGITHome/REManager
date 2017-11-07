package com.dgut.modules.order.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.dgut.modules.customer.entity.Customer;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.order.entity.support.AuditedStateEnum;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.owner.entity.Owner;
import com.dgut.modules.room.entity.Room;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="room_order")
public class RoomOrder implements Serializable {

	/**
	 * 委托单
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

    private Employee creator; //创建者
    private Date createTime;

    private Employee auditor; //审核者
    private Date auditTime;
    private AuditedStateEnum auditedState;//״̬

    private Room room;//房子
    private Double price;
    private String ownerName;
    private SalesTypeEnum salesType;

    private Integer isDistribute;
    
    
    
    
    /**
     * @return ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    /**
     * @return � ֵ��: 1970/01/01:yyyy/MM/dd hh:mm:ss
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @return �е������ʱ��: 1970/01/01 00:00:00 - ��ǰʱ�� ��ʽ:yyyy/MM/dd hh:mm:ss
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "audit_time")
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * @return 房子
     */
    @OneToOne
    @Cascade(value=CascadeType.ALL)
    @JoinColumn(name = "roomOrder_id")
    public Room getRoom() {
        return room;
    }

    
   /*
    * 返回员工
    */

    @OneToOne
    @JoinColumn(name = "creator")
    public Employee getCreator() {
        return creator;
    }

    @OneToOne
    @JoinColumn(name = "auditor_id")
    public Employee getAuditor() {
        return auditor;
    }

    /**
     * @return �: ö���� AuditedStateEnum(PASS, NOT_PASS, WAIT_AUDIT)
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "audited_state")
    public AuditedStateEnum getAuditedState() {
        return auditedState;
    }

    /**
     * @return 房子租售状态: ö���� SalesTypeEnum(RENT, SALE)
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sales_type")
    public SalesTypeEnum getSalesType(){
        return salesType;
    }

    /**
     * @return 返回交易金额
     */
    @Column(name = "price")
    public Double getPrice(){
        return price;
    }

   

    @Column(name="ownerName",nullable=false)
	public String getOwnerName() {
		return ownerName;
	}
    

    @Column(name="isdistribute")
    public Integer getIsDistribute() {
		return isDistribute;
	}

	/**
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param createTime : : yyyy/MM/dd hh:mm:ss
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @param auditTime : yyyy/MM/dd hh:mm:ss
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * @param room 
     */
    public void setRoom(Room room) {
        this.room = room;
    }


    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public void setAuditor(Employee auditor){
        this.auditor = auditor;
    }

    /**
     * @param auditedState AuditedStateEnum(PASS, NOT_PASS, WAIT_AUDIT)
     */
    public void setAuditedState(AuditedStateEnum auditedState) {
        this.auditedState = auditedState;
    }

    /**
     * @param salesType  ö���� SalesTypeEnum(RENT, SALE)
     */
    public void setSalesType(SalesTypeEnum salesType){
        this.salesType = salesType;
    }

    /**
     * @param price 
     */
    public void setPrice(Double price){
        this.price = price;
    }

	public void setIsDistribute(Integer isDistribute) {
		this.isDistribute = isDistribute;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	

	
	
}

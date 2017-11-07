package com.dgut.modules.room.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.dgut.modules.order.entity.RoomOrder;
import com.dgut.modules.order.entity.support.SalesTypeEnum;
import com.dgut.modules.owner.entity.Owner;
import com.dgut.modules.realEstate.entity.RealEstate;

@Entity
@Table(name="room")
public class Room implements Serializable {

	/**
	 * 房子的信息
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String roomNo;
	private Integer floor;
	private Integer roofBeam;
	private Double area;
	private Integer isParking;
	private RealEstate realEstate;

	private Integer roomNumber;
	private Integer living;
	private Integer toilet;
	private Integer layer;
	private Integer isYard;
	private SalesTypeEnum salesType;
	private Owner owner;
	private RoomOrder roomOrder;
	
	private Set<ImagFile> imagSet;
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    public Integer getId() {
	        return id;
	    }

	    /**
	     * @return返回房子的房号
	     */
	    @Column(name = "room_no",nullable=false)
	    public String getRoomNo() {
	        return roomNo;
	    }

	    /**
	     * @return 返回房子的楼层
	     */
	    @Column(name = "floor",nullable=false)
	    public Integer getFloor() {
	        return floor;
	    }

	    /**
	     * @return返回房子的层数革面
	     */
	    @Column(name = "roof_beam",nullable=false)
	    public Integer getRoofBeam() {
	        return roofBeam;
	    }

	    /**
	     * @return返回房子的面积
	     */
	    @Column(name = "area",nullable=false)
	    public Double getArea() {
	        return area;
	    }

	    /**
	     * @return 返回是否有停车场
	     */
	  
	    
	    /*
	     * @return 房子照片路径类
	     */
	    
	    @OneToMany(fetch=FetchType.EAGER)
	    @Cascade(value=CascadeType.ALL)
	    public Set<ImagFile> getImagSet() {
			return imagSet;
		}

		/**
	     * @return 返回所属的楼盘
	     */
	    @ManyToOne
	    @Cascade(value = {CascadeType.SAVE_UPDATE })
	    @JoinColumn(name = "realEstate_id")
	    public RealEstate getRealEstate(){
	        return realEstate;
	    }

	    /**
	     * @return 返回房子的个数
	     */
	    @Column(name = "room",nullable=false)
	    public Integer getRoomNumber() {
	        return roomNumber;
	    }

	    /**
	     * @return ���ظ÷�Դ�����е� ��
	     */
	    @Column(name = "living")
	    public Integer getLiving() {
	        return living;
	    }

	    /**返回厕所的个数
	     */
	    @Column(name = "toilet",nullable=false)
	    public Integer getToilet() {
	        return toilet;
	    }

	    /**
	     * @return 返回阳台的个数
	     */
	    @Column(name = "layer",nullable=false)
	    public Integer getLayer() {
	        return layer;
	    }

	 
	    @Column(name="isParking",nullable=false)
	    public Integer getIsParking() {
			return isParking;
		}

	    @Column(name="isYard",nullable=false)
		public Integer getIsYard() {
			return isYard;
		}

		@Enumerated(EnumType.STRING)
	    @Column(name="sales_type")
	    public SalesTypeEnum getSalesType() {
			return salesType;
		}

		@ManyToOne
	    @Cascade(value=CascadeType.SAVE_UPDATE)
	    @JoinColumn(name="owner_id")
	    public Owner getOwner() {
			return owner;
		}

		@OneToOne
	    @Cascade(value=CascadeType.SAVE_UPDATE)
	    @JoinColumn(name="roomOrder_id")
		public RoomOrder getRoomOrder() {
			return roomOrder;
		}

		public void setId(Integer id) {
	        this.id = id;
	    }

	    public void setRoomNo(String roomNo) {
	        this.roomNo = roomNo;
	    }

	    public void setFloor(Integer floor) {
	        this.floor = floor;
	    }

	    public void setRoofBeam(Integer roofBeam) {
	        this.roofBeam = roofBeam;
	    }

	    public void setArea(Double area) {
	        this.area = area;
	    }

	   

	    public void setRealEstate(RealEstate realEstate){
	        this.realEstate = realEstate;
	    }

	 

	    public void setImagSet(Set<ImagFile> imagSet) {
			this.imagSet = imagSet;
		}

		public void setRoomNumber(Integer roomNumber) {
	        this.roomNumber = roomNumber;
	    }

	    public void setLiving(Integer living) {
	        this.living = living;
	    }

	    public void setToilet(Integer toilet) {
	        this.toilet = toilet;
	    }

	    public void setLayer(Integer layer) {
	        this.layer = layer;
	    }

	   
		public void setOwner(Owner owner) {
			this.owner = owner;
		}

		public void setRoomOrder(RoomOrder roomOrder) {
			this.roomOrder = roomOrder;
		}

		public void setSalesType(SalesTypeEnum salesType) {
			this.salesType = salesType;
		}

		public void setIsParking(Integer isParking) {
			this.isParking = isParking;
		}

		public void setIsYard(Integer isYard) {
			this.isYard = isYard;
		}
	

}

package com.dgut.modules.realEstate.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.dgut.modules.room.entity.Room;

@Entity
@Table(name="realEstate")
public class RealEstate implements Serializable {

	/**
	 * ¥�̹���
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String estateName;
    private String location;
    private String propertyCompany;
    private Set<Room> roomSet;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId(){
        return id;
    }

    /**
     * @return 楼盘名
     */
    @Column(name = "estate_name")
    public String getEstateName() {
        return estateName;
    }

    /**
     * @return地址ַ
     */
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    /**
     * @return 物业公司
     */
    @Column(name = "property_company")
    public String getPropertyCompany() {
        return propertyCompany;
    }

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name="realEstate_id")
	public Set<Room> getRoomSet() {
		return roomSet;
	}
    
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * @param estateName
     */
    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    /**
     * @param location ַ
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @param propertyCompany
     */
    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }


	public void setRoomSet(Set<Room> roomSet) {
		this.roomSet = roomSet;
	}
}

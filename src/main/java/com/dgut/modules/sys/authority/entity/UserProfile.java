package com.dgut.modules.sys.authority.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity  
@Table(name="user_profile") 
public class UserProfile implements Serializable {

	/**
	 * 用户角色类
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Integer id;   
   

    @Column(name="type", length=15, unique=true, nullable=false)  
    private String type ;  
    
    @Column(name="description",nullable=false)
    private String description;
    
    @Column(name="isabled",nullable=false)
    private Integer isabled;
    
    
  
     
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsabled() {
		return isabled;
	}

	public void setIsabled(Integer isabled) {
		this.isabled = isabled;
	}

	@Override  
    public int hashCode() {  
        final int prime = 31;  
        int result = 1;  
        result = prime * result + id;  
        result = prime * result + ((type == null) ? 0 : type.hashCode());  
        return result;  
    }  
   
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (!(obj instanceof UserProfile))  
            return false;  
        UserProfile other = (UserProfile) obj;  
        if (id != other.id)  
            return false;  
        if (type == null) {  
            if (other.type != null)  
                return false;  
        } else if (!type.equals(other.type))  
            return false;  
        return true;  
    }  
   
    @Override  
    public String toString() {  
        return "UserProfile [id=" + id + ",  type=" + type  + "]";  
    } 
}

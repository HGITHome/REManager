package com.dgut.modules.sys.login.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.sys.authority.entity.UserProfile;
import com.dgut.modules.sys.login.entity.support.State;



@Entity  
@Table(name="app_user")
public class User implements Serializable {

	/**
	 * 用户类
	 */
	private static final long serialVersionUID = 1L;
	
	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)  
	    private Integer id;  
	   
	    @Column(name="username", unique=true, nullable=false)  
	    private String username;  
	       
	    @Column(name="password", nullable=false)  
	    private String password;  
	    
	    @Column(name="email", nullable=false)  
	    private String email;  
	   
	    @Column(name="state", nullable=false)  
	    private String state=State.INACTIVE.getState();  
	    
	    @Column(name="validateCode",nullable=false)
	    private String validateCode;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="register_time",nullable=false)
	    private Date registerTime;
	    
	    @ManyToMany(fetch = FetchType.EAGER)  
	    @JoinTable(name = "APP_USER_USER_PROFILE",   
	             joinColumns = {@JoinColumn(name = "USER_ID") },   
	             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })  
	    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();  
	   
	    @OneToOne
	    @JoinColumn(name="employee_id")
	    private Employee employee;
	    
	    @Column(name="iconUrl")
	    private String iconUrl;
	    
	    @Column(name="miniUrl")
	    private String miniUrl;
	    
	    public Integer getId() {  
	        return id;  
	    }  
	   
	    public void setId(Integer id) {  
	        this.id = id;  
	    }  
	   
	    
	   
	    public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {  
	        return password;  
	    }  
	   
	    public void setPassword(String password) {  
	        this.password = password;  
	    }  
	   
	  
	   
	    public String getEmail() {  
	        return email;  
	    }  
	   
	    public void setEmail(String email) {  
	        this.email = email;  
	    }  
	   
	    public String getState() {  
	        return state;  
	    }  
	   
	    public void setState(String state) {  
	        this.state = state;  
	    }  
	   
	    
	    
	    public String getValidateCode() {
			return validateCode;
		}

		public void setValidateCode(String validateCode) {
			this.validateCode = validateCode;
		}

		public Date getRegisterTime() {
			return registerTime;
		}

		public void setRegisterTime(Date registerTime) {
			this.registerTime = registerTime;
		}

		public Set<UserProfile> getUserProfiles() {  
	        return userProfiles;  
	    }  
	   
	    public void setUserProfiles(Set<UserProfile> userProfiles) {  
	        this.userProfiles = userProfiles;  
	    }  
	    
	    
	    
	    public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		@Transient  
	    public Date getLastActivateTime() {  
	        Calendar cl = Calendar.getInstance();  
	        cl.setTime(registerTime);  
	        cl.add(Calendar.DATE , 1);  
	          
	        return cl.getTime();  
	    }  
	    
	   
	    public String getIconUrl() {
			return iconUrl;
		}

		public void setIconUrl(String iconUrl) {
			this.iconUrl = iconUrl;
		}

		public String getMiniUrl() {
			return miniUrl;
		}

		public void setMiniUrl(String miniUrl) {
			this.miniUrl = miniUrl;
		}

		@Override  
	    public int hashCode() {  
	        final int prime = 31;  
	        int result = 1;  
	        result = prime * result + id;  
	        result = prime * result + ((username == null) ? 0 : username.hashCode());  
	        return result;  
	    }  
	   
	    @Override  
	    public boolean equals(Object obj) {  
	        if (this == obj)  
	            return true;  
	        if (obj == null)  
	            return false;  
	        if (!(obj instanceof User))  
	            return false;  
	        User other = (User) obj;  
	        if (id != other.id)  
	            return false;  
	        if (username == null) {  
	            if (other.username != null)  
	                return false;  
	        } else if (!username.equals(other.username))  
	            return false;  
	        return true;  
	    }  
	   
	    @Override  
	    public String toString() {  
	        return "User [id=" + id + ", username=" + username + ", password=" + password  
	                +  ", email=" + email + ", state=" + state + ", userProfiles=" + userProfiles +"]";  
	    }  
}
	   
	       
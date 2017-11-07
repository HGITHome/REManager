package com.dgut.modules.employee.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.room.entity.support.EmployeeTypeEnum;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>,JpaSpecificationExecutor<Employee>{
	
	@Query("select bean from Employee bean where bean.name like:name")
	public List<Employee> findByNameLike(@Param("name") String name);
	
	@Query("select bean from Employee bean where bean.employeeType =:employeeType")
    public List<Employee> getManagerDtoList(@Param("employeeType")EmployeeTypeEnum employeeType);
}

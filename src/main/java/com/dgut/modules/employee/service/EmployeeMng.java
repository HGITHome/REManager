package com.dgut.modules.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.employee.entity.dto.EmployeeDto;
import com.dgut.modules.employee.entity.dto.ManagerDto;

public interface EmployeeMng {

	    public  WebPage<EmployeeDto, Long> findAll(Specification<Employee> spec,Pageable pageable);
		
	    public List<ManagerDto> getManagerDtoList();
	    
		public Employee findById(Integer id);
		
		public Employee findByNameLike(String name);
		
		public Employee save(Employee bean);
		
		public Employee update(Employee bean);
		
		public Employee deleteById(Integer id);
		
		public List<Employee> deleteByIds(Integer[] ids);
}

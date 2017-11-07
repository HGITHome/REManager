package com.dgut.modules.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.common.web.WebPage;
import com.dgut.modules.employee.dao.EmployeeDao;
import com.dgut.modules.employee.entity.Employee;
import com.dgut.modules.employee.entity.dto.EmployeeDto;
import com.dgut.modules.employee.entity.dto.ManagerDto;
import com.dgut.modules.employee.service.EmployeeMng;
import com.dgut.modules.room.entity.support.EmployeeTypeEnum;

@Service
@Transactional
public class EmployeeMngImpl implements EmployeeMng {

	@Transactional(readOnly=true)
	public WebPage<EmployeeDto, Long> findAll(Specification<Employee> spec,
			Pageable pageable) {
		Page<Employee> page = dao.findAll(spec, pageable);
		List<Employee> list = page.getContent();
		List<EmployeeDto> dtoList = new ArrayList<EmployeeDto>();
		EmployeeDto dto = null;
		for(Employee e : list) {
			dto = new EmployeeDto();
			EmployeeDto.entityToDto(e, dto);
			dtoList.add(dto);
		}
		WebPage<EmployeeDto, Long> webPage = new WebPage<EmployeeDto, Long>(dtoList, page.getTotalElements());
		return webPage;
	}

	@Transactional(readOnly=true)
	public List<ManagerDto> getManagerDtoList(){
		List<Employee> managerList = dao.getManagerDtoList(EmployeeTypeEnum.MANAGER);
		List<ManagerDto> managerDtoList = new ArrayList<ManagerDto>();
		ManagerDto dto = null;
		for(Employee e : managerList) {
			dto = new ManagerDto();
			ManagerDto.entityToDto(e, dto);
			managerDtoList.add(dto);
		}
		return managerDtoList;
	}
	
	@Transactional(readOnly=true)
	public Employee findById(Integer id) {
		
		return dao.findOne(id);
	}

	@Transactional(readOnly=true)
	public Employee findByNameLike(String name) {
		List<Employee> list = dao.findByNameLike(name);
		return list.size() == 0 ? null : list.get(0);
	}

	public Employee save(Employee bean) {
		
		return dao.save(bean);
	}

	public Employee update(Employee bean) {
		
		return dao.save(bean);
	}

	public Employee deleteById(Integer id) {
		Employee bean = dao.findOne(id);
		if(bean != null){
			dao.delete(bean);
		}
		return bean;
	}

	public List<Employee> deleteByIds(Integer[] ids) {
		List<Employee> lists = new ArrayList<Employee>(ids.length);
		for(int i = 0 ; i < ids.length ;i ++){
			Employee bean  = dao.findOne(ids[i]);
			if(bean != null){
				dao.delete(bean);
				lists.add(bean);
			}
		}
		return lists;
	}
	
	@Autowired
	private EmployeeDao dao;

}

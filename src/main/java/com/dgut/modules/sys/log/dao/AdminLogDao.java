package com.dgut.modules.sys.log.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dgut.modules.sys.log.entity.AdminLog;

@Repository
public interface AdminLogDao extends JpaRepository<AdminLog, Integer>,JpaSpecificationExecutor<AdminLog>{

}

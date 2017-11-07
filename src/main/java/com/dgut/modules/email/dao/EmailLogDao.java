package com.dgut.modules.email.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dgut.modules.email.entity.EmailLog;

@Repository
public interface EmailLogDao extends JpaRepository<EmailLog, Integer>,JpaSpecificationExecutor<EmailLog> {
}

package com.dgut.modules.border.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dgut.modules.border.entity.BusinessOrder;
import com.dgut.modules.border.entity.support.TransactionStateEnum;

@Repository
public interface BusinessOrderDao extends PagingAndSortingRepository<BusinessOrder, Integer>,JpaSpecificationExecutor<BusinessOrder> {
	
	@Query("select month(bean.finishTime),count(*) from BusinessOrder bean where  bean.transactionState =:transactionState group by month(bean.finishTime)")
     public List<Object[]> getTransactionStatisticsCount(@Param("transactionState")TransactionStateEnum transactionState);
}

package com.dgut.modules.order.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dgut.modules.order.entity.RoomOrder;

@Repository
public interface RoomOrderDao extends PagingAndSortingRepository<RoomOrder, Integer>,JpaSpecificationExecutor<RoomOrder>{

}

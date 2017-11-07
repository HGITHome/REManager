package com.dgut.modules.room.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.room.entity.Room;
import com.dgut.modules.sys.statistic.entity.Statistic;

@Repository
public interface RoomDao extends PagingAndSortingRepository<Room, Integer>,JpaSpecificationExecutor<Room> {


	@Query("select count(*)  from Room bean where bean.realEstate =:realEstate ")
	public Integer findNumberByRealEstate(@Param("realEstate") RealEstate r);
	
	
//	public Order save(Order order);
//	public Order findById(Long id); 
	
/**
 * CrudRepository�ӿڷ�����	
 */
//	���󼯺� orderDao.save(����);//saveOrUpdate
//	����  orderDao.save(Object object);//saveOrUpdate
//	void orderDao.delete(Object����);
//	void orderDao.delete(Long id);
//	void orderDao.delete(Object object);
//	void orderDao.deleteAll();
//	���󼯺� orderDao.findAll();
//	���󼯺� orderDao.findAll(id����);
//	���� orderDao.findOne(Long id);
//	boolean orderDao.exists(Long id);
//	int orderDao.count();//�ܼ�¼��  
	
/**
 * PagingAndSortingRepository�ӿڷ�����	
 */
//	Page<Order> findAll(Pageable pageable);//��ҳ+�����ѯ
//	List<Order> findAll(Sort sort);//�����ѯ
	
/**
 * Specification	
 */
//Page<Order> findAll(Pageable pageable,Specification specification);
	
	/**
	 *	�Զ��巽�� ��
	 */
	
}

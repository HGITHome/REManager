package com.dgut.modules.sys.statistic.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.modules.border.entity.support.TransactionStateEnum;
import com.dgut.modules.border.service.BusinessOrderMng;
import com.dgut.modules.realEstate.entity.RealEstate;
import com.dgut.modules.realEstate.service.RealEstateMng;
import com.dgut.modules.room.service.RoomMng;
import com.dgut.modules.sys.statistic.entity.Statistic;
import com.dgut.modules.sys.statistic.service.StatisticMng;

@Service
@Transactional
public class StatisticMngImpl implements StatisticMng {

	@Transactional(readOnly=true)
	public List<Statistic> getCount() {
		List<RealEstate> rlists = realEastateMng.getRealEastaeList();
		List<Statistic> slist = new ArrayList<Statistic>();
		Statistic statistic = null;
		for(RealEstate r : rlists) {
			Integer count = roomMng.countByRealEstate(r);
			statistic = new Statistic(r.getEstateName(), count);
			slist.add(statistic);
		}
		return slist;
	}

	@Transactional(readOnly=true)
	public List<Object[]> getTransactionStatisticsCount(){
//		Calendar a=Calendar.getInstance();
//		int currentYear = a.get(Calendar.YEAR);
		//Map<Object,List<Object[]>> oHashMapLink = new LinkedHashMap<Object,List<Object[]>>();
		List<Object[]> lists = new ArrayList<Object[]>();
		  lists= businessOrderMng.getTransactionStatisticsCount(TransactionStateEnum.SOLD);
		return lists;
	}
	
	@Autowired
	private RealEstateMng realEastateMng;
	
	@Autowired
	private RoomMng roomMng;
	
	@Autowired
	private BusinessOrderMng businessOrderMng;

}

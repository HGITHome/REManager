package com.dgut.modules.sys.statistic.service;

import java.util.List;
import java.util.Map;

import com.dgut.modules.sys.statistic.entity.Statistic;

public interface StatisticMng {
	
	
	public List<Statistic> getCount();
	
	public List<Object[]> getTransactionStatisticsCount();
}

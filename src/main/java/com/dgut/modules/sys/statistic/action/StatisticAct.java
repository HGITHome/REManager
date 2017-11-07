package com.dgut.modules.sys.statistic.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.modules.sys.statistic.entity.Statistic;
import com.dgut.modules.sys.statistic.service.StatisticMng;

@Controller
@RequestMapping("statistic")
public class StatisticAct {

	private static final Logger log = LoggerFactory.getLogger(StatisticAct.class);
	
	@RequestMapping("o_count.do")
	public @ResponseBody List<Statistic> countEastate(HttpServletRequest request,HttpServletResponse response){
		return statisticMng.getCount();
	}
	
	@RequestMapping("o_transactionCount.do")
	public @ResponseBody List<Object[]> getTransactionStatisticsCount(HttpServletRequest request,HttpServletResponse response){
		return statisticMng.getTransactionStatisticsCount();
	}
	
	@Autowired
	private StatisticMng statisticMng;
}

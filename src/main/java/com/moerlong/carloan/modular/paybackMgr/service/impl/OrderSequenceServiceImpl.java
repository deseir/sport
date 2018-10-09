package com.moerlong.carloan.modular.paybackMgr.service.impl;


import com.moerlong.carloan.modular.paybackMgr.dao.OrderSequenceDao;
import com.moerlong.carloan.modular.paybackMgr.entity.OrderSequence;
import com.moerlong.carloan.modular.paybackMgr.service.IOrderSequenceService;
import com.moerlong.carloan.util.DateUtil;
import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单序列号生成相关服务.
 * @author Administrator
 *
 */
@Service
public class OrderSequenceServiceImpl implements IOrderSequenceService {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(OrderSequenceServiceImpl.class);
	
	@Autowired
	private OrderSequenceDao orderSequenceDao;

	/**
	 * 生成订单号.
	 * @return
	 */
	public String getTradeSequence() {
		//生成规则:系统编码（200）时间序列(8位)+保留(00)+数据库自增id
		String key="200";
		key += DateUtil.format(new Date(), "yyyyMMddHHmmss");
		key += "00";
		OrderSequence seq = new OrderSequence();
		seq.setOrderType(OrderSequence.ORDERTYPE_CON);
		orderSequenceDao.insert(seq);	
		String senIdStr = seq.getSeqId().toString();
		int num = senIdStr.length();
		
		if (num > 6) {
			senIdStr = senIdStr.substring(num - 6);
		}

		key += senIdStr;

		return key;
	}

}

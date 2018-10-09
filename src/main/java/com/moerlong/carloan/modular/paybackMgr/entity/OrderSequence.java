package com.moerlong.carloan.modular.paybackMgr.entity;

/**
 * 
 *
 * 
 **/
public class OrderSequence {
	//订单类型
	public static Integer ORDERTYPE_CON = 1;

	/****/

	private Long seqId;

	/** 订单对应的渠道 **/

	private Integer orderType;

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}

	public Long getSeqId() {
		return this.seqId;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

}

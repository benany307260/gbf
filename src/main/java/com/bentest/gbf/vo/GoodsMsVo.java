package com.bentest.gbf.vo;

import java.sql.Timestamp;

import com.bentest.gbf.domain.entity.Goods;

public class GoodsMsVo extends Goods {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Timestamp endTime;

	private Double msPrice;

	private Timestamp startTime;

	private Integer stockCount;

	public GoodsMsVo() {
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Double getMsPrice() {
		return msPrice;
	}

	public void setMsPrice(Double msPrice) {
		this.msPrice = msPrice;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
}

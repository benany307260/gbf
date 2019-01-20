package com.bentest.gbf.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the goods_ms database table.
 * 
 */
public class GoodsMs implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Timestamp endTime;

	private Long goodsId;

	private Double msPrice;

	private Timestamp startTime;

	private Integer stockCount;

	public GoodsMs() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
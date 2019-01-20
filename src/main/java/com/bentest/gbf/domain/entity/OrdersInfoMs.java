package com.bentest.gbf.domain.entity;

import java.io.Serializable;


/**
 * The persistent class for the orders_info_ms database table.
 * 
 */
public class OrdersInfoMs implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long goodsId;

	private Long orderId;

	private Long userId;

	public OrdersInfoMs() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
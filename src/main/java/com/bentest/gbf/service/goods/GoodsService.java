package com.bentest.gbf.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bentest.gbf.domain.mapper.GoodsMapper;
import com.bentest.gbf.vo.GoodsMsVo;

@Service
public class GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	public List<GoodsMsVo> getGoodsMsVoList() {
		return goodsMapper.getGoodsMsVoList();
	}
}

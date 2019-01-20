package com.bentest.gbf.controller.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bentest.gbf.controller.response.Result;
import com.bentest.gbf.domain.entity.User;
import com.bentest.gbf.service.goods.GoodsService;
import com.bentest.gbf.vo.GoodsMsVo;

@RestController
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value="/std/v1/goodslist")
	public Object getList(User currentLoginUser) {
		List<GoodsMsVo> goodsMsVoList = goodsService.getGoodsMsVoList();
		return Result.success(goodsMsVoList);
	}
	
	@RequestMapping(value="/std/v1/goodsdetail")
	public Object getDetail(User currentLoginUser) {
		List<GoodsMsVo> goodsMsVoList = goodsService.getGoodsMsVoList();
		return Result.success(goodsMsVoList);
	}
	
}

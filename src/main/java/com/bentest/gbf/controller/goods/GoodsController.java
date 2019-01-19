package com.bentest.gbf.controller.goods;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bentest.gbf.controller.response.Result;
import com.bentest.gbf.domain.entity.User;

@RestController
public class GoodsController {
	
	@RequestMapping(value="/std/v1/goodslist")
	public Object getList(User currentLoginUser) {
		return Result.success(currentLoginUser);
	}
}

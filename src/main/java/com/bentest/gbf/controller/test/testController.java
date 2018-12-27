package com.bentest.gbf.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bentest.gbf.controller.response.ErrorCode;
import com.bentest.gbf.controller.response.Result;
import com.bentest.gbf.redis.RedisService;

@RestController
public class testController {
	
	@Autowired
	private RedisService redis;

	@RequestMapping(value="/test/get")
	public Result<String> get()
	{
		String value = redis.get("aaa");
		return Result.success(value);
	}
	
	@RequestMapping(value="/test/set")
	public Result<String> set()
	{
		boolean  result = redis.set("aaa", "好哦 撒旦法师");
		if(result)
		{
			return Result.success();
		}
		else {
			return Result.failure(ErrorCode.e1_100001);
		}
	}
}

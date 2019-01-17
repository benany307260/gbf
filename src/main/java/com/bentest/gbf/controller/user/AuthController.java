package com.bentest.gbf.controller.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bentest.gbf.service.user.UserService;
import com.bentest.gbf.vo.LoginInfoVo;

@RestController
public class AuthController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/std/v1/login")
	public Object login(@Valid LoginInfoVo inputParam) {
		
		// 方法里会对各种情况直接抛异常，由全局异常处理响应页面错误，所以这里不需要在判断结果
		userService.checkUser(inputParam.getUserName(), inputParam.getPassword());
		return true;
	}
}

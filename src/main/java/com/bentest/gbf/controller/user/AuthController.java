package com.bentest.gbf.controller.user;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bentest.gbf.controller.response.ErrorCode;
import com.bentest.gbf.controller.response.Result;
import com.bentest.gbf.service.user.UserService;
import com.bentest.gbf.vo.LoginInfoVo;

@RestController
public class AuthController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/std/v1/login")
	public Object login(LoginInfoVo inputParam) {
		if(inputParam == null || StringUtils.isEmpty(inputParam.getUserName()) || StringUtils.isEmpty(inputParam.getPassword()) ) {
			return Result.failure(ErrorCode.e1_100002);
		}
		
		int result = userService.checkUser(inputParam.getUserName(), inputParam.getPassword());
		if(result > 0) {
			return Result.success();
		}else {
			logger.error("登录，检查用户结果："+result);
			return Result.failure(ErrorCode.e1_100003);
		}
	}
}

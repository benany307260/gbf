package com.bentest.gbf.controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bentest.gbf.controller.response.Result;
import com.bentest.gbf.domain.entity.User;
import com.bentest.gbf.service.user.AuthService;
import com.bentest.gbf.vo.LoginInfoVo;
import com.bentest.gbf.vo.UserVo;

@RestController
public class AuthController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/std/v1/login")
	public Result<UserVo> login(@Valid LoginInfoVo inputParam, HttpServletResponse response) {
		
		logger.info("登录，信息。userName="+inputParam.getUserName()+",pwd="+inputParam.getPassword());
		
		// 方法里会对各种情况直接抛异常，由全局异常处理响应页面错误，所以这里不需要在判断结果
		UserVo userVo = authService.login(inputParam.getUserName(), inputParam.getPassword());
		
		Cookie tokenCookie = authService.createCookies(userVo.getToken());
		
		response.addCookie(tokenCookie);
		
		logger.info("登录，成功。userName="+inputParam.getUserName()+",token="+userVo.getToken());
		
		return Result.success(userVo);
	}
	
	@RequestMapping(value="/std/v1/user")
	public Result<User> getCurrentUser(User user) {
		return Result.success(user);
	}
}

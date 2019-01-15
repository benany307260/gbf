package com.bentest.gbf.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bentest.gbf.vo.LoginInfoVo;

@RestController
public class AuthController {
	
	@RequestMapping(value="/std/v1/login")
	public String login(LoginInfoVo inputParam) {
		return "";
	}
}

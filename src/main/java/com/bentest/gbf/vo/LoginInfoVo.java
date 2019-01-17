package com.bentest.gbf.vo;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class LoginInfoVo {
	@NotBlank
	private String userName;
	@NotBlank
	@Length(min=32)
	private String password;
	
	private String verifyCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
}

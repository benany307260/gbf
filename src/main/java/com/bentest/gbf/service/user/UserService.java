package com.bentest.gbf.service.user;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bentest.gbf.Exception.BusinessException;
import com.bentest.gbf.controller.response.ErrorCode;
import com.bentest.gbf.domain.entity.User;
import com.bentest.gbf.domain.mapper.UserMapper;
import com.bentest.gbf.util.MD5Util;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	
	public boolean checkUser(String userName, String password) {
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			logger.error("检查用户，userName或password为空");
			throw new BusinessException(ErrorCode.e1_100002);
		}
		
		User user = userMapper.getUserByUserName(userName);
		if(user == null) {
			logger.error("检查用户，获取不到用户对象。userName="+userName);
			throw new BusinessException(ErrorCode.e1_100004);
		}
		
		String dbPwd = user.getPassword();
		
		String salt = user.getSlat();
		String inputPwd = MD5Util.getInputPwdMd5(password, salt);
		
		if(dbPwd.equals(inputPwd)) {
			return true;
		}else {
			logger.error("检查用户，密码不正确。userName="+userName);
			throw new BusinessException(ErrorCode.e1_100005);
		}
	}
}

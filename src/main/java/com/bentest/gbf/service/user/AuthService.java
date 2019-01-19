package com.bentest.gbf.service.user;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bentest.gbf.Exception.BusinessException;
import com.bentest.gbf.constant.Constant;
import com.bentest.gbf.controller.response.ErrorCode;
import com.bentest.gbf.domain.entity.User;
import com.bentest.gbf.domain.mapper.UserMapper;
import com.bentest.gbf.redis.RedisService;
import com.bentest.gbf.redis.UserKeyPre;
import com.bentest.gbf.util.MD5Util;
import com.bentest.gbf.util.TokenUtil;
import com.bentest.gbf.vo.UserVo;

@Service
public class AuthService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 检查用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User checkUser(String userName, String password) {
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
		
		// 密码不正确
		if(!dbPwd.equals(inputPwd)) {
			logger.error("检查用户，密码不正确。userName="+userName);
			throw new BusinessException(ErrorCode.e1_100005);
		}
		
		return user;
	}
	
	public UserVo login(String userName, String password) {
		
		User user = checkUser(userName, password);
		
		String token = TokenUtil.createToken();
		
		user.setPassword("");
		user.setSlat("");
		
		boolean redisResult = redisService.set(UserKeyPre.token, token, user);
		if(!redisResult) {
			logger.error("登录，存redis失败。userName="+userName+",token="+token);
			throw new BusinessException(ErrorCode.e1_100003);
		}
		
		UserVo userVo = userToUserVo(user);
		userVo.setToken(token);
		
		return userVo;
	}
	
	public Cookie createCookies(String token) {
		Cookie cookie = new Cookie(Constant.COOKIE_NAME_TOKEN, token);
		cookie.setMaxAge(UserKeyPre.token.getExpireTime());
		cookie.setPath("/");
		return cookie;
	}
	
	public User getUserByToken(String token) {
		return redisService.get(UserKeyPre.token, token, User.class);
	}
	
	public boolean updateTokenExpireTime(String token) {
		redisService.expire(UserKeyPre.token, token, UserKeyPre.token.getExpireTime());
		return true;
	}
	
	private UserVo userToUserVo(User user) {
		UserVo userVo = new UserVo();
		userVo.setUserName(user.getUserName());
		userVo.setNickName(user.getNickName());
		return userVo;
	}
	
}

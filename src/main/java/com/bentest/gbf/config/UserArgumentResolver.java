package com.bentest.gbf.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bentest.gbf.constant.Constant;
import com.bentest.gbf.domain.entity.User;
import com.bentest.gbf.service.user.AuthService;
import com.bentest.gbf.util.CookieUtil;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	private Logger logger = LoggerFactory.getLogger(UserArgumentResolver.class);
	
	@Autowired
	private AuthService authService;
	
	@Override
	public User resolveArgument(MethodParameter parameter, ModelAndViewContainer container, 
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		try {
			HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
			HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
			
			String tokenHeader = request.getHeader(Constant.HEADER_NAME_TOKEN);
			String tokenCookie = CookieUtil.getCookieValue(request, Constant.COOKIE_NAME_TOKEN);
			
			// 都获取不到token
			if(StringUtils.isAllBlank(tokenHeader, tokenCookie)) {
				return null;
			}
			
			String token = null;
			if(StringUtils.isNotBlank(tokenHeader)) {
				token = tokenHeader;
			}else if(StringUtils.isNotBlank(tokenCookie)) {
				token = tokenCookie;
			}
			
			User user = authService.getUserByToken(token);
			
			if(user == null) {
				return null;
			}
			
			// 更新token的生效时间
			authService.updateTokenExpireTime(token);
			// 页面的cookie时间也要更新
			Cookie cookieToken = authService.createCookies(token);
			response.addCookie(cookieToken);
			
			return user;
		} catch (Exception e) {
			logger.error("获取当前用户，异常。", e);
			return null;
		}
		
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		return clazz == User.class;
	}

}

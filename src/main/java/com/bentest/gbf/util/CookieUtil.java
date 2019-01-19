package com.bentest.gbf.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class CookieUtil {
	
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		if(request == null || StringUtils.isBlank(cookieName)) {
			return "";
		}
		return getCookieValue(request.getCookies(), cookieName);
	}
	
	public static String getCookieValue(Cookie[] cookiesArray, String cookieName) {
		if(cookiesArray == null || cookiesArray.length < 1 || StringUtils.isBlank(cookieName)) {
			return "";
		}
		for(Cookie cookie : cookiesArray) {
			if(cookieName.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return "";
	}
}

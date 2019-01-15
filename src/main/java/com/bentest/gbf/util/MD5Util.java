package com.bentest.gbf.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	private static final String salt = "zxcvbnm,.1234567890-=qwertyuiopasdfghjkl;zxcvbnm,.j";
	
	/**
	 * 输入的明文密码转换为md5
	 * @param inputPwd
	 * @return
	 */
	public static String getInputPwdMd5(String inputPwd) {
		return getInputPwdMd5(inputPwd, salt);
	}
	
	/**
	 * 输入的明文密码转换为md5
	 * @param inputPwd 输入的密码
	 * @param salt 盐需要大于等于32个字符
	 * @return
	 */
	public static String getInputPwdMd5(String inputPwd, String salt) {
		StringBuffer sbInputPwdMd5 = new StringBuffer();
		sbInputPwdMd5
		.append(salt.charAt(11))
		.append(salt.charAt(14))
		.append(salt.charAt(28))
		.append(inputPwd)
		.append(salt.charAt(6))
		.append(salt.charAt(21))
		.append(salt.charAt(2))
		.append(salt.charAt(32));
		return md5(sbInputPwdMd5.toString());
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.getInputPwdMd5("abc123"));
	}
	
}

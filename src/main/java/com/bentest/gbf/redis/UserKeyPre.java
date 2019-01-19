package com.bentest.gbf.redis;

public class UserKeyPre extends BaseKeyPre {

	public UserKeyPre(String keyPre) {
		super(keyPre);
	}
	
	public UserKeyPre(String keyPre, int expireTime) {
		super(keyPre, expireTime);
	}
	
	/**
	 * 用户id前缀
	 */
	public static UserKeyPre id = new UserKeyPre("id");
	
	/**
	 * 用户token前缀，有效期7天（单位秒）
	 */
	public static UserKeyPre token = new UserKeyPre("token", 60*60*24*7);

}

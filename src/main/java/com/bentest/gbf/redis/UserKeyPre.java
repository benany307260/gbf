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

}

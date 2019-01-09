package com.bentest.gbf.redis;

public abstract class BaseKeyPre implements KeyPre {
	
	public BaseKeyPre(String keyPre) {
		this.keyPre = keyPre;
		this.expireTime = 0;
	}
	
	public BaseKeyPre(String keyPre, int expireTime) {
		this.keyPre = keyPre;
		this.expireTime = expireTime;
	}
	
	// 失效时间，默认为0，永不失效
	private int expireTime = 0;
	
	private String keyPre;
	
	public int getExpireTime() {
		return expireTime;
	}
	
	public String getKeyPre() {
		String className = this.getClass().getSimpleName();
		return className + "-" +keyPre + "-";
	}
}

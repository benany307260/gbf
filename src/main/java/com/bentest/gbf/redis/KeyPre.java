package com.bentest.gbf.redis;

/**
 * redis key前缀 接口
 *
 */
public interface KeyPre {
	/**
	 * 获取过期时间，单位秒
	 * @return
	 */
	public int getExpireTime();
	
	/**
	 * 获取key前缀
	 * @return
	 */
	public String getKeyPre();
}

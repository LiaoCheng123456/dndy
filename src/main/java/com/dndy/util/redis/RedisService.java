package com.dndy.util.redis;

import java.util.Map;

/**
 * redis相关服务
 * 
 * @author rex
 *
 */
@org.springframework.stereotype.Service("redisService")
public class RedisService{
	private static final String LOCK_SUCCESS ="OK";


	/**
	 * 获取 -根据key获取value
	 * @param key
	 * @return Object
	 */
	public Object getKey(String key) {
		return RedisUtil.getKey(key);
	}

	/**
	 * 存入 -将value转成json
	 * 
	 * @param expire 有效期（天）
	 * @param key
	 * @param valueobj
	 */
	public void setKey(String key, Object valueobj, int expire) {

		RedisUtil.setKey(key, valueobj, expire);
	}

	/**
	 * 存入 -将value转成json 默认不设置有效期
	 * @param key
	 * @param valueobj
	 */
	public void setKey(String key, Object valueobj) {

		RedisUtil.setKey(key, valueobj);
	}

	/**
	 * 存入   有效期秒 返回OK或者null
	 * @param key valueobj expire
	 */
	public String setKeyBySecond(String key, String valueobj, int expire) {

		return RedisUtil.setKeyBySecond(key, valueobj, expire);
	}

	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param expire
	 *            过期时间（天） 传0则不过期
	 */
	public void setExpire(String key, int expire) {
		RedisUtil.setExpire(key, expire);
	}

	/**
	 * 删除key
	 * 
	 * @param key
	 */
	public void remove(String key) {

		RedisUtil.remove(key);
	}

	
	/**
	 * 试图获取分布式锁
	 * @param lockKey 使用key来当锁
	 * @param value ,判断value用来自己解锁
	 * @param expireTime 过期时间
	 * @return
	 */
	public  boolean  tryGetDistributedLock(String lockKey,String value,int expireTime){
		try {
			if(LOCK_SUCCESS.equals(RedisUtil.tryGetDistributedLock(lockKey, value, expireTime))){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 释放分布式锁
	 * @param lockKey 锁
	 * @param requestId 请求标识
	 * @return 是否释放成功
	 */
	public  boolean releaseDistributedLock( String lockKey, String requestId) {
		try {
			if ("1".equals(RedisUtil.remove(lockKey))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}

	/**
	 * hash表设值
	 * 成功返回字符串“OK”
	 */
	public String hashSetKey(String key, String field, Object value) {
		return RedisUtil.hset(key, field, value);
		
	}
	/**
	 * hash表获取值
	 */
	public Object hashGetKey(String key, String field) {
		return RedisUtil.hget(key, field);
	}

	/**
	 * 获取整个hash表
	 */
	
	public Map<String, String> hashGetAll(String key) {
		return RedisUtil.hgetAll(key);
	}

	/**
	 * hash表删除其中一个域
	 */
	
	public String hashDelField(String key, String field) {
		return RedisUtil.hdel(key, field);
	}
	
	
	public Boolean hexists(String key, String field) {
		return RedisUtil.hexists(key,field);
	}

	
}

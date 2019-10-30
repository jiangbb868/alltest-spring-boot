package com.alkaid.shiroredis.cache;

import com.alkaid.shiroredis.common.Resource;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheManager implements CacheManager {
    private static final Logger logger = LoggerFactory.getLogger(
            org.crazycake.shiro.RedisCacheManager.class);

    public final static String SHIRO_TOKEN_KEY = "relax:shiro:token";

    private RedisCache redisCache;

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisCacheManager() {
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache c = this.redisCache;
        if (c == null) {
            RedisCache redisCache = new RedisCache(redisTemplate);
            redisCache.setName(name);
        }
        return (Cache)c;
    }

}

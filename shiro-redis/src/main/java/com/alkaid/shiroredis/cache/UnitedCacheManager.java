package com.alkaid.shiroredis.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;

public class UnitedCacheManager implements CacheManager {

    private CacheManager cacheManager;

    public UnitedCacheManager() {
        if (true) {
            cacheManager = new RedisCacheManager();
        } else {
            EhCacheManager ehCacheManager = new EhCacheManager();
            ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
            cacheManager = ehCacheManager;
        }
    }


    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return cacheManager.getCache(s);
    }
}

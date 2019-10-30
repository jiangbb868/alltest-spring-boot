package com.alkaid.shiroredis.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

public class RedisCache<K, V> implements Cache<K, V> {

    private String name;

    private RedisTemplate redisTemplate;

    public RedisCache(){}

    public RedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public V get(K key) throws CacheException {
        try {
            if (key == null) {
                return null;
            } else {
                Object element = this.redisTemplate.opsForHash().get(name, key);
                //Object element = this.redisTemplate.opsForValue().get(key);
                if (element == null) {
                    return null;
                } else {
                    return (V)element;
                }
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        try {
            V previous = this.get(key);
            this.redisTemplate.opsForHash().put(name, key, value);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        try {
            V previous = this.get(key);
            this.redisTemplate.opsForHash().delete(name, key);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        try {
            this.redisTemplate.delete(name);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public int size() {
        try {
        return this.redisTemplate.opsForHash().size(name).intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Set keys() {
        try {
            return this.redisTemplate.opsForHash().keys(name);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection values() {
        try {
            return this.redisTemplate.opsForHash().values(name);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}

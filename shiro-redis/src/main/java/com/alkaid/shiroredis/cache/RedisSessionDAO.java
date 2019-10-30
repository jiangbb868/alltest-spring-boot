package com.alkaid.shiroredis.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.alkaid.shiroredis.util.TypeUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.crazycake.shiro.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(org.crazycake.shiro.RedisSessionDAO.class);

    private RedisCache redisCache;

    private String keyPrefix = RedisCacheManager.SHIRO_TOKEN_KEY;

    public RedisSessionDAO() {
    }

    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if (session != null && session.getId() != null) {
            byte[] value = SerializeUtils.serialize(session);
            session.setTimeout((long)(300 * 1000));
            this.redisCache.put(getStringKey(session.getId()), TypeUtil.byteArrayToHexStr(value));
        } else {
            logger.error("session or session id is null");
        }
    }

    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            this.redisCache.remove(getStringKey(session.getId()));
        } else {
            logger.error("session or session id is null");
        }
    }

    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet();
        Set<String> keys = this.redisCache.keys();
        if (keys != null && keys.size() > 0) {
            Iterator i$ = keys.iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                String value = (String)this.redisCache.get(key);
                Session s = (Session)SerializeUtils.deserialize(TypeUtil.hexStrToByteArray(value));
                sessions.add(s);
            }
        }

        return sessions;
    }

    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        } else {
            String value = (String)this.redisCache.get(getStringKey(sessionId));
            Session s = (Session)SerializeUtils.deserialize(TypeUtil.hexStrToByteArray(value));
            return s;
        }
    }

    private byte[] getByteKey(Serializable sessionId) {
        String preKey = sessionId.toString();
        return preKey.getBytes();
    }

    private String getStringKey(Serializable sessionId) {
        return sessionId.toString();
    }


    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}

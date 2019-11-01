package com.alkaid.shiroredis.cache;

import com.alkaid.shiroredis.util.TypeUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.crazycake.shiro.SerializeUtils;

import java.io.Serializable;

public class RedisCachingSessionDAO extends CachingSessionDAO {

    private RedisCache<String, String> redisCache;

    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    protected void doUpdate(Session session) {
        saveSession(session);
    }

    @Override
    protected void doDelete(Session session) {
        if (session != null && session.getId() != null) {
            try {
                String old = this.redisCache.remove(getStringKey(session.getId()));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if (session != null && session.getId() != null) {
            try {
                byte[] value = SerializeUtils.serialize(session);
                session.setTimeout((long) (300 * 1000));
                String old = this.redisCache
                        .put(getStringKey(session.getId()), TypeUtil.byteArrayToHexStr(value));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        try {
            this.assignSessionId(session, sessionId);
            this.saveSession(session);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        } else {
            Session sesion = null;
            try {
                String value = this.redisCache.get(getStringKey(sessionId));
                sesion = (Session) SerializeUtils.deserialize(TypeUtil.hexStrToByteArray(value));
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return sesion;
        }
    }

    private String getStringKey(Serializable sessionId) {
        return sessionId.toString();
    }

}

package com.wasu.springboot.integration.lock;

import com.wasu.springboot.integration.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("lockService")
public class RedisLockServiceImpl implements LockService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockServiceImpl.class);
    private static final String LOCPREFIX = "locks_";

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void releaseLock(String resourceName, String randomToken) {
        String key = LOCPREFIX + resourceName;
        String value = redisUtil.get(key);
        if (value != null && value.equals(randomToken)) {
            redisUtil.removeKey(key);
        } else {
            new RuntimeException("方法执行时间过长，已自动释放锁！").printStackTrace();
        }
    }

    @Override
    public boolean tryLock(String resourceName, String randomToken, LockMode lockMode, int expireSeconds) {
        String key = LOCPREFIX + resourceName;
        Long i = redisUtil.setNx(key, randomToken);
        if (i == 1L) {
            redisUtil.expire(key, expireSeconds);
            return true;
        } else {
            return false;
        }
    }
}

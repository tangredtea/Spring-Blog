package com.blog.util;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Ryan

 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    private static final double size = Math.pow(2, 32);


    public boolean setBit(String key, long offset, boolean isShow) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.setBit(key, offset, isShow);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean getBit(String key, long offset) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            result = operations.getBit(key, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    public Object get(final String key) {
        Object result;
        ValueOperations operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    public Object hmGet(String key, Object hashKey) {
        HashOperations hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }


    public void lPush(String k, Object v) {
        ListOperations list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }


    public List lRange(String k, long l, long l1) {
        ListOperations list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }


    public void add(String key, Object value) {
        SetOperations set = redisTemplate.opsForSet();
        set.add(key, value);
    }


    public Set setMembers(String key) {
        SetOperations set = redisTemplate.opsForSet();
        return set.members(key);
    }


    public void zAdd(String key, Object value, double source) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        zset.add(key, value, source);
    }


    public Set rangeByScore(String key, double source, double scoure_1) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        redisTemplate.opsForValue();
        return zset.rangeByScore(key, source, scoure_1);
    }

    public void saveDataToRedis(String name) {
        double index = Math.abs(name.hashCode() % size);
        long indexLong = new Double(index).longValue();
        boolean availableUsers = setBit("availableUsers", indexLong, true);
    }


    public boolean getDataToRedis(String name) {
        double index = Math.abs(name.hashCode() % size);
        long indexLong = new Double(index).longValue();
        return getBit("availableUsers", indexLong);
    }



    public Long zRank(String key, Object value) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        return zset.rank(key, value);
    }


    public Set zRankWithScore(String key, long start, long end) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        Set ret = zset.rangeWithScores(key, start, end);
        return ret;
    }


    public Double zSetScore(String key, Object value) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        return zset.score(key, value);
    }


    public void incrementScore(String key, Object value, double scoure) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        zset.incrementScore(key, value, scoure);
    }


    public Set<ZSetOperations.TypedTuple<Object>> reverseZRankWithScore(String key, long start, long end) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        return (Set<ZSetOperations.TypedTuple<Object>>) zset.reverseRangeByScoreWithScores(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<Object>> reverseZRankWithRank(String key, long start, long end) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        return (Set<ZSetOperations.TypedTuple<Object>>) zset.reverseRangeWithScores(key, start, end);
    }
}

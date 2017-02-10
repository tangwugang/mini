package com.sanweibook.lingdu.redis.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sanweibook.lingdu.redis.dataSource.RedisDataSource;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Set;

/**
 * Created by twg on 17/2/9.
 */
@Slf4j
public class RedisClientExecute<T> implements RedisClientCallBack<T> {
    private RedisDataSource redisDataSource;

    public RedisClientExecute(RedisDataSource redisDataSource) {
        this.redisDataSource = redisDataSource;
    }

    @Override
    public T get(T key) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return (T) jedis.get((String) key);
            } else if (key instanceof byte[]) {
                return (T) jedis.get((byte[]) key);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.get方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return null;
    }

    @Override
    public boolean exists(T key) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return jedis.exists((String) key);
            } else if (key instanceof byte[]) {
                return jedis.exists((byte[]) key);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.exists方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return false;
    }

    @Override
    public Long del(T key) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return jedis.del((String) key);
            } else if (key instanceof byte[]) {
                return jedis.del((byte[]) key);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.del方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return 0L;
    }

    @Override
    public Set<T> hKeys(T key) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return (Set<T>) jedis.hkeys((String) key);
            } else if (key instanceof byte[]) {
                return (Set<T>) jedis.hkeys((byte[]) key);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.hKeys方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return Sets.newHashSet();
    }

    @Override
    public List<T> hVals(T key) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return (List<T>) jedis.hvals((String) key);
            } else if (key instanceof byte[]) {
                return (List<T>) jedis.hvals((byte[]) key);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.hVals方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return Lists.newArrayList();
    }

    @Override
    public Long expire(T key, int second) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return jedis.expire((String) key, second);
            } else if (key instanceof byte[]) {
                return jedis.expire((byte[]) key, second);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.expire方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return 0L;
    }

    @Override
    public String setEX(T key, T value, int second) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return jedis.setex((String) key, second, (String) value);
            } else if (key instanceof byte[]) {
                return jedis.setex((byte[]) key, second, (byte[]) value);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.setEX方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return null;
    }

    @Override
    public T getSet(T key, T value) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return (T) jedis.getSet((String) key, (String) value);
            } else if (key instanceof byte[]) {
                return (T) jedis.getSet((byte[]) key, (byte[]) value);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.getSet方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return null;
    }

    @Override
    public Long hSet(T key, T field, T value) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return jedis.hset((String) key, (String) field, (String) value);
            } else if (key instanceof byte[]) {
                return jedis.hset((byte[]) key, (byte[]) field, (byte[]) value);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.hSet方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return 0L;
    }

    @Override
    public Long setRange(T key, Long offset, T value) {
        Jedis jedis = redisDataSource.getDataSource();
        boolean broken = true;
        try {
            if (key instanceof String) {
                return jedis.setrange((String) key, offset, (String) value);
            } else if (key instanceof byte[]) {
                return jedis.setrange((byte[]) key, offset, (byte[]) value);
            }
        } catch (JedisException jedisException) {
            log.error("执行RedisClientTemplate.setRange方法时异常：", jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(jedis, broken);
        }
        return 0L;
    }
}

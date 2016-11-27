package com.sanweibook.lingdu.redis.core;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Sets;
import com.sanweibook.lingdu.redis.dataSource.RedisDataSource;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by twg on 16/11/3.
 */
@Slf4j
public class RedisClientTemplate {

    /**
     * redis过期时间
     */
    private int expiration = 1800;
    /**
     * redis默认来源库是master
     */
    private String redisSource = "master";

    private RedisDataSource redisDataSource;

    /**
     * 设置值,默认过期时间1800ms
     * 1.过期时间是毫秒级
     * 2.只添加key不存在的值，当key值存在会时，返回null
     *
     * @param key   键值
     * @param value 内容
     * @return OK 成功 null 失败
     */
    public String setNXPX(String key, String value) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.set(key, value, "NX", "PX", expiration);
        } catch (JedisException jedisException) {
            log.error("执行[{}]RedisClientTemplate.setNXPX方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 设置值,可自定义过期时间
     * 1.过期时间是毫秒级
     * 2.只添加key不存在的值，当key值存在会时，返回null
     *
     * @param key         键值
     * @param value       内容
     * @param millisecond 过期时间
     * @return OK 成功 null 失败
     */
    public String setNXPX(String key, String value, long millisecond) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.set(key, value, "NX", "PX", millisecond);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.setNXPX方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 设置值,默认过期时间60000ms
     * 1.过期时间是毫秒级
     * 2.只添加key存在的值，当key值不存在会时，返回null
     *
     * @param key   键值
     * @param value 内容
     * @return OK 成功 null 失败
     */
    public String setXXPX(String key, String value) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.set(key, value, "XX", "PX", expiration);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.setXXPX方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 设置值,可自定义过期时间
     * 1.过期时间是毫秒级
     * 2.只添加key存在的值，当key值不存在会时，返回null
     *
     * @param key         键值
     * @param value       内容
     * @param millisecond 过期时间
     * @return OK 成功 null 失败
     */
    public String setXXPX(String key, String value, long millisecond) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.set(key, value, "XX", "PX", millisecond);
        } catch (JedisException jedisException) {
            log.error("执行[{}]RedisClientTemplate.setXXPX方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 指定的 key 设置值及其过期时间
     *
     * @param key    键值
     * @param value  内容
     * @param second 过期时间，秒
     * @return OK 成功 null 失败
     */
    public String setEX(String key, String value, int second) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.setex(key, second, value);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.setEX方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 指定的 key 设置值及其过期时间
     *
     * @param key    键值
     * @param value  内容
     * @param second 过期时间，秒
     * @return OK 成功 null 失败
     */
    public String setEX(byte[] key, byte[] value, int second) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.setex(key, second, value);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.setEX方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 用指定的字符串覆盖执行定 key 所储存的字符串值，覆盖的位置从偏移量 offset 开始
     *
     * @param key    键值
     * @param offset 偏移量开始值
     * @param value  内容
     * @return 被修改后的字符串长度
     */
    public Long setRange(String key, Long offset, String value) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long status = 0L;
        try {
            status = shardedJedis.setrange(key, offset, value);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.setRange方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 用于删除已存在的键。不存在的 key 会被忽略
     *
     * @param key 键值
     * @return 被删除 key 的数量
     */
    public Long del(String key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long status = 0L;
        try {
            status = shardedJedis.del(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.del方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 用于删除已存在的键。不存在的 key 会被忽略
     *
     * @param key 键值
     * @return 被删除 key 的数量
     */
    public Long del(byte[] key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long status = 0L;
        try {
            status = shardedJedis.del(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.del方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 用于检查执行定 key 是否存在
     *
     * @param key 键值
     * @return
     */
    public boolean exists(String key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        boolean status = false;
        try {
            status = shardedJedis.exists(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.exists方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }


    /**
     * 用于检查执行定 key 是否存在
     *
     * @param key 键值
     * @return
     */
    public boolean exists(byte[] key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        boolean status = false;
        try {
            status = shardedJedis.exists(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.exists方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }


    /**
     * 用于设置 key 的过期时间。key 过期后将不再可用
     *
     * @param key    键值
     * @param second 秒
     * @return 0 失败 1 成功
     */
    public Long expire(String key, int second) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long status = 0L;
        try {
            status = shardedJedis.expire(key, second);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.expire方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 获取指定 key 的值
     *
     * @param key 键值
     * @return key不存在，返回null
     */
    public String get(String key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String value = "";
        try {
            value = shardedJedis.get(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.get方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return value;
    }

    /**
     * 获取指定 key 的值
     *
     * @param key 键值
     * @return key不存在，返回null
     */
    public byte[] get(byte[] key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        byte[] value = null;
        try {
            value = shardedJedis.get(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.get方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return value;
    }

    /**
     * 设置指定 key 的值，并返回 key 旧的值，如果key 不存在，就添加
     *
     * @param key   键值
     * @param value 内容
     * @return 返回 key 旧的值，不存在返回null
     */
    public String getSet(String key, String value) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.getSet(key, value);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.getSet方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 获取哈希表中的所有字段值
     * @param key 键值
     * @return
     */
    public Set<byte[]> hKeys(byte[] key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Set<byte[]> status = Sets.newHashSet();
        try {
            status = shardedJedis.hkeys(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hKeys方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 用于删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略
     * @param key 键值
     * @param field 字段
     * @return 被成功删除字段的数量，不包括被忽略的字段
     */
    public Long hDel(String key,String... field) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long value = 0L;
        try {
            value = shardedJedis.hdel(key, field);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hDel方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return value;
    }

    /**
     * 用于获取哈希表中的所有字段名
     *
     * @param key 键值
     * @return 匹配keys的字段名列表
     */
    public Set<String> hkeys(String key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Set<String> values = Sets.newHashSet();
        try {
            values = shardedJedis.hkeys(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hkeys方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return values;
    }

    /**
     * 哈希表所有字段的值
     *
     * @param key 键值
     * @return 字段值列表
     */
    public List<String> hVals(String key) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        List<String> values = Lists.newArrayList();
        try {
            values = shardedJedis.hvals(key);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hVals方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return values;
    }

    /**
     * 用于返回哈希表中，一个键，设定一个或多个执行定字段的值
     *
     * @param key   键值
     * @param field 字段
     * @param value 字段值
     * @return 1 成功 0 失败
     */
    public Long hSet(String key, String field, String value) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long status = 0L;
        try {
            status = shardedJedis.hset(key, field, value);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hSet方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 用于为哈希表中不存在的的字段赋值
     *
     * @param key   键值
     * @param field 字段
     * @param value 字段值
     * @return 1 成功 0 失败
     */
    public Long hSetNX(String key, String field, String value) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long status = 0L;
        try {
            status = shardedJedis.hsetnx(key, field, value);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hSetNX方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }

    /**
     * 用于返回哈希表中，一个键，设定key-value集合
     *
     * @param key      键值
     * @param paramMap 键值对集合信息
     * @return OK 成功
     */
    public String hmSet(String key, Map<String, String> paramMap) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        String status = "";
        try {
            status = shardedJedis.hmset(key, paramMap);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hmSet方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }


    /**
     * 用于返回哈希表中，一个键，返回一个或多个执行定字段的值
     *
     * @param key    键值
     * @param fields 字段列表
     * @return 返回 字段值列表
     */
    public List<String> hmGet(String key, String... fields) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        List<String> values = Lists.newArrayList();
        try {
            values = shardedJedis.hmget(key, fields);
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.hmGet方法时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return values;
    }

    /*public Long setNX(String key, String value) {
        Jedis shardedJedis = redisDataSource.getDataSource();
        boolean broken = true;
        Long status = 0L;
        try {
            status = shardedJedis.setex();
        } catch (JedisException jedisException) {
            log.error("执行[{}] RedisClientTemplate.setEX方法，设置值时异常：", redisSource, jedisException);
            if (jedisException instanceof JedisDataException) {
                broken = false;
            }
            throw jedisException;
        } finally {
            redisDataSource.closeResource(shardedJedis);
        }
        return status;
    }*/


    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setRedisSource(String redisSource) {
        this.redisSource = redisSource;
    }

    public void setRedisDataSource(RedisDataSource redisDataSource) {
        this.redisDataSource = redisDataSource;
    }

}

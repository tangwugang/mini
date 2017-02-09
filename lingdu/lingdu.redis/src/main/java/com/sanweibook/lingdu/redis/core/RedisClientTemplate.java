package com.sanweibook.lingdu.redis.core;

import com.sanweibook.lingdu.redis.dataSource.RedisDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.List;
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

    private RedisDataSource redisDataSource;

    /**
     * 指定的 key 设置值及其过期时间
     *
     * @param key    键值
     * @param value  内容
     * @param second 过期时间，秒
     * @return OK 成功 null 失败
     */
    public <T> String setEX(T key, T value, int second) {
        Assert.notNull(key,"not null key required");
        Assert.notNull(value,"not null value required");
        Assert.notNull(second,"not null second required");
        return new RedisClientExecute<T>(redisDataSource).setEX(key,value,second);
    }


    /**
     * 用指定的字符串覆盖执行定 key 所储存的字符串值，覆盖的位置从偏移量 offset 开始
     *
     * @param key    键值
     * @param offset 偏移量开始值
     * @param value  内容
     * @return 被修改后的字符串长度
     */
    public <T> Long setRange(T key, Long offset, T value) {
        Assert.notNull(key,"not null key required");
        Assert.notNull(value,"not null value required");
        Assert.notNull(offset,"not null offset required");
        return new RedisClientExecute<T>(redisDataSource).setRange(key,offset,value);
    }


    /**
     * 用于删除已存在的键。不存在的 key 会被忽略
     *
     * @param key 键值
     * @return 被删除 key 的数量
     */
    public <T> Long del(T key){
        Assert.notNull(key,"not null key required");
        return new RedisClientExecute<T>(redisDataSource).del(key);
    }


    /**
     * 用于检查执行定 key 是否存在
     *
     * @param key 键值
     * @return
     */
    public <T> boolean exists(T key){
        Assert.notNull(key,"not null key required");
        return new RedisClientExecute<T>(redisDataSource).exists(key);
    }

    /**
     * 用于设置 key 的过期时间。key 过期后将不再可用
     *
     * @param key    键值
     * @param second 秒
     * @return 0 失败 1 成功
     */
    public <T> Long expire(T key, int second) {
        Assert.notNull(key,"not null key required");
        Assert.notNull(second,"not null second required");
        return new RedisClientExecute<T>(redisDataSource).expire(key,second);
    }


    /**
     * 获取指定 key 的值
     *
     * @param key 键值
     * @return key不存在，返回null
     */
    public <T> T get(T key){
        Assert.notNull(key,"not null key required");
        return new RedisClientExecute<T>(redisDataSource).execute(key);
    }

    /**
     * 设置指定 key 的值，并返回 key 旧的值，如果key 不存在，就添加
     *
     * @param key   键值
     * @param value 内容
     * @return 返回 key 旧的值，不存在返回null
     */
    public <T> T getSet(T key,T value){
        Assert.notNull(key,"not null key required");
        Assert.notNull(value,"not null key required");
        return new RedisClientExecute<T>(redisDataSource).getSet(key, value);
    }

    /**
     * 获取哈希表中的所有字段值
     * @param key 键值
     * @return
     */
    public <T> Set<T> hKeys(T key){
        Assert.notNull(key,"not null key required");
        return new RedisClientExecute<T>(redisDataSource).hKeys(key);
    }

    /**
     * 哈希表所有字段的值
     *
     * @param key 键值
     * @return 字段值列表
     */
    public <T> List<T> hVals(T key) {
        Assert.notNull(key,"not null key required");
        return new RedisClientExecute<T>(redisDataSource).hVals(key);
    }


    /**
     * 用于返回哈希表中，一个键，设定一个或多个执行定字段的值
     *
     * @param key   键值
     * @param field 字段
     * @param value 字段值
     * @return 1 成功 0 失败
     */
    public <T> Long hSet(T key,T field ,T value){
        Assert.notNull(key,"not null key required");
        Assert.notNull(field,"not null field required");
        Assert.notNull(value,"not null value required");
        return new RedisClientExecute<T>(redisDataSource).hSet(key, field, value);
    }


    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setRedisDataSource(RedisDataSource redisDataSource) {
        this.redisDataSource = redisDataSource;
    }


}

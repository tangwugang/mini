package com.sanweibook.lingdu.redis.core;

import java.util.List;
import java.util.Set;

/**
 * Created by twg on 17/2/9.
 */
public interface RedisClientCallBack<T> {

    T get(T key);

    boolean exists(T key);

    Long del(T key);

    Set<T> hKeys(T key);

    List<T> hVals(T key);

    Long expire(T key, int second);

    T getSet(T key, T value);

    Long hSet(T key, T field, T value);

    String setEX(T key, T value, int second);

    Long setRange(T key, Long offset, T value);

}

package com.sanweibook.lingdu.redis.core;

import java.util.List;
import java.util.Set;

/**
 * Created by twg on 17/2/9.
 */
public interface RedisClientCallBack {

    <T> T execute(T key);

    <T> boolean exists(T key);

    <T> Long del(T key);

    <T> Set<T> hKeys(T key);

    <T> List<T> hVals(T key);

    <T> Long expire(T key, int second);

    <T> T getSet(T key, T value);

    <T> Long hSet(T key, T field, T value);

    <T> String setEX(T key, T value, int second);

    <T> Long setRange(T key, Long offset, T value);

}

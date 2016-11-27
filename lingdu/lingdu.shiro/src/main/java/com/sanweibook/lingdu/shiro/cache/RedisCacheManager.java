/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sanweibook.lingdu.shiro.cache;

import com.sanweibook.lingdu.redis.core.RedisClientTemplate;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by twg on 16/11/4.
 */
public class RedisCacheManager implements CacheManager {

    private RedisClientTemplate redisClientTemplate;

    private String prefixKey = "shiro_redis_cache_";

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if(!StringUtils.hasText(name)){
            throw new CacheException("getCache the name cannot be null");
        }
        Cache cache = caches.get(name);
        if (cache == null && StringUtils.hasText(prefixKey)) {
            cache = new RedisCache(redisClientTemplate, prefixKey);
        }
        caches.put(name, cache);
        return cache;
    }

    public void setRedisClientTemplate(RedisClientTemplate redisClientTemplate) {
        this.redisClientTemplate = redisClientTemplate;
    }

    public void setPrefixKey(String prefixKey) {
        this.prefixKey = prefixKey;
    }

}

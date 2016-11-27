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
package com.sanweibook.lingdu.redis.factory;

import com.sanweibook.lingdu.redis.dataSource.RedisDataSource;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by twg on 16/11/3.
 */
@Slf4j
public class RedisDataSourceFactory implements RedisDataSource {

    private JedisPool jedisPool;

    private Jedis jedis;

    private JedisShardInfo shardInfo;

    private JedisPoolConfig poolConfig = new JedisPoolConfig();


    @Override
    public Jedis getDataSource() {
        jedisPool = new JedisPool(poolConfig, shardInfo.getHost(), shardInfo.getPort(), shardInfo.getConnectionTimeout(), shardInfo.getPassword());
        if (jedisPool == null) {
            log.error("JedisPool property cannot be null.");
            throw new JedisException("JedisPool property cannot be null");
        }
        try {
            jedis = jedisPool.getResource();
            if (log.isInfoEnabled()) {
                log.info("Jedis property get success");
            }
        } catch (JedisException exception) {
            log.error("Jedis property cannot be null.", exception);
            throw new JedisException(exception);
        }
        return jedis;
    }

    @Override
    public void closeResource(Jedis jedis) {
        destroy();
    }

    @Override
    public void destroy() {
        if (jedisPool != null) {
            jedisPool.destroy();
        }
    }


    public void setShardInfo(JedisShardInfo shardInfo) {
        this.shardInfo = shardInfo;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

}

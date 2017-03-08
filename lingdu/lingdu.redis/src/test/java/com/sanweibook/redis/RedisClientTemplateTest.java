package com.sanweibook.redis;

import com.sanweibook.lingdu.redis.core.RedisClientTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Created by twg on 16/11/4.
 */
public class RedisClientTemplateTest {

    @Test
    public void testRedisTemplate(){
        ClassPathXmlApplicationContext pathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-redis-test.xml");
        RedisClientTemplate redisClientTemplate = (RedisClientTemplate)pathXmlApplicationContext.getBean("RedisClientTemplate");
        String v = (String) redisClientTemplate.getSet("twg03","灌灌灌灌");
        System.out.println(v);
        /*for (int i = 0; i < 10000; i++) {
            String status1 = redisClientTemplate.setEX("twg"+i, "1234",100000);
            Assert.assertEquals("OK",status1);
//            String status = redisClientTemplate.setXXPX("twg"+i, "滕王阁",100000);
//            Assert.assertEquals("OK",status);
        }*/
    }
}

package com.sanweibook.rabbitmq;

import com.sanweibook.lingdu.rabbitmq.MQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by twg on 17/1/13.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbit-test.xml")
public class MQProducerTest {
    @Autowired
    private MQProducer mqProducer;

    @Test
    public void testSendToQueue(){
        String key = "dev-direct-queueKey";
        mqProducer.sendToQueue(key,"This is spring-rabbit-queue.");
    }
}

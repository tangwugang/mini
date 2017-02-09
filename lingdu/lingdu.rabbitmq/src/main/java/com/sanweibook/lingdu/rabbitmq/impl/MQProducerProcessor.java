package com.sanweibook.lingdu.rabbitmq.impl;

import com.sanweibook.lingdu.rabbitmq.MQProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by twg on 17/1/13.
 */
@Component
public class MQProducerProcessor implements MQProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendToQueue(String queueKey, Object object) {
        rabbitTemplate.convertAndSend(queueKey, object);
    }
}

package com.sanweibook.lingdu.rabbitmq.impl;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

/**
 * Created by twg on 17/1/13.
 */
@Component
public class MQConsumerProcessor implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("This is consumer:"+ new Jackson2JsonMessageConverter().fromMessage(message));
        /*消费者手动应答：false*/
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}

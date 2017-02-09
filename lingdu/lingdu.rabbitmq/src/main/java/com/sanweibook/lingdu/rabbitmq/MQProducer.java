package com.sanweibook.lingdu.rabbitmq;

/**
 * Created by twg on 17/1/13.
 */
public interface MQProducer {
    /**
     * 发送消息给QUEUE
     * @param queueKey 队列键值
     * @param object 消息内容
     */
    void sendToQueue(String queueKey,Object object);
}

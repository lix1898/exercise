package com.itkjb.exercise.rabbitmq.springboot.consumer;

import com.itkjb.exercise.rabbitmq.springboot.config.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/20 10:15 上午
 * @Description:
 * @Version: V1.0.0
 */
@Component
public class RbMqReciveListener {
    /**
     *  01 - 接收 简单消息队列的消息
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.SIMPLE_QUEUE)
    public void receiveSimpleQueue(Message msg) {
        // 只包含发送的消息
        System.out.println(RabbitmqConfig.SIMPLE_QUEUE+ "：接收到消息：" + msg.getBody());
    }

    /**
     * 02 - 接收 work 模式的消息队列
     * 以下receiveWorkQueue1 和 receiveWorkQueue2 会轮流接收消息
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.WORK_QUEUE_2)
    public void receiveWorkQueue1(Message msg) {
        // 只包含发送的消息
        System.out.println("第一个work模式："+RabbitmqConfig.WORK_QUEUE_2+ "：接收到消息：" + msg);
    }
    /**
     * 02- 接收 work 模式的消息队列
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.WORK_QUEUE_2)
    public void receiveWorkQueue2(Message msg) {
        // 只包含发送的消息
        System.out.println("第二个work模式："+RabbitmqConfig.WORK_QUEUE_2+ "：接收到消息：" + msg);
    }
    /*********************************************************/
    /**
     * 03 - 接收 FANOUT 发布与订阅的消息 模式的消息队列
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE_1)
    public void receiveFanoutQueue1(Message msg) {
        // 只包含发送的消息
        System.out.println("发布与订阅模式，队列1："+RabbitmqConfig.FANOUT_QUEUE_1+ "：接收到消息：" + msg);
    }

    /**
     * 03 - 接收 FANOUT 发布与订阅的消息 模式的消息队列
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE_2)
    public void receiveFanoutQueue2(Message msg) {
        // 只包含发送的消息
        System.out.println("发布与订阅模式，队列1："+RabbitmqConfig.FANOUT_QUEUE_2+ "：接收到消息：" + msg);
    }
    /*********************************************************/

    /**
     * 04- 接收 DIRECT 模式的消息队列
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.DIRECT_QUEUE_INSERT)
    public void receiveDirectQueue1(Message msg) {
        System.out.println("route 模式，insert队列："+RabbitmqConfig.DIRECT_QUEUE_INSERT+ "：接收到消息：" + msg);
    }

    /**
     * 04- 接收 DIRECT  模式的消息队列
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.DIRECT_QUEUE_UPDATE)
    public void receiveDirectQueue2(Message msg) {
        System.out.println("route 模式，update队列："+RabbitmqConfig.DIRECT_QUEUE_UPDATE+ "：接收到消息：" + msg);
        // 测试确认机制 与 listem.simple 配置有关
        // int i = 1/0;
    }
    /*********************************************************/
    /**
     * 05 - topic 模式的消息队列
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE_1)
    public void receiveTopicQueue1(Message msg) throws Exception {
        System.out.println("TOPIC 模式，item.*队列："+RabbitmqConfig.TOPIC_QUEUE_1+ "：接收到消息：" + msg);
        Thread.sleep(2000);
    }

    /**
     * 05 - topic  模式的消息队列
     * @param msg
     */
    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE_2)
    @RabbitHandler
    public void receiveTopicQueue2(String body ,Channel channel, Message msg) {
        System.out.println(body);
        System.out.println(channel.getChannelNumber());
        System.out.println("TOPIC 模式，*.delete队列："+RabbitmqConfig.TOPIC_QUEUE_2+ "：接收到消息：" + msg);
        // 测试确认机制
        // 这里如果没有手动指定消费者的监听容器 ，默认Spring为自动生成一个SimpleMessageListenerContainer
        // int i = 1/0;
    }
}

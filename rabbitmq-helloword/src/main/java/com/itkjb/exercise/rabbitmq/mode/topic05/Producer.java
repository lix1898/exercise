package com.itkjb.exercise.rabbitmq.mode.topic05;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/18 4:23 下午
 * @Description: 统配符模式 生产者
 * @Version: V1.0.0
 */
public class Producer {
    /**
     * topic 交换机
     */
    static String TOPIC_EXCHANGE = "topic_exchange";
    /**
     * 队列名称 topic queue_1
     */
    static String TOPIC_QUEUE_1 = "topic_queue_1";
    /**
     * 队列名称 topic queue_2
     */
    static final String TOPIC_QUEUE_2 = "topic_queue_2";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = connection.createChannel();
        // 申明交换机
        channel.exchangeDeclare(TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);

        // 申明队列
        channel.queueDeclare(TOPIC_QUEUE_1, true, false, false, null);
        // 申明队列
        channel.queueDeclare(TOPIC_QUEUE_2, true, false, false, null);

        //队列绑定交换机
        channel.queueBind(TOPIC_QUEUE_1, TOPIC_EXCHANGE, "item.*");
        channel.queueBind(TOPIC_QUEUE_2, TOPIC_EXCHANGE, "*.delete");

        // 发送信息
        String message = "新增了商品。Topic模式；routing key 为 item.insert " ;
        channel.basicPublish(TOPIC_EXCHANGE, "item.insert", null, message.getBytes());
        System.out.println("已发送消息：" + message);
        // 发送信息
        message = "修改了商品。Topic模式；routing key 为 item.update" ;
        channel.basicPublish(TOPIC_EXCHANGE, "item.update", null, message.getBytes());
        System.out.println("已发送消息：" + message);
        // 发送信息
        message = "删除了商品。Topic模式；routing key 为 item.delete" ;
        channel.basicPublish(TOPIC_EXCHANGE, "item.delete", null, message.getBytes());
        System.out.println("已发送消息：" + message);
        // 发送信息
        message = "删除了商品。Topic模式；routing key 为 aaa.delete" ;
        channel.basicPublish(TOPIC_EXCHANGE, "aaa.delete", null, message.getBytes());
        System.out.println("已发送消息：" + message);


        // 关闭资源
        channel.close();
        connection.close();


    }

}

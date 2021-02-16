package com.itkjb.exercise.rabbitmq.mode.ps03;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/17 3:26 下午
 * @Description: 发布与订阅模式中的第二个消费者
 * @Version: V1.0.0
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = connection.createChannel();
        // 申明交换机
        channel.exchangeDeclare(Producer.FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT);
       /*
       // 经测试，在生产者中申明绑定了 交换机和 队列的关系，在消费者中可以不需要再做绑定
       // 申明队列（创建队列）
        channel.queueDeclare(Producer.FANOUT_QUEUE_2, true, false, false, null);
        // 绑定队列
        channel.queueBind(Producer.FANOUT_QUEUE_2, Producer.FANOUT_EXCHANGE, "");
        */

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("交换机:" + envelope.getExchange());
                System.out.println("路由键:"+ envelope.getRoutingKey());
                System.out.println("消息id为：" + envelope.getDeliveryTag());
                System.out.println("消息为："+ new String(body, "utf-8"));
            }
        };
        channel.basicConsume(Producer.FANOUT_QUEUE_2,true, consumer);


    }
}

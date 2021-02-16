package com.itkjb.exercise.rabbitmq.mode.routing04;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/18 3:25 下午
 * @Description: 路由模式 消费者2
 * @Version: V1.0.0
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = connection.createChannel();
        // 声明交换机， 消费的时候和交换机已经没什么关系，可以直接制动 队列名进行消费
//        channel.exchangeDeclare(Producer.DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);

        //声明队列 以及绑定队列，可以省略
//        channel.queueDeclare(Producer.DIRECT_QUEUE_INSERT, true, false, false, null);
//        channel.queueBind(Producer.DIRECT_QUEUE_INSERT, Producer.DIRECT_EXCHANGE, "insert");

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息id:" + envelope.getDeliveryTag());
                System.out.println("交换机:" + envelope.getExchange());
                System.out.println("路由建：" + envelope.getRoutingKey());
                System.out.println("消息：" + new String(body, "utf-8"));
            }
        };

        channel.basicConsume(Producer.DIRECT_QUEUE_UPDATE, true ,consumer);

        // 不能关闭资源，关闭了资源 子线程也会被关闭，消费者属于子线程，
        // channel.close();
        // connection.close();



    }
}

package com.itkjb.exercise.rabbitmq.mode.topic05;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/18 4:41 下午
 * @Description: 通配符模式 消费者1
 * @Version: V1.0.0
 */
public class Consumer1 {
    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = connection.createChannel();
        // 申明交换机和队列省略，先运行producer ，生产者里面都申明好了，这里可以不重复申明

        DefaultConsumer consumer1 = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1=====================");
                System.out.println("消息id:" + envelope.getDeliveryTag());
                System.out.println("交换机:" + envelope.getExchange());
                System.out.println("路由建：" + envelope.getRoutingKey());
                System.out.println("消息：" + new String(body, "utf-8"));
            }


        };
        channel.basicConsume(Producer.TOPIC_QUEUE_1,true, consumer1);
    }
}

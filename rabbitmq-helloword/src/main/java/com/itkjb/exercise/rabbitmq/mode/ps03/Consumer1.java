package com.itkjb.exercise.rabbitmq.mode.ps03;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/15 9:39 上午
 * @Description: 发布与订阅模式中的第一个消费者
 * @Version: V1.0.0
 */
public class Consumer1 {
    public static void main(String[] args) throws Exception {

        //获取 连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建channel
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(Producer.FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT);
        // 经测试，在生产者中申明绑定了 交换机和 队列的关系，在消费者中可以不需要再做绑定（有个前提，是消费者先执行）
        // 声明队列（创建队列）
        // channel.queueDeclare(Producer.FANOUT_QUEUE_1, true, false, false, null);
        // 队列绑定交换机
        // channel.queueBind(Producer.FANOUT_QUEUE_1, Producer.FANOUT_EXCHANGE, "");

        // 创建消费者；并设置消息处理
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:"+ consumerTag);
                //路由key
                System.out.println("路由key为：" + envelope.getRoutingKey());
                //交换机
                System.out.println("交换机为：" + envelope.getExchange());
                //消息id
                System.out.println("消息id为：" + envelope.getDeliveryTag());
                //收到的消息
                System.out.println("消费者1-接收到的消息为：" + new String(body, "utf-8"));
            }
        };
        //监听消息
        /**
         * 参数1：队列名称
         * 参数2：是否自动确认，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动确认
         * 参数3：消息接收到后回调
         */
        channel.basicConsume(Producer.FANOUT_QUEUE_1,true, consumer);

    }
}

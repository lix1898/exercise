package com.itkjb.exercise.rabbitmq.mode.routing04;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/17 4:50 下午
 * @Description: 路由模式 中的生产者
 * @Version: V1.0.0
 */
public class Producer {
    /**
     * 交换机名称
     * Direct：定向，把消息交给符合指定routing key 的队列
     */
    static String DIRECT_EXCHANGE ="direct_exchange";
    /**
     * INSERT 队列名称
     */
    static String DIRECT_QUEUE_INSERT = "direct_queue_insert";
    /**
     * UPDATE 队列名称
     */
    static String DIRECT_QUEUE_UPDATE = "direct_queue_update";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建channel
        Channel channel = connection.createChannel();
        // 申明交换机
        /**
         * 声明交换机
         * 参数1：交换机名称
         * 参数2：交换机类型，fanout、topic、direct、headers
         */
        channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        // 申明队列
        channel.queueDeclare(DIRECT_QUEUE_INSERT, true, false, false, null);
        // 申明队列
        channel.queueDeclare(DIRECT_QUEUE_UPDATE, true, false, false, null);
        // 绑定交换机
        channel.queueBind(DIRECT_QUEUE_INSERT, DIRECT_EXCHANGE, "insert");
        // 绑定交换机
        channel.queueBind(DIRECT_QUEUE_UPDATE, DIRECT_EXCHANGE, "update");

        // 发送信息
        String message = "新增了商品。路由模式；routing key 为 insert " ;
        /**
         * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数2：路由key,简单模式可以传递队列名称
         * 参数3：消息其它属性
         * 参数4：消息内容
         */
        channel.basicPublish(DIRECT_EXCHANGE, "insert", null, message.getBytes());
        System.out.println("已发送消息：" + message);
        // 发送信息
        message = "修改了商品。路由模式；routing key 为 update" ;

        channel.basicPublish(DIRECT_EXCHANGE, "update", null, message.getBytes());
        System.out.println("已发送消息：" + message);
        // 关闭资源
        channel.close();
        connection.close();


    }

}

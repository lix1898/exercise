package com.itkjb.exercise.rabbitmq.mode.simple01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Copyright (C), 2020-2099
 *
 * @Author: lix
 * @Date: 2020/12/25 5:57 下午
 * @Description: 生产者
 * @Version: V1.0.0
 */
public class Producer {
    public static final String QUEUE_NAME="simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂，连接工厂主要用于连接的配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        // 连接虚拟主机，相当于连接数据库
        factory.setVirtualHost("/xiaoyao");
        //连接用户名
        factory.setUsername("xiaoyao");
        //连接密码
        factory.setPassword("xiaoyao");
        // 创建连接
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        // 声明（创建）队列
        /**
         * 参数1：队列名称
         * 参数2：是否定义持久化队列
         * 参数3：是否独占本次连接
         * 参数4：是否在不使用的时候自动删除队列
         * 参数5：队列其它参数
         */
        channel.queueDeclare(QUEUE_NAME,true,false, false,null);
        String msg = "沙发沙发发送到发";
        /**
         * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
         * 参数2：路由key,简单模式可以传递队列名称
         * 参数3：消息其它属性
         * 参数4：消息内容
         */
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("已发送消息：" + msg);
        // 关闭资源
        channel.close();
        connection.close();
    }
}

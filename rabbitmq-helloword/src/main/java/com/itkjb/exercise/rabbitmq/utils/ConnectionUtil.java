package com.itkjb.exercise.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Copyright (C), 2020-2099
 *
 * @Author: lix
 * @Date: 2020/12/28 4:36 下午
 * @Description: rabbitmq 连接工具类
 * @Version: V1.0.0
 */
public class ConnectionUtil {

    public static Connection getConnection() throws Exception{
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
        return factory.newConnection();
    }


}

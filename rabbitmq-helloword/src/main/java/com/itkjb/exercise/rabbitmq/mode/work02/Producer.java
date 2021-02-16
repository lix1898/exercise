package com.itkjb.exercise.rabbitmq.mode.work02;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/14 5:10 下午
 * @Description: work02 work02 模式的生产者
 * @Version: V1.0.0
 */
public class Producer {
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        /**
         * 参数1：队列名称
         * 参数2：是否定义持久化队列
         * 参数3：是否独占本次连接
         * 参数4：是否在不使用的时候自动删除队列
         * 参数5：队列其它参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for(int i=1; i<30 ;i++){
            // 发送信息
            String message = "你好；小兔子！work模式--" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息已发送"+i+"。。。");
        }
        channel.close();
        connection.close();

    }
}

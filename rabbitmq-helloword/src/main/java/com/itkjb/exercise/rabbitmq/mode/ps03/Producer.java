package com.itkjb.exercise.rabbitmq.mode.ps03;

import com.itkjb.exercise.rabbitmq.utils.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/15 9:14 上午
 * @Description: 发布与订阅模式的 生产者
 * @Version: V1.0.0
 */
public class Producer {
    /**
     * 交换机名称
     */
    static final String FANOUT_EXCHANGE = "fanout_exchange";
    /**
     * 队列名称1
     */
    static final String FANOUT_QUEUE_1 = "fanout_queue1";
    /**
     * 队列名称2
     */
    static final String FANOUT_QUEUE_2 = "fanout_queue2";


    public static void main(String[] args) throws Exception {
        // 创建连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建 channel
        Channel channel1 = connection.createChannel();
        /**
         * 声明交换机
         * 参数1：交换机名称
         * 参数2：交换机类型，fanout:广播、topic：通配符名称、direct：定向、headers
         */
        channel1.exchangeDeclare(FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT);
        // 声明（创建）队列
        channel1.queueDeclare(FANOUT_QUEUE_1, true, false, false, null);
        channel1.queueDeclare(FANOUT_QUEUE_2, true, false, false, null);

        // 队列绑定交换机
        channel1.queueBind(FANOUT_QUEUE_1, FANOUT_EXCHANGE, "");
        channel1.queueBind(FANOUT_QUEUE_2, FANOUT_EXCHANGE, "");

        // 发消息
        for(int i=0; i< 30; i++){
            // 发送信息
            String message = "你好；小兔子！发布订阅模式--" + i;
            /**
             * 参数1：交换机名称，如果没有指定则使用默认Default Exchage
             * 参数2：路由key,简单模式可以传递队列名称
             * 参数3：消息其它属性
             * 参数4：消息内容
             */
            channel1.basicPublish(FANOUT_EXCHANGE, "", null, message.getBytes());
            System.out.println("已发送消息：" + message);
        }
        // 关闭 channel
        channel1.close();
        // 关闭
        connection.close();

    }

}

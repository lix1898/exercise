package com.itkjb.exercise.rabbitmq.springboot.producer.controller;

import com.itkjb.exercise.rabbitmq.springboot.config.RabbitmqConfig;
import com.itkjb.exercise.rabbitmq.springboot.model.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/19 5:59 下午
 * @Description: 消息 生产  controller
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/rbmq/")
@Slf4j
public class MsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *  第一种模式，简单模式 SIMPLE_QUEUE 发送消息到简单队列
     * @param msg
     * @return
     */
    @GetMapping(RabbitmqConfig.SIMPLE_QUEUE)
    public Object sendToSimpleQueue(Msg msg){
        msg.setId(System.currentTimeMillis());
        msg.setName(msg.getName()+"："+RabbitmqConfig.SIMPLE_QUEUE);
        rabbitTemplate.convertAndSend(RabbitmqConfig.SIMPLE_QUEUE, msg);
        return "success";
    }

    /**
     * 第二种模式 work 模式 WORK_QUEUE_2 发送消息到work_queue_2 队列
     * @param msg
     * @return
     */
    @GetMapping(RabbitmqConfig.WORK_QUEUE_2)
    public Object sendToWorkQueue(Msg msg){
        msg.setId(System.currentTimeMillis());
        msg.setName(msg.getName()+"："+RabbitmqConfig.WORK_QUEUE_2);
        rabbitTemplate.convertAndSend(RabbitmqConfig.WORK_QUEUE_2, msg.getName());
        return "success";
    }

    /**
     * 第三种模式 Publish/Subscribe 发布与订阅模式 发送消息到 FANOUT_EXCHANGE 交换机
     * @param msg
     * @return
     */
    @GetMapping(RabbitmqConfig.FANOUT_EXCHANGE)
    public Object sendToFanoutExchange(Msg msg){
        msg.setId(System.currentTimeMillis());
        msg.setName(msg.getName()+"："+RabbitmqConfig.FANOUT_EXCHANGE);
        rabbitTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE,"",msg.getName());
        return "success";
    }


    /**
     * 第四种模式 route  发送消息到 DIRECT_EXCHANGE 交换机
     * @param msg
     * @return
     */
    @GetMapping(RabbitmqConfig.DIRECT_EXCHANGE)
    public Object sendToDirectExchange(Msg msg){
        msg.setId(System.currentTimeMillis());
        msg.setName(msg.getName()+"："+RabbitmqConfig.DIRECT_EXCHANGE);
        rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE,"insert",msg.getName()+"-insert");
        rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE,"update",msg.getName()+"-update");
        return "success";
    }
    /**
     * 第五种模式 topic 通配符模式  发送消息到 DIRECT_EXCHANGE 交换机
     * @param msg
     * @return
     */
    @GetMapping(RabbitmqConfig.TOPIC_EXCHANGE)
    public Object sendToTopicExchange(Msg msg){
        msg.setId(System.currentTimeMillis());
        msg.setName(msg.getName()+"："+RabbitmqConfig.TOPIC_EXCHANGE);
        // 发送确认模式，这里只管消息是否发送到exchange，如果发送不成功过ack为false，如果发送成功，但后面根据路由key，路由到queue是失败时，ack也是true
        /** 这里需要在yml中配置 publisher-confirm-type: correlated
         * Confirmation callback.
         * @param correlationData correlation data for the callback.
         * @param ack true for ack, false for nack
         * @param cause An optional cause, for nack, when available, otherwise null.
         */
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData,boolean ack, String cause) -> {
            System.out.println("===消费发送确认===");
            if(ack){
                System.out.println("发送成功");
            }else{
                System.out.println("发送失败");
                // 发送失败后的处理
            }
        });
        /**
         * 这里需要在yml中配置 publisher-returns: true
         * 消息路由失败回调，如果消息是发送失败，不会触及到消息回调.
         * @param message the returned message.
         * @param replyCode the reply code.
         * @param replyText the reply text.
         * @param exchange the exchange.
         * @param routingKey the routing key.
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("====消息路由失败====");
            System.out.println(routingKey);
            System.out.println(replyCode);
            System.out.println(replyText);
            // 消息存储失败，其他业务操作

        });
        rabbitTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE+"1",msg.getRoutingKey(),msg.getName()+":"+msg.getRoutingKey());
        return "success";
    }


}

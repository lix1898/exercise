package com.itkjb.exercise.rabbitmq.springboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.WebEndpoint;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/19 5:39 下午
 * @Description: rabbitmq 配置类
 * @Version: V1.0.0
 */

@SpringBootConfiguration
public class RabbitmqConfig {
    /**
     *  01-简单队列
     */
    public static final String SIMPLE_QUEUE = "simple_queue";
    /**
     * 02-work 模式队列
     */
    public static final String WORK_QUEUE_2 = "work_queue_2";
    /**
     * 03-广播 队列名称1
     */
    public static final String FANOUT_QUEUE_1 = "fanout_queue1";
    /**
     * 03-广播 队列名称2
     */
    public static final String FANOUT_QUEUE_2 = "fanout_queue2";
    /**
     * 03-广播交换机
     */
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    // routing 路由模式
    /**
     * 04-交换机名称
     * Direct：定向，把消息交给符合指定routing key 的队列
     */
    public static final String DIRECT_EXCHANGE ="direct_exchange";
    /**
     * 04-INSERT 队列名称
     */
    public static final String DIRECT_QUEUE_INSERT = "direct_queue_insert";
    /**
     * 04-UPDATE 队列名称
     */
    public static final String DIRECT_QUEUE_UPDATE = "direct_queue_update";

    // 统配 路由模式
    /**
     * 05-topic 交换机
     */
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    /**
     * 05-队列名称 topic queue_1
     */
    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    /**
     * 05-队列名称 topic queue_2
     */
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";


    /**
     * 01-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）简单队列
     * @return
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue(SIMPLE_QUEUE);
    }

    /**
     * 02-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）work 模式的队列
     * @return
     */
    @Bean
    public Queue workQueue2(){
        return new Queue(WORK_QUEUE_2);
    }

    /**
     * 03-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）FANOUT 模式的队列
     * @return
     */

    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE_1);
    }

    /**
     * 03-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）FANOUT 模式的队列
     * @return
     */
    @Bean
    public Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE_2);
    }

    /**
     * 03-申明一个虚拟机
     * @return
     */
    @Bean
    public FanoutExchange exchangeFanout() {
        // 因在 mode 包 中创建的exchange 不是持久化的，而FanoutExchange 默认创建的是持久化的，所有这里要多指定几个参数
        return new FanoutExchange(FANOUT_EXCHANGE, false, false);
    }

    /**
     * 03-交换机绑定队列
     * @return
     */
    @Bean
    public Binding bindingExchange1() {
        return BindingBuilder.bind(fanoutQueue1()).to(exchangeFanout());
    }

    /**
     * 03-交换机绑定队列
     * @return
     */
    @Bean
    public Binding bindingExchange2() {
        return BindingBuilder.bind(fanoutQueue2()).to(exchangeFanout());
    }


    /**
     * 04-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）DIRECT 模式的队列
     * @return
     */
    @Bean
    public Queue directQueueInsert(){
        return new Queue(DIRECT_QUEUE_INSERT);
    }

    /**
     * 04-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）DIRECT 模式的队列
     * @return
     */
    @Bean
    public Queue directQueueUpdate(){
        return new Queue(DIRECT_QUEUE_UPDATE);
    }

    /**
     * 04- 声明交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE, false, false);
    }

    /**
     * 04- 绑定队列和交换节
     * @return
     */
    @Bean
    public Binding bindingDirectExchange1(){
        // BindingBuilder.bind(directQueueUpdate()).to(directExchange()).with("update") 两个不能写在一起
        return  BindingBuilder.bind(directQueueInsert()).to(directExchange()).with("insert");
    }

    /**
     * 04- 绑定队列和交换节
     * @return
     */
    @Bean
    public Binding bindingDirectExchange2(){
        return  BindingBuilder.bind(directQueueUpdate()).to(directExchange()).with("update");
    }

    /****************************************************************/

    /**
     * 05-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）topic 模式的队列
     * @return
     */
    @Bean(TOPIC_QUEUE_1)
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE_1);
    }

    /**
     * 05-以下方式，程序启动时 会自动创建一个队列
     * 申明（创建）topic 模式的队列
     * @return
     */
    @Bean(TOPIC_QUEUE_2)
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE_2);
    }

    /**
     * 05- 声明交换机
     * @return
     */
    @Bean(TOPIC_EXCHANGE)
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE, false, false);
    }

    /**
     * 05- 绑定队列和交换节
     * @return
     */
    @Bean
    public Binding bindingTopocExchange1(){
        // BindingBuilder.bind(directQueueUpdate()).to(directExchange()).with("update") 两个不能写在一起
        return  BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("item.*");
    }

    /**
     * 05- 绑定队列和交换节
     * @return
     */
    @Bean
    public Binding bindingTopocExchange2(@Qualifier(TOPIC_QUEUE_2) Queue queue, @Qualifier(TOPIC_EXCHANGE) TopicExchange exchange){
        return  BindingBuilder.bind(queue).to(exchange).with("*.delete");
    }




}

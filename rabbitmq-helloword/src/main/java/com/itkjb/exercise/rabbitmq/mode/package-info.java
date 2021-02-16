/**
 * 这里主要是 rabbitmq的入门程序，主要实现了 rabbitmq的 5中工作模式<br/>
 * 官网 https://www.rabbitmq.com/getstarted.html<br/>
 * 官网上总共有7 中，最后两种（RPC 和 发布者确认），在本项目代码中暂未实现，本项目中实现了一下几种模式：
 * <p>
 *     RabbitMQ工作模式：<br/>
 *     1、简单模式 HelloWorld<br/>
 *     一个生产者、一个消费者，不需要设置交换机（使用默认的交换机）<br/>
 *
 *     2、工作队列模式 Work Queue<br/>
 *     一个生产者、多个消费者（竞争关系），不需要设置交换机（使用默认的交换机）<br/>
 *
 *     3、发布订阅模式 Publish/subscribe<br/>
 *     需要设置类型为fanout的交换机，并且交换机和队列进行绑定，当发送消息到交换机后，交换机会将消息发送到绑定的队列<br/>
 *
 *     4、路由模式 Routing<br/>
 *     需要设置类型为direct的交换机，交换机和队列进行绑定，并且指定routing key，当发送消息到交换机后，交换机会根据routing key将消息发送到对应的队列<br/>
 *
 *     5、通配符模式 Topic<br/>
 *     需要设置类型为topic的交换机，交换机和队列进行绑定，并且指定通配符方式的routing key，当发送消息到交换机后，交换机会根据routing key将消息发送到对应的队列
 *
 *     <br/> 下面两种模式，跟队列关系不大<br/>
 *
 *     6、 rpc 模式 用的不多，有空再说
 *
 *     7、发布者确认模式： 主要是为了解决发布者在消息发送失败时导致消息丢失的问题，提供的解决方案，官网写的还是比较清楚的，也比较简单
 *     <a href="https://www.rabbitmq.com/tutorials/tutorial-seven-java.html>发布者确认</a>
 *
 * <p/>
 *
 *
 */
package com.itkjb.exercise.rabbitmq.mode;

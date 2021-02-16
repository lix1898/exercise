/**
 * 该模块主要实现了通过springboot 配置和启动rabbitmq
 * <p>
 *     <a href="https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-rabbitmq">
 *     rabbitmq spring boot 官网地址
 *     </a>
 *     spring boot 官网地址 https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-rabbitmq
 * </p>
 * <p>
 *     延续mode 模式包里的代码，这里主要还是 rabbitmq的入门程序，使用 spring-boot-amqb实现了 rabbitmq的 5中工作模式
 * </p>
 * <p>
 *     config 包-> 中 什么了队列和交换机，以及指定了交换机和队列的绑定关系 <br/>
 *     producer包 ->  主要模拟消息发送  <br/>
 *     consumer包 -> 负责消息的接收  <br/>
 *     model 中 简单的封装了一个消息体 <br/>
 * </p>
 */
package com.itkjb.exercise.rabbitmq.springboot;

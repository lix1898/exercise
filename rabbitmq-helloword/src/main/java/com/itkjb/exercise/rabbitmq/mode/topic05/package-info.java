/**
 * 第五中模式 ： topic 模式 <br/>
 * Topic类型与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。只不过Topic类型Exchange可以让队列在绑定Routing key 的时候使用通配符！
 *  <br/>
 *  Routingkey 一般都是有一个或多个单词组成，多个单词之间以”.”分割，例如： item.insert
 *  <p>
 *       通配符规则：<br/>
 *       #：匹配一个或多个词<br/>
 *       *：匹配不多不少恰好1个词<br/>
 *  </p>
 *  <p>
 *      举例：<br/>
 *      item.#：能够匹配item.insert.abc 或者 item.insert<br/>
 *      item.*：只能匹配item.insert
 *  </p>
 *  <p>
 *      测试：<br/>
 *      启动所有消费者，然后使用生产者发送消息；在消费者对应的控制台可以查看到生产者发送对应routing key对应队列的消息；到达按照需要接收的效果；并且这些routing key可以使用通配符。<br/>
 *      在执行完测试代码后，其实到RabbitMQ的管理后台找到Exchanges选项卡，点击 topic_exchange 的交换机，可以查看到相应的绑定关系
 *  </p>
 *
 *
 *
 */
package com.itkjb.exercise.rabbitmq.mode.topic05;

/**
 * 第四种模式：Routing路由模式<br/>
 * <p>
 * 模式说明:<br/>
 *     路由模式特点：<br/>
 *      > 队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）<br/>
 *      > 消息的发送方在 向 Exchange发送消息时，也必须指定消息的 RoutingKey。<br/>
 *      > Exchange不再把消息交给每一个绑定的队列，而是根据消息的RoutingKey进行判断，只有队列的Routingkey与消息的 Routing key完全一致，才会接收到消息<br/>
 * </p>
 * <p>
 *     测试：<br/>
 *     启动所有消费者，然后使用生产者发送消息；在消费者对应的控制台可以查看到生产者发送对应routing key对应队列的消息；到达按照需要接收的效果。<br/>
 *     在执行完测试代码后，其实到RabbitMQ的管理后台找到Exchanges选项卡，点击 direct_exchange 的交换机，可以查看到他们的绑定关系<br/>
 * </p>
 * <p>
 *     小结:<br/>
 *     Routing模式要求队列在绑定交换机时要指定routing key，消息会转发到符合routing key的队列。
 * </p>
 *
 */
package com.itkjb.exercise.rabbitmq.mode.routing04;

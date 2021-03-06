/**
 * 第二种模式： work02 模式
 * 模式说明：Work Queues与入门程序的简单模式相比，多了一个或一些消费端，多个消费端共同消费同一个队列中的消息。
 *
 * 应用场景：对于 任务过重或任务较多情况使用工作队列可以提高任务处理的速度。
 * 该模式下主要是 一个生产者，多个消费者（轮询消费消息，非一个消息多个消费者消息），这种模式可以提高消费速度
 *
 * Tip：在一个队列中如果有多个消费者，那么消费者之间对于同一个消息的关系是竞争的关系。
 */
package com.itkjb.exercise.rabbitmq.mode.work02;

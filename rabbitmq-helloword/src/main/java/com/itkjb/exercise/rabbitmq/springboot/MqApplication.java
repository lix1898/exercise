package com.itkjb.exercise.rabbitmq.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/19 4:59 下午
 * @Description: RabbitMq Sprintboot 启动类
 * @Version: V1.0.0
 */
@SpringBootApplication(scanBasePackages = "com.itkjb.exercise.rabbitmq.springboot")
public class MqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }
}

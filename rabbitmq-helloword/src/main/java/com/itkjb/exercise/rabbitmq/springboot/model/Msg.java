package com.itkjb.exercise.rabbitmq.springboot.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/1/19 5:32 下午
 * @Description: 测试消息
 * @Version: V1.0.0
 */
@Data
@ToString
public class Msg implements Serializable {

    private static final long serialVersionUID = 1946249341732141315L;

    private Long id;
    private String name;
    private String routingKey ="item.insert";
    private String password;



}

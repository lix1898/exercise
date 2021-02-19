package com.itkjb.exercise.mongodb.dao;

import com.itkjb.exercise.mongodb.po.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/2/16 8:43 下午
 * @Description: dao
 * @Version: V1.0.0
 */
public interface CommentRepository extends MongoRepository<Comment,String> {
    // 业务层的dao 只需要定义 interface 继承 MongoRepository，由spring data mongo 动态代理调用实现类

    /**
     * 根据点赞数查询 ，仅为了测试 派生方法
     * 派生的查询，自定义查询，符合一定的规范，不需要写实现 [find/conud/delete]By[properityName] 前面是动作 +By + 属性名称
     * @param likenum
     * @return
     */
    List<Comment> findByLikenum(int likenum);
}

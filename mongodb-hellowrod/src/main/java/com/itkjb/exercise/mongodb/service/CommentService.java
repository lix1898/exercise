package com.itkjb.exercise.mongodb.service;

import com.itkjb.exercise.mongodb.dao.CommentRepository;
import com.itkjb.exercise.mongodb.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/2/16 8:50 下午
 * @Description: 评论业务层
 * @Version: V1.0.0
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //注入MongoTemplate
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存
     * @param comment
     */
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * 更新
     * @param comment
     */
    public Comment updateComment(Comment comment) {
        // 设置了comment 中的，id，会执行更新操作，否则会执行 新增操作
        return commentRepository.save(comment);
    }

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    public Comment findCommentById(String id) {
        Optional<Comment> byId = commentRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }else{
            return null;
        }
    }

    /**
     * 查询所有
     * @return
     */
    public List<Comment> findCommentList(){
        return commentRepository.findAll();
    }

    /**
     * 查询所有 分页
     * @param example
     * @param pageable
     * @return
     */
    public Page<Comment>  findCommentPage(Example<Comment> example, Pageable pageable){
        // example 查询 官方文档 https://docs.spring.io/spring-data/mongodb/docs/3.1.3/reference/html/#query-by-example
        return commentRepository.findAll(example, pageable);
    }

    /**
     * 根据 点赞数查询，仅为了测试派生的自定义方法
     * @param likenum
     * @return
     */
    public List<Comment> findBylikenum(int likenum){
        return commentRepository.findByLikenum(likenum);
    }

    /**
     * 查询  分页
     * @param context
     * @param size
     * @param page
     * @return
     */
    public Page<Comment>  findCommentPageByContext(String context, int size,int page){
        // example 查询 官方文档 https://docs.spring.io/spring-data/mongodb/docs/3.1.3/reference/html/#query-by-example
        Comment comment = new Comment();
        comment.setContent(context);
        // 这个默认情况下Example严格输入。这意味着映射查询具有包含的类型匹配，将其限制为探测可分配类型。例如，使用默认类型键（_class）时，查询具有诸如（_class : { $in : [ com.itkjb.exercise.mongodb.po.Comment] }）之类的限制。
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("content",match -> match.contains());
        // 通过使用UntypedExampleMatcher，可以绕过默认行为并跳过类型限制。因此，只要字段名称匹配，几乎任何域类型都可以用作创建引用的探针,代码如下 ：
        // UntypedExampleMatcher matcher = UntypedExampleMatcher.matching().withMatcher("content", match -> match.contains());
        Example<Comment> example = Example.of(comment,matcher);
        Pageable pageable = PageRequest.of(page, size);
        return commentRepository.findAll(example, pageable);
    }



    // 根据mongodb-template
    /**
     * 点赞数+1
     * @param id
     *
     */
    public void updateCommentLikenum(String id) { //查询对象
        //更新对象
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update();
        //局部更新，相当于$set
        // update.set(key, value)
        //递增$inc
        update.inc("likenum",1); // update.inc("likenum");
        //参数1:查询对象
        //参数2:更新对象
        // 参数3:集合的名字或实体类的类型Comment.class
        mongoTemplate.updateFirst(query,update,"comment");
    }


}

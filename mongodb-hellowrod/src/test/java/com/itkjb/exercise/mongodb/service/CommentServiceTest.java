package com.itkjb.exercise.mongodb.service;

import com.itkjb.exercise.mongodb.po.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    /**
     * 测试保存
     */
    @Test
    void testSaveComment() {
        Comment com = new Comment();
        com.setArticleid("1001");
        com.setUserid("123");
        com.setLikenum(1001);
        com.setNickname("王五");
        Comment comment = commentService.saveComment(com);
        System.out.println(comment);
    }

    /**
     * 测试更新
     */
    @Test
    void testUpdateComment() {
        Comment commentById = commentService.findCommentById("602bc326976a4f0971cce87f");
        System.out.println(commentById);
        commentById.setContent("新增内容");
        commentById.setLikenum(commentById.getLikenum()+1);
        // 这里设置了id，如果id存在则更新，如果id不存在,则新增
        // commentById.setId("0001");
        final Comment comment = commentService.saveComment(commentById);
        System.out.println(comment);
    }

    @Test
    void testFindCommentById() {
        Comment commentById = commentService.findCommentById("602bc326976a4f0971cce87f");
        System.out.println(commentById);
    }



    @Test
    void findCommentListTest() {
        List<Comment> commentList = commentService.findCommentList();
        System.out.println(commentList);
    }

    @Test
    void findCommentPageTest() {
        Comment comment = new Comment();
        // comment.setLikenum(1000);
        comment.setArticleid("1001");
        Example<Comment> example = Example.of(comment);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Comment> commentPage = commentService.findCommentPage(example, pageable);
        System.out.println(commentPage.getTotalElements());
        System.out.println(commentPage.getNumber());
        System.out.println(commentPage.getSize());
        System.out.println(commentPage.getContent());
        System.out.println(commentPage);
    }

    @Test
    void findCommentPageByContext() {
        Page<Comment> commentPage = commentService.findCommentPageByContext("内容", 3, 0);
        System.out.println(commentPage.getTotalElements());
        List<Comment> content = commentPage.getContent();
        System.out.println(content);
    }

    @Test
    void findBylikenum() {
        List<Comment> bylikenum = commentService.findBylikenum(1003);
        System.out.println(bylikenum);
    }

    @Test
    void updateCommentLikenum() {
        Comment commentById = commentService.findCommentById("1");
        System.out.println(commentById);
        commentService.updateCommentLikenum("1");
        commentById = commentService.findCommentById("1");
        System.out.println(commentById);

    }
}

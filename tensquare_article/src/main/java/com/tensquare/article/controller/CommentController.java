package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 评论
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.Ok,"提交成功");
    }

    /**
     * 根据文章ID查询评论列表
     * @param articleid
     * @return
     */
    @RequestMapping(value = "/article/{articleid}",method = RequestMethod.GET)
    public Result findByArticleid(String articleid){
        return new Result(true,StatusCode.Ok,"查询成功",commentService.findByArticleid(articleid));
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(String id){
        commentService.deleteById(id);
        return new Result(true,StatusCode.Ok,"删除成功");
    }
}

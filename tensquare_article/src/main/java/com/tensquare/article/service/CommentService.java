package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker IdWorker;

    /**
     * 评论
     * @param comment
     */
    public void add(Comment comment){
        comment.set_id(IdWorker.nextId()+"");
        commentDao.save(comment);
    }

    /**
     * 根据文章ID查询评论列表
     * @param articleid
     * @return
     */
    public List<Comment>findByArticleid(String articleid){
        return commentDao.findByArticleid(articleid);
    }

    /**
     * 删除评论
     * @param id
     */
    public void deleteById(String id){
        commentDao.deleteById(id);
    }
}

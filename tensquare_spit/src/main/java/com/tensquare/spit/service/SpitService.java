package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;
@Service
public class SpitService {

    @Autowired
    private SpitDao SpitDao;
    @Autowired
    private IdWorker IdWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询全部记录
     * @return
     */
    public List<Spit> findAll(){
        return SpitDao.findAll();
    }

    /**
     * 根据主键查询实体
     * @param id
     * @return
     */
    public Spit findById(String id){
        Spit spit = SpitDao.findById(id).get();
        return spit;
    }

    /**
     * 增加
     * @param spit
     */
    public void add (Spit spit){
        //主键值
        spit.set_id(IdWorker.nextId()+"");
        //发布日期
        spit.setPublishtime(new Date());
        //浏览量
        spit.setVisits(0);
        //分享数
        spit.setShare(0);
        //点赞数
        spit.setThumbup(0);
        //回复数
        spit.setComment(0);
        //状态
        spit.setState("1");
        //如果存在上级ID，评论
        if (spit.getParentid()!=null&&!"".equals(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        SpitDao.save(spit);
    }

    /**
     * 修改
     * @param spit
     */
    public void update(Spit spit){
        SpitDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id){
        SpitDao.deleteById(id);
    }

    /**
     * 根据上级ID查询吐槽列表
     * @param Parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String Parentid,int page,int size){
        PageRequest pageRequest = PageRequest.of(page-1,size);
        return SpitDao.findByParentid(Parentid,pageRequest);
    }

    /**
     * 点赞
     * @param id
     */
    public void updateThumbup(String id){

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("1"));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}

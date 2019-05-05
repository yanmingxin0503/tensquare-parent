package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 吐槽数据访问层
 * @author Administrator
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    /**
     * 根据上级ID查询吐槽列表（分页）
     * @param Parentid
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String Parentid, Pageable pageable);
}

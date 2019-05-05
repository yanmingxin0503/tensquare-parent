package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 标签业务逻辑
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao LabelDao;
    @Autowired
    private IdWorker IdWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findALL(){
        return LabelDao.findAll();
    }

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    public Label findById(String id){
        return LabelDao.findById(id).get();
    }

    /**
     * 增加标签
     * @param Label
     */
    public void add(Label Label){
        //设置Id
        Label.setId(IdWorker.nextId()+"");
        LabelDao.save(Label);
    }

    /**
     * 修改标签
     * @param Label
     */
    public void update(Label Label){
        LabelDao.save(Label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        LabelDao.deleteById(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Specification<Label> createSpecification(Map searchMap){
        return  new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cd) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("labelname")!=null&&!"".equals(searchMap.get("labelname"))){
                    predicateList.add(cd.like(root.get("labelname").as(String.class),"%"+(String)searchMap.get("labelname")+"%"));
                    if (searchMap.get("state")!=null&&!"".equals(searchMap.get("state"))){
                        predicateList.add(cd.equal(root.get("state").as(String.class),(String)searchMap.get("state")));
                        if (searchMap.get("recommend")!=null&&!"".equals(searchMap.get("recommend"))){
                            predicateList.add(cd.equal(root.get("recommend").as(String.class),(String)searchMap.get("recommend")));
                        }

                    }
                }
                return cd.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap){
        Specification specification = createSpecification(searchMap);
        return LabelDao.findAll(specification);
    }

    /**
     * 分页条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Label>findSearch(Map searchMap,int page,int size){
        Specification specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page-1,size);
        return LabelDao.findAll(specification,pageRequest);
    }
}

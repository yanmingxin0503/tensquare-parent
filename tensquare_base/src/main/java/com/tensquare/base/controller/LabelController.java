package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 标签控制器
 */
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询全部列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.Ok,"查询成功",labelService.findALL());
    }

    /**
     * 根据Id查询标签
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        System.out.println("No.1");
        return new Result(true,StatusCode.Ok,"查询成功",labelService.findById(id));
    }

    /**
     * 增加标签
     * @param Label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label Label){
        labelService.add(Label);
        return new Result(true,StatusCode.Ok,"增加成功");
    }

    /**
     * 修改标签
     * @param Label
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public Result update(@RequestBody Label Label,@PathVariable String id){
        Label.setId(id);
        labelService.update(Label);
        return new Result(true,StatusCode.Ok,"修改成功");
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.Ok,"删除成功");
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.Ok,"查询成功",labelService.findSearch(searchMap));
    }

    /**
     * 条件+分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page pageList = labelService.findSearch(searchMap,page,size);
        return new Result(true,StatusCode.Ok,"查询成功",new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }
}

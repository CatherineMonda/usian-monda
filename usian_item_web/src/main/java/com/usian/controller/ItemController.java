package com.usian.controller;

import com.usian.feign.ItemFeign;
import com.usian.pojo.TbItem;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("backend/item")
public class ItemController {
    @Autowired
    private ItemFeign itemFeign;


    @RequestMapping("/selectTbItemAllByPage")
    public Result selectItemInfo(@RequestParam(defaultValue = "1") String page ,
                                 @RequestParam(defaultValue = "10" )String rows){

        PageResult pageResult = itemFeign.selectTbItemAllByPage(page,rows);
        return Result.ok(pageResult);

    }

    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams){
        Integer count =itemFeign.insertTbItem(tbItem,desc,itemParams);
        if (count==3){
            return Result.ok();
        }
        return Result.error("添加失败");


    }


    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams){
        Integer count =itemFeign.updateTbItem(tbItem,desc,itemParams);
        if (count==3){
            return Result.ok();
        }
        return Result.error("修改失败");


    }

    @RequestMapping("preUpdateItem")
    public Result preUpdateItem(String itemId){
        Map<String,Object> map =itemFeign.preUpdateItem(itemId);
        if (map!=null){
            return  Result.ok(map);
        }
        return Result.error("回显查询有误");
    }

    @RequestMapping("deleteItemById")
    public Result deleteItemById(@RequestParam Long itemId){
        Integer i= itemFeign.deleteItemById(itemId);
       if (i==1){
           return Result.ok();
       }
        return Result.error("删除有误");
    }






}

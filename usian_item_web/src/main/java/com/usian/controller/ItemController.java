package com.usian.controller;

import com.usian.feign.ItemFeign;
import com.usian.pojo.TbItem;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("frontend/item")
public class ItemController {
    @Autowired
    private ItemFeign itemFeign;

    @RequestMapping("selectItemInfo")
    public Result selectItemInfo(Long itemId){
        TbItem tbItem=itemFeign.findByid(itemId);
       if (tbItem!=null){return Result.ok(tbItem);}

       return Result.error("错了错了");


    }




}

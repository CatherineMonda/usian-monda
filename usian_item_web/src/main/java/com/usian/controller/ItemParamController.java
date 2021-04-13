package com.usian.controller;

import com.usian.feign.ItemFeign;
import com.usian.pojo.TbItemParam;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backend/itemParam")
public class ItemParamController {
    @Autowired
    private ItemFeign itemFeign;

    @RequestMapping("selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable Integer itemCatId){
        TbItemParam tbItemParam=itemFeign.selectItemParamByItemCatId(itemCatId);
        return Result.ok(tbItemParam);
    }

}

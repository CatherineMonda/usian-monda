package com.usian.controller;

import com.usian.feign.ItemFeign;
import com.usian.pojo.TbItemCat;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("backend/itemCategory")
public class ItemCategoryController {
    @Autowired
    private ItemFeign itemFeign;


    @RequestMapping("selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(defaultValue = "0") Integer id){

        List<TbItemCat> list = itemFeign.selectItemCategoryByParentId(id);
        return Result.ok(list);

    }
}

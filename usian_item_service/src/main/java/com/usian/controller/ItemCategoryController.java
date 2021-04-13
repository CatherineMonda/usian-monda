package com.usian.controller;

import com.usian.pojo.TbItemCat;
import com.usian.service.ItemCarServaice;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("service/itemCategory")
public class ItemCategoryController {
    @Autowired
    private ItemCarServaice itemCarServaice;


    @RequestMapping("/selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(Integer id){

        return itemCarServaice.selectItemCategoryByParentId(id);


    }
}

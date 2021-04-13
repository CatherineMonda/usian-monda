package com.usian.controller;

import com.usian.pojo.TbItemParam;
import com.usian.service.ItemparamService;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service/itemparam")
public class ItemparamController {
    @Autowired
    private ItemparamService itemparamService;

    @RequestMapping("selectItemParamByItemCatId/{itemCatId}")
    public TbItemParam selectItemParamByItemCatId(@PathVariable Long itemCatId){
        return  itemparamService.selectItemParamByItemCatId(itemCatId);

    }
}

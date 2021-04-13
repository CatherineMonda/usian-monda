package com.usian.controller;

import com.usian.pojo.TbItem;
import com.usian.service.ItemService;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("service/item")
public class ItemController {
@Autowired
private ItemService itemService;


    @RequestMapping("selectTbItemAllByPage")
  public   PageResult selectTbItemAllByPage(String page,String rows){
       return itemService.selectTbItemAllByPage(page,rows);
    }


    @RequestMapping("insertTbItem")
   public Integer insertTbItem(@RequestBody TbItem tbItem,
                               @RequestParam String desc,
                               @RequestParam String itemParams){
      return   itemService.insert(tbItem,desc,itemParams);
    }

    @RequestMapping("updateTbItem")
   public Integer updateTbItem(@RequestBody TbItem tbItem,
                               @RequestParam String desc,
                               @RequestParam String itemParams){
      return   itemService.updateTbItem(tbItem,desc,itemParams);
    }

    @RequestMapping("preUpdateItem")
    public Map<String,Object> preUpdateItem(@RequestParam String itemId){
        return itemService.preUpdateItem(Long.parseLong(itemId));
    }

    @RequestMapping("deleteItemById")
   public Integer deleteItemById( Long itemId){
        return itemService.deleteItemById(itemId);
    }
}

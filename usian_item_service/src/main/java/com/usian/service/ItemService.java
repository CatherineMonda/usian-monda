package com.usian.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usian.mapper.*;
import com.usian.pojo.*;
import com.usian.utils.IDUtils;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;



    public PageResult selectTbItemAllByPage(String page,String rows) {
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(rows));
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("updated desc");
        criteria.andStatusEqualTo((byte)1);
        List<TbItem> pages =tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo=new PageInfo<>();
        pageInfo.setList(pages);

        PageResult pageResult=new PageResult();
        pageResult.setTotalPage(pageInfo.getTotal());
        pageResult.setResult(pages);
        pageResult.setPageIndex(1);
        return pageResult;
    }




    public  Integer insert( TbItem tbItem,String desc,String itemParams){
        Date date = new Date();
        Long id= IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        tbItem.setStatus((byte)1);
        int insert = tbItemMapper.insert(tbItem);
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int insert1 = tbItemDescMapper.insert(tbItemDesc);
        TbItemParamItem tbItemParam=new TbItemParamItem();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        tbItemParam.setItemId(id);
        tbItemParam.setParamData(itemParams);
        int insert2 = tbItemParamItemMapper.insert(tbItemParam);
        return insert+insert1+insert2;
    }

    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String,Object> map=new HashMap<>();
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item",tbItem);
        TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(tbItem.getCid());
        map.put("itemCat",tbItemCat.getId());
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc",tbItemDesc.getItemDesc());
        TbItemParamItemExample example1 =new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andItemIdEqualTo(tbItem.getId());
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(example1);
        if (tbItemParamItems!=null){
            map.put("itemParamItem",tbItemParamItems.get(0).getParamData());
        }

//        {“itemCat”:”xxxx”,”item”:{xxxx},”itemDesc”:”xxxx”,”itemParamItem”:”xxxxx”}



        return map;
    }

    public Integer updateTbItem(TbItem tbItem, String desc, String itemParams) {

        Date date = new Date();
        tbItem.setUpdated(date);
        int i = tbItemMapper.updateByPrimaryKeySelective(tbItem);
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(date);
        int i1 = tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
        TbItemParamItem tbItemParam=new TbItemParamItem();
        tbItemParam.setUpdated(date);
        tbItemParam.setItemId(tbItem.getId());
        tbItemParam.setParamData(itemParams);
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(tbItem.getId());
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExample(example);
        if (tbItemParamItems!=null){
            tbItemParam.setId(tbItemParamItems.get(0).getId());
        }
        int i2 = tbItemParamItemMapper.updateByPrimaryKeySelective(tbItemParam);
        return i+i1+i2;
    }

    public Integer deleteItemById(Long itemId) {
        TbItem  tbItem=new TbItem();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)3);
        int i = tbItemMapper.updateByPrimaryKeySelective(tbItem);
        return i;

    }
}

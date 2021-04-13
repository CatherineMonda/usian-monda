package com.usian.service;

import com.usian.mapper.TbItemCatMapper;
import com.usian.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarServaice {
@Autowired
private TbItemCatMapper tbItemCatMapper;

    public List<TbItemCat> selectItemCategoryByParentId(Integer id) {

        return  tbItemCatMapper.findAllbypid(id);

    }
}

package com.icloud.dao.business;

import java.util.List;

import com.icloud.model.business.BeanGoods;
import com.icloud.model.business.BeanGoodsWithBLOBs;

public interface BeanGoodsMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(BeanGoodsWithBLOBs record);

    int insertSelective(BeanGoodsWithBLOBs record);

    BeanGoodsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BeanGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BeanGoodsWithBLOBs record);

    public List<BeanGoods> findForList(BeanGoods t);
    
    public int updateGoodsStore(BeanGoods t);
}
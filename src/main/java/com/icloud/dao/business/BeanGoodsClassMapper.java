package com.icloud.dao.business;

import java.util.List;

import com.icloud.model.business.BeanCouponConsumeRecord;
import com.icloud.model.business.BeanGoodsClass;

public interface BeanGoodsClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BeanGoodsClass record);

    int insertSelective(BeanGoodsClass record);

    BeanGoodsClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BeanGoodsClass record);

    int updateByPrimaryKey(BeanGoodsClass record);
    
    public List<BeanGoodsClass> findForList(BeanGoodsClass record);
}
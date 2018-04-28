package com.icloud.dao.business;

import java.util.List;

import com.icloud.model.business.BeanCouponConsumeRecord;

public interface BeanCouponConsumeRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BeanCouponConsumeRecord record);

    int insertSelective(BeanCouponConsumeRecord record);

    BeanCouponConsumeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BeanCouponConsumeRecord record);

    int updateByPrimaryKey(BeanCouponConsumeRecord record);
    

    public List<BeanCouponConsumeRecord> findForList(BeanCouponConsumeRecord record);
}
package com.icloud.dao.business;

import java.util.List;

import com.icloud.model.business.BeanAdvert;

public interface BeanAdvertMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BeanAdvert record);

    int insertSelective(BeanAdvert record);

    BeanAdvert selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BeanAdvert record);

    int updateByPrimaryKey(BeanAdvert record);
    

    public List<BeanAdvert> findForList(BeanAdvert record);
}
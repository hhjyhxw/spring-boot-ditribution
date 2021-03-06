package com.icloud.dao.business;

import java.util.List;

import com.icloud.model.business.BeanOrderItem;

public interface BeanOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BeanOrderItem record);

    int insertSelective(BeanOrderItem record);

    BeanOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BeanOrderItem record);

    int updateByPrimaryKey(BeanOrderItem record);

	List<BeanOrderItem> findListByOrderId(Long id);

	List<BeanOrderItem> findList(BeanOrderItem t);
	
	List<BeanOrderItem> getOrderItemListByOrderId(Long orderId);
	
}
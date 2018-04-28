package com.icloud.dao.business;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.icloud.model.business.BeanAddress;

public interface BeanAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BeanAddress record);

    int insertSelective(BeanAddress record);

    BeanAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BeanAddress record);

    int updateByPrimaryKey(BeanAddress record);


	List<BeanAddress> findList(BeanAddress t);
	
	int updateByUserId(Long userId);

	//Integer findCount(BeanAddress t);

	//PageInfo<BeanAddress> findByPage(int pageNo, int pageSize, BeanAddress t);
}
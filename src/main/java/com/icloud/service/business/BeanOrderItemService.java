package com.icloud.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.business.BeanOrderItemMapper;
import com.icloud.model.business.BeanOrderItem;

@Service
public class BeanOrderItemService {

	@Autowired
	private BeanOrderItemMapper beanOrderItemMapper;
	
	@Transactional
	public void save(BeanOrderItem t){
		// TODO Auto-generated method stub
		beanOrderItemMapper.insert(t);
	}

	 
	public void update(BeanOrderItem t){
		// TODO Auto-generated method stub
		beanOrderItemMapper.updateByPrimaryKey(t);
	}

	 
	public List<BeanOrderItem> findList(BeanOrderItem t){
		List<BeanOrderItem> list = beanOrderItemMapper.findList(t);
		return list;
	}

	 
	public Integer findCount(BeanOrderItem t){
		// TODO Auto-generated method stub
		return null;
	}

	 
	public void delete(Long id){
		// TODO Auto-generated method stub
		
	}

	 
	public BeanOrderItem findByKey(Long id){
		// TODO Auto-generated method stub
		return null;
	}

	 
	public PageInfo<BeanOrderItem> findByPage(int pageNo, int pageSize,
			BeanOrderItem t){
		// TODO Auto-generated method stub
		return null;
	}

	public List<BeanOrderItem> findListByOrderId(Long id) {
		List<BeanOrderItem> list = beanOrderItemMapper.findListByOrderId(id);
		return list;
	}

}

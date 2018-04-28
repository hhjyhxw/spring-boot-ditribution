package com.icloud.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.business.BeanAddressMapper;
import com.icloud.model.business.BeanAddress;
import com.icloud.service.BaseService;

@Service
public class BeanAddressService implements BaseService<BeanAddress>{

	@Autowired
	private BeanAddressMapper beanAddressMapper;
	
	@Override
	public void save(BeanAddress t) {
		beanAddressMapper.insert(t);
	}

	@Override
	public void update(BeanAddress t) {
		beanAddressMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<BeanAddress> findList(BeanAddress t) throws Exception {
		return beanAddressMapper.findList(t);
	}

	@Override
	public Integer findCount(BeanAddress t) throws Exception {
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
		beanAddressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public BeanAddress findByKey(Long id) {
		return beanAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<BeanAddress> findByPage(int pageNo, int pageSize,
			BeanAddress t) throws Exception {
		return null;
	}
	
	public int updateByUserId(Long userId){
		return beanAddressMapper.updateByUserId(userId);
	}

	/**
	* Description:更新默认地址为零，并设置新的默认地址
	* @author zhangdehai
	* @param beanAddress  
	* @date 2018年3月8日上午10:57:03
	 */
	@Transactional
	public void saveAndUpdate(BeanAddress beanAddress){
		int rusult = updateByUserId(beanAddress.getUserId());
		if(beanAddress.getId()!=null){
			update(beanAddress);
		}else{
			save(beanAddress);
		}
	}
}

package com.icloud.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.business.BeanFansMapper;
import com.icloud.model.business.BeanFans;
import com.icloud.service.BaseService;

@Service
public class BeanFansService implements BaseService<BeanFans>{

	@Autowired
	private BeanFansMapper beanFansMapper;
	@Override 
	public void save(BeanFans t) throws Exception {
		// TODO Auto-generated method stub
		beanFansMapper.insert(t);
	}

	@Override
	public void update(BeanFans t) throws Exception {
		// TODO Auto-generated method stub
		beanFansMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<BeanFans> findList(BeanFans t) throws Exception {
		// TODO Auto-generated method stub
		return beanFansMapper.findForList(t);
	}

	@Override
	public Integer findCount(BeanFans t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
		beanFansMapper.deleteByPrimaryKey(id);
	}

	@Override
	public BeanFans findByKey(Long id) throws Exception {
		// TODO Auto-generated method stub
		return beanFansMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<BeanFans> findByPage(int pageNo, int pageSize, BeanFans t)
			throws Exception {
		 PageHelper.startPage(pageNo, pageSize);
		 PageInfo<BeanFans> page = new PageInfo<BeanFans>(beanFansMapper.findForList(t));
		 return page;
	}
	
}

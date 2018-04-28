package com.icloud.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.business.BeanAdvertMapper;
import com.icloud.model.business.BeanAdvert;
import com.icloud.service.BaseService;

@Service
public class BeanAdvertService implements BaseService<BeanAdvert>{

	@Autowired
	private BeanAdvertMapper beanAdvertMapper;

	@Override
	public void save(BeanAdvert t) throws Exception {
		// TODO Auto-generated method stub
		beanAdvertMapper.insert(t);
	}

	@Override
	public void update(BeanAdvert t) throws Exception {
		// TODO Auto-generated method stub
		beanAdvertMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<BeanAdvert> findList(BeanAdvert t) throws Exception {
		// TODO Auto-generated method stub
		return beanAdvertMapper.findForList(t);
	}

	@Override
	public Integer findCount(BeanAdvert t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		beanAdvertMapper.deleteByPrimaryKey(id);
	}

	@Override
	public BeanAdvert findByKey(Long id) throws Exception {
		// TODO Auto-generated method stub
		return beanAdvertMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public PageInfo<BeanAdvert> findByPage(int pageNo, int pageSize,
			BeanAdvert t) throws Exception {
		// TODO Auto-generated method stub
		 PageHelper.startPage(pageNo, pageSize);
		 PageInfo<BeanAdvert> page = new PageInfo<BeanAdvert>(beanAdvertMapper.findForList(t));
		 return page;
	}
	
	

}

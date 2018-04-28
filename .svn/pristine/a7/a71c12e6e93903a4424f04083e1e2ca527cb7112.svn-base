package com.icloud.service.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.icloud.dao.business.BeanAreaMapper;
import com.icloud.model.business.BeanArea;
import com.icloud.service.BaseService;

@Service
public class BeanAreaService implements BaseService<BeanArea>{

	@Autowired
	private BeanAreaMapper beanAreaMapper;
	
	@Override
	public void save(BeanArea t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BeanArea t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Cacheable(value="areas")
	@Override
	public List<BeanArea> findList(BeanArea t) throws Exception {
		return beanAreaMapper.findList(t);
	}

	@Override
	public Integer findCount(BeanArea t) throws Exception {
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
	}

	@Override
	public BeanArea findByKey(Long id) throws Exception {
		return null;
	}

	@Override
	public PageInfo<BeanArea> findByPage(int pageNo, int pageSize, BeanArea t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}

package com.icloud.service.bms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.bms.BmsAdminMapper;
import com.icloud.model.bms.BmsAdmin;
@Service
public class AdminService{

	@Autowired
	private BmsAdminMapper bmsAdminMapper;
	
	public BmsAdmin login(BmsAdmin admin) throws Exception {
		// TODO Auto-generated method stub
		List<BmsAdmin> list = bmsAdminMapper.findForList(admin);
		if(null!=list&&0<list.size()){
			return list.get(0);
		}
		return null;
		
	}

	public int selectCountByAccount(BmsAdmin admin) {
		// TODO Auto-generated method stub
		return bmsAdminMapper.findCountByAccount(admin);
	}

	public void save(BmsAdmin admin) throws Exception {
		// TODO Auto-generated method stub
		bmsAdminMapper.insert(admin);
	}

	public void update(BmsAdmin admin) throws Exception {
		// TODO Auto-generated method stub
		bmsAdminMapper.updateByPrimaryKey(admin);
	}

	public List<BmsAdmin> findList(BmsAdmin t) throws Exception {
		// TODO Auto-generated method stub
		return bmsAdminMapper.findForList(t);
	}


	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		bmsAdminMapper.deleteByPrimaryKey(id);
	}

	public BmsAdmin findByKey(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return bmsAdminMapper.selectByPrimaryKey(id);
	}

	public PageInfo<BmsAdmin> findByPage(int pageNo, int pageSize,BmsAdmin admin) throws Exception {
		// TODO Auto-generated method stub
		 PageHelper.startPage(pageNo, pageSize);
		 PageInfo<BmsAdmin> page = new PageInfo<BmsAdmin>(bmsAdminMapper.findForList(admin));
		 return page;
	}

	

}

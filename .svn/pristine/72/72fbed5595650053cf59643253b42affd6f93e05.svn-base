package com.icloud.service.bms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.dao.bms.BmsMenuMapper;
import com.icloud.model.bms.BmsAdmin;
import com.icloud.model.bms.BmsMenu;

@Service
public class MenuService{

	@Autowired
	private BmsMenuMapper bmsMenuMapper;
	public List<BmsMenu> selectMenuByUser(BmsAdmin admin) {
		// TODO Auto-generated method stub
		return bmsMenuMapper.selectMenuByUser(admin);
	}
	
	public List<BmsMenu> selectParentMenu() {
		// TODO Auto-generated method stub
		return bmsMenuMapper.selectParentMenu();
	}
	
	public int insertSelective(BmsMenu record) {
		// TODO Auto-generated method stub
		return bmsMenuMapper.insertSelective(record);
	}
	
	public int selectCountByName(String menuName) {
		// TODO Auto-generated method stub
		return bmsMenuMapper.selectCountByName(menuName);
	}
	public List<BmsMenu> selectAllList() {
		// TODO Auto-generated method stub
		return bmsMenuMapper.selectAllList();
	}
	
	public int countByParent(Integer id) {
		// TODO Auto-generated method stub
		return bmsMenuMapper.countByParent(id);
	}
	
	public List<BmsMenu> selectByRole(Integer roleId) {
		// TODO Auto-generated method stub
		return bmsMenuMapper.selectByRole(roleId);
	}

	public void save(BmsMenu t) throws Exception {
		// TODO Auto-generated method stub
		bmsMenuMapper.insert(t);
		
	}

	public void update(BmsMenu t) throws Exception {
		// TODO Auto-generated method stub
		bmsMenuMapper.updateByPrimaryKeySelective(t);
	}

	public List<BmsMenu> findList(BmsMenu t) throws Exception {
		// TODO Auto-generated method stub
		return bmsMenuMapper.findForList(t);
	}


	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		bmsMenuMapper.deleteByPrimaryKey(id);
	}

	public BmsMenu findByKey(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return bmsMenuMapper.selectByPrimaryKey(id);
	}

	public PageInfo<BmsMenu> findByPage(int pageNo, int pageSize, BmsMenu t)
			throws Exception {
		 PageHelper.startPage(pageNo, pageSize);
		 PageInfo<BmsMenu> page = new PageInfo<BmsMenu>(bmsMenuMapper.findForList(t));
		return page;
	}

	

}

package com.icloud.service.bms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.bms.BmsRoleMenuMapper;
import com.icloud.model.bms.BmsRoleMenu;

@Service
public class RoleMenuService{

	@Autowired
	private BmsRoleMenuMapper bmsRoleMenuMapper;
	
	public int insert(BmsRoleMenu record) {
		// TODO Auto-generated method stub
		return bmsRoleMenuMapper.insert(record);
	}

	public int insertSelective(BmsRoleMenu record) {
		// TODO Auto-generated method stub
		return bmsRoleMenuMapper.insert(record);
	}

	public void delete(BmsRoleMenu record) {
		// TODO Auto-generated method stub
		bmsRoleMenuMapper.delete(record);
	}

	public void batchInsert(List<BmsRoleMenu> list) {
		bmsRoleMenuMapper.batchInsert(list);
	}

}

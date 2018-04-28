package com.icloud.service.bms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icloud.dao.bms.BmsAdminRoleMapper;
import com.icloud.model.bms.BmsAdminRole;

@Service
public class AdminRoleService{

	@Autowired
	private BmsAdminRoleMapper bmsAdminRoleMapper;
	public int insert(BmsAdminRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(BmsAdminRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delele(Integer adminId) {
		// TODO Auto-generated method stub
		bmsAdminRoleMapper.deleleByAdmin(adminId);
	}

	public void batchInsert(List<BmsAdminRole> list) {
		// TODO Auto-generated method stub
		bmsAdminRoleMapper.batchInsert(list);
	}

}

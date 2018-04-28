package com.icloud.dao.bms;

import java.util.List;

import com.icloud.dao.BaseDao;
import com.icloud.model.bms.BmsRole;

public interface BmsRoleMapper extends BaseDao<BmsRole>{
    int deleteByPrimaryKey(Integer id);

    int insert(BmsRole record);

    int insertSelective(BmsRole record);

    BmsRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BmsRole record);

    int updateByPrimaryKey(BmsRole record);
    
    
    List<BmsRole> selectByAdmin(Integer adminId);
    
    List<BmsRole> selectAll();
    
    int selectCountByName(String roleName);
}
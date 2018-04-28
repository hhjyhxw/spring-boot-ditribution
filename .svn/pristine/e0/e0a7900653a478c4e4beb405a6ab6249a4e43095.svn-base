package com.icloud.dao.bms;

import java.util.List;

import com.icloud.dao.BaseDao;
import com.icloud.model.bms.BmsAdmin;

public interface BmsAdminMapper extends BaseDao<BmsAdmin>{
    int deleteByPrimaryKey(Integer id);

    int insert(BmsAdmin record);

    int insertSelective(BmsAdmin record);

    BmsAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BmsAdmin record);

    int updateByPrimaryKey(BmsAdmin record);
    
    int deleteByPrimaryKey(Long id);

    public List<BmsAdmin> findForConditions(BmsAdmin record);
    
    Integer findCountByAccount(BmsAdmin record);
}
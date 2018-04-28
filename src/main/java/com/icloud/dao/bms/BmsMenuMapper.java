package com.icloud.dao.bms;

import java.util.List;

import com.icloud.dao.BaseDao;
import com.icloud.model.bms.BmsAdmin;
import com.icloud.model.bms.BmsMenu;

public interface BmsMenuMapper extends BaseDao<BmsMenu>{
    int deleteByPrimaryKey(Integer id);

    int insert(BmsMenu record);

    int insertSelective(BmsMenu record);

    BmsMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BmsMenu record);

    int updateByPrimaryKey(BmsMenu record);
    
    List<BmsMenu> selectMenuByUser(BmsAdmin admin);

    List<BmsMenu> selectParentMenu();
    
    List<BmsMenu> selectAllList();
    
    List<BmsMenu> selectByRole(Integer roleId);
    
    int selectCountByName(String menuName);
    
    int countByParent(Integer parentId);
    
    
    
    
}
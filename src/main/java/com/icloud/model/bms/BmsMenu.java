package com.icloud.model.bms;

import java.io.Serializable;

import com.icloud.model.BaseModel;

public class BmsMenu extends BaseModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String menuName;

    private String menuUrl;

    private Integer parentId;

    private Integer sortNum;

    private String status;
    

    private BmsMenu parent;
    
    private boolean isHas;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public BmsMenu getParent() {
		return parent;
	}

	public void setParent(BmsMenu parent) {
		this.parent = parent;
	}

	public boolean getIsHas() {
		return isHas;
	}

	public void setIsHas(boolean isHas) {
		this.isHas = isHas;
	}
    
    
}
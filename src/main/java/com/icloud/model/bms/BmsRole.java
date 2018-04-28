package com.icloud.model.bms;

import java.io.Serializable;

import com.icloud.model.BaseModel;

public class BmsRole extends BaseModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String roleName;

    private String status;
    
    private boolean isHas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public boolean getIsHas() {
		return isHas;
	}

	public void setIsHas(boolean isHas) {
		this.isHas = isHas;
	}
    
}
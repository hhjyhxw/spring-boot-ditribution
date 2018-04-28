package com.icloud.model.bms;

import java.io.Serializable;
import java.util.Date;

import com.icloud.model.BaseModel;

public class BmsAdmin extends BaseModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String account;

    private String passwordPrompt;

    private String passWord;

    private String isLock;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPasswordPrompt() {
        return passwordPrompt;
    }

    public void setPasswordPrompt(String passwordPrompt) {
        this.passwordPrompt = passwordPrompt == null ? null : passwordPrompt.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
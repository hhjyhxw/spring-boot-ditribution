package com.icloud.model.business;

import java.io.Serializable;

public class BeanGoodsWithBLOBs extends BeanGoods implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String goodDetail;

    private String goodDesc;

    public String getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(String goodDetail) {
        this.goodDetail = goodDetail == null ? null : goodDetail.trim();
    }

    public String getGoodDesc() {
        return goodDesc;
    }

    public void setGoodDesc(String goodDesc) {
        this.goodDesc = goodDesc == null ? null : goodDesc.trim();
    }
}
package com.icloud.model.business;

import java.io.Serializable;

public class BeanOrderItem implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7668750618894316095L;

	private Long id;//订单明细id

    private Long orderId;//订单id

    private Integer goodId;//商品id

    private String goodName;//商品名称

    private Integer score;//兑换积分

    private Integer coupon;//优惠券值

    private String goodImage;//商品图片

    private Integer goodQuantities;//商品数量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage == null ? null : goodImage.trim();
    }

    public Integer getGoodQuantities() {
        return goodQuantities;
    }

    public void setGoodQuantities(Integer goodQuantities) {
        this.goodQuantities = goodQuantities;
    }
}
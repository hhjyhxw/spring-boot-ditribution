/**
 * @author
 * @version
 * 2018年3月7日 下午4:25:40
 */
package com.icloud.vo;

import java.util.Date;


/**
 * 类名称: GoodsVo
 * 类描述: 
 * 创建人: zhangdehai
 * 创建时间:2018年3月7日 下午4:25:40
 */
public class OrderVo {

	private Long id;//id
	private String goodImage;//图片
	private String name;//商品名称
	private Integer score;//需要乐豆数量
	private Integer coupon;//代金券值
	private String shippingStatus;//
	private String createTime;//
	private Integer totalScore;//总积分

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoodImage() {
		return goodImage;
	}
	public void setGoodImage(String goodImage) {
		this.goodImage = goodImage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}

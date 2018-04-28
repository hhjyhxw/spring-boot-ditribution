/**
 * @author
 * @version
 * 2018年3月7日 下午4:25:40
 */
package com.icloud.vo;

import com.icloud.model.business.BeanFans;

/**
 * 类名称: GoodsVo
 * 类描述: 
 * 创建人: zhangdehai
 * 创建时间:2018年3月7日 下午4:25:40
 */
public class GoodsVo {

	private Integer id;//id
	private String goodImage;//图片
	private String name;//名称
	private Integer score;//需要乐豆数量
	

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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

}

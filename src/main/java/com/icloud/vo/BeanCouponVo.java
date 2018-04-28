/**
 * @author
 * @version
 * 2018年2月27日 下午4:52:58
 */
package com.icloud.vo;

/**
 * 类名称: BeanCouponVo
 * 类描述: 紫光优惠券vo
 * 创建人: zhangdehai
 * 创建时间:2018年2月27日 下午4:52:58
 */
public class BeanCouponVo {
	
	private String ticket_id;//抵价券的唯一ID
	private String value;//抵价券的面值
	private String name;//抵价券名称
//	private String validDate;//有效日期
//	private String status;//使用状态
	private String addtime;//添加时间
	private String vali;//有效日期
	private String stat;//使用状态 0未使用，1已使用 ，
	public String getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getVali() {
		return vali;
	}
	public void setVali(String vali) {
		this.vali = vali;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
}

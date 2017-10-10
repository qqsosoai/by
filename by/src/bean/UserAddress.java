package bean;

import java.io.Serializable;
import java.util.Date;

public class UserAddress implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6942584440911575021L;
	private Integer id;//主键id
	private Integer userId;//用户主键
	private String collect;//收货人
	private String phone;//收货手机号
	private Address province;//省编号(外键)
	private Address city;//城市编号(外键)
	private Address zone;//区县编号(外键)
	private String detail;//详细地址
	private Date createTime;//创建时间
	private Integer isDefault;//是否是默认地址（1:是 0否）
	private String remark;//备注
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCollect() {
		return collect;
	}
	public void setCollect(String collect) {
		this.collect = collect;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getProvince() {
		return province;
	}
	public void setProvince(Address province) {
		this.province = province;
	}
	public Address getCity() {
		return city;
	}
	public void setCity(Address city) {
		this.city = city;
	}
	public Address getZone() {
		return zone;
	}
	public void setZone(Address zone) {
		this.zone = zone;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public UserAddress(Integer id, Integer userId, String collect,
			String phone, Address province, Address city, Address zone,
			String detail, Date createTime, Integer isDefault, String remark) {
		super();
		this.id = id;
		this.userId = userId;
		this.collect = collect;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.zone = zone;
		this.detail = detail;
		this.createTime = createTime;
		this.isDefault = isDefault;
		this.remark = remark;
	}
	
	public UserAddress(Integer id, Integer userId, String collect,
			String phone, String detail, Date createTime, Integer isDefault,
			String remark) {
		super();
		this.id = id;
		this.userId = userId;
		this.collect = collect;
		this.phone = phone;
		this.detail = detail;
		this.createTime = createTime;
		this.isDefault = isDefault;
		this.remark = remark;
	}
	public UserAddress(Integer id) {
		super();
		this.id = id;
	}
	public UserAddress() {
		super();
	}
	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", userId=" + userId + ", collect="
				+ collect + ", phone=" + phone + ", province=" + province
				+ ", city=" + city + ", zone=" + zone + ", detail=" + detail
				+ ", createTime=" + createTime + ", isDefault=" + isDefault
				+ ", remark=" + remark + "]";
	}
}

package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户账单表
 * @author hasee
 *
 */
public class Order implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 748717548734757935L;
	private Integer id;//主键
	private Integer userId;//用户主键
	private Integer userAddress;//用户地址(外键)
	private Date createTime;//创建时间
	private BigDecimal cost;//总消费
	private Integer status;//订单状态(1:未付款,2:未发货,3:未收货,4:未评价,5:交易成功)
	private String serialNumber;//订单号
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
	public Integer getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(Integer userAddress) {
		this.userAddress = userAddress;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Order(Integer id, Integer userId, Integer userAddress,
			Date createTime, BigDecimal cost, Integer status,
			String serialNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.userAddress = userAddress;
		this.createTime = createTime;
		this.cost = cost;
		this.status = status;
		this.serialNumber = serialNumber;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", userAddress="
				+ userAddress + ", createTime=" + createTime + ", cost=" + cost
				+ ", status=" + status + ", serialNumber=" + serialNumber + "]";
	}
	
}

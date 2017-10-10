package bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户资金账单表
 * @author hasee
 *
 */
public class Consume implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 597620488264734784L;
	private Integer id;//资金详单ID
	private Integer userId;//用户ID(外键)
	private Integer type;//详单类型:1充值,2:提现
	private Integer bankId;//银行ID
	private Integer money;//操作金额
	private Integer statu;//状态,1:交易中,2:交易失败
	private Date createDatedate;//创建时间
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	public Date getCreateDatedate() {
		return createDatedate;
	}
	public void setCreateDatedate(Date createDatedate) {
		this.createDatedate = createDatedate;
	}
	public Consume(Integer id, Integer userId, Integer type, Integer bankId,
			Integer money, Integer statu, Date createDatedate) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.bankId = bankId;
		this.money = money;
		this.statu = statu;
		this.createDatedate = createDatedate;
	}
	public Consume() {
		super();
	}
	@Override
	public String toString() {
		return "Consume [id=" + id + ", userId=" + userId + ", type=" + type
				+ ", bankId=" + bankId + ", money=" + money + ", statu="
				+ statu + ", createDatedate=" + createDatedate + "]";
	}
	
	
}

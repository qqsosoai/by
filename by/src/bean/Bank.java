package bean;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 银行账目表
 * @author hasee
 *
 */
public class Bank implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7690979350118104097L;
	private Integer bankId;//银行ID(主键)
	private Integer bankCode;//银行账号
	private Integer bankName;//银行名称
	private String openPerson;//开户人
	private String openPersonId;//身份证号
	private BigDecimal balance;//账户余额
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public Integer getBankCode() {
		return bankCode;
	}
	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}
	public Integer getBankName() {
		return bankName;
	}
	public void setBankName(Integer bankName) {
		this.bankName = bankName;
	}
	public String getOpenPerson() {
		return openPerson;
	}
	public void setOpenPerson(String openPerson) {
		this.openPerson = openPerson;
	}
	public String getOpenPersonId() {
		return openPersonId;
	}
	public void setOpenPersonId(String openPersonId) {
		this.openPersonId = openPersonId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Bank(Integer bankId, Integer bankCode, Integer bankName,
			String openPerson, String openPersonId, BigDecimal balance) {
		super();
		this.bankId = bankId;
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.openPerson = openPerson;
		this.openPersonId = openPersonId;
		this.balance = balance;
	}
	public Bank() {
		super();
	}
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", bankCode=" + bankCode
				+ ", bankName=" + bankName + ", openPerson=" + openPerson
				+ ", openPersonId=" + openPersonId + ", balance=" + balance
				+ "]";
	}
	
}

package bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5454783543454966151L;
	private Integer id;//主键
	private String loginName;//登录名
	private String userName;//用户名
	private String password;//密码
	private Integer balance;//账户余额
	private Integer sex;//性别(1:男 0：女)
	private String identityCode;//身份证号
	private String email;//邮箱
	private String mobile;//手机
	private Integer type;//类型（1：管理员 0:普通用户）
	private Date createDate;//创建时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User(Integer id, String loginName, String userName, String password,
			Integer balance, Integer sex, String identityCode, String email,
			String mobile, Integer type, Date createDate) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.balance = balance;
		this.sex = sex;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
		this.createDate = createDate;
	}
	
	public User(String loginName, String userName, String password,
			Integer balance, Integer sex, String identityCode, String email,
			String mobile, Integer type, Date createDate) {
		super();
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.balance = balance;
		this.sex = sex;
		this.identityCode = identityCode;
		this.email = email;
		this.mobile = mobile;
		this.type = type;
		this.createDate = createDate;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", userName="
				+ userName + ", password=" + password + ", balance=" + balance
				+ ", sex=" + sex + ", identityCode=" + identityCode
				+ ", email=" + email + ", mobile=" + mobile + ", type=" + type
				+ ", createDate=" + createDate + "]";
	}
	@Override
	public User clone(){
		User user=null;
		try {
			user=(User) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return user;
	}
}

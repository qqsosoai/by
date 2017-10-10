package bean;

import java.io.Serializable;

public class Shopping_Car implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2855095143861299038L;
	private Integer id;//购物车主键
	private Integer userId;//用户ID
	private Product productId;//商品ID
	private Integer proNum;//购买数量
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
	
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	public Integer getProNum() {
		return proNum;
	}
	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}
	public Shopping_Car(Integer id, Integer userId, Product productId,
			Integer proNum) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.proNum = proNum;
	}
	public Shopping_Car() {
	}
	
	public Shopping_Car(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Shopping_Car [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", proNum=" + proNum + "]";
	}
	
	
}

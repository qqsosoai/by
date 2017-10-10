package bean;

import java.io.Serializable;
/**
 * 用户评价表
 * @author hasee
 *
 */
public class Comment implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7252124814577435866L;
	private Integer id;//评价ID
	private Integer userId;//用户ID(外键)
	private Integer productId;//商品ID(外键)
	private Integer level;//商品评价等级
	private String context;//评价详情
	private String imgName;//评论图片
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
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public Comment(Integer id, Integer userId, Integer productId,
			Integer level, String context, String imgName) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.level = level;
		this.context = context;
		this.imgName = imgName;
	}
	public Comment() {
		super();
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", productId="
				+ productId + ", level=" + level + ", context=" + context
				+ ", imgName=" + imgName + "]";
	}
	
	
}

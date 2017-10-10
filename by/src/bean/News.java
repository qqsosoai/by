package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻表
 * @author hasee
 *
 */
public class News implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5362503531734967706L;
	private Integer id;//主键
	private String title;//标题
	private String content;//内容
	private Date createTime;//创建时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public News(Integer id, String title, String content, Date createTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}
	public News() {
		super();
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}
	
}

package bean;

import java.io.Serializable;
import java.util.List;

public class ProductCategory implements Serializable,Cloneable {
	private Integer id;//商品主键
	private String name;//名商品称
	private Integer parentId;//父级目录id
	private Integer type;//级别(1:一级 2：二级 3：三级)
	private String iconClass;//图标
	private List<ProductCategory> list;
	
	public List<ProductCategory> getList() {
		return list;
	}
	public void setList(List<ProductCategory> list) {
		this.list = list;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public ProductCategory(Integer id, String name, Integer parentId,
			Integer type, String iconClass) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.iconClass = iconClass;
	}
	public ProductCategory() {
		super();
	}
	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", type=" + type + ", iconClass=" + iconClass
				+ "]";
	}
	
}

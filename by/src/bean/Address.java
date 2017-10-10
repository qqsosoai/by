package bean;

import java.io.Serializable;
import java.util.List;

/**
 * 地址表
 * @author hasee
 *
 */
public class Address implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5875215769337701188L;
	private Integer areano;//城市的编号
	private String areaname;//城市名称
	private Integer parentno;//上一级编号
	private String areacode;//城市编码
	private Integer arealeveltiny;//城市等级
	private String typename;//城市级别
	private List<Address> child;
	
	public List<Address> getChild() {
		return child;
	}
	public void setChild(List<Address> child) {
		this.child = child;
	}
	public Integer getAreano() {
		return areano;
	}
	public void setAreano(Integer areano) {
		this.areano = areano;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public Integer getParentno() {
		return parentno;
	}
	public void setParentno(Integer parentno) {
		this.parentno = parentno;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public Integer getArealeveltiny() {
		return arealeveltiny;
	}
	public void setArealeveltiny(Integer arealeveltiny) {
		this.arealeveltiny = arealeveltiny;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public Address(Integer areano, String areaname, Integer parentno,
			String areacode, Integer arealeveltiny, String typename) {
		super();
		this.areano = areano;
		this.areaname = areaname;
		this.parentno = parentno;
		this.areacode = areacode;
		this.arealeveltiny = arealeveltiny;
		this.typename = typename;
	}
	public Address() {
		super();
	}
	
	public Address(Integer areano) {
		this.areano = areano;
	}
	@Override
	public String toString() {
		return "Address [areano=" + areano + ", areaname=" + areaname
				+ ", parentno=" + parentno + ", areacode=" + areacode
				+ ", arealeveltiny=" + arealeveltiny + ", typename=" + typename
				+ ", child=" + child + "]";
	}
	
	
}

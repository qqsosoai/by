package util;

public class PageUtil {
	private Integer pageIndex=1;//当前页码
	private Integer pageSize=6;//页面大小
	private Integer pageCount=1;//总页数
	private Integer sqlCount=0;//数据库总记录数
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public Integer getSqlCount() {
		return sqlCount;
	}
	public void setSqlCount(Integer sqlCount) {
		this.sqlCount = sqlCount;
		this.pageCount=sqlCount%this.pageSize==0?sqlCount/this.pageSize:sqlCount/this.pageSize+1;
	}
	
	
}

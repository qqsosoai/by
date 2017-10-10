package dao.product;

import java.sql.Connection;
import java.util.List;

import bean.ProductCategory;
import dao.SuperDao;

public interface ProductCategoryDao extends SuperDao<ProductCategory>{
	/**
	 * 根据父级ID查询子级分类
	 * @param parentId 父级ID
	 * @return 返回所有子级分类
	 */
	List<ProductCategory> findByParentId(Connection c,Integer parentId);
}

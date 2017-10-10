package service.product;

import java.util.List;

import bean.Product;
import bean.ProductCategory;

public interface ProductService {
	/**
	 * 查询所有的分类一级二级三级
	 * @return
	 */
	List<ProductCategory> findToCategories();
	/**
	 * 根据商品名称模糊查询
	 * @param name 商品名称
	 * @param pageIndex 当前页码
	 * @param pageSize 页面大小
	 * @return
	 */
	List<Product> findByLikeName(String name,Integer pageIndex,Integer pageSize);
	/**
	 * 根据商品名称模糊查询总记录数
	 * @param name 商品名称
	 * @return 数据库总记录数
	 */
	Integer findByLikeNameCount(String name);
}

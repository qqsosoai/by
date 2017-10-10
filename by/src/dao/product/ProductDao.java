package dao.product;

import java.sql.Connection;
import java.util.List;

import bean.Product;
import dao.SuperDao;

public interface ProductDao extends SuperDao<Product>{
	/**
	 * 根据商品名称模糊分页查询
	 * @param c 数据库连接
	 * @param name 商品名称
	 * @param pageIndex 当前页码
	 * @param pageSize 页面大小
	 * @return 商品集合
	 */
	List<Product> findByLikeName(Connection c,String name,Integer pageIndex,Integer pageSize);
	/**
	 * 根据品名称模糊查询总记录数
	 * @param c
	 * @param name
	 * @return
	 */
	Integer findByLikeNameCount(Connection c,String name);
}

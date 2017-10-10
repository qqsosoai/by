package service.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import dao.product.ProductCategoryDao;
import dao.product.ProductDao;
import bean.Product;
import bean.ProductCategory;
import util.BaseDao;
import util.BeanFactory;
import util.TestSession;


public class ProductServiceImpl implements ProductService{
	private ProductCategoryDao categoryDao;
	private ProductDao dao;

	
	public ProductServiceImpl(){
		BeanFactory.factoryByType(this);
	}
	@Override//查询所有的分类一级二级三级
	public List<ProductCategory> findToCategories() {
		Connection c = null;
		List<ProductCategory> categpryList=null;
		try {
			c = TestSession.getConnection();
			categpryList = categoryDao.findByParentId(c, 0);
			for (int i = 0; i < categpryList.size(); i++) {
				ProductCategory category1 = categpryList.get(i);
				List<ProductCategory> categpryList2 = categoryDao.findByParentId(c, category1.getId());
				for (int j = 0; j < categpryList2.size(); j++) {
					ProductCategory category2 = categpryList2.get(j);
					category2.setList(categoryDao.findByParentId(c, category2.getId()));
				}
				category1.setList(categpryList2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return categpryList;
	}
	@Override
	public List<Product> findByLikeName(String name, Integer pageIndex,
			Integer pageSize) {
		Connection c = null;
		List<Product> list=null;
		try {
			c = TestSession.getConnection();
			pageIndex=(pageIndex-1)*pageSize;
			list = dao.findByLikeName(c, name, pageIndex, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return list;
	}
	@Override//查询商品总记录
	public Integer findByLikeNameCount(String name) {
		Integer count=null;
		Connection c=null;
		try {
			c=TestSession.getConnection();
			count = dao.findByLikeNameCount(c, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return count;
	}

}

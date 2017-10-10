package dao.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.BaseDao;
import util.ResultSetUtil;
import bean.ProductCategory;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

	@Override
	public List<ProductCategory> findByParentId(Connection c,Integer parentId) {
		ResultSet rs=null;
		String sql ="select id,name,parentId,type,iconClass from ProductCategory where parentId=?";
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = BaseDao.executeQuery(ps, new Object[]{parentId});
		List<ProductCategory> list = ResultSetUtil.eachResultSet(rs, ProductCategory.class);
		BaseDao.close(null, ps, rs);
		return list;
	}

	@Override
	public ProductCategory findById(Connection c, Integer id) {
		return null;
	}

	@Override
	public Integer add(Connection c, ProductCategory t) {
		return null;
	}

	@Override
	public Integer update(Connection c, ProductCategory t) {
		return null;
	}

	@Override
	public Integer delete(Connection c, ProductCategory t) {
		return null;
	}
}

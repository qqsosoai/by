package dao.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.BaseDao;
import util.ResultSetUtil;
import util.TestSession;
import bean.Product;

public class ProductDaoImpl implements ProductDao {
	private final String sqlString=" id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,"
			+ "categoryLevel3Id,fileName,isDelete ";
	@Override//根据商品ID查询商品
	public Product findById(Connection c, Integer id) throws SQLException {
		Product product = TestSession.get(c, id, Product.class, "id", true);
		return product;
	}

	@Override
	public Integer add(Connection c, Product t) throws SQLException {
		return TestSession.insert(c, Product.class, t, true, "id");
	}

	@Override
	public Integer update(Connection c, Product t) throws SQLException {
		return TestSession.update(c, Product.class, t, "id",  true);
	}

	@Override
	public Integer delete(Connection c, Product t) throws SQLException {
		return TestSession.delete(c, Product.class, t.getId(), "id", true);
	}

	@Override
	public List<Product> findByLikeName(Connection c, String name,
			Integer pageIndex, Integer pageSize) {
		String sql="select "+sqlString+" from Product where name like concat('%',?,'%') and isDelete=0 "
				+ "limit ?,?;";
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Product> list=null;
		try {
			ps = c.prepareStatement(sql);
			rs = BaseDao.executeQuery(ps, new Object[]{name,pageIndex,pageSize});
			list= ResultSetUtil.eachResultSet(rs, Product.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(null, ps, rs);
		}
		return list;
	}

	@Override//
	public Integer findByLikeNameCount(Connection c, String name) {
		Integer result=null;
		String sql="select count(1) from Product where name like concat('%',?,'%') and isDelete=0";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = c.prepareStatement(sql);
			rs = BaseDao.executeQuery(ps, new Object[]{name});
			if (rs.next()) {
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(null, ps, rs);
		}
		return result;
	}
}

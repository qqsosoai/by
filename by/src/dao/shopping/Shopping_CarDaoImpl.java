package dao.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.BaseDao;
import util.ResultSetUtil;
import util.TestSession;
import bean.Shopping_Car;

public class Shopping_CarDaoImpl implements Shopping_CarDao {
	private final String sqlString=" id,userId,productId,proNum";
	@Override
	public List<Shopping_Car> findByUserId(Connection c,Integer userId) {
		String sql="select "+sqlString+" from Shopping_Car where userId=?";
		List<Shopping_Car> car=null;
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = BaseDao.executeQuery(ps, new Object[]{userId});
			car = ResultSetUtil.resultManyToOneLIst(rs, Shopping_Car.class, "productId", "id");
			BaseDao.close(null, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
	}

	@Override//根据ID查询
	public Shopping_Car findById(Connection c, Integer id) throws SQLException {
		return TestSession.get(c, id, Shopping_Car.class, "id", false);
	}

	@Override
	public Integer add(Connection c, Shopping_Car t) throws SQLException {
		return TestSession.insert(c, Shopping_Car.class, t, false, "id");
	}

	@Override
	public Integer update(Connection c, Shopping_Car t) throws SQLException {
		return TestSession.update(c, Shopping_Car.class, t, "id", false);
	}

	@Override
	public Integer delete(Connection c, Shopping_Car t) throws SQLException {
		return TestSession.delete(c, Shopping_Car.class, t.getId(), "id", false);
	}

}

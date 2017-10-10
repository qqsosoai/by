package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.BaseDao;
import util.ResultSetUtil;
import util.TestSession;
import bean.User;

public class UserDaoImpl implements UserDao {
	private final String portion=" id,loginName,userName,password,"
			+ "balance,sex,identityCode,email,mobile,type,createDate ";
	@Override
	public User findById(Connection c, Integer id) {
		return null;
	}

	

	@Override
	public Integer update(Connection c, User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Connection c, User t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override//添加用户方法
	public Integer add(Connection c, User t) throws SQLException {
		return TestSession.insert(c, User.class, t, false, "id");
	}
	@Override//根据用户名查询
	public User findByLoginName(Connection c, String loginName) {
		String sql="select "+portion+" from User where loginName=?";
		PreparedStatement ps=null;
		User user=null;
		try {
			ps = c.prepareStatement(sql);
			ResultSet rs = BaseDao.executeQuery(ps, new Object[]{loginName});
			user = ResultSetUtil.resultSet(rs, User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}

package dao.user.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.BaseDao;
import util.ResultSetUtil;
import util.TestSession;
import bean.Address;
import bean.UserAddress;

public class AddressDaoImpl implements AddressDao {
	private final String sqlString=" areano,areaname,parentno,areacode,arealevel,typename ";
	@Override//根据ID查询地址
	public Address findById(Connection c, Integer id) throws SQLException {
		return TestSession.get(c, id, Address.class, "areano", true);
	}

	@Override//添加地址
	public Integer add(Connection c, Address t) throws SQLException {
		return TestSession.insert(c, Address.class, t, true, "areano");
	}

	@Override//更新地址
	public Integer update(Connection c, Address t) throws SQLException {
		return TestSession.update(c, Address.class, t, "areano", true);
	}

	@Override//删除地址
	public Integer delete(Connection c, Address t) throws SQLException {
		return TestSession.delete(c, Address.class, t.getAreano(), "areano", true);
	}

	@Override//根据父ID查询地址
	public List<Address> findByParentno(Connection c, Integer parentno) {
		String sql="select "+sqlString+" from Address where parentno=?";
		List<Address> addresses=null;
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = BaseDao.executeQuery(ps, new Object[]{parentno});
			addresses = ResultSetUtil.eachResultSet(rs, Address.class);
			BaseDao.close(null, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addresses;
	}

	
}

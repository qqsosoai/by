package dao.user.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.BaseDao;
import util.TestSession;
import bean.Address;
import bean.UserAddress;

public class UserAddressDaoImpl implements UserAddressDao {
	private final String  sqlString=" id,userId,collect,phone,province,city,"
			+ "zone,detail,createTime,isDefault,remark ";
	private final String insertString = " userId,collect,phone,province,city,"
			+ "zone,detail,createTime,isDefault,remark ";
	@Override//根据用户地址ID查询
	public UserAddress findById(Connection c, Integer id) throws SQLException {
		UserAddress address=null;
		String sql="select "+sqlString+" from UserAddress where id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = BaseDao.executeQuery(ps, new Object[]{id});
		while(rs.next()){
			address=new UserAddress();
			address.setId(rs.getInt("id"));
			address.setCollect(rs.getString("collect"));
			address.setUserId(rs.getInt("userId"));
			address.setPhone(rs.getString("phone"));
			address.setProvince(new Address(rs.getInt("province")));
			address.setCity(new Address(rs.getInt("city")));
			address.setZone(new Address(rs.getInt("zone")));
			address.setDetail(rs.getString("detail"));
			address.setCreateTime(rs.getTimestamp("createTime"));
			address.setIsDefault(rs.getInt("isDefault"));
			address.setRemark(rs.getString("remark"));
		}
		return address;
	}
	@Override//根据用户ID查询用户地址总记录数
	public Integer selectAddressCount(Connection c, Integer userId) {
		Integer count=0;
		String sql="select count(1) from userAddress where userId=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = c.prepareStatement(sql);
			rs = BaseDao.executeQuery(ps, new Object[]{userId});
			if (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(null, ps, rs);
		}
		return count;
	}
	@Override//添加
	public Integer add(Connection c, UserAddress t) throws SQLException {
		String sql="insert into UserAddress("+insertString+") value(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		Object[] params={t.getUserId(),t.getCollect(),t.getPhone(),t.getProvince().getAreano(),
				t.getCity().getAreano(),t.getZone().getAreano(),t.getDetail(),
				t.getCreateTime(),t.getIsDefault(),t.getRemark()};
		return BaseDao.executeUpdate(ps, params);
	}

	@Override//更新
	public Integer update(Connection c, UserAddress t) throws SQLException {
		String sql="update UserAddress set userId=?,collect=?,phone=?,province=?,city=?,"
				+ "zone=?,detail=?,createTime=?,isDefault=?,remark=? where id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		Object[] params={t.getUserId(),t.getCollect(),t.getPhone(),t.getProvince().getAreano(),
				t.getCity().getAreano(),t.getZone().getAreano(),t.getDetail(),
				t.getCreateTime(),t.getIsDefault(),t.getRemark(),t.getId()};
		return BaseDao.executeUpdate(ps, params);
	}
	
	@Override//根据用户地址ID修改为默认地址
	public Integer updateIsDefault(Connection c, Integer id)
			throws SQLException {
		String sql="update UserAddress set isDefault=1 where id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		int row = BaseDao.executeUpdate(ps, new Object[]{id});
		BaseDao.close(null, ps, null);
		return row;
	}
	@Override//删除
	public Integer delete(Connection c, UserAddress t) throws SQLException {
		return TestSession.delete(c, UserAddress.class, t.getId(), "id", false);
	}

	@Override//根据用户ID查询
	public List<UserAddress> findByUserId(Connection c, Integer userId) {
		String sql="select "+sqlString+" from UserAddress where userId=?";
		List<UserAddress> list=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = c.prepareStatement(sql);
			rs = BaseDao.executeQuery(ps, new Object[]{userId});
			while(rs.next()){
				UserAddress address=new UserAddress();
				address.setId(rs.getInt("id"));
				address.setCollect(rs.getString("collect"));
				address.setUserId(rs.getInt("userId"));
				address.setPhone(rs.getString("phone"));
				address.setProvince(new Address(rs.getInt("province")));
				address.setCity(new Address(rs.getInt("city")));
				address.setZone(new Address(rs.getInt("zone")));
				address.setDetail(rs.getString("detail"));
				address.setCreateTime(rs.getTimestamp("createTime"));
				address.setIsDefault(rs.getInt("isDefault"));
				address.setRemark(rs.getString("remark"));
				list.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(null, ps, rs);
		}
		return list;
	}

	@Override//取消用户原有的默认地址
	public Integer updateUserAddressToNoDefault(Connection c, Integer userId) throws SQLException {
		String sql="update UserAddress set isDefault=0 where userId=? and isDefault=1";
		PreparedStatement ps = c.prepareStatement(sql);
		int row = BaseDao.executeUpdate(ps, new Object[]{userId});
		BaseDao.close(null, ps, null);
		return row;
	}
	@Override//查询最近添加的ID
	public Integer selectInsterId(Connection c) {
		Integer result=null;
		String sql="SELECT LAST_INSERT_ID() AS id;";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = c.prepareStatement(sql);
			rs = BaseDao.executeQuery(ps);
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

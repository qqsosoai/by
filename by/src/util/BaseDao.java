package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 执行数据库sql语句
 * @author hasee
 *
 */
public class BaseDao {
	/**
	 * 执行数据库查询语句
	 * @param c
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet executeQuery(PreparedStatement ps,Object... params){
		ResultSet rs=null;
		try {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public static int executeUpdate(PreparedStatement ps,Object... params) throws SQLException{
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i+1, params[i]);
		}
		int result = ps.executeUpdate();
		return result;
	}
	/**
	 * 关闭资源方法
	 * @param c
	 * @param ps
	 * @param rs
	 * @return
	 */
	public static boolean close(Connection c,PreparedStatement ps,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		if (c!=null) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}

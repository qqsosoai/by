package dao.user.address;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.UserAddress;
import dao.SuperDao;

public interface UserAddressDao extends SuperDao<UserAddress> {
	/**
	 * 根据用户ID查询
	 * @param c 数据库连接
	 * @param userId 用户ID
	 * @return 用户地址集合
	 */
	List<UserAddress> findByUserId(Connection c,Integer userId);
	/**
	 * 取消用户地址原有默认方法
	 * @param c 数据库连接
	 * @param userId 用户ID
	 * @return 返回影响行数
	 */
	Integer updateUserAddressToNoDefault(Connection c,Integer userId)throws SQLException;
	/**
	 * 根据用户地址ID修改默认地址
	 * @param c 数据库连接
	 * @param id 用户地址ID
	 * @return 影响行数
	 * @throws SQLException
	 */
	Integer updateIsDefault(Connection c,Integer id)throws SQLException;
	/**
	 * 根据用户ID查询用户地址总数
	 * @param c 数据库连接
	 * @param userId 用户ID
	 * @return 总记录数
	 */
	Integer selectAddressCount(Connection c,Integer userId);
	/**
	 * 查询最近添加的ID
	 * @param c 数据库连接
	 * @return 返回ID
	 */
	Integer selectInsterId(Connection c);
}

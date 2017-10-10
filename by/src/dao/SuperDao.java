package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SuperDao<T> {
	/**
	 * 根据ID查询对象
	 * @param id
	 * @return
	 */
	T findById(Connection c, Integer id)throws SQLException;
	/**
	 * 新增对象
	 * @param t 实体类对象
	 * @return 返回影响行数
	 */
	Integer add(Connection c,T t)throws SQLException;
	/**
	 * 更新方法
	 * @param t 实体类对象
	 * @return 返回影响行数
	 */
	Integer update(Connection c,T t)throws SQLException;
	/**
	 * 删除方法
	 * @param t 实体类对象
	 * @return 返回影响行数
	 */
	Integer delete(Connection c,T t)throws SQLException;
}

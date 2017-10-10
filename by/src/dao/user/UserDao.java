package dao.user;

import java.sql.Connection;

import bean.User;
import dao.SuperDao;

public interface UserDao extends SuperDao<User> {
	/**
	 * 根据用户账号
	 * @param loginName 用户账号
	 * @return 用户
	 */
	User findByLoginName(Connection c,String loginName);
}

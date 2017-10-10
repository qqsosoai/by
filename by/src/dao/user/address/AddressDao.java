package dao.user.address;

import java.sql.Connection;
import java.util.List;

import bean.Address;
import dao.SuperDao;

public interface AddressDao extends SuperDao<Address> {
	/**
	 * 根据地址父编号查询地址
	 * @param c 数据库连接
	 * @param parentno 父编号
	 * @return 地址集合
	 */
	List<Address> findByParentno(Connection c,Integer parentno);
	
	
}

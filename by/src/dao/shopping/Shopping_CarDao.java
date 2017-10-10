package dao.shopping;

import java.sql.Connection;
import java.util.List;

import dao.SuperDao;
import bean.Shopping_Car;

public interface Shopping_CarDao extends SuperDao<Shopping_Car> {
	List<Shopping_Car> findByUserId(Connection c,Integer userId);
}

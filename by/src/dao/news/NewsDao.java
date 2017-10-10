package dao.news;

import java.sql.Connection;
import java.util.List;

import bean.News;
import dao.SuperDao;

public interface NewsDao extends SuperDao<News> {
	List<News> findByLimit(Connection c,Integer pageIndex,Integer pageSize);
}

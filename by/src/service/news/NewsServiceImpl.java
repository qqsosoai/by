package service.news;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import util.BaseDao;
import util.BeanFactory;
import util.TestSession;
import dao.news.NewsDao;
import bean.News;

public class NewsServiceImpl implements NewsService {
	private Logger logger = Logger.getLogger(NewsServiceImpl.class);
	private NewsDao dao;
	
	public NewsServiceImpl(){
		BeanFactory.factoryByType(this);
	}
	@Override
	public List<News> findByLimit(Integer pageIndex, Integer pageSize) {
		Connection c = null;
		List<News> list=null;
		try {
			c = TestSession.getConnection();
			list=dao.findByLimit(c, pageIndex, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(c, null, null);
		}
		return list;
	}

}

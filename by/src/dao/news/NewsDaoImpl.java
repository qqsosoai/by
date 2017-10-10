package dao.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.BaseDao;
import util.ResultSetUtil;
import bean.News;

public class NewsDaoImpl implements NewsDao{
	
	@Override
	public News findById(Connection c, Integer id) {
		
		return null;
	}

	@Override
	public Integer add(Connection c, News t) {
		return null;
	}

	@Override
	public Integer update(Connection c, News t) {
		return null;
	}

	@Override
	public Integer delete(Connection c, News t) {
		return null;
	}

	@Override//查询新闻并分页
	public List<News> findByLimit(Connection c,Integer pageIndex,Integer pageSize) {
		String sql="select * from news limit ?,?";
		List<News> list=null;
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = BaseDao.executeQuery(ps, new Object[]{(pageIndex-1)*pageSize,pageSize});
			list = ResultSetUtil.eachResultSet(rs, News.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}

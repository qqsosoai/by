package web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import util.TestSession;
import util.ResultSetUtil;
import com.danga.MemCached.SockIOPool;

@WebListener
public class ContextListener extends TestSession implements
		ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Context context = new InitialContext();
			DataSource source = (DataSource) context
					.lookup("java:comp/env/jdbc/news");
			super.source = source;
			SockIOPool pool = SockIOPool.getInstance();
			pool.setServers(new String[] { "192.168.30.249:11211" });
			pool.setWeights(new Integer[] { 3 });
			super.pool = pool;
			super.pool.initialize();
			System.out.println("初始化成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

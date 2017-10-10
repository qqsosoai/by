package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

public abstract class SuperServlet extends HttpServlet {
	private Logger logger=Logger.getLogger(SuperServlet.class);
	
	/**
	 * 获取要执行servlet的Class类型
	 * @return
	 */
	public abstract Class<?> getHandlerClass();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String methodString = req.getParameter("method");
		logger.debug("==========================================="+methodString);
		Object result=null;
		try {
			result=getHandlerClass().getMethod(methodString, HttpServletRequest.class,HttpServletResponse.class)
				.invoke(this, req,resp);
		} catch (NoSuchMethodException e) {
			logger.error("没有找到方法");
			e.printStackTrace();
		} catch (SecurityException e) {
			logger.error("类没有找到");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error("访问权限，无法访问");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.error("参数不正确");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error("方法中出现未捕获异常");
			e.printStackTrace();
		}
		toView(req, resp,result);
	}

	private void toView(HttpServletRequest req, HttpServletResponse resp,Object result) throws ServletException, IOException {
		if (result instanceof String) {
			String string = result.toString();
			if (string.contains("redirect:")) {
				try {
					resp.sendRedirect(string.substring(string.indexOf("redirect:")+1));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
					if (string.contains("forward:")) {
						string=string.substring(string.indexOf("forward:")+1);
					}
					req.getRequestDispatcher("/WEB-INF/jsp/" + string + ".jsp").forward(req, resp);
			}
			
		}else if (result instanceof Boolean){
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().print(result);
		}else if (result == null){//验证码什么都不做
			
		}else {
			try {
				resp.setCharacterEncoding("utf-8");
				PrintWriter writer = resp.getWriter();
				writer.print(JSON.toJSONString(result));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}

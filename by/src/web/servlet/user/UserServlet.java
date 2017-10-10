package web.servlet.user;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import constants.ConstantsPool;
import bean.Address;
import bean.News;
import bean.Product;
import bean.ProductCategory;
import bean.Shopping_Car;
import bean.User;
import bean.UserAddress;
import service.news.NewsService;
import service.product.ProductService;
import service.user.UserService;
import util.BeanFactory;
import util.MyMd5;
import util.PageUtil;
import util.ReturnResult;
import web.servlet.SuperServlet;
import dao.product.ProductCategoryDao;

@WebServlet(urlPatterns={"/main.html","/login.html","/regist","/user.html"})
public class UserServlet extends SuperServlet {
	private Logger logger=Logger.getLogger(getHandlerClass());
	private ProductService productService;
	private NewsService newsService;
	private UserService userService;
	
	@Override//实例化对象
	public void init() throws ServletException {
		BeanFactory.factoryByType(this);
	}

	@Override
	public Class<?> getHandlerClass() {
		return UserServlet.class;
	}
	
	/**
	 * 跳转主页面方法
	 * @param req 
	 * @param resp
	 * @return 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String main(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		List<ProductCategory> list = productService.findToCategories();//获取商品分类
		List<News> news = newsService.findByLimit(1, 5);//获取新闻
		logger.debug(news);
		logger.debug(list);
		req.setAttribute("list", list);//商品分类
		req.setAttribute("news", news);//新闻列表
		return "Index";
	}
	//跳转注册页面
	public String regist(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		return "user/Regist";
	}
	//验证用户名是否存在
	public Boolean validateLoginName(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		String loginName = req.getParameter("loginName");
		boolean b = userService.isExistLoginName(loginName);
		return b;
	}
	/**
	 * 提交注册请求
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult sub(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		logger.debug("===========================================sub");
		String loginName = req.getParameter("loginName");
		String password = req.getParameter("password");
		String rPassword = req.getParameter("rPassword");
		String email = req.getParameter("email");
		String mobile = req.getParameter("phone");
		String userName = req.getParameter("userName");
		String identityCode = req.getParameter("identityCode");
		String sex = req.getParameter("sex");
		password = MyMd5.toMd5String(password);
		User user=new User(loginName, userName, rPassword, 0, Integer.parseInt(sex), identityCode, email, mobile, 0, new Date());
		if (userService.addUser(user)) {//添加成功
			return new ReturnResult(ConstantsPool.STATUS_SECCESS,"true","注册成功");
		}else {//添加失败
			return new ReturnResult(ConstantsPool.STATUS_ERROR,"false","注册失败");
		}
		
	}
	//跳转模糊查询页面
	public String likePro(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException{
		String proName = req.getParameter("proName");
		String pageIndex = req.getParameter("pageIndex");
		if (proName!=null && !"".equals(proName)) {
			PageUtil util = new PageUtil();
			if(pageIndex!=null && !"".equals(pageIndex)){
				util.setPageIndex(Integer.parseInt(pageIndex));
			}
			util.setSqlCount(productService.findByLikeNameCount(proName));
			List<Product> proList = productService.findByLikeName(proName, util.getPageIndex(), util.getPageSize());
			List<ProductCategory> list = productService.findToCategories();//获取商品分类
			req.setAttribute("proName", proName);//用户输入
			req.setAttribute("page", util);//商品
			req.setAttribute("proList", proList);//商品
			req.setAttribute("list", list);//商品分类
			return "BrandList";
		}else{
			return "Index";
		}
	}
	//ajax请求分页查询
	public void ajaxLikePro(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException{
		String index = req.getParameter("pageIndex");
		String proName = req.getParameter("proName");
		if (proName!=null&&!"".equals(proName)) {
			PageUtil util=new PageUtil();
			util.setPageIndex(Integer.parseInt(index));
			util.setSqlCount(productService.findByLikeNameCount(proName));
			List<Product> list = productService.findByLikeName(proName, util.getPageIndex(), util.getPageSize());
			String listjson = JSON.toJSONString(list);
			String out = listjson.replace("]", ","+JSON.toJSONString(util)+"]");
			resp.getWriter().print(out);
		}else{//用户输入没有条件
			req.getRequestDispatcher("Index");
		}
	}
	//跳转登录页面
	public String login(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		Cookie[] cookies = req.getCookies();
		String name=null;
		String pwd=null;
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals(ConstantsPool.COOKIE_NAME)) {
				name=cookie.getValue();
			}
			if (cookie.getName().equals(ConstantsPool.COOKIE_PWD)) {
				pwd=cookie.getValue();
			}
		}
		req.setAttribute("name", name);
		req.setAttribute("pwd", pwd);
		return "user/Login";
	}
	//提交登录请求
	public ReturnResult subLogin(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		String name = req.getParameter("userName");
		String pwd = req.getParameter("password");
		String isMy = req.getParameter("my");
		User login = userService.login(name);
		if (login!=null) {//判断是否存在改账号
			if (MyMd5.isMd5String(pwd, login.getPassword())) {//将购物车与用户收获地址对象放入session
				if (isMy!=null && !"".equals(isMy)) {//登录成功判断是否保存cookie
					Cookie username=new Cookie(ConstantsPool.COOKIE_NAME, name);
					username.setMaxAge(60*60*24*7);//过期时间7天
					Cookie password=new Cookie(ConstantsPool.COOKIE_PWD, pwd);
					password.setMaxAge(60*60*24*7);//过期时间7天
					resp.addCookie(username);
					resp.addCookie(password);
				}
				List<Shopping_Car> car = userService.findByUserId(login.getId());//查询用户下的购物车
				List<UserAddress> userAddress = userService.findByUserAddress(login.getId());
				req.getSession().setAttribute("car", car);//将购物车添加到session中
				req.getSession().setAttribute("userAddress", userAddress);//将用户地址添加session中
				req.getSession().setAttribute("user", login);//将用户信息添加session中
				return new ReturnResult(1,"登录成功","登录成功");
			}else{//密码输入错误
				return new ReturnResult("密码输入不正确，请重新输入");
			}
		}
		return new ReturnResult("用户名不存在请重新输入");
	}
	//退出登录方法
	public String out(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/main.html?method=main";
	}
	//跳转用户管理主页面(过滤器过滤)
	public String toUser(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException{
		User user=(User) req.getSession().getAttribute("user");
		User clone = user.clone();
		clone.setIdentityCode(getStarString(clone.getIdentityCode(), 0, 14));
		clone.setMobile(getStarString(clone.getMobile(), 2, 8));
		req.setAttribute("clone", clone);
		return "user/Member";
	}
	//跳转用户修改地址页面(过滤器过滤)
	public String toUserAddress(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException{
		return "user/Member_Address";
	}
	//删除用户地址
	public ReturnResult delUserAddress(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException{
		boolean flag=false;
		String idString = req.getParameter("id");
		if (idString!=null && !idString.equals("")) {
			List<UserAddress> userAddress=(List<UserAddress>) req.getSession().getAttribute("userAddress");
			for (int i = 0; i < userAddress.size(); i++) {
				UserAddress address = userAddress.get(i);
				int id = Integer.parseInt(idString);
				if (address.getId().intValue()==id) {//找到用户要删除的地址ID
					userAddress.remove(i);//删除session中的用户地址
					flag = userService.deleteUserAddress(id);//删除数据库的用户地址
				}
			}
		}
		if (flag) {
			return new ReturnResult(1,"删除成功","删除成功");
		}else {
			return new ReturnResult("删除失败");
		}
	}
	//更新用户地址的默认地址
	public ReturnResult updateIsDefault(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		boolean flag=false;
		String idString = req.getParameter("id");//获取前台的用户地址ID
		if (idString!=null && !"".equals(idString)) {
			User user=(User) req.getSession().getAttribute("user");//获取用户信息
			List<UserAddress> userAddress=(List<UserAddress>) req.getSession().getAttribute("userAddress");//获取用户地址
			int id = Integer.parseInt(idString);
			for (int i = 0; i < userAddress.size(); i++) {
				UserAddress address = userAddress.get(i);
				if (address.getIsDefault()==1) {//找到用户的默认地址
					address.setIsDefault(0);//将用户的默认地址设置为0
				}else if(address.getId().intValue()==id){//找到用户点击的默认地址ID
					address.setIsDefault(1);//将用户的选择设置为默认地址
					if (userService.updateDefaultUserAddrees(user.getId(), id)) {
						flag=true;
					}
				}
			}
		}
		if (flag) {
			return new ReturnResult(1,"更新成功","更新成功");
		}else {
			return new ReturnResult("更新失败");
		}
	}
	//跳转修改用户地址页面
	public String toAddress(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		String idString = req.getParameter("id");//
		if (idString!=null && !"".equals(idString)) {//代表用户要修改地址
			List<UserAddress> userAddress=(List<UserAddress>) req.getSession().getAttribute("userAddress");
			for (int i = 0; i < userAddress.size(); i++) {
				UserAddress address = userAddress.get(i);
				if (address.getId().intValue()==Integer.parseInt(idString)) {
					req.setAttribute("reqAddress", address);//将用户要修改的地址放入作用域
				}
			}
		}
		//查询所有地址放入request作用域
		List<Address> all = userService.findAddressAll();
		req.getSession().getServletContext().setAttribute("address", all);
		return "user/addMember_Address";
	}
	//地址ajax请求
	public List<Address> ajax(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException{
		List<Address> resultAddresses=new ArrayList<>();
		String idString = req.getParameter("id");
		String parentString = req.getParameter("parent");
		if (idString!=null && parentString!=null) {
			List<Address> all=(List<Address>) req.getSession().getServletContext().getAttribute("address");
			for (int i = 0; i < all.size(); i++) {
				Address address = all.get(i);
				if ("0".equals(parentString)) {//代表要查询二级
					if (address.getAreano().intValue() == Integer
							.parseInt(idString)) {
						return address.getChild();
					}
				}else{//代表要查询三级
					if (address.getAreano().intValue()==Integer.parseInt(parentString)) {//找到对应一级
						List<Address> toList = address.getChild();
						for (int j = 0; j < toList.size(); j++) {
							Address to = toList.get(j);
							if (to.getAreano().intValue()==Integer.parseInt(idString)) {
								return to.getChild();
							}
						}
					}
				}
			}
		}
		return resultAddresses;
	}
	//提交添加用户地址
	public String subAddress(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException{
		String province = req.getParameter("province");
		String city = req.getParameter("city");
		String zone = req.getParameter("zone");
		String collect = req.getParameter("collect");
		String phone = req.getParameter("phone");
		String detail = req.getParameter("detail");
		String remark = req.getParameter("remark");
		User user = (User) req.getSession().getAttribute("user");
		UserAddress address=new UserAddress(null, user.getId(), collect, phone,detail, new Date(), 0, remark);
		if (province!=null&&city!=null&&zone!=null) {
			address.setProvince(userService.findAddressByAreano(Integer.parseInt(province)));
			address.setCity(userService.findAddressByAreano(Integer.parseInt(city)));
			address.setZone(userService.findAddressByAreano(Integer.parseInt(zone)));
		}
		if (userService.addUserAddress(address)) {//添加成功
			List<UserAddress> userAddress=(List<UserAddress>) req.getSession().getAttribute("userAddress");
			userAddress.add(address);
			return "user/Member_Address";
		}else {//添加失败
			return "user/Member_Address";
		}
	}
	//转换用户信息为*
	private String getStarString(String content, int begin, int end){
		if (begin >= content.length() || begin < 0) {  
            return content;  
        }  
        if (end >= content.length() || end < 0) {  
            return content;  
        }  
        if (begin >= end) {  
            return content;  
        }  
        String starStr = "";  
        for (int i = begin; i < end; i++) {  
            starStr = starStr + "*";  
        }  
        return content.substring(0, begin) + starStr + content.substring(end, content.length());  
	}
}

package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import bean.ProductCategory;
import bean.Shopping_Car;
import bean.User;
import bean.UserAddress;
import service.product.ProductService;
import service.user.UserService;
import util.BeanFactory;
import util.ReturnResult;
@WebServlet(urlPatterns={"/pay.html"})
public class PayServlet extends SuperServlet {
	private Logger logger=Logger.getLogger(PayServlet.class);
	private ProductService productService;
	private UserService userService;
	@Override
	public Class<?> getHandlerClass() {
		return PayServlet.class;
	}

	@Override
	public void init() throws ServletException {
		BeanFactory.factoryByType(this);
	}
	//跳转确认购物车页面
	public String toCar(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		List<ProductCategory> list = productService.findToCategories();//获取商品分类
		req.setAttribute("list", list);
		logger.debug(list);
		req.setAttribute("list", list);
		return "user/BuyCar";
	}
	//跳转选择收获地址页面
	public String toCarTw(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		return "user/BuyCar_Two";
	}
	//删除购物车商品
	public ReturnResult delCarPro(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		boolean flag=false;
		String car = req.getParameter("carId");
		if (car!=null&& !car.equals("")) {
			int id = Integer.parseInt(car);
			List<Shopping_Car> car1 = (List<Shopping_Car>) req.getSession().getAttribute("car");
			for (int i = 0; i < car1.size(); i++) {
				Shopping_Car shopping_Car = car1.get(i);
				if (shopping_Car.getId().intValue()==id) {//删除session购物车商品
					car1.remove(i);
					if (userService.deleteShopping_Car(id)) {
						flag=true;
					}
				}
			}
		}
		if (flag) {
			return new ReturnResult(1,null,"删除成功");
		}else{
			return new ReturnResult("删除失败");
		}
	}
	//用户修改购物车数量
	public void changeCar(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
		String carJson = req.getParameter("arrayList");
		logger.debug(carJson);
		if (!carJson.equals("[]")) {
			List<Shopping_Car> jspCar = JSON.parseArray(carJson,
					Shopping_Car.class);
			logger.debug(jspCar);
			List<Shopping_Car> sessionCar = (List<Shopping_Car>) req
					.getSession().getAttribute("car");
			for (int i = 0; i < sessionCar.size(); i++) {
				Shopping_Car forSessionCar = sessionCar.get(i);
				for (int j = 0; j < jspCar.size(); j++) {
					Shopping_Car forJapCar = jspCar.get(i);
					if (forSessionCar.getId().intValue()==forJapCar.getId().intValue() 
							&& forSessionCar.getProNum().intValue()!=forJapCar.getProNum().intValue()) {//证明ID相等数量不等
						forSessionCar.setProNum(forJapCar.getProNum());//更改session中的购物车数量
						userService.updateShopping_Car(forJapCar);//同步数据库
					}
				}
			}
		}
	}
	
	

}

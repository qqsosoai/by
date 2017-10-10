package web.servlet.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ReturnResult;
import util.ValidateUtil;
import web.servlet.SuperServlet;
@WebServlet(urlPatterns={"/data.html"})
public class ValidateServlet extends SuperServlet {
	
	@Override
	public Class<?> getHandlerClass() {
		return ValidateServlet.class;
	}
	//生成验证码方法
	public void isVal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
		String code = ValidateUtil.valiCode(4);
		request.getSession().removeAttribute("validateCode");
		request.getSession().setAttribute("validateCode", code);
		ValidateUtil.outValiCodeImage(100, 30, response.getOutputStream(), code);
	}

	public ReturnResult validate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// 首先是先判断用户输入的验证是否正确
		// 然后再判断用户输入的账号密码是否正确
		HttpSession session = request.getSession();
		// 获取服务器生成的验证码
		String ServiceCode = session.getAttribute("validateCode").toString();
		// 获取用户输入的的验证码
		String ClientCode = request.getParameter("vali");
		// 验证用户输入的验证码与服务器生产的验证码是否一样
		if (ClientCode.equalsIgnoreCase(ServiceCode)) {//验证码输入正确
			return new ReturnResult(true);
		} else {//验证码输入错误
			return new ReturnResult(false);
		}
	}
}

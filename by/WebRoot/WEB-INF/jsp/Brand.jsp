<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<base href="<%=basePath%>">
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->    
    <script type="text/javascript" src="js/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider_e88acd1b.js"></script>
    
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>    
            
	<script type="text/javascript" src="js/lrscroll_1.js"></script>
    
    
	<script type="text/javascript" src="js/n_nav.js"></script>    
    <script type="text/javascript" src="js/milk_ban.js"></script>
    <script type="text/javascript" src="js/paper_ban.js"></script>
    <script type="text/javascript" src="js/baby_ban.js"></script>
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
        <span class="fr">
        	<span class="fl">你好，请<a href="Login.html">登录</a>&nbsp; <a href="Regist.html" style="color:#ff4e00;">免费注册</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>
        	<span class="ss">
            	<div class="ss_list">
                	<a href="#">收藏夹</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">我的收藏夹</a></li>
                            </ul>
                        </div>
                    </div>     
                </div>
                <div class="ss_list">
                	<a href="#">客户服务</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">客户服务</a></li>
                                <li><a href="#">客户服务</a></li>
                                <li><a href="#">客户服务</a></li>
                            </ul>
                        </div>
                    </div>    
                </div>
                <div class="ss_list">
                	<a href="#">网站导航</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">网站导航</a></li>
                                <li><a href="#">网站导航</a></li>
                            </ul>
                        </div>
                    </div>    
                </div>
            </span>
            <span class="fl">|&nbsp;关注我们：</span>
            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
        </span>
    </div>
</div>
<div class="top">
    <div class="logo"><a href="Index.html"><img src="images/logo.png" /></a></div>
    <div class="search">
    	<form>
        	<input type="text" value="" class="s_ipt" />
            <input type="submit" value="搜索" class="s_btn" />
        </form>                      
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
    </div>
    <div class="i_car">
    	<div class="car_t">购物车 [ <span>0</span> ]</div>
        <div class="car_bg">
       		<!--Begin 购物车未登录 Begin-->
        	<div class="un_login">还未登录！<a href="Login.html" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
            	<li>
                	<div class="img"><a href="#"><img src="images/car1.jpg" width="58" height="58" /></a></div>
                    <div class="name"><a href="#">法颂浪漫梦境50ML 香水女士持久清新淡香 送2ML小样3只</a></div>
                    <div class="price"><font color="#ff4e00">￥399</font> X1</div>
                </li>
            <div class="price_a"><a href="#">去购物车结算</a></div>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>
<!--End Header End--> 
<!--Begin Menu Begin-->
<div class="menu_bg">
	<div class="menu">
    	<!--Begin 商品分类详情 Begin-->    
    	<div class="nav">
        	<div class="nav_t">全部商品分类</div>
            <div class="leftNav none">
                <ul>   
                	<!-- 123级标签开始    -->
                	<c:forEach items="${prodectCategory}" var="category">
	                    <li>
	                    	<div class="fj">
	                        	<span class="n_img"><span></span><img src="images/nav1.png" /></span>
	                            <span class="fl">${category.name}</span><!-- 一级标题 -->
	                        </div>
	                        <div class="zj">
	                            <div class="zj_l">
	                                <div class="zj_l_c">
	                                	<c:forEach items="${category.list}" var="categoryTo">
	                                    <h2>${categoryTo.name}</h2><!-- 二级标题 -->
	                                    	<c:forEach items="${categoryTo.list}" var="category3"><a href="servlet?id=${category3.id}">${category3.name}</a>|</c:forEach>
	                                	</c:forEach>
	                                </div>
	                            </div>
	                            <div class="zj_r">
	                                <a href="#"><img src="images/n_img1.jpg" width="236" height="200" /></a>
	                                <a href="#"><img src="images/n_img2.jpg" width="236" height="200" /></a>
	                            </div>
	                        </div>
	                    </li>
                    </c:forEach>
                    <!-- 123级标签结束 -->
                </ul>            
            </div>
        </div>  
        <!--End 商品分类详情 End-->                                                     
    	<ul class="menu_r">
    		<li><a href="Index.html">首页</a></li>
        	<c:forEach items="${prodectCategory}" var="category"><li><a href="Index.html?id=${category.id}">${category.name}</a></li></c:forEach>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
<!--End Menu End--> 
<div class="i_bg">
	<div class="postion">
    	<span class="fl">全部 > 美妆个护 > 香水 > 香奈儿</span>
    </div>    
    <div class="content mar_20">
    	<div class="l_history">
        	<div class="his_t">
            	<span class="fl">浏览历史</span>
                <span class="fr"><a href="#">清空</a></span>
            </div>
        	<ul>
            	<li>
                    <div class="img"><a href="#"><img src="images/his_1.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="images/his_2.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>768.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="images/his_3.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>680.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="images/his_4.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="images/his_5.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
        	</ul>
        </div>
        <div class="l_list">
			<div class="brand_t">所有品牌</div>
            <div class="list_c">
            	
                <ul class="brand">
                	<li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand1.jpg" width="226" height="108" /></div>
                        <div class="name"><span>普拉达</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand2.jpg" width="226" height="108" /></div>
                        <div class="name"><span>劳力士</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand3.jpg" width="226" height="108" /></div>
                        <div class="name"><span>古奇</span>（20）</div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/brand4.jpg" width="226" height="108" /></div>
                        <div class="name"><span>迪奥</span>（20）</div>
                    </li>
                </ul>
                
                <div class="pages">
                	<a href="#" class="p_pre">上一页</a><a href="#" class="cur">1</a><a href="#">2</a><a href="#">3</a>...<a href="#">20</a><a href="#" class="p_pre">下一页</a>
                </div>
                
                
                
            </div>
        </div>
    </div>
    
    <!--Begin Footer Begin -->
    <div class="b_btm_bg bg_color">
        <div class="b_btm">
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b1.png" width="62" height="62" /></td>
                <td><h2>正品保障</h2>正品行货  放心购买</td>
              </tr>
            </table>
			<table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b2.png" width="62" height="62" /></td>
                <td><h2>满38包邮</h2>满38包邮 免运费</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b3.png" width="62" height="62" /></td>
                <td><h2>天天低价</h2>天天低价 畅选无忧</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b4.png" width="62" height="62" /></td>
                <td><h2>准时送达</h2>收货时间由你做主</td>
              </tr>
            </table>
        </div>
    </div>
    <div class="b_nav">
    	<dl>                                                                                            
        	<dt><a href="#">新手上路</a></dt>
            <dd><a href="#">售后流程</a></dd>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">订购方式</a></dd>
            <dd><a href="#">隐私声明</a></dd>
            <dd><a href="#">推荐分享说明</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">配送与支付</a></dt>
            <dd><a href="#">货到付款区域</a></dd>
            <dd><a href="#">配送支付查询</a></dd>
            <dd><a href="#">支付方式说明</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">会员中心</a></dt>
            <dd><a href="#">资金管理</a></dd>
            <dd><a href="#">我的收藏</a></dd>
            <dd><a href="#">我的订单</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">服务保证</a></dt>
            <dd><a href="#">退换货原则</a></dd>
            <dd><a href="#">售后服务保证</a></dd>
            <dd><a href="#">产品质量保证</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">联系我们</a></dt>
            <dd><a href="#">网站故障报告</a></dd>
            <dd><a href="#">购物咨询</a></dd>
            <dd><a href="#">投诉与建议</a></dd>
        </dl>
        <div class="b_tel_bg">
        	<a href="#" class="b_sh1">新浪微博</a>            
        	<a href="#" class="b_sh2">腾讯微博</a>
            <p>
            服务热线：<br />
            <span>400-123-4567</span>
            </p>
        </div>
        <div class="b_er">
            <div class="b_er_c"><img src="images/er.gif" width="118" height="118" /></div>
            <img src="images/ss.png" />
        </div>
    </div>    
    <div class="btmbg">
		<div class="btm">
        	备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
            <img src="images/b_1.gif" width="98" height="33" /><img src="images/b_2.gif" width="98" height="33" /><img src="images/b_3.gif" width="98" height="33" /><img src="images/b_4.gif" width="98" height="33" /><img src="images/b_5.gif" width="98" height="33" /><img src="images/b_6.gif" width="98" height="33" />
        </div>    	
    </div>
    <!--End Footer End -->    
</div>

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

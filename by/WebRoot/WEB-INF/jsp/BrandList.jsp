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
	<link type="text/css" rel="stylesheet" href="static/css/style.css" />
    <!--[if IE 6]>
    <script src="static/js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->    
    <script type="text/javascript" src="static/js/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="static/js/jquery.bxslider_e88acd1b.js"></script>
    
    <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="static/js/menu.js"></script>    
            
	<script type="text/javascript" src="static/js/lrscroll_1.js"></script>
    
    
	<script type="text/javascript" src="static/js/n_nav.js"></script>    
    <script type="text/javascript" src="static/js/milk_ban.js"></script>
    <script type="text/javascript" src="static/js/paper_ban.js"></script>
    <script type="text/javascript" src="static/js/baby_ban.js"></script>
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
        <span class="fr">
        	<span class="fl">你好，
			<c:choose>
        	<c:when test="${sessionScope.user!=null}">
        	<a href="user.html?method=toUser">${sessionScope.user.userName}</a>|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;<a href="login.html?method=out">退出登录</a>&nbsp;|</c:when>
        	<c:otherwise><a href="login.html?method=login">请  登录</a>&nbsp; <a href="main.html?method=regist" style="color:#ff4e00;">免费注册</a></c:otherwise>
        	</c:choose>&nbsp;</span>
        	<span class="ss">
            	<div class="ss_list">
                	<a href="#">收藏夹</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">我的收藏夹</a></li>
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
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="static/images/s_tel.png" align="absmiddle" /></a></span>
        </span>
    </div>
</div>
<div class="top">
    <div class="logo"><a href="Index.html"><img src="static/images/logo.png" /></a></div>
    <div class="search">
    	<form method="post" action="main.html">
    	<input type="hidden" name="method" value="likePro"/> 
        	<input type="text" value="${proName}" name="proName" id="proName" class="s_ipt" />
            <input type="submit" value="搜索" class="s_btn" />
        </form>                      
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
    </div>
    <div class="i_car">
    	<div class="car_t">购物车 [ <span><c:choose>
    		<c:when test="${sessionScope.car==null}">0</c:when>
    		<c:otherwise>${sessionScope.car.size()}</c:otherwise>
    	</c:choose></span> ]</div>
        <div class="car_bg">
        	<c:if test="${sessionScope.user==null}">
       		<!--Begin 购物车未登录 Begin-->
        	<div class="un_login">还未登录！<a href="login.html?method=login" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            <!--End 购物车未登录 End-->
            </c:if>
        	<c:if test="${sessionScope.car.size()==0}">
       		<!--Begin 购物车未登录 Begin-->
        	<div class="un_login">购物车是空的<a href="#" style="color:#ff4e00;">马上查看商品</a> 添加到购物车！</div>
            <!--End 购物车未登录 End-->
            </c:if>
            <c:if test="${sessionScope.user!=null && sessionScope.car.size()>0}">
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
            	<c:forEach items="${sessionScope.car}" var="product">
            	<li>
                	<div class="img"><a href="#"><img src="static/static/images/files/${product.productId.fileName}" width="58" height="58" /></a></div>
                    <div class="name"><a href="#">${product.productId.name}</a></div>
                    <div class="price">￥<font color="#ff4e00" name="price">${product.productId.price}</font>X<span name="num">${product.proNum}</span></div>
                </li>
                </c:forEach>
            </ul>
            <div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span id="pay">1058</span></div>
            <div class="price_a"><a href="pay.html?method=toCar">去购物车结算</a></div>
            <!--End 购物车已登录 End-->
            </c:if>
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
                <c:forEach items="${list}" var="pro">   
                    <li>
                    	<div class="fj">
                        	<span class="n_img"><span></span><img src="static/images/nav1.png" /></span>
                            <span class="fl">${pro.name}</span>
                        </div>
                        <div class="zj">
                            <div class="zj_l">
                            <c:forEach items="${pro.list}" var="to">
                                <div class="zj_l_c">
                                    <h2>${to.name}</h2>
                                    <c:forEach items="${to.list}" var="three">
                                    <a href="${three.id}">${three.name}</a>|
                                    </c:forEach>
                                </div>
                               </c:forEach>
                            </div>
                            <div class="zj_r">
                                <a href="#"><img src="static/images/n_img1.jpg" width="236" height="200" /></a>
                                <a href="#"><img src="static/images/n_img2.jpg" width="236" height="200" /></a>
                            </div>
                        </div>
                    </li>
                    </c:forEach>
                </ul>            
            </div>
        </div>  
        <!--End 商品分类详情 End-->                                                     
    	<ul class="menu_r">                                                                                                                                               
        	<li><a href="Index.html">首页</a></li>
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
        <div class="l_list">
        	<div class="list_t">
            	<span class="fl list_or">
                	<a href="#" class="now">默认</a>
                    <a href="#">
                    	<span class="fl">销量</span>                        
                        <span class="i_up">销量从低到高显示</span>
                        <span class="i_down">销量从高到低显示</span>                                                     
                    </a>
                    <a href="#">
                    	<span class="fl">价格</span>                        
                        <span class="i_up">价格从低到高显示</span>
                        <span class="i_down">价格从高到低显示</span>     
                    </a>
                    <a href="#">新品</a>
                </span>
                <span class="fr">共发现${proList.size()}件</span>
            </div>
            <div class="list_c">
                <ul class="cate_list">
                <c:forEach items="${proList}" var="pro">
                	<li>
                    	<div class="img"><a href="#"><img src="static/images/files/${pro.fileName}" width="210" height="185" /></a></div>
                        <div class="price">
                            <font>￥<span>${pro.price}</span></font> &nbsp; 26R
                        </div>
                        <div class="name"><a href="#">${pro.name}</a></div>
                        <div class="carbg">
                        	<a href="#" class="ss">收藏</a>
                            <a href="javascript:;" class="j_car">加入购物车</a>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
                <div class="pages">
                	<a href="javascript:;" class="cur">1</a>
                	<c:if test="${page.pageCount>2}"><a href="javascript:;">2</a></c:if>
                	<c:if test="${page.pageCount>3}"><a href="javascript:;">3</a></c:if>
                	<c:if test="${page.pageCount>4}"><a href="javascript:;">4</a></c:if>
                	<c:if test="${page.pageCount>2}"><a href="javascript:;" name="next" class="p_pre">下一页</a></c:if>
                </div>
            </div>
        </div>
    </div>
    
    <!--Begin Footer Begin -->
    <div class="b_btm_bg bg_color">
        <div class="b_btm">
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="static/images/b1.png" width="62" height="62" /></td>
                <td><h2>正品保障</h2>正品行货  放心购买</td>
              </tr>
            </table>
			<table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="static/images/b2.png" width="62" height="62" /></td>
                <td><h2>满38包邮</h2>满38包邮 免运费</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="static/images/b3.png" width="62" height="62" /></td>
                <td><h2>天天低价</h2>天天低价 畅选无忧</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="static/images/b4.png" width="62" height="62" /></td>
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
            <div class="b_er_c"><img src="static/images/er.gif" width="118" height="118" /></div>
            <img src="static/images/ss.png" />
        </div>
    </div>    
    <div class="btmbg">
		<div class="btm">
        	备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
            <img src="static/images/b_1.gif" width="98" height="33" /><img src="static/images/b_2.gif" width="98" height="33" /><img src="static/images/b_3.gif" width="98" height="33" /><img src="static/images/b_4.gif" width="98" height="33" /><img src="static/images/b_5.gif" width="98" height="33" /><img src="static/images/b_6.gif" width="98" height="33" />
        </div>    	
    </div>
    <!--End Footer End -->    
</div>
<script type="text/javascript">
var pageIndex=${page.pageIndex};    //当前页码 
var pageSize=${page.pageSize};     //页大小  
var pageCount=${page.pageCount};    //总页数  
var totalCount=${page.sqlCount};   //总记录数 
var pageBtns="";    //分页按钮总个数
jQuery(function($){
	$("[class='pages']").on("click","a[name=prev]",function(){
	        var pageIndex = $("input#pageIndex").val();
	        if(pageIndex > 1)
	            pageInit(Number(pageIndex) - 1);
    });
	$("[class='pages']").on("click","a[name!=prev][name!=next]",function(){//当前按钮
	        //获取分页按钮的文本
	        var pageBtnTxt = $(this).html();
	        pageInit(pageBtnTxt);
    });
	$("[class='pages']").on("click","a:[name=next]",function(){
	        var pageIndex = $("input#pageIndex").val();
	        if(pageIndex < pageCount)
	            pageInit(Number(pageIndex) + 1);
    });
});

//分页AJAX请求
function pageInit(pageIndex){
    jQuery.ajax({
        url:"",
        type:"POST",
        data:{"pageIndex":pageIndex,proName:jQuery("#proName").val()},
        dataType:"json",
        success:callBack
    });
}
function callBack(data){
	var $ul=jQuery("[class='cate_list']");
	$ul.heml("");
	jQuery(data).each(function(){
		if(this.id){//拼接第二页商品
			$ul.append(""
				+"	<li>                                                                                                            "
                +"	<div class='img'><a href='#'><img src='static/images/files/"+this.fileName+"' width='210' height='185' /></a></div>"
                +"    <div class='price'>                                                                                           "
                +"        <font>￥<span>"+this.price+"</span></font> &nbsp; 26R                                                       "
                +"    </div>                                                                                                        "
                +"    <div class='name'><a href='#'>"+this.name+"</a></div>                                                           "
                +"    <div class='carbg'>                                                                                           "
                +"    	<a href='#' class='ss'>收藏</a>                                                                             "
                +"        <a href='javascript:;' class='j_car'>加入购物车</a>                                                       "
                +"    </div>                                                                                                        "
                +"</li>                                                                                                             "
					+"");
			
		}else if(this.pageIndex){//拼接分页按钮
			pageIndex=this.pageIndex;
			pageSize=this.pageSize;
			pageCount=this.pageCount;
			sqlCount=this.sqlCount;
			 //总页数不足5页
			if(pageCount <= 5 && pageCount > 0) {
				pageBtns = new Array(pageCount);
				for(var i = 0; i < pageCount; i++) {
					pageBtns[i] = i + 1;
				}
			}else{
				//多余五页
				pageBtns = new Array(5);
				if(pageIndex >= 1 && pageIndex <= 3) {//判断是否在1-3页如果是则不向后移动
					for(var i = 0; i < 5; i++) {
						pageBtns[i] = i + 1;
					}
				}else if(pageIndex >= pageCount - 2 &&//判断当前页码是否在总页数的最后两页范围内则将前面页码按钮显示两个其他取消
						pageIndex <= pageCount)	{
					pageBtns[0] = pageCount - 4;
					pageBtns[1] = pageCount - 3;
					pageBtns[2] = pageCount - 2;
					pageBtns[3] = pageCount - 1;
					pageBtns[4] = pageCount;
				}else{//当前页码既不靠前，也不靠后
					pageBtns[0] = pageIndex - 2;
					pageBtns[1] = pageIndex - 1; 
					pageBtns[2]	= pageIndex;
					pageBtns[3] = pageIndex + 1;
					pageBtns[4] = pageIndex + 2;
				}
			}
			 //获取分页功能区
	        var $pageDiv = jQuery("[class='pages']");
	        //清空上一次生成的分页组件
	        $pageDiv.html("");
	        //拼接分页功能部件
	        var pageComponent = "";
	        if(pageIndex > 1)
	            pageComponent += "<a href='javascript:;' name='prev' class='p_pre'>上一页</a>";
            for(var i = 0; i < pageBtns.length; i++) {
                pageComponent +=  "<a href='javascript:' ";
                //设置当前页按钮样式
                if(pageBtns[i] == pageIndex)
                    pageComponent += " class='cur' ";

                pageComponent += " >" + pageBtns[i] + "</a> ";
            }
            //如果当前页是最后一页不显示下一页按钮
            if(pageIndex < pageCount)
                pageComponent += " <li><a href='javascript:' class='p_pre' name='next'>下一页</a></li> ";
            $pageDiv.append(pageComponent);
		}
	});
}
</script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

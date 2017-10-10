<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="static/css/style.css" />
	<base href="<%=basePath%>">
    <!--[if IE 6]>
    <script src="static/js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
    
    <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="static/js/menu.js"></script>    
                
	<script type="text/javascript" src="static/js/n_nav.js"></script>   
    
    <script type="text/javascript" src="static/js/num.js">
    	var jq = jQuery.noConflict();
    </script>     
    
    <script type="text/javascript" src="static/js/shade.js"></script>
    
<title>尤洪</title>
</head>
<body>  
<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
        <span class="fr">
        	<span class="fl">你好，
        	<a href="user.html?method=toUser">${sessionScope.user.userName}</a>|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;<a href="login.html?method=out">退出登录</a>&nbsp;|&nbsp;</span>
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
    	<form>
        	<input type="text" value="" class="s_ipt" />
            <input type="submit" value="搜索" class="s_btn" />
        </form>                      
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
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
                <c:forEach var="Category1" items="${list}">   
                    <li>
                    	<div class="fj">
                        	<span class="n_img"><span></span><img src="static/images/nav1.png" /></span>
                            <span class="fl">${Category1.name}</span>
                        </div>
                        <div class="zj">
                        <c:forEach items="${Category1.list}" var="Category2">
                            <div class="zj_l">
                                <div class="zj_l_c">
                                    <h2>${Category2.name}</h2>
                                    <c:forEach items="${Category2.list}" var="Category3">
                                    <a href=".html?method=?&${Category3.id}">${Category3.name}</a>|
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
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
        	<li><a href="main.html?method=main">首页</a></li>
            <c:forEach items="${list}" var="ll">
            <li><a href=".html?method=?&id=${ll.id}">${ll.name}</a></li></c:forEach>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
<!--End Menu End--> 
<div class="i_bg">  
    <div class="content mar_20">
    	<img src="static/images/img1.jpg" />        
    </div>
    
    <!--Begin 第一步：查看购物车 Begin -->
    <div class="content mar_20">
    	<table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
          <tr>
            <td class="car_th" width="490">商品名称</td>
            <td class="car_th" width="140">属性</td>
            <td class="car_th" width="150">购买数量</td>
            <td class="car_th" width="130">小计</td>
            <td class="car_th" width="140">返还积分</td>
            <td class="car_th" width="150">操作</td>
          </tr>
          <c:forEach items="${sessionScope.car}" var="product" varStatus="s">
          <tr id="${product.id}" <c:if test="${s.count%2==0}">class="car_tr"</c:if>>
            <td>
            	<div class="c_s_img"><img src="static/images/files/${product.productId.fileName}" width="73" height="73" /></div>
                 ${product.productId.name}
            </td>
            <td align="center">颜色：灰色</td>
            <td align="center">
            	<div class="c_num">
                    <input name="p" type="button" value="" style="display: none" onclick="jianUpdate1(jq(this));" class="car_btn_1" />
                	<input type="text" id="num" value="${product.proNum}" onblur="payCount()" name="num" class="car_ipt" readonly/>  
                    <input type="hidden" name="id" value="${product.id}"/>
                    <input name="n" type="button" value="" style="display: none" onclick="addUpdate1(jq(this));" class="car_btn_2" />
                </div>
            </td>
            <td align="center" style="color:#ff4e00;">￥<span name="price">${product.productId.price}</span></td>
            <td align="center">26R</td>
            <td align="center"><a onclick="delval('${product.id}');">删除</a>&nbsp; &nbsp;<a href="#">加入收藏</a></td>
          </tr>
          </c:forEach>
          <tr height="70">
          	<td colspan="6" style="font-family:'Microsoft YaHei'; border-bottom:0;">
            	<!-- <label class="r_rad"><input type="checkbox" name="clear" checked="checked"  /></label><label class="r_txt">清空购物车</label> -->
                <span class="fr">商品总价：<b style="font-size:22px; color:#ff4e00;">￥<span id="pay">2899</span></b></span>
            </td>
          </tr>
          <tr valign="top" height="150">
          	<td colspan="6" align="right">
            	<a href="javascript:tog();"><img id="t" src="static/images/buy3.gif" /></a>&nbsp; &nbsp;<!-- 继续购物  -->
            	<a href="main.html?method=main"><img src="static/images/buy1.gif" /></a>&nbsp; &nbsp;<!-- 继续购物  -->
            	<a href="pay.html?method=toCarTw"><img src="static/images/buy2.gif" /></a><!-- 确认结算 -->
            </td>
          </tr>
        </table>
    </div>
    <input type="hidden" id="del" />
	<!--End 第一步：查看购物车 End--> 
    <!--Begin 弹出层-删除商品 Begin-->
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">             
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv('MyDiv','fade')"><img src="static/images/close.gif" /></span>
            </div>
            <div class="notice_c">
                <table border="0" align="center" style="font-size:16px;" cellspacing="0" cellpadding="0">
                  <tr valign="top">
                    <td>您确定要把该商品移除购物车吗？</td>
                  </tr>
                  <tr height="50" valign="bottom">
                    <td><a href="javascript:toDel();" class="b_sure">确定</a><a href="javascript:hid();" class="b_buy">取消</a></td>
                  </tr>
                </table>
            </div>
        </div>
    </div>    
    <!--End 弹出层-删除商品 End-->
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
	jQuery(function($){
		payCount();
	});
	function tog(){
		jQuery("#t").toggle(function(){//用户点击编辑
			jQuery("#num").removeAttr("readonly");
			jQuery("#t").attr("src","static/images/buy4.gif");
			jQuery("[name='p']").show();
			jQuery("[name='n']").show();
		},function(){//用户点击确定
			//获取用户修改后的购物车
			var id=document.getElementsByName("id");
			var num=document.getElementsByName("num");
			var arrayList="";
			var flag=false;
			for(var i=0;i<id.length;i++){
				if(i==0){arrayList+="[";}
				var forId=id[i].value;
				var fornum=num[i].value;
				if(forId){arrayList+='{"id":"'+forId+'","proNum":"'+fornum+'"},';flag=true}
				if(i==id.length-1){
					if(flag){
						arrayList=arrayList.substring(0, arrayList.length-1)+"]"
					}else{
						arrayList+="]";
					}
				}
			}
			jQuery.post("pay.html",{method:"changeCar",arrayList:arrayList},function(data){
			},"json");
			jQuery("#num").attr("readonly","readonly");
			jQuery("#t").attr("src","static/images/buy3.gif");
			jQuery("[name='p']").hide();
			jQuery("[name='n']").hide();
			//jQuery.post();
		});
	}
	function delval(id){//点击删除按钮传ID
		jQuery("#del").val(id);
		ShowDiv('MyDiv','fade');
	}
	function toDel(){//真正删除
		hid();
		var carId=jQuery("#del").val();
		jQuery.post("pay.html?method=delCarPro",{carId:carId},function(data){
			if (data.status==1) {
				jQuery("#"+carId).remove();
				payCount();//重新计算价格
			}else{
				alert("删除失败");
			}
		},"json");
	}
	function payCount(){//计算购物车物品总值方法
		var pay=0;
		var price=document.getElementsByName("price");
		var num=document.getElementsByName("num");
		for(var i=0;i<price.length;i++){
			pay+=parseInt(price[i].innerHTML)*parseInt(num[i].value);
		}
		jQuery("#pay").html(pay);
		jQuery("[ name='isPay']").html(pay);
	}
	function hid(){//隐藏模态窗口
		jQuery("#fade").hide();
		jQuery("#MyDiv").hide();
	}
</script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

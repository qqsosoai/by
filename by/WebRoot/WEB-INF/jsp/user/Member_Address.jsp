<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        
	<script type="text/javascript" src="static/js/select.js"></script>
        
    
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
<div class="m_top_bg">
    <div class="top">
        <div class="m_logo"><a href="Index.html"><img src="static/images/logo1.png" /></a></div>
        <div class="m_search">
            <form method="post" action="">
                <input type="text" value="" class="m_ipt" />
                <input type="submit" value="搜索" class="m_btn" />
            </form>                      
            <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
        </div>
    </div>
</div>
<!--End Header End--> 
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
	<div class="m_content">
   		<div class="m_left">
        	<div class="left_n">管理中心</div>
            <div class="left_m">
            	<div class="left_m_t t_bg1">订单中心</div>
                <ul>
                	<li><a href="Member_Order.html">我的订单</a></li>
                    <li><a href="user.html?method=toUserAddress" class="now">收货地址</a></li>
                    <li><a href="#">缺货登记</a></li>
                    <li><a href="#">跟踪订单</a></li>
                </ul>
            </div>
            <div class="left_m">
            	<div class="left_m_t t_bg2">会员中心</div>
                <ul>
                	<li><a href="user.html?method=toUser">用户信息</a></li>
                    <li><a href="Member_Collect.html">我的收藏</a></li>
                    <li><a href="Member_Msg.html">我的留言</a></li>
                    <li><a href="Member_Links.html">推广链接</a></li>
                    <li><a href="#">我的评论</a></li>
                </ul>
            </div>
            <div class="left_m">
            	<div class="left_m_t t_bg3">账户中心</div>
                <ul>
                	<li><a href="Member_Safe.html">账户安全</a></li>
                    <li><a href="Member_Packet.html">我的红包</a></li>
                    <li><a href="Member_Money.html">资金管理</a></li>
                </ul>
            </div>
            <div class="left_m">
            	<div class="left_m_t t_bg4">分销中心</div>
                <ul>
                	<li><a href="Member_Member.html">我的会员</a></li>
                    <li><a href="Member_Results.html">我的业绩</a></li>
                    <li><a href="Member_Commission.html">我的佣金</a></li>
                    <li><a href="Member_Cash.html">申请提现</a></li>
                </ul>
            </div>
        </div>
		<div class="m_right">
            <p></p>
            <div class="mem_tit">收货地址</div>
            <c:forEach  items="${sessionScope.userAddress}" var="address">
			<div class="address" id="${address.id}">
            	<div class="a_close"><a href="javascript:del('${address.id}','您确定要把该地址删除么？',1)"><img src="static/images/a_close.png" /></a></div><!-- 删除按钮 -->
            	<table border="0" class="add_t" align="center" style="width:98%; margin:10px auto;" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="2" style="font-size:14px; color:#ff4e00;">${address.remark}</td>
                  </tr>
                  <tr>
                    <td align="right" width="80">收货人姓名：</td>
                    <td>${address.collect}</td>
                  </tr>
                  <tr>
                    <td align="right">配送区域：</td>
                    <td><c:if test="${address.province.areacode==''}">${address.province.areaname}</c:if>
                    ${address.city.areaname}${address.zone.areaname}</td>
                  </tr>
                  <tr>
                    <td align="right">详细地址：</td>
                    <td>${address.detail}</td>
                  </tr>
                  <tr>
                    <td align="right">手机：</td>
                    <td>${address.phone}</td>
                  </tr>
                </table>
                <p align="right">
                	<a href="javascript:;" onclick="del('${address.id}','确定要将改地址设为默认地址么？',2)" name="default" flag="${address.id}" style="color:#ff4e00;<c:if test="${address.isDefault==1}">display:none;</c:if>">设为默认</a>&nbsp; &nbsp; &nbsp; &nbsp; 
                	<a href="user.html?method=toAddress&id=${address.id}" style="color:#ff4e00;">编辑</a>&nbsp; &nbsp; &nbsp; &nbsp; 
                </p>
            </div>
            </c:forEach>
            <div class="mem_tit">
            	<a href="user.html?method=toAddress"><img src="static/images/add_ad.gif" /></a><!-- 添加地址 -->
            </div>
        </div>
    </div>
    <!-- 删除的模态窗口 -->
    <input type="hidden" id="id" />
    <input type="hidden" id="type" />
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">             
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="hid()"><img src="static/images/close.gif" /></span>
            </div>
            <div class="notice_c">
                <table border="0" align="center" style="font-size:16px;" cellspacing="0" cellpadding="0">
                  <tr valign="top">
                    <td id="message">您确定要把该地址删除么？</td>
                  </tr>
                  <tr height="50" valign="bottom">
                    <td><a href="javascript:toDel();" class="b_sure">确定</a><a href="javascript:hid();" class="b_buy">取消</a></td>
                  </tr>
                </table>
            </div>
        </div>
    </div>    
	<!--End 用户中心 End--> 
    <!--Begin Footer Begin -->
    <div class="b_btm_bg b_btm_c">
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
	function del(id,message,type){//用户点击删除地址按钮
		jQuery("#id").val(id);
		jQuery("#type").val(type);
		jQuery("#message").html(message);
		jQuery("#fade").show();
		jQuery("#MyDiv").show();
	}
	function hid(){
		jQuery("#fade").hide();
		jQuery("#MyDiv").hide();
	}
	function toDel(){//用户确认删除地址
		var id=jQuery("#id").val();
		var type=jQuery("#type").val();
		jQuery("#fade").hide();
		jQuery("#MyDiv").hide();
		if(type==1){//用户点击删除
			jQuery.post("user.html",{method:"delUserAddress",id:id},function(data){
			if (data.status==1) {//删除成功
				jQuery("#"+id).remove();
			}else{//删除失败
				alert("用户地址删除失败");
			}
			},"json");
		}else{//用户点击设为默认地址
			jQuery.post("user.html",{method:"updateIsDefault",id:id},function(data){
				if(data.status==1){
					jQuery("[name='default']").each(function(){
						var $a=jQuery(this);
						if($a.attr("flag")==id){
							$a.css("display", "none");
						}else{
							$a.css("display", "inline");
						}
					});
				}else{
					alert("修改默认地址失败");
				}
			},"json");
		}
	}
</script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

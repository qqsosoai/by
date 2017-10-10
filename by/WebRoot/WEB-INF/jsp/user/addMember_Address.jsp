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
		<base href="<%=basePath%>">
    <!--[if IE 6]>
    <script src="static/js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
 
        
    
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
            <form>
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
                    <li><a href="Member_Address.html" class="now">收货地址</a></li>
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
		<form method="post" action="user.html" onsubmit="return isSubmit()">
		<input type="hidden" name="method" value="subAddress"/>
           <!--  数据回显 -->
            <table border="0" class="add_tab" style="width:930px;"  cellspacing="0" cellpadding="0">
              <tr>
                <td width="135" align="right">配送地区</td>
                <td colspan="3" style="font-family:'宋体';">
                	<select id="province" onchange="one()" name="province" style="background-color:#f6f6f6;">
                      <option value="0" >请选择...</option>
                      <c:forEach items="${address}" var="one">
                      <option value='${one.areano}' 
                      <c:choose>
                      	<c:when test="${reqAddress!=null}"><c:if test='${one.areano==reqAddress.province.areano}'>selected</c:if></c:when>
                      </c:choose>
                      >${one.areaname}</option>
                      </c:forEach>
                    </select><!-- 一级结尾 -->
                	<select  id="city" onchange="to()" name="city"><!-- 二级开始 -->
                      <option value="0">请选择...</option>
                      <c:forEach items="${address}" var="one">
                      <c:if test="${one.areano==reqAddress.province.areano}"><!-- 找到用户所在的省遍历城市 -->
                      <c:if test="${reqAddress!=null}"><!-- 用户地址等于空则说明为添加地址不遍历二级菜单 -->
                      <c:forEach items="${one.child}" var="to">
                      <option 
                      <c:if test="${to.areano==reqAddress.city.areano}">selected</c:if>
                      value="${to.areano}">${to.areaname}</option>
                      </c:forEach>
                      </c:if>
                      </c:if>
                      </c:forEach>
                    </select><!-- 二级结尾 -->
                    
                    
                    <select  id="zone" name="zone"><!-- 三级开始 -->
                      <option value="0">请选择...</option>
                      <c:forEach items="${address}" var="one">
                      <c:if test="${reqAddress!=null}">
                      <c:if test="${one.areano==reqAddress.province.areano}">
                      <c:forEach items="${one.child}" var="to">
                      	<c:if test="${to.areano==reqAddress.city.areano}">
                      	<c:forEach items="${to.child}" var="three">
                      	<option value="${three.areano}"
                      	<c:if test="${three.areano==reqAddress.zone.areano}">selected</c:if>
                      	>${three.areaname}</option>
                      	</c:forEach>
                        </c:if>
                      </c:forEach>
                      </c:if>
                      </c:if>
                      </c:forEach>
                    </select><!-- 三级结尾 -->
                    <span style="color:red"></span>
                </td>
              </tr>
              <tr>
                <td align="right">收货人姓名</td>
                <td style="font-family:'宋体';"><input type="text" onblur="isCollect()" onfocus="isfocus(this)" name="collect" value="${reqAddress.collect}" class="add_ipt" /><span style="color:red"></span></td>
                <td align="right">手机</td>
                <td style="font-family:'宋体';"><input type="text" onblur="isPhone()" onfocus="isfocus(this)" name="phone" value="${reqAddress.phone}" class="add_ipt" /><span style="color:red"></span></td>
              </tr>
              <tr>
                <td align="right">详细地址</td>
                <td style="font-family:'宋体';"><input type="text" onblur="isDetail()" onfocus="isfocus(this)" name="detail" value="${reqAddress.detail}" class="add_ipt" /><span style="color:red"></span></td>
                <td align="right">备注</td>
                <td style="font-family:'宋体';"><input type="text" onblur="isremark()" onfocus="isfocus(this)" name="remark" value="${reqAddress.remark}" class="add_ipt"/><span style="color:red"></span></td>
              </tr>
            </table>
           	<p align="right">
            	<a href="javascript:history.go(-1);" class="add_b">返回</a>&nbsp;&nbsp;&nbsp;
            	<input type="submit" value="确认" class="add_b"></input>
            </p> 
           </form>

            
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
       
    <script type="text/javascript" src="static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="static/js/menu.js"></script>    
        
	<script type="text/javascript" src="static/js/select.js"></script>
<script type="text/javascript">
	function one(){//一级改变事件
		var value=jQuery("#province option:selected").val();
		if(value>0){
			var $city=jQuery("#city");
			var $zone=jQuery("#zone");
			jQuery.post("user.html",{method:"ajax",id:value,parent:0},function(data){
				$city.html("").append("<option value='0'>请选择...</option>");
				$zone.html("").append("<option value='0'>请选择...</option>");
				jQuery(data).each(function(){
					$city.append("<option value='"+this.areano+"'>"+this.areaname+"</option>");
				});
			},"json");
		}
	}
	function to(){//二级改变事件
		var value=jQuery("#city option:selected").val();
		if(value>0){
			var $zone=jQuery("#zone");
			jQuery.post("user.html",{method:"ajax",id:value,parent:jQuery("#province option:selected").val()},function(data){
				$zone.html("").append("<option value='0'>请选择...</option>");
				jQuery(data).each(function(){
					$zone.append("<option value='"+this.areano+"'>"+this.areaname+"</option>");
				});
			},"json");
		}
	}
	function isCollect(){
		var $collect=jQuery("[name='collect']");
		if (/^[\s\S]{2,4}$/.test($collect.val())) {
			$collect.next().html("");
			return true;
		}else{
			$collect.next().html("收货人不能为空，且不能超出4个字符");
			return false;
		}
	}
	function isPhone(){
		var $phone=jQuery("[name='phone']");
		if(/^[0-9]{11}$/.test($phone.val())){
			$phone.next().html("");
			return true;
		}else{
			$phone.next().html("手机号码输入不正确");
			return false;
		}
	}
	function isDetail(){
		var $detail=jQuery("[name='detail']");
		if (/^[\s\S]{1,30}$/.test($detail.val())) {
			$detail.next().html("");
			return true;
		}else{
			$detail.next().html("详细信息不能为空，且不能超过30个字符");
			return false;
		}
	}
	function isremark(){
		var $remark=jQuery("[name='remark']");
		if(/^[\s\S]{1,10}$/.test($remark.val())){
			$remark.next().html("");
			return true;
		}else{
			$remark.next().html("地址备注不能为空，且不能超过10个汉字");
			return false;
		}
	}
	function isSubmit(){
		var one=jQuery("#province option:selected").val();
		var to=jQuery("#city option:selected").val();
		var three=jQuery("#zone option:selected").val();
		if (one>0&&to>0&&three>0) {
			jQuery("select + span").html("");
			if(isCollect()&&isPhone()&&isDetail()&&isremark()){
				return true;
			}else{
				return false;
			}
		}else{
			jQuery("select + span").html("请选择送货地址");
			return false;
		}
	}
	function isfocus(obj){
		jQuery(obj).next().html("");
	}
</script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

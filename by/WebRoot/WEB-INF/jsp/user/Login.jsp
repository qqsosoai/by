<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">
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
    <script type="text/javascript" src="static/js/jquery.validate.js"></script>
    <script type="text/javascript" src="static/js/messages_cn.js"></script>
    <script type="text/javascript" src="static/js/shade.js"></script>
    <script type="text/javascript" src="static/js/menu.js"></script>    
	<script type="text/javascript" src="static/js/select.js"></script>
	<script type="text/javascript" src="static/js/lrscroll.js"></script>
    <script type="text/javascript" src="static/js/iban.js"></script>
    <script type="text/javascript" src="static/js/fban.js"></script>
    <script type="text/javascript" src="static/js/f_ban.js"></script>
    <script type="text/javascript" src="static/js/mban.js"></script>
    <script type="text/javascript" src="static/js/bban.js"></script>
    <script type="text/javascript" src="static/js/hban.js"></script>
    <script type="text/javascript" src="static/js/tban.js"></script>
	<script type="text/javascript" src="static/js/lrscroll_1.js"></script>
    
    
<title>邓哀王曹冲</title>
</head>
<body>  
<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
        <span class="fr">
        	<span class="fl">你好，请<a href="#">登录</a>&nbsp; <a href="main.html?method=regist" style="color:#ff4e00;">免费注册</a>&nbsp; </span>
            <span class="fl">|&nbsp;关注我们：</span>
            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="static/images/s_tel.png" align="absmiddle" /></a></span>
        </span>
    </div>
</div>
<!--End Header End--> 
<!--Begin Login Begin-->
<div class="log_bg">	
    <div class="top">
        <div class="logo"><a href="Index.html"><img src="static/images/logo.png" /></a></div>
    </div>
	<div class="login">
    	<div class="log_img"><img src="static/images/l_img.png" width="611" height="425" /></div>
		<div class="log_c">
        	<form id="subform">
        	<input type="hidden" name="method" value="subLogin"/>
            <table border="0" style="width:370px; font-size:14px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr height="50" valign="top">
              	<td width="55">&nbsp;</td>
                <td>
                	<span class="fl" style="font-size:24px;">登录</span>
                    <span class="fr">还没有商城账号，<a href="main.html?method=regist" style="color:#ff4e00;">立即注册</a></span>
                </td>
              </tr>
              <tr height="70">
                <td>用户名</td>
                <td><input type="text" value='<c:if test="${name!=null&&name!=''&&pwd!=null&&pwd!=''}">${name}</c:if>' name="userName" id="userName" onfocus="isfocus(this)" onblur="isUserName()" class="l_user" /></td>
              </tr>
              <tr>
				<td></td>
				<td><div style="color: red"></div></td>
			 </tr>
              <tr height="70">
                <td>密&nbsp; &nbsp; 码</td>
                <td><input type="password" id="password" name="password" value="<c:if test="${name!=null&&name!=''&&pwd!=null&&pwd!=''}">${pwd}</c:if>" onfocus="isfocus(this)" onblur="isPassword()" class="l_pwd" /></td>
              </tr>
              <tr>
				<td></td>
				<td><div style="color: red"></div></td>
			 </tr>
              <tr height="70">
				<td>验&nbsp;证&nbsp;码：</td>
				<td><input type="text" width="100px" height="30px" name='codetext' flag="false" onblur="isVali()" onfocus="isfocus(this)" id="vali" name="vali" style="width: 70px">
					<img src="data.html?method=isVal"
					  id='code' onclick="change()">
					<a href="javascript:;" onclick="change()">看不清，换一张</a></td>
			 </tr>
			 <tr>
				<td></td>
				<td><div style="color: red"></div></td>
			 </tr>
              <tr>
              	<td>&nbsp;</td>
                <td style="font-size:12px; padding-top:20px;">
                	<span style="font-family:'宋体';" class="fl">
                    	<label class="r_rad"><input type="checkbox" value="y" name="my" <c:if test="${name!=null&&name!=''&&pwd!=null&&pwd!=''}">checked</c:if>/></label><label class="r_txt">请保存我这次的登录信息</label>
                    </span>
                    <span class="fr"><a href="#" style="color:#ff4e00;">忘记密码</a></span>
                </td>
              </tr>
              <tr height="60">
              	<td>&nbsp;</td>
                <td><input type="button" onclick="isSubmit();"  value="登录" class="log_btn" /></td>
              </tr>
            </table>
            </form>
        </div>
    </div>
</div>
<!--End Login End--> 
<!--Begin Footer Begin-->
<div class="btmbg">
    <div class="btm">
        备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
        <img src="static/images/b_1.gif" width="98" height="33" /><img src="static/images/b_2.gif" width="98" height="33" /><img src="static/images/b_3.gif" width="98" height="33" /><img src="static/images/b_4.gif" width="98" height="33" /><img src="static/images/b_5.gif" width="98" height="33" /><img src="static/images/b_6.gif" width="98" height="33" />
    </div>    	
</div>
<!-- <script src="static/js/private/login.js"></script> -->
<script type="text/javascript">
function isUserName(){//验证用户
	var name=$("#userName").val();
	if (/^\S{5,20}$/.test(name)) {
		$("#userName").parents("tr").next().find("div").html("");
		return true;
	}else{
		$("#userName").parents("tr").next().find("div").html("用户名不能小于5位，大于20位");
		return false;
	}
}
function isPassword(){//验证密码
	if (/^[a-zA-Z|0-9]{5,12}$/.test($("#password").val())) {
		$("#password").parents("tr").next().find("div").html("");
		return true;
	}else{
		$("#password").parents("tr").next().find("div").html("用户密码不能小于5位，大于12位");
		return false;
	}
}
function isVali(){//ajax验证验证码
	var vali=$("#vali").val();
	if (vali!=null && vali!='') {
		if (vali.length==4) {
			$.post("data.html", {
				method : 'validate',
				vali : vali
			}, function(data) {
				if (data.data == true) {
					$("#vali").parents("tr").next().find("div").html("");
					$("#vali").attr("flag", "true");
				} else {
					$("#vali").parents("tr").next().find("div").html(
							"验证码输入不正确");
					change();
					$("#vali").attr("flag", "false");
				}
			}, "json");
		}else{
			$("#vali").parents("tr").next().find("div").html("验证码必须是4位");
		}
	}else{
		$("#vali").parents("tr").next().find("div").html("验证码不能为空");
	}
}
function isfocus(obj){
	$(obj).parents("tr").next().find("div").html("");
}
function isSubmit(){
	if (isUserName()&&isPassword()) {
		if ($("#vali").attr("flag")=="true") {
			var userName=$("#userName").val();
			var password=$("#password").val();
			var my=$("#my").val();
			if(!my){my=null;}
			/* $.post("login.html",{method:"subLogin",userName:userName,
				password:password,my:my},function(data){
					if (data.status==1) {
						alert("登录成功");
						//showMessage("3秒之后跳转！"+data.data);
						window.location.href='main.html?method=main';
						//window.location.href='http://www.baidu.com';
					}else{
						alert("登录失败");
						//showMessage(data.message);
					}
				},"json"); */
			$.ajax({
				url:"login.html",
				dataType:"json",
				data:{method:"subLogin",userName:userName,
					password:password,my:my},
				success:function(data){
					if (data.status==1) {
						//showMessage("3秒之后跳转！"+data.data);
						window.location.href='main.html?method=main';
						//window.location.href='http://www.baidu.com';
					}else{
						$("[class='log_btn']").val("登录");
						alert("登录失败");
						//showMessage(data.message);
					}
				},
				beforeSend:function(){$("[class='log_btn']").val("正在登录");
				$("[class='log_btn']").unbind("click");
				},
				error:function(){$("[class='log_btn']").bind("click",isSubmit);
				$("[class='log_btn']").val("登录");
				alert("登录失败");
				}
			});
			return true;
		}else{
			$("#vali").parents("tr").next().find("div").html(
			"验证码输入不正确");
			return false;
		}
	}else{
		return false;
	}
}
//点击更新验证码
function change() {
	document.getElementById("code").src = "data.html?method=isVal&a="
			+ new Date();
}
</script>
<!--End Footer End -->    
</body>
<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>

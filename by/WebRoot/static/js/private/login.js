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
			alert(my)
			if(!my){my=null;}
			$.post("login.html",{method:"subLogin",userName:userName,
				password:password,my:my},function(data){
					if (data.status==1) {
						alert("登录成功");
						//showMessage("3秒之后跳转！"+data.data);
						window.location='mian.html?method=main';
					}else{
						showMessage(data.message);
					}
				},"json");
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
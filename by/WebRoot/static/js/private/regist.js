jQuery(function($){
	$("#subform").validate({
		//鼠标失去焦点立刻验证
		onfocusout:function(element){$(element).valid()},
		rules:{//验证规则
			loginName:{
				required:true,
				minlength:5,
				maxlength:20,
				remote:{
					type:"POST",
	    			url:"regist",
	    			dataType:"html",
	    			data:{method:"validateLoginName",
	    				loginName:function(){
	    					return $("#loginName").val();}
	    			},
	    			dataFilter:function(data,type){
	    				if (data=="true") {
							return false;
						}else{
							return true;
						}
	    			}
				}
			},
			password:{//密码
				required:true,
				minlength:5,
				maxlength:12
			},
			rPassword:{//重复密码
				required:true,
				equalTo:'#password'
			},
			email:{//验证邮箱
				required:true,
				email:true
			},
			phone:{//验证手机
				required:true,
				rangelength:[11,11]
			},
			userName:{//验证用户姓名
				required:true,
				minlength:2,
				maxlength:4
			},
			identityCode:{
				required:true,
				rangelength:[18,18]
			},
			ckeck:{
				required:true
			}
		},
		messages:{//验证错误提示信息
			loginName:{
				required:'请填写用户名',
				minlength:'用户名不能少于5位',
				maxlength:'用户名不能大于20位',
				remote:'用户名已存在,请重新输入'
			},
			password:{
				required:'请输入密码',
				minlength:'密码不能小于5位',
				maxlength:'密码最多12位'
			},
			rPassword:{//重复密码
				required:'请输入重复密码',
				equalTo:'两次密码输入不一致'
			},
			email:{//验证邮箱
				required:'请输入邮箱',
				email:'邮箱格式不正确'
			},
			phone:{//验证手机
				required:'请填写手机号码',
				rangelength:'手机号码输入不正确'
			},
			userName:{//验证用户姓名
				required:'请输入姓名',
				minlength:'姓名不能小于2位',
				maxlength:'中国姓名没有超过4位的，您是火星来的么？'
			},
			identityCode:{//验证身份证号
				required:'请输入身份证号',
				rangelength:'身份证号格式不正确'
			},
			ckeck:{
				required:'请同意协议'
			}
		},
		submitHandler:function(form){
			var loginName=$("#loginName").val();
			var password=$("#password").val();
			var rPassword=$("#rPassword").val();
			var email=$("#email").val();
			var phone=$("#phone").val();
			var userName=$("#userName").val();
			var identityCode=$("#identityCode").val();
			var sex=$("[name='sex']:ckecked").val();
			var method="sub";
			$.ajax({type:"GET",
					url:"regist",
					dataType:"json",
					data:{loginName:loginName,
						password:password,
						rPassword:rPassword,
						email:email,
						method1:method,
						phone:phone,
						userName:userName,
						identityCode:identityCode,
						sex:sex},
				success:function(){
					if (data.status==1) {  //证明 成功
	    				 showMessage("3秒之后跳转！"+data.message);
	    				 setTimeout("window.location.href='login.html?method=login'",3000);
					}else{
						showMessage(data.message);
					}
				}});
		},invalidHandler:function(form,validator){
    	    return false;
		},
	});
});
$(function(){
    var pageIndex="";    //当前页码 
    var pageSize="";     //页大小  
    var pageCount="";    //总页数  
    var totalCount="";   //总记录数 
    var pageBtns="";    //分页按钮总个数
    
    
    
    //用户选择页码的按钮动态绑定click事件
    $("div.pagination ul").on("click","a[name=prev]",function(){
        var pageIndex = $("input#pageIndex").val();
        if(pageIndex > 1)
            pageInit(Number(pageIndex) - 1);
    });
    $("div.pagination ul").on("click","a[name!=prev][name!=next]",function(){//当前按钮
        //获取分页按钮的文本
        var pageBtnTxt = $(this).html();
        pageInit(pageBtnTxt);
    });
    $("div.pagination ul").on("click","a:[name=next]",function(){
        var pageIndex = $("input#pageIndex").val();
        if(pageIndex < pageCount)
            pageInit(Number(pageIndex) + 1);
    });
 
 
    //为删除用户按钮动态绑定click事件
    $("tbody").on("click","a:contains('删除')",function(){
    
    	//获取删除按钮要传递的url
    	var url=$("tbody a:contains('删除')").attr("name");
    	//将url传递给隐藏域，用于模态窗口传递url
    	$("input#url").val(url);
    	//触发模态窗口
    	$("#myModal").modal("show");
    });
    
    //模态窗口按钮事件
    $("div#myModal a:contains('确定')").click(function(){
    	window.location.href=$("input#url").val();
    });
    
    
    
    //初始化分页数据
    pageInit(pageIndex);   
    
    //分页AJAX请求
    function pageInit(pageIndex){
        $.ajax({
            url:"UserPageServlet",
            type:"POST",
            data:{"pageIndex":pageIndex},
            dataType:"json",
            success:callBack
        });
    }
    
    var pageBtns = "";
    
    //回掉函数
    function callBack(data) {
        //清楚上一页的数据
        $("tbody").html("");
        $(data).each(function(){
            if(this.pageUtil != null) {
                pageIndex = this.pageUtil.pageIndex; 
                pageSize = this.pageUtil.pageSize;  
                pageCount = this.pageUtil.pageCount; 
                totalCount = this.pageUtil.totalCount;
                
            }
            
            
            
            //用户年龄
            var age = new Date().getFullYear() - new Date(this.birthday).getFullYear();  
            
          //添加到表格中的行数据字符串穿
            var rowStr = " <tr><td style='vertical-align: middle;'>" + this.userCode + "</td> " 
			           + " <td style='vertical-align: middle;'>" + this.userName + "</td>  " 
			           + " <td style='vertical-align: middle;'>" + ((this.gender == 1) ? "女" : "男") + "</td>  " 
			           + " <td class='center' style='vertical-align: middle;'>" + age + "</td>  " 
			           + " <td class='center' style='vertical-align: middle;'>" + this.phone + "</td> " 
			           + " <td class='center' style='vertical-align: middle;'>" + this.typeName + "</td> "
			           + " <td>";
            
            
            
            //获取获取当前用户的用户类型，判断权限
            var loginUserType = $("input[name=userType]").val();
            //获取当前用户的id，用于权限条件
            var userId = $("input[name=userId]").val();
            
            //系统用户拥有操作用户表的所有权限
            if(loginUserType == 1) {   
         	   rowStr +=  " <a class='btn btn-success' href='UserServlet?requSign=view&id="+ this.id +"'> "
				        + "    <i class='icon-zoom-in icon-white'></i>查看</a> "
						+ " <a class='btn btn-info' href='UserServlet?requSign=update&id="+ this.id +"'> "
						+ "	   <i class='icon-edit icon-white'></i>修改</a> ";
         	   //不能删除自身、与其等级相同的用户
               if(this.userType > 1) {  
             	  rowStr += " <a class='btn btn-danger' href='javascript:' name='UserServlet?requSign=delete&id="+ this.id +"'> "
					      + "    <i class='icon-trash icon-white'></i>删除</a> ";
               }
                
               rowStr += " </td></tr>";
            }else if(loginUserType == 2) {   //经理拥有所有权限
         	    rowStr += " <a class='btn btn-success' href='UserServlet?requSign=view&id="+ this.id +"'> "
		                + "    <i class='icon-zoom-in icon-white'></i>查看</a> ";
         	    if(this.userType > 2 || this.id == userId){  //不能修改与其等级相同的用户和系统用户
                    rowStr += " <a class='btn btn-info' href='UserServlet?requSign=update&id="+ this.id +"'> "
					        + "	   <i class='icon-edit icon-white'></i>修改</a> ";
                }
         	    if(this.userType > 2) {    //不能删除自身、与其等级相同的用户和系统用户
                	rowStr += " <a class='btn btn-danger' href='javascript:' name='UserServlet?requSign=delete&id="+ this.id +"'> "
				            + "    <i class='icon-trash icon-white'></i>删除</a> ";
                }
                
                rowStr += " </td></tr>";
            }else if(loginUserType == 3) {   //普通员工权限
            	rowStr += " <a class='btn btn-success' href='UserServlet?requSign=view&id="+ this.id +"'> "
		                + "    <i class='icon-zoom-in icon-white'></i>查看</a> ";
         	    if(this.id == userId) {  //普通员工仅能对自己修改，但不能删除
         		   rowStr += " <a class='btn btn-info' href='UserServlet?requSign=update&id="+ this.id +"'> "
			               + "	   <i class='icon-edit icon-white'></i>修改</a> ";
         	    }
         	    rowStr += " </td></tr>";
            }
            
            //将拼接好的表格行列追加到表格主体
            $("tbody").append(rowStr);
            
            
            
            //总页数不足5页
    		if(pageCount <= 5 && pageCount > 0) {
    			pageBtns = new Array(pageCount);
    			for(var i = 0; i < pageCount; i++) {
    				pageBtns[i] = i + 1;
    			}
    		}else{
    			//多余五页
    			pageBtns = new Array(5);
    			if(pageIndex >= 1 && pageIndex <= 3) {
    				for(var i = 0; i < 5; i++) {
    					pageBtns[i] = i + 1;
    				}
    			}else if(pageIndex >= pageCount - 2 &&
    					pageIndex <= pageCount)	{
    				pageBtns[0] = pageCount - 4;
    				pageBtns[1] = pageCount - 3;
    				pageBtns[2] = pageCount - 2;
    				pageBtns[3] = pageCount - 1;
    				pageBtns[4] = pageCount;
    			}else{
    				pageBtns[0] = pageIndex - 2;
    				pageBtns[1] = pageIndex - 1; 
    				pageBtns[2]	= pageIndex;
    				pageBtns[3] = pageIndex + 1;
    				pageBtns[4] = pageIndex + 2;
    			}
    		}
            
            
            //将拼接好的表格行列追加到表格主体
            $("tbody").append(rowStr);
             
        });
        
       
        
        
        //分页功能
        
      //获取分页功能区
        var $pageDiv = $("div.pagination ul");
        //清空上一次生成的分页组件
        $pageDiv.html("");

        //拼接分页功能部件
        var pageComponent = "";

        //如果当前页是第一页不显示上一页按钮
        if(pageIndex > 1)
            pageComponent += " <li><a href='javascript:' name='prev'>上一页</a></li> ";

        for(var i = 0; i < pageBtns.length; i++) {
            pageComponent +=  " <li ";
            //设置当前页按钮样式
            if(pageBtns[i] == pageIndex)
                pageComponent += "class='active'";

            pageComponent += "><a href='javascript:'>" + pageBtns[i] + "</a></li> ";
        }

        //如果当前页是最后一页不显示下一页按钮
        if(pageIndex < pageCount)
            pageComponent += " <li><a href='javascript:' name='next'>下一页</a></li> ";

        pageComponent += "<input type='hidden' id='pageIndex' value=' " + pageIndex + " '/> ";

        $pageDiv.append(pageComponent);
		    
       
    }
    
});
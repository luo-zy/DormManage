<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>宿舍管理系统登录</title>
<link href="<%=path%>/style/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=path%>/style/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="<%=path%>/style/bootstrap/js/jQuery.js"></script>
<script src="<%=path%>/style/bootstrap/js/bootstrap.js"></script>
<style type="text/css">
	  body {
        padding-top: 200px;
        padding-bottom: 40px;
        background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
      }
      
      .radio {
      	padding-top: 10px;
       	padding-bottom:10px;
      }
      
      .form-signin-heading{
      	text-align: center;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 0px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
</style>
<script type="text/javascript">
function toLogin(){
	var username = $("#username").val();
	var password = $("#password").val();
	if(username == ""){
		$("#message").html("请输入用户名！");
		return;
	}
	if(password == ""){
		$("#message").html("请输入密码！");
		return;
	}
	$("#myForm").submit();
}


</script>

</head>
<body style="background-image: url('<%=path%>/style/images/bg.jpg');">
<div class="container">
      <form id="myForm" name="myForm" class="form-signin" action="<%=path%>userLogin.action" method="post">
        <h2 class="form-signin-heading"><font color="gray">宿舍管理系统</font></h2>
        <input id="username" name="username" type="text" class="input-block-level" value="${username}" placeholder="用户名...">
        <input id="password" name="password" type="password" class="input-block-level" placeholder="密码..." >
        <label class="radio inline">
      	  	<input id="admin" type="radio" name="role" value="admin"  checked/> 系统管理员
		</label>
		<label class="radio inline">
			<input id="manager" type="radio" name="role" value="manager"/> 宿舍管理员
		</label>
		<label class="radio inline">
			<input id="student" type="radio" name="role" value="student"/> 学生
		</label>
        <label class="checkbox">
          <font id="message" color="red">${message}</font>  
        </label>
        <label style="text-align: center;">
	        <button class="btn btn-large btn-primary" type="button" onclick="toLogin();">登录</button>
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <button class="btn btn-large btn-primary" type="button" >重置</button>
        </label>
      </form>
</div>
</body>
</html>
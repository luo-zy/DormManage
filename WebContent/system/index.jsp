<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>宿舍管理系统</title>
<link href="<%=path%>/style/css/dorm.css" rel="stylesheet">
<link href="<%=path%>/style/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=path%>/style/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="<%=path%>/style/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/dataTables.bootstra.css">
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/jquery.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/jquery.dataTables.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/bootstrap.min.js"></script>
<script type="text/javascript" src="http://sandbox.runjs.cn/uploads/rs/238/n8vhm36h/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/style/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path%>/style/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script src="<%=path%>/style/bootstrap/js/bootstrap.js"></script>

<style type="text/css">
.bs-docs-sidenav {
    background-color: #fff;
    border-radius: 6px;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
    padding: 0;
    width: 228px;
}

.bs-docs-sidenav > li > a {
    border: 1px solid #e5e5e5;
    display: block;
    padding: 8px 14px;
    margin: 0 0 -1px;
}
.bs-docs-sidenav > li:first-child > a {
    border-radius: 6px 6px 0 0;
}
.bs-docs-sidenav > li:last-child > a {
    border-radius: 0 0 6px 6px;
}
.bs-docs-sidenav > .active > a {
    border: 0 none;
    box-shadow: 1px 0 0 rgba(0, 0, 0, 0.1) inset, -1px 0 0 rgba(0, 0, 0, 0.1) inset;
    padding: 9px 15px;
    position: relative;
    text-shadow: 0 1px 0 rgba(0, 0, 0, 0.15);
    z-index: 2;
}
.bs-docs-sidenav .icon-chevron-right {
    float: right;
    margin-right: -6px;
    margin-top: 2px;
    opacity: 0.25;
}
.bs-docs-sidenav > li > a:hover {
    background-color: #f5f5f5;
}
.bs-docs-sidenav a:hover .icon-chevron-right {
    opacity: 0.5;
}
.bs-docs-sidenav .active .icon-chevron-right, .bs-docs-sidenav .active a:hover .icon-chevron-right {
    background-image: url("../img/glyphicons-halflings-white.png");
    opacity: 1;
}
</style>
<script type="text/javascript">
function logout(){
	if (confirm("确定要退出？")) {
		window.location="<%=path%>logout.action";
    }
}
</script>
</head>
<body>
<div class="container-fluid" style="padding-right: 0px;padding-left: 0px;">
	<div region="north" style="height: 100px;background-image: url('<%=path%>/style/images/bg.jpg')">
		<div align="left" style="width: 75%;height:100px ;float: left;padding-top: 40px;padding-left: 30px;" ><font color="#0088cc" size="6" >学生公寓管理系统</font></div>
		<div style="padding-top: 70px;padding-right: 20px;">当前用户：&nbsp;<font color="red" size="5px">${currentUser.name }</font>&nbsp;&nbsp;&nbsp;
		<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='<%=path%>toPasswordUpdate.action'">修改密码</button>
		<button class="btn btn-mini btn-info" type="button" onclick="logout();">退出系统</button>
		</div>
	</div>
</div>
	<div class="container-fluid">
		<div class="row-fluid">
				<div class="span2 bs-docs-sidebar" >
					<ul class="nav nav-list bs-docs-sidenav">
						<li id="indexLi"><a href="<%=path%>index.action"><i class="icon-chevron-right"></i>首页</a></li>
						<li id="userInfoLi"><a href="<%=path%>user/userInfo.action?id=${currentUser.id}"><i class="icon-chevron-right"></i>个人信息</a></li>
						<li id="floorLi"><a href="<%=path%>floor/getFloorList.action"><i class="icon-chevron-right"></i>公寓管理</a></li>
						<c:if test="${currentUserRole == 'admin'}">
							<li id="userLi"><a href="<%=path%>user/getUserList.action"><i class="icon-chevron-right"></i>用户管理</a></li>
						</c:if>
						<c:if test="${currentUserRole == 'manager'}">
							<li id="userLi"><a href="<%=path%>user/getUserList.action"><i class="icon-chevron-right"></i>学生管理</a></li>
							<li id="inAndOutLi"><a href="#"><i class="icon-chevron-right"></i>出入管理</a></li>
							<li id="chargeLi"><a href="#"><i class="icon-chevron-right"></i>缴费管理</a></li>
							<li id="lostpropertyLi"><a href="#"><i class="icon-chevron-right"></i>失物管理</a></li>
							<li id="disciplineLi"><a href="#"><i class="icon-chevron-right"></i>公寓纪律</a></li>
							<li id="hygieneLi"><a href="#"><i class="icon-chevron-right"></i>公寓卫生</a></li>
							<li id="noticeLi"><a href="#"><i class="icon-chevron-right"></i>公告管理</a></li>
						</c:if>
						<c:if test="${currentUserRole == 'student'}">
							<li id="chargeLi"><a href="#"><i class="icon-chevron-right"></i>缴费记录</a></li>
						</c:if>
					</ul>
				</div>
			<%-- <c:if test="${currentUserRole == 'student'}">
				<div class="span2 bs-docs-sidebar" >
					<ul class="nav nav-list bs-docs-sidenav">
						<li><a href="<%=path%>index.action"><i class="icon-chevron-right"></i>首页</a></li>
						<li><a href="<%=path%>user/getUserList.action?role=student"><i class="icon-chevron-right"></i>学生管理</a></li>
						<li><a href="<%=path%>floor/getFloorList.action"><i class="icon-chevron-right"></i>公寓管理</a></li>
						<!-- <li><a href=""><i class="icon-chevron-right"></i>个人信息</a></li> -->
						<li><a href="<%=path%>logout.action"><i class="icon-chevron-right"></i>退出系统</a></li>
					</ul>
				</div>
			</c:if> --%>
			<div class="span10">
				<jsp:include page="${mainPage==null?'system/Welcome.jsp':mainPage}"></jsp:include>
			</div>
			</div>
		</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<div class="data_list">
		<div class="data_list_title">用户管理</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#userLi").addClass("active");
		});
		function addUser(role){
			window.location='<%=path%>user/create.action?role='+role;
		}
		function del(id){
			if (confirm("确认要删除？")) {
				window.location="<%=path%>user/delete.action?id="+id;
	        }
		}
		</script>
		<form name="myForm" class="form-search" method="post" action="<%=path%>user/getUserList.action">
			<c:if test="${currentUserRole == 'admin'}">
			<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="addUser('admin');">添加系统管理员</button>
			<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="addUser('manager');">添加宿舍管理员</button>
			</c:if>
			<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="addUser('student');">添加学生</button>
			<span class="data_search">
				<c:if test="${currentUserRole == 'admin'}">
				角色：<select id="role" name="role" style="width: 90px;">
					<option value="">请选择</option>
					<option value="admin" ${role == 'admin'?'selected':'' }>系统管理员</option>
					<option value="manager" ${role == 'manager'?'selected':'' }>宿舍管理员</option>
					<option value="student" ${role == 'student'?'selected':'' }>学生</option>
				</select>
				</c:if>
				姓名：<input id="name" name="name" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${name }">
				&nbsp;<button type="submit" class="btn btn-info" >搜索</button>
			</span>
		</form>
		<div>
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>用户名</th>
					<th>角色</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>电话</th>
					<th>地址</th>
					<th>操作</th>
				</tr>
				<c:forEach  varStatus="i" var="user" items="${userList }">
					<tr>
						<td>${user.username }</td>
						<td>${user.role }</td>
						<td>${user.name }</td>
						<td>${user.sex }</td>
						<td>${user.age }</td>
						<td>${user.tel }</td>
						<td>${user.address }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='<%=path%>user/update.action?id=${user.id}'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="del(${user.id})">删除</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center"><font color="red">${message}</font></div>
</div>
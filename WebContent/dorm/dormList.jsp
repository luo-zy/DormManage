<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<div class="data_list">
		<div class="data_list_title">宿舍管理</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#floorLi").addClass("active");
		});
		function add(){
			window.location='<%=path%>dorm/create.action';
		}
		function del(id){
			if (confirm("确认要删除？")) {
				window.location="<%=path%>dorm/delete.action?id="+id;
	        }
		}
		</script>
		<form name="myForm" class="form-search" method="post" action="<%=path%>dorm/getDormList.action">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="add();">添加宿舍</button>
				<span class="data_search">
					宿舍名称：<input id="dormName" name="dormName" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${dormName }">
					&nbsp;<button type="submit" class="btn btn-info" >搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>宿舍楼</th>
					<th>宿舍名称</th>
					<th>说明</th>
					<th>操作</th>
				</tr>
				<c:forEach  varStatus="i" var="dorm" items="${dormList }">
					<tr>
						<td>${dorm.floor.floorName}</td>
						<td>${dorm.dormName}</td>
						<td>${dorm.detail }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='<%=path%>dorm/update.action?id=${dorm.id}'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="del(${dorm.id})">删除</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center"><font color="red">${message}</font></div>
		<div align="center">
			<button class="btn btn-primary" type="button" onclick="javascript:window.location='<%=path%>floor/getFloorList.action'">返回</button>
		</div>
</div>
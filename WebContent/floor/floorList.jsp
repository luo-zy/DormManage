<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<div class="data_list">
		<div class="data_list_title">公寓管理</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#floorLi").addClass("active");
		});
		function addFloor(){
			window.location='<%=path%>floor/create.action';
		}
		function addDorm(){
			window.location='<%=path%>dorm/create.action';
		}
		function del(id){
			if (confirm("确认要删除？")) {
				window.location="<%=path%>floor/delete.action?id="+id;
	        }
		}
		</script>
		<form name="myForm" class="form-search" method="post" action="<%=path%>floor/getFloorList.action">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="addFloor();">添加楼栋</button>
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="addDorm();">添加宿舍</button>
				<span class="data_search">
					楼栋名称：<input id="floorName" name="floorName" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${floorName }">
					&nbsp;<button type="submit" class="btn btn-info" >搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>楼栋名称</th>
					<th>管理员</th>
					<th>说明</th>
					<th>操作</th>
				</tr>
				<c:forEach  varStatus="i" var="floor" items="${floorList }">
					<tr>
						<td>${floor.floorName}</td>
						<td>${floor.manager.name}</td>
						<td>${floor.detail }</td>
						<td>
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='<%=path%>floor/update.action?id=${floor.id}'">修改</button>&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='<%=path%>dorm/getDormList.action?floorId=${floor.id}'">查看宿舍</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="del(${floor.id})">删除</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center"><font color="red">${message}</font></div>
</div>
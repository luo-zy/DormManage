<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
	function checkForm(){
		var floorName = $("#floorName").val();
		if(floorName == ""){
			$("#error").html("信息填写不完整！");
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("#floorLi").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${floor == null}">
				添加楼栋
			</c:when>
			<c:otherwise>
				修改楼栋
			</c:otherwise>
		</c:choose>
		</div>
		<form action="<%=path%>floor/save.action" method="post"  onsubmit="return checkForm();">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value="${floor.id }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>楼栋名称：</td>
							<td><input type="text" id="floorName"  name="floorName" value="${floor.floorName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>&nbsp;楼栋介绍：</td>
							<td><input type="text" id="detail"  name="detail" value="${floor.detail }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>管理员：</td>
							<td>
								<select id="userId" name="userId" style="width: 100px;">
									<option value="">请选择...</option>
									<c:forEach  varStatus="i" var="user" items="${userList }">
									<option value="${user.id}"${floor.manager.id==user.id?'selected':'' }>${user.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='<%=path%>floor/getFloorList.action'">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red"></font>
					</div>
			</div>
		</form>
</div>
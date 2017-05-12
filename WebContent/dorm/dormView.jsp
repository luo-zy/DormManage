<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
	function checkForm(){
		var dormName = $("#dormName").val();
		var floor=$("#floorId").val();
		if(dormName == ""){
			$("#error").html("信息填写不完整！");
			return false;
		}
		if(floor==""){
			$("#error").html("请选择宿舍楼！");
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
			<c:when test="${dorm == null}">
				添加宿舍
			</c:when>
			<c:otherwise>
				修改宿舍
			</c:otherwise>
		</c:choose>
		</div>
		<form action="<%=path%>dorm/save.action" method="post"  onsubmit="return checkForm();">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value="${dorm.id }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>宿舍楼：</td>
							<td>
								<select id="floorId" name="floorId" style="width: 90px;">
									<option value="">请选择</option>
									<c:forEach var="floor" items="${floorList }">
										<option value="${floor.id }" ${dorm.floor.id==floor.id?'selected':'' }>${floor.floorName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>宿舍名称：</td>
							<td><input type="text" id="dormName"  name="dormName" value="${dorm.dormName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>&nbsp;宿舍介绍：</td>
							<td><input type="text" id="detail"  name="detail" value="${dorm.detail }"  style="margin-top:5px;height:30px;" /></td>
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
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
	function checkForm(){
		var username=$("#username").val();
		var password=$("#password").val();
		var rPassword=$("#rPassword").val();
		var name=$("#name").val();
		var sex=$("#sex").val();
		var tel=$("#tel").val();
		
		if(username==""||password==""||rPassword==""||name==""||sex==""||tel==""){
			$("#error").html("信息填写不完整！");
			return false;
		} else if(password!=rPassword){
			$("#error").html("密码填写不一致！");
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("#userLi").addClass("active");
		showTr();
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${user != null }">
				修改信息
			</c:when>
			<c:otherwise>
				添加信息
			</c:otherwise>
		</c:choose>
		</div>
		<form action="<%=path%>user/save.action" method="post"  onsubmit="return checkForm();">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value="${user.id }"/>
				<input type="hidden" id="role" name="role" value="${role }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>用户名：</td>
							<td><input type="text" id="username"  name="username" value="${user.username }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>密码：</td>
							<td><input type="password" id="password"  name="password" value="${user.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>重复密码：</td>
							<td><input type="password" id="rPassword" value="${user.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>姓名：</td>
							<td><input type="text" id="name"  name="name" value="${user.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别：</td>
							<td>
								<select id="sex" name="sex" style="width: 90px;">
									<option value="">请选择...</option>
									<option value="男" ${user.sex eq "男"?'selected':'' }>男</option>
									<option value="女" ${user.sex eq "女"?'selected':'' }>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>年龄：</td>
							<td><input type="text" id="age"  name="age" value="${user.age }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>地址：</td>
							<td><input type="text" id="address"  name="address" value="${user.address }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>联系电话：</td>
							<td><input type="text" id="tel"  name="tel" value="${user.tel }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<c:if test="${role == 'student'}">
						<tr>
							<td><font color="red">*</font>学号：</td>
							<td><input type="text" id="stuNumber"  name="stuNumber" value="${user.stuNumber }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">&nbsp;</font>寝室：</td>
							<td>
								<select id="dormId" name="dormId" style="width: 100px;">
									<option value="">请选择...</option>
									<c:forEach  varStatus="i" var="dorm" items="${dormList }">
									<option value="${dorm.id}" ${user.dorm.id==dorm.id?'selected':'' }>${dorm.dormName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						</c:if>
						<c:if test="${role == 'manager'}">
						<tr>
							<td><font color="red">*</font>工号：</td>
							<td><input type="text" id="manNumber"  name="manNumber" value="${user.manNumber }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						</c:if>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='<%=path%>user/getUserList.action'">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red"></font>
					</div>
			</div>
		</form>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
	function checkForm(){
		var username=$("#username").val();
		var name=$("#name").val();
		var sex=$("#sex").val();
		var tel=$("#tel").val();
		
		if(username==""||name==""||sex==""||tel==""){
			$("#message").html("信息填写不完整！");
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("#userInfoLi").addClass("active");
		showTr();
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		个人信息
		</div>
		<form action="<%=path%>user/userInfoSave.action" method="post"  onsubmit="return checkForm();">
			<div class="data_form" >
				<input type="hidden" id="role" name="role" value="${role }"/>
				<input type="hidden" id="id" name="id" value="${user.id }"/>
				<input type="hidden" id="username" name="username" value="${user.username }"/>
				<input type="hidden" id="password" name="password" value="${user.password }"/>
				<input type="hidden" id="stuNumber" name="stuNumber" value="${user.stuNumber }"/>
				<input type="hidden" id="manNumber" name="manNumber" value="${user.manNumber }"/>
				<input type="hidden" id="dormId" name="dormId" value="${user.dorm.id }"/>
					<table align="center">
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
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
					</div>
					<div align="center">
						<font id="message" color="red">${message}</font>
					</div>
			</div>
		</form>
</div>
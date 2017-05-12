<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<script type="text/javascript">
function checkForm(){
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var rPassword = $("#rPassword").val();
	if(oldPassword==""||newPassword==""||rPassword==""){
		$("#message").html("信息填写不完整！");
		return false;
	} else if(newPassword!=rPassword){
		$("#message").html("密码填写不一致！");
		return false;
	}
	return true;
}
</script>
<div class="data_list">
		<div class="data_list_title">
			修改密码
		</div>
		<form action="<%=path%>passwordUpdate.action" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value="${currentUser.id }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>原密码：</td>
							<td><input type="password" id="oldPassword"  name="oldPassword" value=""  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>新密码：</td>
							<td><input type="password" id="newPassword"  name="newPassword" value="" style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>重复密码：</td>
							<td><input type="password" id="rPassword"  name="rPassword" value="" style="margin-top:5px;height:30px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="提交"/>
					</div>
					<div align="center">
						<font id="message" color="red">${message }</font>
					</div>
			</div>
		</form>
</div>
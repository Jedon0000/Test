<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录首页</title>
<link href="css/loginStyle.css" rel="stylesheet" />
<script src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			if ($("#username").val() == "") {
				alert("请输入用户名");
				$("#username").focus();
				return;
			}
			if ($("#password").val() == "") {
				alert("请输入密码");
				$("#password").focus();
				return;
			}
			$("#form1").submit();
		});
	});
</script>
</head>
<body>
	<!-- 登录页面头信息 -->
	<div class="login_head">
		<img src="img/login_logo.png" height="107" width="563" alt="图片">
	</div>

	<div class="login_bg">
		<form id="form1" method="post" action="chat">
		<input type="hidden" name="method" value="login"/>
			<div class="login_box">
				<div class="login_title">Java联盟聊天室</div>
				<div class="login_body">
					<div class="login_input_box">
						<div class="tb_box">
							<i class="user_tb"></i>
						</div>
						<input id="username" name="username" placeholder="请输入用户账号"
							type="text">
					</div>
					<div class="login_input_box">
						<div class="tb_box">
							<i class="pass_tb"></i>
						</div>
						<input id="password" name="password" placeholder="请输入用户密码"
							type="password">
					</div>
					<div>
						<a href="javascript:void(0);" class="login_btn" id="login">立即登录</a>
					</div>
				</div>
				<h3 style="color: red; text-align: center;">${ msg }</h3>
			</div>
		</form>
	</div>

	<!-- 登录页面页脚 -->
	<div class="login_foot">
		<span style="color: #FFFFF"><font face="DFKai-SB">@CopyRight
				Java联盟</font></span>
	</div>

	<!-- 登录对话框  -->

</body>
</html>
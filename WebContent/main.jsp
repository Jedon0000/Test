<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java联盟聊天室</title>
<link href="css/style.css" rel="stylesheet" />
<script src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	//每秒刷新一次我们的在线人员列表
	window.setInterval("showOnline();", 1000);
	//每秒检查一次用户的session是否有效
	window.setInterval("check();", 1000);
	
	//1秒刷新一次显示聊天内容
	window.setInterval("showContent();", 1000);

	//显示聊天的内容
	function showContent(){
		$.post("chat",{'method': 'showMsg'}, function(data){
			$("#content").html(data);
			
		});
	}	
	//检查用户是否在线的函数
	function check() {
		$.post("chat?method=check", function(data, status) {
			if (data == 1) {
				//提示用户下线
				alert("账户已下线!");
				//回到登录页面
				window.location = "index.jsp";
			}
		});
	}
	//选择聊天对象的set函数
	function set(Person) {
		if (Person != "${exitUser.userName}") {
			form1.to.value = Person;
		} else {
			alert("重新选择聊天对象");
		}
	}

	//退出聊天室 
	function exit() {
		alert("欢迎下次光临！");
		window.location.href = "chat?method=exit";
	}

	//显示在线人员列表 
	function showOnline() {
		$.get("online.jsp", function(data, status) {
			$("#online").html(data);
		});
	}
	
	//发送聊天信息
	function send(){
		if(form1.to.value == ""){
			alert("请选择聊天对象");
			return false;
		}
		if(form1.content.value == ""){
			alert("聊天内容不能为空");
			form1.content.focus();
			return false;
		}
		$.post("chat", $("#form1").serialize(), function(data, status){
			var content1 = $("#content");
			content1.html(data);
		});
	}
</script>
</head>
<body>
	<div class="main">
		<table width="100%" height="300" align="center">
			<tr height="100" bgcolor="#2F333B">
				<td colspan="2"><font size="12" color="white">Java联盟聊天室</font>
				</td>
			</tr>
			<tr>
				<td width="200" valign="top" bgcolor="#D9DBE2" id="online"
					style="padding: 5px">在线人员列表 <!--
				<jsp:include page="online.jsp" />
				-->
				</td>

				<td width="613" height="200px" valign="top"
					background="img/main_bj.jpg" bgcolor="#E1E1E1"
					style="padding: 5px;">
					<div style="height: 290px; overflow: auto" id="content">聊天内容</div>
					<div>
						<a id="msg_end" name="1" href="#1">&nbsp;</a>
					</div>
				</td>
			</tr>

		</table>
		<table width="100%" height="95" border="0" align="center"
			cellpadding="0" cellspacing="0" bgcolor="#606779">

			<form action="" id="form1" name="form1" method="post">
				<input type="hidden" name="method" value="sendMsg"/>
				<tr>
					<td height="30" align="left">&nbsp;</td>
					<td height="37" align="left"><input name="from" type="hidden"value="">
					[${exitUser.userName}]对 <input name="to"type="text" value="" size="35" readonly="readonly" /> 
						表情<select name="face" class="wenbenkuang">
							<option value="无表情的">无表情的</option>
							<option value="微笑着" selected>微笑着</option>
							<option value="笑呵呵地">笑呵呵地</option>
							<option value="热情的">热情的</option>
							<option value="温柔的">温柔的</option>
							<option value="红着脸">红着脸</option>
							<option value="幸福的">幸福的</option>
							<option value="嘟着嘴">嘟着嘴</option>
							<option value="热泪盈眶的">热泪盈眶的</option>
							<option value="依依不舍的">依依不舍的</option>
							<option value="得意的">得意的</option>
							<option value="神秘兮兮的">神秘兮兮的</option>
							<option value="恶狠狠的">恶狠狠的</option>
							<option value="大声的">大声的</option>
							<option value="生气的">生气的</option>
							<option value="幸灾乐祸的">幸灾乐祸的</option>
							<option value="同情的">同情的</option>
							<option value="遗憾的">遗憾的</option>
							<option value="正义凛然的">正义凛然的</option>
							<option value="严肃的">严肃的</option>
							<option value="慢条斯理的">慢条斯理的</option>
							<option value="无精打采的">无精打采的</option>
					</select> 说：</td>
					<td width="189" align="left">&nbsp;&nbsp;字体颜色： <select
						name="color" size="1" class="wenbenkuang" id="select">
							<option selected>默认颜色</option>
							<option style="color: #FF0000" value="FF0000">红色热情</option>
							<option style="color: #0000FF" value="0000ff">蓝色开朗</option>
							<option style="color: #ff00ff" value="ff00ff">桃色浪漫</option>
							<option style="color: #009900" value="009900">绿色青春</option>
							<option style="color: #009999" value="009999">青色清爽</option>
							<option style="color: #990099" value="990099">紫色拘谨</option>
							<option style="color: #990000" value="990000">暗夜兴奋</option>
							<option style="color: #000099" value="000099">深蓝忧郁</option>
							<option style="color: #999900" value="999900">卡其制服</option>
							<option style="color: #ff9900" value="ff9900">镏金岁月</option>
							<option style="color: #0099ff" value="0099ff">湖波荡漾</option>
							<option style="color: #9900ff" value="9900ff">发亮蓝紫</option>
							<option style="color: #ff0099" value="ff0099">爱的暗示</option>
							<option style="color: #006600" value="006600">墨绿深沉</option>
							<option style="color: #999999" value="999999">烟雨蒙蒙</option>
					</select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td width="21" height="30" align="left">&nbsp;</td>
					<td width="549" align="left"><input name="content" type="text"
						size="70"> <input name="Submit2" id="sendMsg"
						type="button" value="发送" onClick="send()"></td>
					<td align="right"><input name="button_exit" type="button"
						value="退出聊天室" onClick="exit()"></td>
					<td align="center">&nbsp;</td>
				</tr>
			</form>
		</table>

	</div>
</body>
</html>
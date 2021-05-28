<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>岳麓书城会员注册页面</title>
	<%--	静态包含base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {

			//检查用户名是否可用(是否已存在)
			$("#username").blur(function () {
				var username=this.value;
				//通过ajax向后台请求
				$.getJSON("${basePath}user","action=ajaxExistUsername&username="+username,function (data) {
					//传回来false表示用户名不存在，true表示已存在
					if (data.isExistsUsername){
						$("span.errorMsg").text("用户名已存在！");
					}else {
						$("span.errorMsg").text("用户名可用！");
					}
				});
			});

			//给验证码图片绑定单击事件，以刷新验证码图片
			$("#verifyCode").click(function () {
				//在事件响应的function函数中有一个this对象，它是当前正在响应事件的dom对象，即img标签对象
				//src是img的属性可读可写，在重新赋值后会重新发出请求
				//加时间戳是为了避免由于浏览器缓存的原因而导致的图片不可刷新
			this.src="${basepath}kaptcha.jsp?med="+new Date();
			});

			$("#sub_btn").click(function () {
				// 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				//1.先获取用户名
				var usernameText=$("#username").val();
				//2.创建正则表达式
				var usernamePatt=/^\w{5,12}$/;
				//3.使用正则表达式的test表达式进行验证
				if (!usernamePatt.test(usernameText)){
					//4.给出提示信息
					$("span.errorMsg").text("用户名不合法！");

					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
				//1.拿到密码
				var userpasswordText=$("#password").val();
				//2.创建正则表达式
				var passwordPatt=/^\w{5,12}$/;
				//3.使用正则表达式的test表达式进行验证
				if (!passwordPatt.test(userpasswordText)){
					//4.给出提示信息
					$("span.errorMsg").text("密码不合法，请重新输入！");

					return false;
				}

				// 验证确认密码：和密码相同
				//1.拿到密码
				var repwdText=$("#repwd").val();
				//2.比较两次输入密码是否一样
				if (repwdText!=userpasswordText){
					//3.给出提示信息
					$("span.errorMsg").text("两次密码输入不一致！");

					return false;
				}

				// 邮箱验证：xxxxx@xxx.com
				//1.获取输入的邮箱
				var emailText=$("#email").val();
				//2.创建正则表达式
				var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				//3.用test进行验证
				if (!emailPatt.test(emailText)){
					//4.给出提示信息
					$("span.errorMsg").text("邮箱格式不正确！");

					return false;
				}
				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
				var codeText=$("#code").val();

				//去掉验证码首尾空格
				alert("["+codeText+"]");
				codeText=$.trim(codeText);
				alert("["+codeText+"]");
				if(codeText==null ||codeText==""){
					//4.给出提示信息
					$("span.errorMsg").text("验证码不能为空！");

					return false;
				}

				//验证成功，去掉提示信息
				$("span.errorMsg").text("");

			});
		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="./static/img/logo.png" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册岳麓书苑会员</h1>
								<span class="errorMsg" >
									${requestScope.errorMsg}
								</span>
							</div>
							<div class="form">
								<form action="./user" method="post">
									<input type="hidden" name="action" value="register"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" value="${requestScope.username}" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" value="${requestScope.email}" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
									<img id="verifyCode" alt="" src="./kaptcha.jsp" style="float: right; margin-right: 20px;width:100px;height:45px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>				</div>
			</div>
		<%--	静态包含页脚--%>
		<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>
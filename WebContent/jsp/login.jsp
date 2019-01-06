﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
	 float:left; */
	
}

font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}
</style>
</head>
<body>




	<%@ include file="/jsp/header.jsp"%>
	<%
		// 获取cookie数组
		Cookie[] cookies = request.getCookies();
		String username = "";
		String password = "";
		if (cookies != null) {// 防止空指针
			for (int i = 0; i < cookies.length; i++) {
				//System.out.println(cookies[i].getName() + "-----" + cookies[i].getValue());
				if ("remuser".equals(cookies[i].getName())) {
					username = cookies[i].getValue().split("#")[0];
					password = cookies[i].getValue().split("#")[1];
				}
			}
		}
	%>


	<div class="container"
		style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/img/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5">
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
					<font>会员登录</font>USER LOGIN <br /> ${msg }
					<div>&nbsp;</div>
					<form class="form-horizontal"
						action="${pageContext.request.contextPath}/UserServlet?method=userLogin"
						method="post">

						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="username"
									id="username" placeholder="请输入用户名" value="<%=username%>">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" name="password"
									id="inputPassword3" placeholder="请输入密码" value="<%=password%>">
							</div>
						</div>



						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="code" name="code"
									placeholder="请输入验证码">
							</div>
							<div class="col-sm-3">
								<img src="${pageContext.request.contextPath}/checkCode"
									onclick="changeCode()" id="imgcode" />
							</div>

						</div>
						<script type="text/javascript">
							function changeCode() {
								document.getElementById("imgcode").src = "${pageContext.request.contextPath}/checkCode?imgpath="
										+ Math.random();
							}
						</script>




						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> <input type="checkbox" name="auto_login">
										自动登录
									</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
										type="checkbox" name="rem_name"> 记住用户名
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" value="登录" name="submit"
									border="0"
									style="background: url('${pageContext.request.contextPath}/img/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>



	<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
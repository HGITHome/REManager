<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Sign In</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Easy Admin Panel Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <!-- Bootstrap Core CSS -->
<link href="res/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="res/css/style.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="res/css/font-awesome.css" rel="stylesheet"> 
<!-- jQuery -->
<!-- lined-icons -->
<link rel="stylesheet" href="res/css/icon-font.min.css" type='text/css' />
<!-- //lined-icons -->
<!-- chart -->
<script src="res/js/Chart.js"></script>
<!-- //chart -->
<!--animate-->
<link href="res/css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="res/js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
<script src="res/js/jquery-1.10.2.min.js"></script>
<!-- Placed js at the end of the document so the pages load faster -->

</head> 
   
 <body class="sign-in-up">
    <section>
			<div id="page-wrapper" class="sign-in-wrapper">
				<div class="graphs">
					<div class="sign-in-form">
						<div class="sign-in-form-top">
							<p><span>欢迎使用房地产管理系统</span></p>
						</div>
						<div class="signin">
							<div class="signin-rit">
								<span class="checkbox1">
									 <label class="checkbox">忘记密码?</label>
								</span>
								<p><a href="o_find.do">点击这里</a> </p>
								<div class="clearfix"> </div>
							</div>
							<form action="login" method="post" class="form-horizontal">
							<c:if test="${error != null}">
								<div class="alert alert-danger" style="text-align: center">
									<p>${error}</p>
								</div>
							</c:if>
							<div class="log-input">
								<div class="log-input-center">
								   <input type="text" class="user"  name="username" />
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="log-input">
								<div class="log-input-center">
								   <input type="password" class="lock" name="password" />
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="checkbox">
								<label>
									<input name="remember-me" type="checkbox" />记住密码
								</label>
							</div>
							
							<input type="submit" value="登录">
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
						</form>	 
						<h5>没账号？<a href="signUp">马上注册</a></h5>
						</div>
					</div>
				</div>
			</div>
		
	</section>
	
<script src="res/js/jquery.nicescroll.js"></script>
<script src="res/js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
   <script src="res/js/bootstrap.min.js"></script>
</body>
</html>
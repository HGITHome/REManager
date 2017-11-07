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
<!--//end-animate-->
<!----webfonts--->
<link href='http://fonts.useso.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<!---//webfonts---> 
 <!-- Meters graphs -->
<script src="res/js/jquery-1.10.2.min.js"></script>
<!-- Placed js at the end of the document so the pages load faster -->

</head> 
   
 <body class="sign-in-up">
    <section>
			<div id="page-wrapper" class="sign-in-wrapper">
				<div class="graphs">
					<div class="sign-in-form">
						<div class="sign-in-form-top">
							<p><span>找回密码</span></p>
						</div>
						<div class="signin">
							<form action="o_send.do" method="post" class="form-horizontal" onsubmit="return check()">
							<c:if test="${error != null}">
								<div class="alert alert-danger" style="text-align: center">
									<p>${error}</p>
								</div>
							</c:if>
							<div class="log-input">
								<div class="log-input-center">
								   <input type="text" class="user"  name="username" placeholder="请输入用户名" id="username"/>
								  <span id="checkUsername" style="color: red"></span>
								</div>
								
							</div>
							<div class="log-input">
								<div class="log-input-center">
								   <input type="text" class="user" name="email" placeholder="请输入已绑定的邮箱地址" id="email"/>
								<span id="checkEmail" style="color: red"></span>
								</div>
								
							</div>
							<input type="submit" value="提交">
						</form>	 
						</div>
					</div>
				</div>
			</div>
		
	</section>
	
<script type="text/javascript">
function usernameCheck(){
	 var check = false;
	 var username = document.getElementById("username").value; 
	 if(trim(username)==""){
   	 document.getElementById("checkUsername").innerHTML = "请输入用户名";  
        check = false;
	 }else{
		 document.getElementById("checkUsername").innerHTML = "";
	 }
	 return check;
}
function emailCheck() {  
    var check = false;  
    var email = document.getElementById("email").value; 
    if(trim(email)==""){
    	 document.getElementById("checkEmail").innerHTML = "请输入邮箱地址";  
         check = false;  
     } else {  
    	 if (email.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1) {  
    	        document.getElementById("checkEmail").innerHTML = " 请输入正确的邮箱地址";  
    	        check = false;  
    	    } else {  
    	    	document.getElementById("checkEmail").innerHTML = "";
    	        check = true;  
    	    }    
     }  
    
    return check;  
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function check() { 
	var check = true;
	check = usernameCheck();
	check = emailCheck();  
    return check;  
}
</script>
	
<script src="res/js/jquery.nicescroll.js"></script>
<script src="res/js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
   <script src="res/js/bootstrap.min.js"></script>
</body>
</html>
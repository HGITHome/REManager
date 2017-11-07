<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>激活失败</title>

    <!-- BOOTSTRAP STYLES-->
    <link href="res/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="res/css/font-awesome.css" rel="stylesheet" />
     <!-- PAGE LEVEL STYLES-->
    <link href="res/css/error.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

</head>
<body>
    <div class="container">
        
         <div class="row text-center">
               
                <div class="col-md-12 set-pad" >
                           
                            <strong class="error-txt">激活失败</strong>
                           <p class="p-err">${message}</p>
                    <a href="http://localhost:8080/spring-security/login" class="btn btn-danger" ><i class="fa fa-mail-reply"></i>&nbsp;登录</a>
                        </div>
        </div>
    </div>
    

  

    
</body>
</html>

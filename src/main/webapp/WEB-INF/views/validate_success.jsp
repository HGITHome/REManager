<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>激活账号成功</title>
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
                           
                            <strong class="error-txt">激活链接成功</strong>
                           <p class="p-err"><span id="jumpTo">5</span>秒后自动跳转到重设密码界面或者点击<a href="o_updateForm.do">找回密码</a></p>
                        </div>
                
                
        </div>
    </div>



<script type="text/javascript">
   countDown(5,'o_updateForm.do');
function countDown(secs,surl){     
 //alert(surl);     
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs;  
 if(--secs>0){     
     setTimeout("countDown("+secs+",'"+surl+"')",1000);  
     }     
 else{       
     location.href=surl;     
     }     
 }     
</script>  
</body>
</html>
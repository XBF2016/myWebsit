<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

<script type="text/javascript" language="javascript">
function checkform(){
	if(document.getElementById("password1Id").value==""){
		
		alert('原密码不能为空');
		return false;
	}
	if(document.getElementById("password2Id").value==""){
		
		alert('新密码不能为空');
		return false;
	}
	if(document.getElementById("password3Id").value==""){
		
		alert('确认密码不能为空');
		return false;
	}
	
	if(document.getElementById("password2Id").value.length<6){
		
		alert('新密码长度必须大于6位');
		return false;
	}
	
	if(document.getElementById("password2Id").value != document.getElementById("password3Id").value){
		
		alert('新密码和新密码确认不一致');
		return false;
	}
	
	
	return true;

}


</script>


</head>
<body>

<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  
  
  <%@ include file="left.jsp" %>
  
  
  <div id="main-content">
    
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3 >修改密码</h3>
        <ul class="content-box-tabs">
          
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        
        
        
      
        <div class="tab-content default-tab" id="tab1">
          <form action="manageAction!passwordChange.action" method="post"  onsubmit="return checkform()">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label>原密码</label>
              <input class="text-input small-input" type="text" name="password1"  id="password1Id" />
   
            </p>
            
            <p>
              <label>新密码</label>
              <input class="text-input small-input datepicker" type="text"  name="password2" id="password2Id" />
            
            <p>
              <label>确认新密码</label>
              <input class="text-input small-input" type="text" name="password3" id="password3Id" />
            </p>
            
            <p>
              <input class="button" type="submit" value="提交" />
              
              &nbsp;&nbsp;&nbsp;
              
              <input type="button" value="返回"  onclick="javascript:history.go(-1)" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    
  
  </div>
  <!-- End #main-content -->
</div>


</body>

</html>

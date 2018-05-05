<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<!-- Main Stylesheet -->
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/manage/resources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="${pageContext.request.contextPath}/manage/resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/manage/resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/manage/resources/scripts/jquery.wysiwyg.js"></script>

<script type="text/javascript" language="javascript">
function checkform(){
	
	 
	    if (document.getElementById('usernameid').value=="")
	{
		alert("账户不能为空");
		return false;
	}
	
    if (document.getElementById('passwordid').value=="")
	{
		alert("密码不能为空");
		return false;
	}
	
	
	return true;

}
function register(){
	
	 
	    window.location.href='zhuce.jsp';

}

</script>

</head>
<body  marginheight="0" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" text="#666666" bgcolor="5f97b2">
  <table align="center">
    <tr>
      <td align="center" height="400" valign="middle">
      <form method="post"   action="manageAction_login.action" onsubmit="return checkform()">
      <table  background="${pageContext.request.contextPath}/images/login.jpg" width="500" height="300" border="0" cellspacing="0" cellpadding="0" style="margin-top:90" align="center">
          <tr>
            <th height="130" colspan="2" class="" scope="col"></th>
		  </tr>
          <tr>
            <th width="45%" height="30" align="right" scope="row" class="">用户名:</th>
            <td width="55%" height="30" align="left">&nbsp;<input class="text-input" type="text" name="username" id="usernameid"/></td>
          </tr>
          <tr>
            <th width="40%" height="30" align="right" scope="row" class="">密&nbsp;&nbsp;码:</th>
            <td height="30" align="left">&nbsp;<input class="text-input" type="password" name="password" id="passwordid"/></td>
          </tr>
         
          <tr>
            <th height="35" colspan="2" scope="row" class=""><input class="button" type="submit" value="登 录" />  &nbsp;&nbsp;
            <a href="./">返回首页</a>
            </th>
		</tr>
        </table>
      </form>
      </td>
    </tr>
  </table>
</body>
</html>

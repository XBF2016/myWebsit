<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/webmain.css" rel="stylesheet" type="text/css" />
<link href="css/ddsmoothmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="scripts/jquery.KinSlideshow-1.2.1.js"></script>
<script type="text/javascript" src="scripts/webtry_roll.js"></script>
<script type="text/javascript" src="scripts/ddsmoothmenu.js"></script>
<script type="text/javascript">
ddsmoothmenu.init({
	mainmenuid: "MainMenu", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})
</script>
</head>
<body>
<div id="wrapper">
  
   <div id="MainMenu" class="ddsmoothmenu">
    <ul>
      <li><a href="." title="公司主页" id="menu_selected"><span>公司主页</span></a></li>
      <li><a href="indexAction!aboutUsPage.action" title="关于我们" ><span>企业介绍</span></a> </li>
      <li><a href="indexAction!productListPage.action" title="产品展示"><span>产品中心</span></a></li>
      <li><a href="indexAction!newsListPage.action" title="新闻中心"><span>新闻中心</span></a></li>
      <li><a href="indexAction!recruitListPage.action" title="招聘信息"><span>招聘信息</span></a></li>
      <li><a href="indexAction!serviceSupportPage.action" title="服务支持"><span>服务支持</span></a></li>
      <li><a href="indexAction!feedbackPage.action" title="留言反馈"><span>留言反馈</span></a></li>
      <li><a href="manageAction!loginPage.action"  ><span>管理员登录</span></a></li>
    </ul>
  </div>
  
<script type="text/javascript">
$(function(){
    $("#banner").KinSlideshow({
            moveStyle:"right",
            titleBar:{titleBar_height:32,titleBar_bgColor:"#000",titleBar_alpha:0},
            titleFont:{TitleFont_size:12,TitleFont_color:"#FFFFFF",TitleFont_weight:"normal"},
            btn:{btn_bgColor:"#2d2d2d",btn_bgHoverColor:"#1072aa",btn_fontColor:"#FFF",btn_fontHoverColor:"#FFF",btn_borderColor:"#4a4a4a",btn_borderHoverColor:"#1188c0",btn_borderWidth:1}
    });
})
</script>

<!-- js图片和图片得文字说明 -->
  <div id="banner">
  <c:forEach items="${piclist}" var="pic">
    <a href="."><img src="<%=basePath %>uploadfile/${pic.path }" ！" width="1300px" height="250" /></a>
  </c:forEach>
  </div>
  
  
  
  
  <div id="page_main" class="clearfix">
    <div class="page-right">
      <div class="site-nav"><span>当前位置 : </span><a href=".">公司主页</a> &gt;&gt; <a href="indexmethod!zhaopinlist.action" title="招聘信息">招聘信息</a>&gt;&gt;招聘介绍</div>
      <div class="page-single">
<p style="LINE-HEIGHT: 25px">
<input  type="button" value="返回"  onclick="javascript:history.go(-1);" /><br/>
职位名称：${zhaopin.zhiwei }<br/>

职位介绍：${zhaopin.jieshao }<br/>

<input  type="button" value="返回"  onclick="javascript:history.go(-1);" /><br/>
</p>
      </div>
    </div>
    
    <div class="page-left">
      
      <script type="text/javascript">
function fromfrom(){
var searchid = document.getElementById("searchid").value;

if("2"==searchid){

sitesearch.action="indexmethod!products.action";
sitesearch.submit();

}

if("3"==searchid){

sitesearch.action="indexmethod!xinwenlist.action";
sitesearch.submit();

}

if("4"==searchid){

sitesearch.action="indexmethod!zhaopinlist.action";
sitesearch.submit();

}

}

</script>
      
      <div class="index-search">
        <h2><span>站内搜索</span></h2>
        <form action="" method="post" id="sitesearch" name="sitesearch">
          <p>
            <select name="searchid" id="searchid">
            <option value="2">产品展示</option>
            <option value="3">新闻中心</option>
            <option value="4">招聘信息</option>
            </select>
          </p>
          <p>
            <input name="searchtext" value="${searchtext }" type="text" id="searchtext"/>
          </p>
          <p>
            <input name="searchbutton" type="submit" id="searchbutton" value="" onclick="fromfrom()" />
          </p>
        </form>
      </div>
      <div class="left-contact">
         <h2><span>联系我们</span></h2>
        <p><span>地址: </span>${company.address }<br />
          <span>邮编: </span>${company.postcode }<br />
          <span>联系人: </span>${company.contact }<br />
          <span>电话: </span>${company.tel }<br />
          <span>传真: </span>${company.fax }<br />
          <span>手机: </span>${company.phone }<br />
          <span>邮箱: </span>${company.mailbox }</p>
      </div>
      
    </div>
  </div>
    <div id="copyright"> Copyright ©&nbsp;<a href="http://www.netgather.com" >${company.name }</a> All Rights Reserved.<br />
    <span>地址: </span>${company.address }　<span>邮编: </span>${company.postcode }　<span>联系人: </span>${company.contact }<br />
    <span>电话: </span>${company.tel }　<span>传真: </span>${company.fax }　<span>手机: </span>${company.phone }<br />
    <span>邮箱: </span>${company.mailbox }
  </div>
</div>
</body>
</html>

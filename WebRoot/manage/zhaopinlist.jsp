<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


</head>
<body>

<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  
  
  <%@ include file="left.jsp" %>
  
  
  <div id="main-content">
    <!-- Main Content Section with everything -->
    
    <!-- Page Head -->
    
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>${title }</h3>
        
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          
         
            <div>
           <form action="${url }" method="post">
<a href="${url2 }add.action"><span style="font-size: 20px;font-weight: bold;">添加新招聘信息</span></a>
            职位名：<input type="text" name="zhiwei"  value="${zhiwei }"/>
         
            <input type="submit" value="查询" />
            </form>
            
            
              </div>
          
          
          <table>
            <thead>
              <tr>
               
                <th>职位名</th>
           
                <th>是否推荐</th>
                <th>添加时间</th>
                <th>操作</th>
              </tr>
            </thead>
            
            <tbody>
            <c:forEach items="${list}" var="bean">
              <tr>
                
                <td>${bean.zhiwei }</td>
              
                <td>${bean.tuijian }</td>
                <td>${bean.createtime }</td>
                <td>
                 <a href="${url2 }update.action?id=${bean.id }">修改</a> &nbsp; 
                 <a href="${url2 }update3.action?id=${bean.id }">查看</a> &nbsp;
                 <c:if test="${bean.tuijian=='未推荐'}">
                 <a href="${url2 }delete2.action?id=${bean.id }">推荐</a> &nbsp;
                 </c:if>
                  <c:if test="${bean.tuijian=='推荐'}">
                 <a href="${url2 }delete3.action?id=${bean.id }">取消推荐</a> &nbsp;
                 </c:if>
                 <a href="${url2 }delete.action?id=${bean.id }">删除</a> &nbsp;
                 </td>
              </tr>
              </c:forEach>
             
            </tbody>
            
            
            <tfoot>
              <tr>
                <td colspan="6">
               ${pagerinfo }
                </td>
              </tr>
            </tfoot>
            
          </table>
        </div>
        
        
      
      </div>
 
    </div>
    
    
  
    

   
  </div>
  
</div>


</body>

</html>

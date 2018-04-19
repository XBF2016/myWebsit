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
            <a href="${url2 }AddPage.action"><button class="greenButton">添加新产品</button></a><br/><br/>
           <form action="${url}" method="post">
            产品名：<input type="text" name="product_name"  value="${product_name }"/>
            <input class="greenButton" type="submit" value="查询" />
            </form>
            
            
              </div>
          
          
          <table>
            <thead>
              <tr>
               
                <th>产品名</th>
           
                <th>是否推荐</th>
                <th>添加时间</th>
                <th>操作</th>
              </tr>
            </thead>
            
            <tbody>
            <c:forEach items="${productList}" var="product">
              <tr>
                
                <td>${product.product_name }</td>
              
                <td>
                <c:if test="${product.is_recommend=='不推荐'}">
                                                                否 &nbsp;
                 </c:if>
                  <c:if test="${product.is_recommend=='推荐'}">
                                                               是  &nbsp;
                 </c:if>
                </td>
                <td>${product.created_time }</td>
                <td>
                 <a href="${url2 }UpdatePage.action?id=${product.id }">修改</a> &nbsp; 
                 <a href="${url2 }DetailPage.action?id=${product.id }">查看</a> &nbsp;
                 <c:if test="${product.is_recommend=='不推荐'}">
                 <a href="${url2 }RecommendChange.action?id=${product.id }&flag=0">推荐</a> &nbsp;
                 </c:if>
                  <c:if test="${product.is_recommend=='推荐'}">
                 <a href="${url2 }RecommendChange.action?id=${product.id }&flag=1">取消推荐</a> &nbsp;
                 </c:if>
                 <a href="${url2 }Delete.action?id=${product.id }">删除</a> &nbsp;
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

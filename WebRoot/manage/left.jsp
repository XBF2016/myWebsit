<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  
%>

<div id="sidebar">
    <div id="sidebar-wrapper">
     <br/>
      
      <a href="#">
      <span style="font-size: 30px;font-weight: bold;">管理菜单</span>
      </a>
     
      
      <ul id="main-nav">
        
        
        <li> <a href="#" class="nav-top-item">系统管理 </a>
          <ul>
            <li><a href="manageAction!passwordChangePage.action">修改密码</a></li>
             <li><a href="manageAction!loginout.action">安全退出</a></li>
          </ul>
        </li>
        
        <li> <a href="#" class="nav-top-item">基本信息管理</a>
          <ul>
            <li><a href="manageAction!picListPage.action">首页展示图片管理</a></li>
            <li><a href="manageAction!companyUpdatePage.action">企业信息管理</a></li>
          </ul>
        </li>
        
         <li> <a href="#" class="nav-top-item">产品信息管理</a>
          <ul>
            <li><a href="manageAction!productListPage.action">产品信息管理</a></li>
        
          </ul>
        </li>
        
        
        <li> <a href="#" class="nav-top-item">新闻信息管理</a>
          <ul>
            <li><a href="manageAction!newsListPage.action">新闻信息管理</a></li>
        
          </ul>
        </li>
        
        <li> <a href="#" class="nav-top-item">招聘信息管理</a>
          <ul>
            <li><a href="manageAction!recruitListPage.action">招聘信息管理</a></li>
        
          </ul>
        </li>
        
         <li> <a href="#" class="nav-top-item">网点管理</a>
          <ul>
            <li><a href="manageAction!serviceListPage.action">网点信息管理</a></li>
        
          </ul>
        </li>
        
         <li> <a href="#" class="nav-top-item">留言反馈查看</a>
          <ul>
            <li><a href="manageAction!messageListPage.action">留言反馈查看</a></li>
        
          </ul>
        </li>
      
       
      </ul>
    </div>
  </div>



  
  
  
  <!-- End #sidebar -->
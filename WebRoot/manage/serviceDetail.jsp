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
        <h3>${title }</h3>
        <ul class="content-box-tabs">
          
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        
        
        
      
        <div class="tab-content default-tab" id="tab1">
           <form action="${url }"   method="post"   >
            <fieldset>
            
            
            <p>
              <label>网点名称</label>
              <input class="text-input small-input" type="text"  name="name" value="${service.name }" />
            </p>
            
            
            <p>
              <label>地址</label>
              <input class="text-input small-input" type="text"  name="address" value="${service.address }" />
            </p>
            
             <p>
              <label>邮编</label>
              <input class="text-input small-input" type="text"  name="postcode" value="${service.postcode }" />
            </p>
            
             <p>
              <label>联系人</label>
              <input class="text-input small-input" type="text"  name="contact" value="${service.contact }" />
            </p>
            
             <p>
              <label>电话</label>
              <input class="text-input small-input" type="text"  name="tel"  value="${service.tel }"/>
            </p>
            
            <p>
              <label>传真</label>
              <input class="text-input small-input" type="text"  name="fax"  value="${service.fax }"/>
            </p>
            
             <p>
              <label>手机</label>
              <input class="text-input small-input" type="text"  name="phone" value="${service.phone }" />
            </p>
            
             <p>
              <label>邮箱</label>
              <input class="text-input small-input" type="text"  name="mailbox"  value="${service.mailbox }"/>
            </p>
            
            
            
            <p>
              
              &nbsp;&nbsp;&nbsp;
              
             <input class="button" type="button" value="返回"  onclick="javascript:history.go(-1);" />
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改数据</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

  </head>
  
  <body>
    <form action="<%=request.getContextPath() %>/updateStore.do" method="post">

        储藏名：<input type="text" name="name" value="${store.name }">
        类型：<input type="text" name="type" value="${store.type }">
        联系：<input type="text" name="contact" value="${store.contact }">
        手机：<input type="text" name="mobile" value="${store.mobile }">
        地址：<input type="text" name="address" value="${store.address }">

        <input type="hidden" name="id" value="${store.id }">
    <input type="submit" value="提交数据">

      </form>
  </body>
</html>

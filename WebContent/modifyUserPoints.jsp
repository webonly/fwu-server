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
    <form action="<%=request.getContextPath() %>/updateUserPoints.do" method="post">

        用户号：<input type="text" name="userId" value="${userPoints.userId }">
        点击数：<input type="text" name="point" value="${userPoints.point }">
        日期：<input type="text" name="date" value="${userPoints.date }">
         详情：<input type="text" name="detail" value="${userPoints.detail }">
        <input type="hidden" name="id" value="${userPoints.id }">
    <input type="submit" value="提交数据">

      </form>
  </body>
</html>

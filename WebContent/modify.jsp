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
    <form action="<%=request.getContextPath() %>/update.do" method="post">

    	用户名：<input type="text" name="username" value="${user.username }">
		密码：<input type="password" name="password" value="${user.password }">
		手机：<input type="text" name="mobile" value="${user.mobile }">
        是否：<input type="text" name="deleted" value="${user.deleted }">
		 关注：<input type="text" name="wechatId" value="${user.wechatId }">
		<input type="hidden" name="id" value="${user.id }">
    <input type="submit" value="提交数据">

      </form>
  </body>
</html>

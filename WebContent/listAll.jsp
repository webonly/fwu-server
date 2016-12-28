<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>所有数据</title>
    
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
    所有结果<br/>
    <table border="1">
    	<tr>
    		<td>编号</td>
    		<td>用户名</td>
    		<td>密码</td>
    		<td>是否</td>
    		<td>手机</td>
            <td>关注</td>

    	</tr>
    	<c:forEach var="list"  items="${userLists}">
    	<tr>
    		<td>${list.userId}</td>
    		<td>${list.username}</td>
    		<td>${list.password}</td>
    		<td>${list.deleted}</td>
    		<td>${list.mobile}</td>
            <td>${list.wechatId}</td>
    		<td><a href="modify.do?id=${list.userId}">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="del.do?id=${list.userId}">删除</a></td>
    	</tr>
    	</c:forEach> 
    </table>
  </body>
</html>

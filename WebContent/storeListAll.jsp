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
            <td>储藏编号</td>
            <td>储藏名</td>
            <td>类型</td>
            <td>联系</td>
            <td>手机</td>
            <td>地址</td>
            <td>储藏日期</td>
            <td>更新日期</td>
        </tr>
        <c:forEach var="list"  items="${storeLists}">
        <tr>
            <td>${list.id}</td>
            <td>${list.name}</td>
            <td>${list.type}</td>
            <td>${list.contact}</td>
            <td>${list.mobile}</td>
            <td>${list.address}</td>
            <td>${list.date}</td>
            <td>${list.updatedate}</td>
            <td><a href="modifyStore.do?id=${list.id}">更新</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="delStore.do?id=${list.id}">删除</a></td>
        </tr>
        </c:forEach> 
    </table>
  </body>
</html>

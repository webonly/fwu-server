<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!--
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("#getAll").click(function(){
            var url = this.href;
            var args = {};
            $.post(url, args, function(data){
                for(var i = 0; i < data.length; i++){
                    var id = data[i].id;
                    var lastName = data[i].lastName;
                    
                    alert(id + ": " + lastName);
                }
            });
            return false;
        });
    })
</script>   -->
</head>

<body>

	<br>
	<a href="add.jsp">新增用户</a>
	</br>
	<br>
	<a href="getAll.do">查看全部用户</a>
	</br>
	<br>
	<a href="addUserInfo.jsp">新增用户全部资料</a>
	</br>
	<br>
	<a href="getUserAll.do">查看用户全部资料</a>
	</br>
	<br>
	<a href="login.do">查看用户</a>
	</br>


	<li><a href="addUserPoints.jsp">新增用户点击数</a> <a
		href="getUserPointsAll.do">查看全部用户点击数</a></li>

	<li><a href="addStore.jsp">新增存储</a> <a href="getStoreAll.do">查看全部存储</a>
	</li>

	<form action="uploadFiles.do" method="post" enctype="multipart/form-data">
		<input type="file" name="file" /> 
		<br/>
		<input type="file" name="file" /> 
		<br/>
		<input type="file" name="file" /> 
		<br/>
		<input type="file" name="file" /> 
		<br/>
		<input type="submit" value="Submit" />
	</form>

</body>
</html>

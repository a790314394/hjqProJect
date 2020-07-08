<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>左部菜单栏</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>

<body>
	<div id="left">
		<p>
			<a href="querytask.do?id=${sessionScope.employee.employeeId}"
				target="_self">查看任务</a>
		</p>
		<p>
			<a href="insertTask.do" target="_self">制定任务</a>
		</p>
		<p>
			<a href="queryNotask.do?id=${sessionScope.employee.employeeId}" target="_self">调整任务</a>
		</p>
		<p>
			<a href="trackingtask.do?id=${sessionScope.employee.employeeId}" target="_self">跟踪任务</a>
		</p>
		<p>
			<a href="queryEmp.do?id=${sessionScope.employee.employeeId}"
				target="_self">查看人员</a>
		</p>
		<p>
			<a href="loginOut.do"
				target="_self">退出系统</a>
		</p>
	</div>
</body>
</html>

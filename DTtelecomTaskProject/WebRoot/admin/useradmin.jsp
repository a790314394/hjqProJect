<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="${pageContext.request.contextPath }/css/css.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<!-- #BeginLibraryItem "/Library/topbanner.lbi" -->
	<div id="logo">
		<img src="${pageContext.request.contextPath }/images/top.jpg"
			width="1002" height="258" />
	</div>
	<!-- #EndLibraryItem -->
	<table width="1002" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="rightimg">
				<!-- #BeginLibraryItem "/Library/left3.lbi" -->
				<div id="left2">
					<p>
						<a href="useradmin.do" target="_self">用户管理</a>
					</p>
					<p>
						<a href="empadmin.do" target="_self">员工管理</a>
					</p>
					<p>
						<a href="empdistribute.do" target="_self">分配人员</a>
					</p>
					<p>
						<a href="emp_input.do" target="_self">退出系统</a>
					</p>
				</div> <!-- #EndLibraryItem -->
				<div id="right">
					<p>您现在的位置 &gt;&gt; 用户管理 &gt;&gt; 用户列表</p>
					<h1>用户详细信息:</h1>

					<form id="form1" name="form1" method="post" action="personadd.do">
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">用户名称</td>
								<td class="tdcolor">用户密码</td>
								<td class="tdcolor">用户角色</td>
								<td class="tdcolor">真实姓名</td>
								<td class="tdcolor">入职时间</td>
								<td class="tdcolor">职位信息</td>
								<td class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${sessionScope.all.data}" var="alls">
								<tr>
									<td>${alls.employeeName}</td>
									<td>${alls.password}</td>
									<td>${alls.role.roleName}</td>
									<td>${alls.realName}</td>
									<td>${alls.enrollDate}</td>
									<td>${alls.duty}</td>
								</tr>
							</c:forEach>
						</table>
						<a>共${sessionScope.all.lastPage }页</a> <a
							href="useradmin.do?pageNo=${sessionScope.all.firstPage}&pageSize=${sessionScope.all.pageSize}">首页</a>
						<a
							href="useradmin.do?pageNo=${sessionScope.all.previousPage}&pageSize=${sessionScope.all.pageSize}">上一页</a>
						<a
							href="useradmin.do?pageNo=${sessionScope.all.nextPage}&pageSize=${sessionScope.all.pageSize}">下一页</a>
						<a
							href="useradmin.do?pageNo=${sessionScope.all.lastPage}&pageSize=${sessionScope.all.pageSize}">末页</a>
						<a>第${sessionScope.all.pageNo }页</a> <a>共${sessionScope.all.totalRecords
							}条记录</a>
						<p>
							<label> <input name="Submit" type="submit" class="menu3"
								value="添加人员" /> </label>
						</p>
					</form>
					<p>&nbsp;</p>
				</div>
			</td>
		</tr>
	</table>
	<!-- #BeginLibraryItem "/Library/bottom.lbi" -->
	<div id="bottom">
		<img src="../images/button.jpg" width="1002" height="17" />
	</div>
	<!-- #EndLibraryItem -->
</body>
</html>

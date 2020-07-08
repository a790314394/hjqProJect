<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员详细信息</title>
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
				<!-- #BeginLibraryItem "/Library/left.lbi" --> <jsp:include
					page="left.jsp" />
				<div id="right">
					<p>您现在的位置 &gt;&gt; 查看人员 &gt;&gt; 人员详细信息</p>
					<h1>人员详细信息:</h1>
					<form id="form1" name="form1" method="post"
						action="queryEmp.do?id=${sessionScope.employee.employeeId }">
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">姓名</td>
								<td>${requestScope.emp.realName }</td>
							</tr>
							<tr>
								<td class="tdcolor">性别</td>
								<td>${requestScope.emp.sex }</td>
							</tr>
							<tr>
								<td class="tdcolor">入职时间</td>
								<td><fmt:formatDate value="${requestScope.emp.enrollDate }"
										pattern="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<td class="tdcolor">职位</td>
								<td>${requestScope.emp.role.roleName }</td>
							</tr>
							<tr>
								<td class="tdcolor">出生年月</td>
								<td><fmt:formatDate value="${requestScope.emp.birthday }"
										pattern="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<td class="tdcolor">学历</td>
								<td>${requestScope.emp.edcucation }</td>
							</tr>
							<tr>
								<td class="tdcolor">专业</td>
								<td>${requestScope.emp.major}</td>
							</tr>
							<tr>
								<td class="tdcolor">行业经验</td>
								<td>${requestScope.emp.experience}</td>
							</tr>
						</table>
						<p>
							<label> <input name="Submit" type="submit" class="menu4"
								value="返回" /> </label>
						</p>
					</form>
				</div>
			</td>
		</tr>
	</table>
	<div id="bottom">
		<img src="${pageContext.request.contextPath }/images/button.jpg"
			width="1002" height="17" />
	</div>
	<!-- #EndLibraryItem -->
</body>
</html>

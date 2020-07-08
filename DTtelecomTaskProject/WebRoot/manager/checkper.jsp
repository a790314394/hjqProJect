<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看人员</title>
<link href="${pageContext.request.contextPath }/css/css.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("input[name=employeeId]").click(function() {
			$("#sb1").attr("disabled", false);
		});

	});
</script>
</head>

<body>
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
					<p>您现在的位置 &gt;&gt; 查看人员 &gt;&gt; 人员信息</p>
					<h1>人员信息:</h1>
					<form id="form1" name="form1" method="post"
						action="queryEmpInfo.do">
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">姓名</td>
								<td class="tdcolor">性别</td>
								<td class="tdcolor">入职时间</td>
								<td class="tdcolor">职位</td>
								<td class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${sessionScope.emps.data }" var="emp">
								<tr>
									<td>${emp.realName}</td>
									<td>${emp.sex}</td>
									<td><fmt:formatDate value="${emp.enrollDate }"
											pattern="yyyy-MM-dd" />
									</td>
									<td>${emp.duty }</td>
									<td><label> <input type="radio" name="employeeId"
											value="${emp.employeeId }" /> </label></td>
								</tr>

							</c:forEach>


						</table>
						<a>共${sessionScope.emps.lastPage }页</a> <a
							href="queryEmp.do?pageNo=${sessionScope.emps.firstPage}&pageSize=${sessionScope.emps.pageSize}&id=${sessionScope.employee.employeeId}">首页</a>
						<a
							href="queryEmp.do?pageNo=${sessionScope.emps.previousPage}&pageSize=${sessionScope.emps.pageSize}&id=${sessionScope.employee.employeeId}">上一页</a>
						<a
							href="queryEmp.do?pageNo=${sessionScope.emps.nextPage}&pageSize=${sessionScope.emps.pageSize}&id=${sessionScope.employee.employeeId}">下一页</a>
						<a
							href="queryEmp.do?pageNo=${sessionScope.emps.lastPage}&pageSize=${sessionScope.emps.pageSize}&id=${sessionScope.employee.employeeId}">末页</a>
						<a>第${sessionScope.emps.pageNo }页</a> <a>共${sessionScope.emps.totalRecords
							}条记录</a>
						<p>
							<label> <input name="Submit" type="submit" id="sb1"
								class="menu3" value="详细信息" disabled="disabled" /> </label>
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

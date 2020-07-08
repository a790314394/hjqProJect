<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>未实施任务</title>
<link href="${pageContext.request.contextPath }/css/css.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("input[name=taskId]").click(function() {
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
			<td class="rightimg"><jsp:include page="left.jsp" />
				<div id="right">
					<p>您现在的位置 &gt;&gt; 查看任务 &gt;&gt; 未实施任务信息</p>
					<h1>未实施任务信息:</h1>
					<form id="form1" name="form1" method="post" action="delete1.do">
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor"><a href="#" target="_self">任务名称</a>
								</td>
								<td class="tdcolor"><a href="#" target="_self">实施人</a>
								</td>
								<td class="tdcolor"><a href="#" target="_self">开始时间</a>
								</td>
								<td class="tdcolor"><a href="#" target="_self">结束时间</a>
								</td>
								<td class="tdcolor"><a href="#" target="_self">任务状态</a>
								</td>
								<td class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${sessionScope.plans.data}" var="plan"
								varStatus="i">
								<tr>
									<td><a href="updateTask.do?id=${i.index}">${plan.task.taskName}</a>
									</td>
									<td>${plan.task.task.realName}</td>
									<td><fmt:formatDate value="${plan.task.beginDate}"
											pattern="yyyy-MM-dd" />
									</td>
									<td><fmt:formatDate value="${plan.task.endDate}"
											pattern="yyyy-MM-dd" />
									</td>
									<td>${plan.task.status}</td>
									<td><label> <input type="radio" name="taskId"
											value="${plan.task.taskId}" /> </label>
									</td>
								</tr>
							</c:forEach>
						</table>
						<a>共${sessionScope.plans.lastPage }页</a> <a
							href="queryNotask.do?pageNo=${sessionScope.plans.firstPage}&pageSize=${sessionScope.plans.pageSize}&id=${sessionScope.employee.employeeId}">首页</a>
						<a
							href="queryNotask.do?pageNo=${sessionScope.plans.previousPage}&pageSize=${sessionScope.plans.pageSize}&id=${sessionScope.employee.employeeId}">上一页</a>
						<a
							href="queryNotask.do?pageNo=${sessionScope.plans.nextPage}&pageSize=${sessionScope.plans.pageSize}&id=${sessionScope.employee.employeeId}">下一页</a>
						<a
							href="queryNotask.do?pageNo=${sessionScope.plans.lastPage}&pageSize=${sessionScope.plans.pageSize}&id=${sessionScope.employee.employeeId}">末页</a>
						<a>第${sessionScope.plans.pageNo }页</a> <a>共${sessionScope.plans.totalRecords
							}条记录</a>

						<p>
							<label> <input name="Submit" type="submit" id="sb1" class="menu4" disabled="disabled"
								value="删除" onclick="return confirm('确认删除吗？')" /> </label>
						</p>
					</form>
				</div></td>
		</tr>
	</table>
	<!-- #BeginLibraryItem "/Library/bottom.lbi" -->
	<div id="bottom">
		<img src="${pageContext.request.contextPath }/images/button.jpg"
			width="1002" height="17" />
	</div>
	<!-- #EndLibraryItem -->
</body>
</html>

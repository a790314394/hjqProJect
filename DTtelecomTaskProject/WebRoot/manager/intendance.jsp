<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>跟踪任务</title>
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
					page="left.jsp" /> <!-- #EndLibraryItem -->
				<div id="right">
					<p>您现在的位置 &gt;&gt; 跟踪任务 &gt;&gt; 跟踪任务信息</p>
					<h1>跟踪任务:</h1>
					<form id="form1" name="form1" method="post" action="show.do">
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">任务名称</td>
								<td class="tdcolor">实施人</td>
								<td class="tdcolor">开始时间</td>
								<td class="tdcolor">结束时间</td>
								<td class="tdcolor">任务状态</td>
								<td class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${sessionScope.allTask.data}" var="task"
								varStatus="i">
								<tr>
									<td>${task.task.taskName }</td>
									<td>${task.task.task.realName }</td>
									<td><fmt:formatDate value="${task.task.beginDate}"
											pattern="yyyy-MM-dd" />
									</td>
									<td><fmt:formatDate value="${task.task.endDate}"
											pattern="yyyy-MM-dd" />
									</td>
									<td>${task.task.status }</td>
									<td><label> <input type="radio" name="taskId"
											value="${i.index }" /> </label>
									</td>
								</tr>
							</c:forEach>
						</table>
						<a>共${sessionScope.allTask.lastPage }页</a> <a
							href="trackingtask.do?pageNo=${sessionScope.allTask.firstPage}&pageSize=${sessionScope.allTask.pageSize}&id=${sessionScope.employee.employeeId}">首页</a>
						<a
							href="trackingtask.do?pageNo=${sessionScope.allTask.previousPage}&pageSize=${sessionScope.allTask.pageSize}&id=${sessionScope.employee.employeeId}">上一页</a>
						<a
							href="trackingtask.do?pageNo=${sessionScope.allTask.nextPage}&pageSize=${sessionScope.allTask.pageSize}&id=${sessionScope.employee.employeeId}">下一页</a>
						<a
							href="trackingtask.do?pageNo=${sessionScope.allTask.lastPage}&pageSize=${sessionScope.allTask.pageSize}&id=${sessionScope.employee.employeeId}">末页</a>
						<a>第${sessionScope.allTask.pageNo }页</a> <a>共${sessionScope.allTask.totalRecords
							}条记录</a>
						<p>
							<p>
								<label> <input name="Submit" type="submit" id="sb1" class="menu3" disabled="disabled"
									value="计划信息" /> </label>
							</p>
					</form>
				</div></td>
		</tr>
	</table>
	<div id="bottom">
		<img src="${pageContext.request.contextPath }/images/button.jpg"
			width="1002" height="17" />
	</div>
	<!-- #EndLibraryItem -->
</body>
</html>

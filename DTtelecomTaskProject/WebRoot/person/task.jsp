<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的任务</title>
<link href="${pageContext.request.contextPath }/css/css.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("input[name=radiobutton]").click(function() {
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

				<div id="left">
					<p>
						<a href="task.do" target="_self">计划管理</a>
					</p>
					<p>
						<a href="checkpro.do" target="_self">查询计划</a>
					</p>
					<p>
						<a href="task.do" target="_self">我的任务</a>
					</p>
					<p>
						<a href="loginOut.do" target="_self">退出系统</a>
					</p>
				</div> <!-- #EndLibraryItem -->
				<div id="right">
					<p>您现在的位置 &gt;&gt; 我的任务 &gt;&gt; 任务信息</p>
					<h1>任务信息:</h1>
					<form id="form1" name="form1" method="post" action="taskview.do">
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">任务名称</td>
								<td width="15%" class="tdcolor">制定人</td>
								<td width="15%" class="tdcolor">开始时间</td>
								<td width="15%" class="tdcolor">结束时间</td>
								<td width="15%" class="tdcolor">任务状态</td>
								<td width="15%" class="tdcolor">计划数目</td>
								<td width="10%" class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${taskplan.data }" var="tp" varStatus="st">
								<tr>
									<td>${tp.task.taskName }</td>
									<td>${sessionScope.employee.parent.realName }</td>
									<td><fmt:formatDate pattern="yyyy年MM月dd日"
											value="${tp.task.beginDate }" /></td>
									<td><fmt:formatDate pattern="yyyy年MM月dd日"
											value="${tp.task.endDate }" /></td>
									<td>${tp.task.status }</td>
									<td>${fn:length(tp.plans)}</td>
									<td><input type="radio" name="radiobutton"
										value="${tp.task.taskId }" /></td>
								</tr>
							</c:forEach>
						</table>
						<p>
							<label> <input name="Submit" type="submit" id="sb1"
								disabled="disabled" class="menu3" value="计划管理" /> </label>
						</p>
					</form>
					<a>共${taskplan.lastPage }页</a> <a
						href="task.do?pageNo=${taskplan.firstPage}&pageSize=${taskplan.pageSize}">首页</a>
					<a
						href="task.do?pageNo=${taskplan.previousPage}&pageSize=${taskplan.pageSize}">上一页</a>
					<a
						href="task.do?pageNo=${taskplan.nextPage}&pageSize=${taskplan.pageSize}">下一页</a>
					<a
						href="task.do?pageNo=${taskplan.lastPage}&pageSize=${taskplan.pageSize}">末页</a>
					<a>第${taskplan.pageNo }页</a> <a>共${taskplan.totalRecords }条记录</a>
				</div>
			</td>
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

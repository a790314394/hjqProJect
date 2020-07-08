<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>计划信息</title>
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
				<!-- #BeginLibraryItem "/Library/left2.lbi" -->

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
				</div>
				<!-- #EndLibraryItem -->
				<div id="right">
					<p>您现在的位置 &gt;&gt; 计划管理 &gt;&gt; 计划信息</p>

					<form action="updatetask.do" method="post">
						<h1>任务详细信息:</h1>
						<input type="hidden" name="taskid" value="${taskp.taskId }" />
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">任务名称</td>
								<td width="579" colspan="3">${taskp.taskName }</td>
							</tr>
							<tr>
								<td class="tdcolor">任务描述</td>
								<td colspan="3">${taskp.taskDesc }</td>
							</tr>
							<tr>
								<td class="tdcolor">开始时间</td>
								<td width="579"><fmt:formatDate pattern="yyyy年MM月dd日"
										value="${taskp.beginDate }" />
								</td>
								<td width="579" class="tdcolor">结束时间</td>
								<td width="579"><p>
										<fmt:formatDate pattern="yyyy年MM月dd日"
											value="${taskp.endDate }" />
									</p>
								</td>
							</tr>

							<tr>
								<td class="tdcolor">实施人</td>
								<td>${taskp.task.employeeName }</td>
								<td class="tdcolor">任务状态</td>
								<c:if test="${taskp.status=='待实施'}">
									<td><select name="select">
											<option value="未实施">待实施</option>
											<option value="实施中">实施中</option>
									</select>
									</td>
								</c:if>
								<c:if test="${taskp.status=='实施中'}">
									<td><select name="select">
											<option value="实施中">实施中</option>
									</select>
									</td>
								</c:if>
								<c:if test="${taskp.status=='已完成'}">
									<td><select name="select">
											<option value="已完成">已完成</option>
									</select>
									</td>
								</c:if>
							</tr>

						</table>
						<p>
							<input name="Submit" type="submit" class="menu1" value="修改状态" />
						</p>
					</form>

					<form id="form1" name="form1" method="post" action="delete2.do">
						<input type="hidden" name="taskid" value="${taskp.taskId }" />
						<h1>计划信息：</h1>
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">计划名称</td>
								<td width="15%" class="tdcolor">完成状态</td>
								<td class="tdcolor">是否反馈</td>
								<td class="tdcolor">开始时间</td>
								<td class="tdcolor">结束时间</td>
								<td class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${plan.data}" var="p">
								<c:forEach items="${p.plans}" var="c">
									<tr>
										<td><a href="selectplan.do?planid=${c.planId }">${c.planName
												}</a>
										</td>
										<td>${c.status }</td>
										<td>${c.isFeedBack }</td>
										<td><fmt:formatDate pattern="yyyy年MM月dd日"
												value="${c.beginDate }" />
										</td>
										<td><fmt:formatDate pattern="yyyy年MM月dd日"
												value="${c.endDate }" />
										</td>
										<td><label> <input type="radio"
												name="radiobutton" value="${c.planId }" /> </label>
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
						</table>
						<p>

							<a href="${pageContext.request.contextPath }/person/newpro.jsp"
								name="Submit2" class="menu2">新建</a> <input name="Submit"
								type="submit" class="menu1" value="删除" id="sb1"
								disabled="disabled" />
						</p>
					</form>
					<a>共${plan.lastPage }页</a> <a
						href="taskview.do?pageNo=${plan.firstPage}&pageSize=${plan.pageSize}">首页</a>
					<a
						href="taskview.do?pageNo=${plan.previousPage}&pageSize=${plan.pageSize}">上一页</a>
					<a
						href="taskview.do?pageNo=${plan.nextPage}&pageSize=${plan.pageSize}">下一页</a>
					<a
						href="taskview.do?pageNo=${plan.lastPage}&pageSize=${plan.pageSize}">末页</a>
					<a>第${plan.pageNo }页</a> <a>共${plan.totalRecords }条记录</a>
				</div></td>
		</tr>
	</table>
	<!-- #BeginLibraryItem "/Library/bottom.lbi" -->
	<div id="bottom">
		<img src="../images/button.jpg" width="1002" height="17" />
	</div>
	<!-- #EndLibraryItem -->
</body>
</html>

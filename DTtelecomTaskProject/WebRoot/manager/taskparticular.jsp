<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务详细信息</title>
<link href="${pageContext.request.contextPath }/css/css.css"
	rel="stylesheet" type="text/css" />
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
					page="left.jsp" /> <!-- #EndLibraryItem -->
				<div id="right">
					<p>您现在的位置 &gt;&gt; 查看任务 &gt;&gt; 任务详细信息</p>
					<h1>任务详细信息:</h1>

					<table width="700" border="0" cellpadding="0" cellspacing="0"
						class="table">
						<tr>
							<td width="15%" class="tdcolor">任务名称</td>
							<td width="579" colspan="5">${requestScope.tp.task.taskName
								}</td>
						</tr>
						<tr>
							<td class="tdcolor">任务描述</td>
							<td colspan="5">${requestScope.tp.task.taskDesc}</td>
						</tr>
						<tr>
							<td class="tdcolor">开始时间</td>
							<td width="15%"><fmt:formatDate
									value="${requestScope.tp.task.beginDate}" pattern="yyyy-MM-dd" />
							</td>
							<td width="20%" class="tdcolor">结束时间</td>
							<td width="50%" colspan="3"><p>
									<fmt:formatDate value="${requestScope.tp.task.endDate}"
										pattern="yyyy-MM-dd" />
								</p>
							</td>
						</tr>
						<tr>
							<td class="tdcolor">实际开始时间</td>
							<td><fmt:formatDate
									value="${requestScope.tp.task.realBeginDate}"
									pattern="yyyy-MM-dd" />
							</td>
							<td class="tdcolor">实际结束时间</td>
							<td colspan="3"><fmt:formatDate
									value="${requestScope.tp.task.realEndDate}"
									pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<td class="tdcolor">实施人</td>
							<td>${requestScope.tp.task.task.realName}</td>
							<td class="tdcolor">任务状态</td>
							<td>${requestScope.tp.task.status}</td>
							<td class="tdcolor">计划数目</td>
							<td>${fn:length(requestScope.tp.plans)}</td>
						</tr>
					</table>
					<form id="form1" name="form1" method="post"
						action="showPlanInfo.do">
						<h1>实施计划：</h1>
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">计划名称</td>
								<td width="15%" class="tdcolor">完成状态</td>
								<td width="20%" class="tdcolor">是否反馈</td>
								<td class="tdcolor">结束时间</td>
								<td class="tdcolor">结束时间</td>
								<td class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${requestScope.tp.plans }" var="plan">
								<tr>
									<td>${plan.planName }</td>
									<td>${plan.status }</td>
									<td>${plan.isFeedBack}</td>
									<td><fmt:formatDate value="${plan.beginDate}"
											pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${plan.endDate}"
											pattern="yyyy-MM-dd" /></td>
									<td><label> <input type="radio" name="planId"
											value="${plan.planId}" /> </label>
									</td>
								</tr>
							</c:forEach>
						</table>
						<p>
							<label> <input name="Submit" type="submit" class="menu3"
								value="详细信息" /> </label>
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

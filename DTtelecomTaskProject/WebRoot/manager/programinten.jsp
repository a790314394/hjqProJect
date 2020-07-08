<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>跟踪计划信息</title>
<link href="${pageContext.request.contextPath }/css/css.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#Submit").click(
				function() {

					$("input[name=fkxq]:checked").each(
							function() {

								var index = $(this).val();
								alert(index);
								$(this).parent().parent().after(
										"<tr><td>" + index + "</td></tr>");
							});

				})

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
					page="left.jsp" /> <!-- #EndLibraryItem -->
				<div id="right">
					<p>您现在的位置 &gt;&gt; 跟踪任务 &gt;&gt; 跟踪计划信息</p>
					<form id="form1" name="form1" method="post"
						action="submit.do?id=${requestScope.p.task.taskId }">
						<h1>任务详细信息:</h1>

						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">任务名称</td>
								<td width="579" colspan="3">${requestScope.p.task.taskName}</td>
							</tr>
							<tr>
								<td class="tdcolor">任务描述</td>
								<td colspan="3">${requestScope.p.task.taskDesc}</td>
							</tr>
							<tr>
								<td class="tdcolor">开始时间</td>
								<td width="579"><fmt:formatDate
										value="${requestScope.p.task.beginDate}" pattern="yyyy-MM-dd" />
								</td>
								<td width="579" class="tdcolor">结束时间</td>
								<td width="579"><p>
										<fmt:formatDate value="${requestScope.p.task.endDate}"
											pattern="yyyy-MM-dd" />
									</p>
								</td>
							</tr>

							<tr>
								<td class="tdcolor">实施人</td>
								<td>${requestScope.p.task.task.realName}</td>
								<td class="tdcolor">任务状态</td>
								<td><select name="status">
										<option value="实施中" selected="selected">实施中</option>
										<option value="已完成">已完成</option>
								</select>
								</td>
							</tr>
						</table>
						<input name="Submit" type="submit" class="menu4" value="提交" />
					</form>
					<form id="form2" name="form1" method="post" action="">
						<h1>计划信息：</h1>
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">计划名称</td>
								<td width="15%" class="tdcolor">完成状态</td>
								<td class="tdcolor">是否反馈</td>
								<td class="tdcolor">结束时间</td>
								<td class="tdcolor">结束时间</td>
								<td class="tdcolor">&nbsp;</td>
							</tr>
							<c:forEach items="${requestScope.p.plans}" var="plan">
								<tr>
									<td>${plan.planName }</td>
									<td>${plan.status }</td>
									<td>${plan.isFeedBack eq '是'?'已反馈':'未反馈'}</td>
									<td>${plan.beginDate }</td>
									<td>${plan.endDate }</td>
									<td><label> <c:if test="${plan.isFeedBack eq '是'}">
												<input type="checkbox" name="fkxq"
													value="${plan.feedBackInfo}" />
											</c:if> </label></td>
								</tr>
							</c:forEach>
						</table>
						<p>
							<input id="Submit" name="Submit" type="button" class="menu3"
								value="反馈信息" />
						</p>
					</form>
				</div>
			</td>
		</tr>
	</table>
	<div class="copyright">COPYRIGHT 2007 DATANG TELECOM TECHNOLOGY
		CO.LTD 京ICP备06071639号</div>
	<!-- #EndLibraryItem -->
	<!-- #BeginLibraryItem "/Library/bottom.lbi" -->
	<div id="bottom">
		<img src="${pageContext.request.contextPath }/images/button.jpg"
			width="1002" height="17" />
	</div>
</body>
</html>

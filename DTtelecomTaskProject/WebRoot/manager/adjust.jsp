<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ include file="../commons/mytaglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>调整任务</title>
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
			<td class="rightimg"><jsp:include page="left.jsp" />
				<div id="right">
					<p>您现在的位置 &gt;&gt; 调整任务 &gt;&gt; 调整任务信息</p>
					<h1>调整任务信息</h1>

					<form id="form1" name="form1" method="post"
						action="updateTaskInfo.do?taskId=${requestScope.tsp.task.taskId}">
						<p>
							任务名称：<label> <input name="taskName" type="text" size="50"
								value="${requestScope.tsp.task.taskName}" /> </label>
						</p>
						<p>
							任务描述： <label> <textarea name="taskDesc">${requestScope.tsp.task.taskDesc}</textarea>
							</label>
						</p>
						<p>
							开始时间： <label> <input name="beginDate" type="text"
								size="16"
								value="<fmt:formatDate value="${requestScope.tsp.task.beginDate}"
											pattern="yyyy-MM-dd" />" />
							</label> <span class="marginleft1">结束时间： <label> <input
									name="endDate" type="text" size="16"
									value="<fmt:formatDate value="${requestScope.tsp.task.endDate}" pattern="yyyy-MM-dd"  />" />
							</label> </span>
						</p>
						<p>
							实施人员： <label> <select name="employeeId">
									<c:forEach items="${requestScope.persons }" var="person">
										<c:if
											test="${requestScope.tsp.task.task.employeeId == person.employeeId}">
											<option value="${person.employeeId}" selected="selected">${person.realName
												}</option>
										</c:if>
										<c:if
											test="${requestScope.tsp.task.task.employeeId != person.employeeId}">
											<option value="${person.employeeId}">${person.realName
												}</option>
										</c:if>
									</c:forEach>

							</select> </label> <span class="marginleft">任务状态：
								${requestScope.tsp.task.status} </span>
						</p>
						<p>
							<label> <input name="Submit" type="reset" class="menu2"
								value="重置" /> </label> &nbsp; <label> <input name="Submit2"
								type="submit" class="menu1" value="修改"
								onclick="return confirm('确认修改吗？')" /> </label>
						</p>
					</form>
					<p>&nbsp;</p>
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

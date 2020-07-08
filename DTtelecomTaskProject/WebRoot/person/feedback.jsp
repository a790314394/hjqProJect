<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>反馈信息</title>
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
					<p>您现在的位置 &gt;&gt; 计划管理 &gt;&gt; 反馈信息</p>
					<form id="form1" name="form1" method="post" action="update1.do">
						<h1>输入反馈信息:</h1>
						<input type="hidden" name="planid" value="${pl.planId }" /> <input
							type="hidden" name="taskid" value="${pl.taskId }" />
						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">计划名称</td>
								<td width="579" colspan="3">${pl.planName }</td>
							</tr>
							<tr>
								<td class="tdcolor">计划描述</td>
								<td colspan="3">${pl.planDesc }</td>
							</tr>
							<tr>
								<td class="tdcolor">开始时间</td>
								<td width="579"><fmt:formatDate pattern="yyyy年MM月dd日"
										value="${pl.beginDate }" />
								</td>
								<td width="579" class="tdcolor">结束时间</td>
								<td width="579"><p>
										<fmt:formatDate pattern="yyyy年MM月dd日" value="${pl.endDate }" />
									</p>
								</td>
							</tr>

							<tr>
								<td class="tdcolor">计划状态</td>
								<c:if test="${pl.status=='待实施'}">
									<td colspan="3"><select name="select">
											<option value="待实施">待实施</option>
											<option value="实施中">实施中</option>
											<option value="已完成">已完成</option>
									</select>
									</td>
								</c:if>
								<c:if test="${pl.status=='实施中'}">
									<td colspan="3"><select name="select">
											<option value="实施中">实施中</option>
											<option value="已完成">已完成</option>
									</select>
									</td>
								</c:if>
								<c:if test="${pl.status=='已完成'}">
									<td colspan="3"><select name="select">
											<option value="已完成">已完成</option>
									</select>
									</td>
								</c:if>
							</tr>
							<tr>
								<td class="tdcolor">是否反馈</td>
								<c:if test="${pl.isFeedBack=='否'}">
									<td colspan="3"><select name="select2">
											<option value="否">否</option>
											<option value="是">是</option>
									</select>
									</td>
								</c:if>
								<c:if test="${pl.isFeedBack=='是'}">
									<td colspan="3"><select name="select2">
											<option value="是">是</option>
									</select>
									</td>
								</c:if>
							</tr>
							<tr>
								<td class="tdcolor">反馈信息</td>
								<td colspan="3"><label> <textarea name="textarea"
											cols="60" rows="4">${pl.feedBackInfo }</textarea> </label>
								</td>
							</tr>
						</table>


						<p>
							<input name="Submit" type="reset" class="menu2" value="取消" /> <input
								name="Submit" type="submit" class="menu1" value="提交" />
						</p>
					</form>
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

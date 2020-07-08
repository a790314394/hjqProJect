<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询计划</title>
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
					<p>您现在的位置 &gt;&gt; 查询计划 &gt;&gt; 查询</p>
					<form action="pro.do" method="post">
						<input type="hidden" name="flag" value="1" />
						<h1>查询信息:</h1>

						<table width="700" border="0" cellpadding="0" cellspacing="0"
							class="table">
							<tr>
								<td width="15%" class="tdcolor">计划名称</td>
								<td width="579"><input name="search_planName" type="text"
									value="${plan_dto.search_planName }" size="15" /></td>
							</tr>
							<tr>
								<td class="tdcolor">所属任务</td>
								<td><select name="select">
										<c:forEach items="${taskplan }" var="tp">
											<option value="${tp.task.taskId }">${tp.task.taskName
												}</option>
										</c:forEach>
								</select></td>
							</tr>

							<tr>
								<td class="tdcolor">开始时间</td>
								<td>从 <input name="search_begin_date1" type="text"
									value="<fmt:formatDate pattern="yyyy-MM-dd" value="${plan_dto.search_begin_date1 }" />"
									size="15" /> 到 <input name="search_begin_date2" type="text"
									value="<fmt:formatDate pattern="yyyy-MM-dd" value="${plan_dto.search_begin_date2 }" />"
									size="15" />
								</td>
							</tr>
							<tr>
								<td class="tdcolor">结束时间</td>
								<td>从 <input name="search_end_date1" type="text"
									value="<fmt:formatDate pattern="yyyy-MM-dd" value="${plan_dto.search_end_date1 }" />"
									size="15" /> 到 <input name="search_end_date2" type="text"
									value="<fmt:formatDate pattern="yyyy-MM-dd" value="${plan_dto.search_end_date2 }" />"
									size="15" /></td>
							</tr>
							<tr>
								<td class="tdcolor">是否反馈</td>
								<td><label> <input name="search_info" type="text"
										value="${plan_dto.feedback_info }" size="15" /> </label></td>
							</tr>
						</table>

						<p>
							<input name="submit" type="submit" class="menu3" value="查询计划" />
						</p>
					</form>
					<h1>计划信息：</h1>
					<table width="700" border="0" cellpadding="0" cellspacing="0"
						class="table">

						<tr>
							<td width="15%" class="tdcolor">计划名称</td>
							<td width="15%" class="tdcolor">所属任务</td>
							<td class="tdcolor">开始时间</td>
							<td class="tdcolor">结束时间</td>
							<td class="tdcolor">计划状态</td>
							<td class="tdcolor">是否反馈</td>
						</tr>
						<c:forEach items="${plan_pb.data}" var="p">
							<c:forEach items="${p.plans}" var="c">
								<tr>
									<td><a
										href="selectplan.do?planid=${c.planId }&taskid=${c.taskId}"
										target="_self">${c.planName }</a></td>
									<td>${c.taskId }</td>
									<td><fmt:formatDate pattern="yyyy年MM月dd日"
											value="${c.beginDate }" /></td>
									<td><fmt:formatDate pattern="yyyy年MM月dd日"
											value="${c.endDate }" /></td>
									<td>${c.status }</td>
									<td><label>${c.isFeedBack }</label></td>
								</tr>
							</c:forEach>
						</c:forEach>
					</table>
	</TABLE>
	<a>共${plan_pb.lastPage }页</a>
	<a
		href="pro.do?pageNo=${plan_pb.firstPage}&pageSize=${plan_pb.pageSize}">首页</a>
	<a
		href="pro.do?pageNo=${plan_pb.previousPage}&pageSize=${plan_pb.pageSize}">上一页</a>
	<a
		href="pro.do?pageNo=${plan_pb.nextPage}&pageSize=${plan_pb.pageSize}">下一页</a>
	<a
		href="pro.do?pageNo=${plan_pb.lastPage}&pageSize=${plan_pb.pageSize}">末页</a>
	<a>第${plan_pb.pageNo }页</a>
	<a>共${plan_pb.totalRecords }条记录</a>


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

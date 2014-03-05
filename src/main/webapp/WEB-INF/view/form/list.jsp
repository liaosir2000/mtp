<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<td>工作面</td>
				<td>巷道</td>
				<td>填表日期</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${forms}" var="form">
				<tr>
					<td>${form.workingSurfaceName}</td>
					<td>${form.tunnelName}</td>
					<td><joda:format value="${form.createTime}" pattern="yyyy年MM月dd日 HH:mm" /></td>
					<td><a href="<%=request.getContextPath()%>/form/${form.id}" class="btn btn-primary">详情</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
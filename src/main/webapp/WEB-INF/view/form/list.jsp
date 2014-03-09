<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="container" ng-controller = "FormList">
	<table class="table table-striped">
		<thead>
			<tr>
				<td>工作面</td>
				<td>巷道</td>
				<td>观测点</td>
				<td>填表日期</td>
				<td>报告人</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="form in forms">
				<td>{{form.surfaceName}}</td>
				<td>{{form.tunnelName}}</td>
				<td>{{form.pointName}}</td>
				<td>{{form.createTime}}</td>
				<td>{{form.reporterName}}</td>
				<td><a href="<%=request.getContextPath()%>/form/{{form.id}}?view" class="btn btn-primary">详情</a></td>
			</tr>
		</tbody>
	</table>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/formList.js"></script>
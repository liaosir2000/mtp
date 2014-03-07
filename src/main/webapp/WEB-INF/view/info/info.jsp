<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div class="container" ng-controller="Info" >
		<div class="h2">观测情况</div>
		<div class="row">
			<form class="form-inline" ng-submit="createInfo()">
				<input type="text" name="name" ng-model="name" placeholder="观测情况" class="form-control">
				<input type="submit" value="新建" class="btn btn-primary">
			</form>
		</div>
		<table class="table table-striped">
			<tr>
				<th>观测情况</th>
				<th>操作</th>
			</tr>
			<tr ng-repeat="info in infos">
				<td><a>{{info.name}}</a></td>
				<td><a class="btn btn-primary" ng-click="deleteInfo($index)">删除</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/info.js"></script>
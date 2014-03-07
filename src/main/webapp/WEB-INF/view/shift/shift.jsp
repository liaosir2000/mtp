<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div class="container" ng-controller="Shift" >
		<div class="h2">轮班</div>
		<div class="row">
			<form class="form-inline" ng-submit="createShift()">
				<input type="text" name="name" ng-model="name" placeholder="班次" class="form-control">
				<input type="submit" value="新建" class="btn btn-primary">
			</form>
		</div>
		<table class="table table-striped">
			<tr>
				<th>班次</th>
				<th>操作</th>
			</tr>
			<tr ng-repeat="shift in shifts">
				<td><a>{{shift.name}}</a></td>
				<td><a class="btn btn-primary" ng-click="deleteShift($index)">删除</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/shift.js"></script>
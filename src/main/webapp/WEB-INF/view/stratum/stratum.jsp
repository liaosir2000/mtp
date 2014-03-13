<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div ng-controller="Stratum" class="mtp-config">
		<div class="mtp-config-edit">
			<form class="form-inline" ng-submit="createStratum()">
				<input type="text" name="name" ng-model="name" placeholder="岩层" class="form-control">
				<input type="submit" value="新建" class="btn btn-primary">
			</form>
		</div>
		<table class="table table-striped form-list">
			<tr>
				<th>岩层</th>
				<th>操作</th>
			</tr>
			<tr ng-repeat="stratum in stratums">
				<td><a>{{stratum.name}}</a></td>
				<td><a class="btn btn-primary" ng-click="deleteStratum($index)">删除</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/stratum.js"></script>
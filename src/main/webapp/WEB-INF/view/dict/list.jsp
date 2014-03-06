<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
<!--
function SurfaceCtrl($scope) {
	$scope.submit() {
		alert($scope.name);
		$scope.name = "";
	};
}
//-->
</script>

	<div class="container">
		<div class="h1">${type}配置管理</div>
		<div class="container">
			<div class="row">
				<form action="" method="post" class="form-inline" role="form" ng-submit="submit()" ng-controller="SurfaceCtrl">
					<input type="hidden" name="type" ng-model="type" value="${type}">
					<input type="text" name="name" ng-model="name" placeholder="配置项名称" class="form-control">
					<input type="submit" value="新建" class="btn btn-primary">
				</form>
			</div>
		</div>
		<table class="table table-striped">
			<tr>
				<th>配置项</th>
				<th>操作</th>
			</tr>
			<tr ng-repeat="surface in surfaces">
				<td>{{surface.name}}</td>
				<td><a class="dictDelete btn btn-primary" target="_self"
					data-id="{{surface.id}}">删除</a></td>
			</tr>
		</table>
	</div>

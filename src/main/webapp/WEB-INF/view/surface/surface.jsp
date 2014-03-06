<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	<div class="container"  >
		<div class="col-lg-4">
			<div class="h1">工作面配置管理</div>
			<div class="row">
				<form class="form-inline" ng-submit="submit()" ng-controller="SurfaceEdit">
					<input type="text" name="name" ng-model="name" placeholder="配置项名称" class="form-control">
					<input type="submit" value="新建" class="btn btn-primary">
				</form>
			</div>
			<table class="table table-striped" ng-controller="SurfaceLoading">
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
		<div class="col-lg-4">
			<div class="h1">巷道配置管理</div>
		</div>
		<div class="col-lg-4">
			<div class="h1">观测点配置管理</div>
		</div>
	</div>

<script type="text/javascript">
function SurfaceEdit($scope, $http) {
	$scope.submit = function(){
		$http.post("surface", {name:$scope.name})
		.success(function(data, status, headers, config){
			
		})
		.error(function(data, status, headers, config){
			
		});
		$scope.name = "";
	};
};

function SurfaceLoading($scope, $http) {
	$http.get("surface/list")
	.success(function(data, status, headers, config){
		$scope.surfaces = data;
	})
	.error(function(data, status, headers, config){
		
	});
}
</script>
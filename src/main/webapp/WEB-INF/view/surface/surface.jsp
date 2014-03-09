<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	<div class="container" ng-controller="Surface" >
		<div class="col-lg-3">
			<div class="h2">工作面</div>
			<div class="row">
				<form class="form-inline" ng-submit="createSurface()">
					<input type="text" name="name" ng-model="name" placeholder="工作面名称" class="form-control">
					<input type="submit" value="新建" class="btn btn-primary">
				</form>
			</div>
			<table class="table table-striped">
				<tr>
					<th>配置项</th>
					<th>操作</th>
				</tr>
				<tr ng-repeat="surface in surfaces">
					<td><a ng-click="selectSurface($index)" class="pointer" ng-class="{selected: $index == selectedSurfaceRow}">{{surface.name}}</a></td>
					<td><a class="btn btn-primary" ng-click="deleteSurface($index)">删除</a></td>
				</tr>
			</table>
		</div>
		<div class="col-lg-3">
			<div class="h2">巷道</div>
			<div class="row">
				<form class="form-inline" ng-submit="createTunnel()">
					<input type="text" name="name" ng-model="tunnelName" placeholder="巷道名称" class="form-control">
					<input type="submit" value="新建" class="btn btn-primary">
				</form>
			</div>
			<table class="table table-striped">
				<tr>
					<th>配置项</th>
					<th>操作</th>
				</tr>
			    <tr ng-repeat="tunnel in tunnels">
					<td><a ng-click="selectTunnel($index)" class="pointer" ng-class="{selected: $index == selectTunnelRow}">{{tunnel.name}}</a></td>
					<td><a class="btn btn-primary" ng-click="deleteTunnel($index)">删除</a></td>
				</tr>
			</table>
		</div>
		<div class="col-lg-6">
			<div class="h2">观测点</div>
			<div class="row">
				<form class="form-inline" ng-submit="createPoint()">
					<input type="text" name="pointName" ng-model="pointName" placeholder="观测点名称" class="form-control">
					坐标
					X=<input type="number" name="x" ng-model="x" pattern="[0-9]+(\.[0-9]+)?" step="0.1"  class="form-control input-short">
					Y=<input type="number" name="y" ng-model="y" pattern="[0-9]+(\.[0-9]+)?" step="0.1"  class="form-control input-short">
					Z=<input type="number" name="z" ng-model="z" pattern="[0-9]+(\.[0-9]+)?" step="0.1"  class="form-control input-short">
					<input type="submit" value="新建" class="btn btn-primary">
				</form>
			</div>
			<table class="table table-striped">
				<tr>
					<th>配置项</th>
					<th>X</th>
					<th>Y</th>
					<th>Z</th>
					<th>操作</th>
				</tr>
			    <tr ng-repeat="point in points">
					<td>{{point.name}}</td>
					<td>{{point.x}}</td>
					<td>{{point.y}}</td>
					<td>{{point.z}}</td>
					<td><a class="btn btn-primary" ng-click="deletePoint($index)">删除</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/surface.js"></script>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div ng-controller="Surface" class="mtp-config">
	<div class="surface-left">
		<div class="h3">工作面</div>
		<div>
			<form class="form-inline" ng-submit="createSurface()">
				<input type="text" name="name" required ng-model="name" placeholder="工作面名称" class="form-control"> <input
					type="submit" value="新建" class="btn btn-primary">
			</form>
		</div>
		<table class="table table-striped form-list">
			<tr>
				<th>工作面名称</th>
				<th>操作</th>
			</tr>
			<tr ng-repeat="surface in surfaces">
				<td><a ng-click="selectSurface($index)" class="pointer" ng-class="{selected: $index == selectedSurfaceRow}">{{surface.name}}</a></td>
				<td><a class="btn btn-primary" ng-click="deleteSurface($index)">删除</a></td>
			</tr>
		</table>
	</div>
	<div class="surface-middle">
		<div class="h3">巷道</div>
		<div>
			<form class="form-inline" ng-submit="createTunnel()">
				<input type="text" name="name" required ng-model="tunnelName" placeholder="巷道名称" class="form-control"> <input
					type="submit" value="新建" class="btn btn-primary">
			</form>
		</div>
		<table class="table table-striped form-list">
			<tr>
				<th>巷道名称</th>
				<th>操作</th>
			</tr>
			<tr ng-repeat="tunnel in tunnels">
				<td><a ng-click="selectTunnel($index)" class="pointer" ng-class="{selected: $index == selectTunnelRow}">{{tunnel.name}}</a></td>
				<td><a class="btn btn-primary" ng-click="deleteTunnel($index)">删除</a></td>
			</tr>
		</table>
	</div>
	<div class="surface-right">
		<div class="h3">观测点</div>
		<div>
			<form class="form-inline" ng-submit="createPoint()">
				<input type="text" name="pointName" required ng-model="pointName" placeholder="观测点名称" class="form-control" id="point-input"> 坐标 X=<input
					type="number" name="x" required ng-model="x" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short" placeholder="X轴">
				Y=<input type="number" name="y" required ng-model="y" pattern="[0-9]+(\.[0-9]+)?" step="0.1"
					class="form-control number-short" placeholder="Y轴"> Z=<input type="number" name="z" required ng-model="z" pattern="[0-9]+(\.[0-9]+)?"
					step="0.1" class="form-control number-short" placeholder="Z轴"> <input type="submit" value="新建" class="btn btn-primary">
			</form>
		</div>
		<table class="table table-striped form-list">
			<tr>
				<th>观测点名称</th>
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
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<div ng-controller="Profile"  class="profile">
		<div class="profile-select">
			<span>工作面</span>
			<select ng-model="surfaceId" class="form-control input-stratum" ng-change="selectSurface()">
				<option ng-repeat="surface in surfaces" value="{{surface.id}}">{{surface.name}}</option>
			</select>
			<span>巷道</span>
			<select ng-model="tunnelId" class="form-control input-stratum" ng-change="drawProfile()">
				<option ng-repeat="tunnel in tunnels" value="{{tunnel.id}}">{{tunnel.name}}</option>
			</select>
			<!-- <button ng-click="drawProfile()" class="btn btn-primary">绘制剖面图</button> -->
		</div>
		<div>
			<canvas id="profile">
			
			</canvas>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/profile.js"></script>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div ng-controller="Info"  class="mtp-config">
		<div class="mtp-config-edit">
			<form class="form-inline" ng-submit="createInfo()">
				<input type="text" name="name" required ng-model="name" placeholder="观测情况" class="form-control">&nbsp;&nbsp;
				是否告警<input type="checkbox" name="warn" ng-model="warn" placeholder="是否告警" class="form-control">
				<div class="warn-receiver" ng-show="warn">
					告警接收人
					<select ng-model="personId" class="form-control input-stratum">
							<option ng-repeat="p in persons" value="{{p.id}}">{{p.name}}</option>
					</select>
				</div>
				<input type="hidden" ng-model="persionName">
				<input type="hidden" ng-model="persionEmail">
				<input type="submit" value="新建" class="btn btn-primary">
			</form>
		</div>
		<table class="table table-striped form-list">
			<tr>
				<th>观测情况</th>
				<th>是否告警</th>
				<th>告警接受人</th>
				<th>操作</th>
			</tr>
			<tr ng-repeat="info in infos">
				<td><a>{{info.name}}</a></td>
				<td><a>{{info.warn}}</a></td>
				<td><a>{{info.personName}}</a></td>
				<td><a class="btn btn-primary" ng-click="deleteInfo($index)">删除</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/info.js"></script>
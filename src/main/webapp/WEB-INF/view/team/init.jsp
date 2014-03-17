<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div ng-controller="Team" ng-init="loadTeam()">
		<div ng-repeat = "team in teams">
			<a ng-repeat = "member in team.members" ng-click="loginWith(member.id)" class="btn btn-primary">
				{{member.name}}
			</a>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/team.js"></script>
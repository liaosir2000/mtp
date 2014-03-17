<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div ng-controller="Team" ng-init="loadTeam()">
		<div ng-repeat = "team in teams">
			<div ng-repeat = "member in team.members">
				<span data-id={{member.id}}>{{member.name}}</span>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/team.js"></script>
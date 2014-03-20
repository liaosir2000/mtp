<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div ng-controller="Team" ng-init="loadTeam()" class="team-login">
		<span class="team-border">
		<div ng-repeat = "team in teams">
			<span class="team-header">{{team.name}}</span>
			<a ng-repeat = "member in team.members" ng-click="loginWith(member.id)" class="btn btn-primary team-list" >
				{{member.name}}
			</a>
		</div>
		</span>
	</div>
	<div id="dialogInHtml" title="请输入队组密码" class="ui-helper-hidden">
		{{message}}
        <input type="password" ng-model="pwd" class="form-control"/>
    </div>
        
	<script type="text/ng-template" src="<%=request.getContextPath()%>/resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/team.js"></script>
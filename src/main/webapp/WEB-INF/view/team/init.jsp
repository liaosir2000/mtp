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
	<script type="text/ng-template" id="loginDialog.html">
<div ng-contoller="loginController" class="loginDialog">
<div class="modal-body" id="modal-body">
	<span class="pwd">口令</span><input type="password" ng-model="pwd" ng-change="pwdInput()">
</div>
<div class="modal-footer" id="modal-footer">
	<button class="btn btn-primary" ng-click="dialogOk()">登陆</button>
	<button class="btn btn-primary" ng-click="cancel()">取消</button>
</div>
</div>
	</script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/team.js"></script>
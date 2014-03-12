<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%
	request.setAttribute("path",request.getContextPath());
%>
<!DOCTYPE html>
<html ng-app="mtp-app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/angular.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/ui-bootstrap-tpls-0.10.0.min.js"></script>
<script src='<%=request.getContextPath()%>/resources/js/jcanvas.min.js'></script>
	<title><t:insertAttribute name="title" /></title>
</head>
<body>
	<div class="title">
	地质保障管理预警平台
	</div>
	<nav class="navbar navbar-default mtpNav" role="navigation">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="<%=request.getContextPath()%>/form/edit">填写信息卡</a></li>
					<li><a href="<%=request.getContextPath()%>/form/listPage">信息卡列表</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">配置管理 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/surface/conf">工作面配置</a></li>
							<li><a href="<%=request.getContextPath()%>/stratum/conf">岩层配置</a></li>
							<li><a href="<%=request.getContextPath()%>/shift/conf">轮班配置</a></li>
							<li><a href="<%=request.getContextPath()%>/info/conf">观测情况</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="mtpBody">
		<t:insertAttribute name="content" />
	</div>
</body>
</html>

<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/main.css">
<script src="resources/js/jquery-1.11.0.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/function.js"></script>
<title>工作面地质信息卡</title>
</head>
<body>
	<div id="form">
		<form action="" method="POST" class="form-inline" role="form">
			<div class="header">
				<select name="workingSurfaceId">
					<c:forEach items="${surfaces}" var="surface">
						<option value="${surface.id}">${surface.name}</option>
					</c:forEach>
				</select>工作面地质信息卡
			</div>
			<div class="shift">
				<joda:format value="${serverTime}" pattern="yyyy年MM月dd日" />
				<select name="shiftId">
					<c:forEach items="${shifts}" var="shift">
						<option value="${shift.id}">${shift.name}</option>
					</c:forEach>
				</select>班
			</div>
			<div>
				<span>巷道名称</span> 
				<span>
					<select name="tunnelId">
						<c:forEach items="${tunnels}" var="tunnel">
							<option value="${tunnel.id}">${tunnel.name}</option>
						</c:forEach>
					</select>
				</span>
			</div>
			<div>
				<span>观测点位置</span> 
				<span>
					<select name="observerPointId">
						<c:forEach items="${observerPoints}" var="observerPoint">
							<option value="${observerPoint.id}">${observerPoint.name}</option>
						</c:forEach>
					</select>
				</span>
			</div>
		</form>
	</div>
</body>
</html>
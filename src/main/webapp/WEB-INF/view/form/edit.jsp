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
					<c:forEach items="${config.surfaces}" var="surface">
						<option value="${surface.id}">${surface.name}</option>
					</c:forEach>
				</select>工作面地质信息卡
			</div>
			<div class="shift">
				<joda:format value="${config.serverTime}" pattern="yyyy年MM月dd日" />
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
						<c:forEach items="${config.tunnels}" var="tunnel">
							<option value="${tunnel.id}">${tunnel.name}</option>
						</c:forEach>
					</select>
				</span>
			</div>
			<div>
				<span>观测点位置</span> 
				<span>
					<select name="observerPointId">
						<c:forEach items="${config.observerPoints}" var="observerPoint">
							<option value="${observerPoint.id}">${observerPoint.name}</option>
						</c:forEach>
					</select>
				</span>
				前X=<input type="text" size="4"  name="observerPointX">米,
				Y=<input type="text" size="4" name="observerPointX">米,
				Z=<input type="text" size="4" name="observerPointX">米
			</div>
			<div>
				<span>岩（煤）层厚度</span>
				<span>
					<div>顶部</div>
					<div>掌子面</div>
					<div>底部</div>
				</span>
			</div>
			<div>
				<span>顶板锚杆及锚索施工情况</span>
				<span>
					<select name="observeInfo1">
						<c:forEach items="${config.observeInfos}" var="observeInfo">
							<option value="${observeInfo.id}">${observeInfo.name}</option>
						</c:forEach>
					</select>
				</span>
			</div>
			<div>
				<span>超前探眼情况</span>
				<span>
					<select name="observeInfo2">
						<c:forEach items="${config.observeInfos}" var="observeInfo">
							<option value="${observeInfo.id}">${observeInfo.name}</option>
						</c:forEach>
					</select>
				</span>
			</div>
			<div>
				<span>掌子面煤岩层、瓦斯、涌水有无变化</span>
				<span>
					<select name="observeInfo3">
						<c:forEach items="${config.observeInfos}" var="observeInfo">
							<option value="${observeInfo.id}">${observeInfo.name}</option>
						</c:forEach>
					</select>
				</span>
			</div>
			<div>
				汇报人：
				<select name="reporterId">
					<c:forEach items="${config.reporters}" var="reporter">
						<option value="${reporter.id}">${reporter.name}</option>
					</c:forEach>
				</select>
				施工队组:
				
			</div>
		</form>
	</div>
</body>
</html>
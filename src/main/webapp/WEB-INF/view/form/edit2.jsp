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
	<form action="" method="POST" class="form-inline" role="form">
		<div class="container">
			<div id="form">
				<div class="row">
					<div class="header col-lg-12">
						<select name="workingSurfaceId" class="form-control">
							<c:forEach items="${config.workingSurfaces}" var="surface">
								<option value="${surface.id}">${surface.name}</option>
							</c:forEach>
						</select>工作面地质信息卡
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 col-lg-offset-8">
						<joda:format value="${config.serverTime}" pattern="yyyy年MM月dd日" />
						<select name="shiftId" class="form-control">
							<c:forEach items="${config.shifts}" var="shift">
								<option value="${shift.id}">${shift.name}</option>
							</c:forEach>
						</select>班
					</div>
				</div>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>巷道名称</td>
							<td>
								<select name="tunnelId" class="form-control">
									<c:forEach items="${config.tunnels}" var="tunnel">
										<option value="${tunnel.id}">${tunnel.name}</option>
									</c:forEach>
								</select>
							</td>
							<td>煤层及顶底板柱状图</td>
						</tr>
						<tr>
							<td>观测点位置</td>
							<td>
								<div>
									<select name="observerPointId" class="form-control">
										<c:forEach items="${config.observerPoints}" var="observerPoint">
											<option value="${observerPoint.id}">${observerPoint.name}</option>
										</c:forEach>
									</select> 前x,y,z=<input type="number" size="1" name="observerPointX"
										class="form-control number-short">,<input type="number"
										size="1" name="observerPointY" class="form-control number-short">,<input type="number" size="1" name="observerPointZ"
										class="form-control number-short">米
								</div>
							</td>
							<td rowspan="5">
							
							</td>
						</tr>
						<tr>
							<td>岩（煤）层厚度</td>
							<td>
								<table class="table table-bordered innel-table">
									<tbody>
										<tr>
											<td>顶部</td>
											<td>
												<div>
													<select name="roofId" class="form-control">
														<option value=""></option>
														<c:forEach items="${config.stratums}" var="stratum">
															<option value="${stratum.id}">${stratum.name}</option>
														</c:forEach>
													</select>厚
													<input type="number" size="1" name="roofValue" class="form-control number-short">米
													<span class="glyphicon glyphicon-plus"></span>
												</div>
											</td>
										</tr>
										<tr>
											<td>掌子面</td>
											<td>
												<div>
													<select name="faceId" class="form-control">
														<option value=""></option>
														<c:forEach items="${config.stratums}" var="stratum">
															<option value="${stratum.id}">${stratum.name}</option>
														</c:forEach>
													</select>厚
													<input type="number" size="1" name="faceValue" class="form-control number-short">米
													<span class="glyphicon glyphicon-plus"></span>
												</div>
											</td>
										</tr>
										<tr>
											<td>底部</td>
											<td>
												<div>
													<select name="floorId" class="form-control">
														<option value=""></option>
														<c:forEach items="${config.stratums}" var="stratum">
															<option value="${stratum.id}">${stratum.name}</option>
														</c:forEach>
													</select>厚
													<input type="number" size="1" name="floorValue" class="form-control number-short">米
													<span class="glyphicon glyphicon-plus"></span>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>顶板锚杆及锚索施工情况</td>
							<td>
								<select name="observerInfo1" class="form-control">
									<c:forEach items="${config.observerInfos}" var="observerInfo">
										<option value="${observerInfo.id}">${observerInfo.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>超前探眼情况</td>
							<td>
								<select name="observerInfo2" class="form-control">
									<c:forEach items="${config.observerInfos}" var="observerInfo">
										<option value="${observerInfo.id}">${observerInfo.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>						
						<tr>
							<td>掌子面煤岩层、瓦斯、涌水有无变化</td>
							<td>
								<select name="observerInfo3" class="form-control">
									<c:forEach items="${config.observerInfos}" var="observerInfo">
										<option value="${observerInfo.id}">${observerInfo.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="row">
					<div class="col-lg-3">
						汇报人： <select name="reporterId">
							<c:forEach items="${config.teamMembers}" var="member">
								<option value="${member.id}">${member.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-9">施工队组:</div>
				</div>
				<div class="row" align="center">
					<input type="submit" value="提交" class="btn btn-primary">
				</div>
			</div>
		</div>
	</form>
</body>
</html>
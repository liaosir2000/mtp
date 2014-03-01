<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.11.0.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/function.js"></script>
<title>工作面地质信息卡</title>
</head>
<body>
	<form:form action="form" method="POST" class="form-inline" role="form" commandName="dto">
		<div class="container">
			<div id="form">
				<div class="row">
					<div class="header col-lg-12">
						<form:select path="workingSurfaceId" class="form-control">
							<form:option value="">请选择</form:option>
							<form:options items="${config.workingSurfaces}" itemValue="id" itemLabel="name"/>
						</form:select>工作面地质信息卡
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 col-lg-offset-8">
						<joda:format value="${config.serverTime}" pattern="yyyy年MM月dd日" />
						<form:select path="shiftId" class="form-control">
							<form:options items="${config.shifts}" itemValue="id" itemLabel="name"/>
						</form:select>班
					</div>
				</div>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>巷道名称</td>
							<td>
								<form:select path="tunnelId" class="form-control">
									<form:options items="${config.tunnels}" itemValue="id" itemLabel="name"/>
								</form:select>
							</td>
							<td>煤层及顶底板柱状图</td>
						</tr>
						<tr>
							<td>观测点位置</td>
							<td>
								<div>
									<form:select path="observerPointId" class="form-control">
										<form:options items="${config.observerPoints}" itemValue="id" itemLabel="name"/>
									</form:select>前x,y,z=
									<form:input path="observerPointAhead[0]" type="number" class="form-control number-short"/>
									<form:input path="observerPointAhead[1]" type="number" class="form-control number-short"/>
									<form:input path="observerPointAhead[2]" type="number" class="form-control number-short"/>
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
													<form:select path="stratum.roof[0].stratumId" class="form-control">
														<form:options items="${config.stratums}" itemValue="id" itemLabel="name"/>
													</form:select>厚
													<form:input type="number" size="1" path="stratum.roof[0].value" class="form-control number-short"/>米
													<span class="glyphicon glyphicon-plus"></span>
												</div>
											</td>
										</tr>
										<tr>
											<td>掌子面</td>
											<td>
												<div>
													<form:select path="stratum.tunnel[0].stratumId" class="form-control">
														<form:options items="${config.stratums}" itemValue="id" itemLabel="name"/>
													</form:select>厚
													<form:input type="number" size="1" path="stratum.tunnel[0].value" class="form-control number-short"/>米
													<span class="glyphicon glyphicon-plus"></span>
												</div>
											</td>
										</tr>
										<tr>
											<td>底部</td>
											<td>
												<div>
													<form:select path="stratum.floor[0].stratumId" class="form-control">
														<form:options items="${config.stratums}" itemValue="id" itemLabel="name"/>
													</form:select>厚
													<form:input type="number" size="1" path="stratum.floor[0].value" class="form-control number-short"/>米
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
								<form:select path="roofAnchor" class="form-control">
									<form:options items="${config.observerInfos}" itemValue="id" itemLabel="name"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>超前探眼情况</td>
							<td>
								<form:select path="aheadHole" class="form-control">
									<form:options items="${config.observerInfos}" itemValue="id" itemLabel="name"/>
								</form:select>
							</td>
						</tr>						
						<tr>
							<td>掌子面煤岩层、瓦斯、涌水有无变化</td>
							<td>
								<form:select path="tunnelInfo" class="form-control">
									<form:options items="${config.observerInfos}" itemValue="id" itemLabel="name"/>
								</form:select>
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
	</form:form>
</body>
</html>
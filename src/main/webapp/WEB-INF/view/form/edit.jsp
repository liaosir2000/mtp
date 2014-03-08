<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<div class="container" ng-controller="Form">
	<form action="${path}/form/save" method="POST" class="form-inline" role="form" ng-submit="saveForm()">
		<hidden path="id"/>
		<div class="container">
			<div id="form">
				<div class="row">
					<div class="header col-lg-12">
						<select ng-model="surfaceId" class="form-control" ng-change="selectSurface()">
							<option ng-repeat="surface in config.surfaces" value="{{surface.id}}">{{surface.name}}</option>
						</select>工作面地质信息卡
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4 col-lg-offset-8">
						<joda:format value="${config.serverTime}" pattern="yyyy年MM月dd日" />
						<select ng-model="shiftId" class="form-control">
							<option ng-repeat="shift in config.shifts" value="{{shift.id}}">{{shift.name}}</option>
						</select>班
					</div>
				</div>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>巷道名称</td>
							<td>
								<select ng-model="tunnelId" class="form-control" ng-change="selectTunnel()">
									<option ng-repeat="tunnel in tunnels" value="{{tunnel.id}}">{{tunnel.name}}</option>
								</select>
							</td>
							<td>煤层及顶底板柱状图</td>
						</tr>
						<tr>
							<td>观测点位置</td>
							<td>
								<div>
									<select ng-model="pointId" class="form-control">
										<option ng-repeat="point in points" value="{{point.id}}">{{point.name}}</option>
									</select>前
									<input ng-model="pointAhead" type="number" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short"/>
								</div>
							</td>
							<td rowspan="5">
								<canvas width="200px" height="400px">
								
								</canvas>
							</td>
						</tr>
						<tr>
							<td>岩（煤）层厚度</td>
							<td>
								<table class="table table-bordered innel-table">
									<tbody>
										<tr>
											<td>顶部</td>
											<td id="roof">
												<c:forEach items="stratum.roof" var="stratum" varStatus="status">
													<div>
														<select path="stratum.roof[${status.index}].stratumId" class="form-control">
															<options items="${config.stratums}" itemValue="id" itemLabel="name"/>
														</select>厚
														<input type="number" size="1" path="stratum.roof[${status.index}].value" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short canvas-sensitive"/>米
														<span class="glyphicon glyphicon-plus"></span>
													</div>
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td>掌子面</td>
											<td id="tunnel">
												<div>
													<select path="stratum.tunnel[0].stratumId" class="form-control">
														<options items="${config.stratums}" itemValue="id" itemLabel="name"/>
													</select>厚
													<input type="number" size="1" path="stratum.tunnel[0].value" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short canvas-sensitive"/>米
													<span class="glyphicon glyphicon-plus"></span>
												</div>
											</td>
										</tr>
										<tr>
											<td>底部</td>
											<td id="floor">
												<div>
													<select path="stratum.floor[0].stratumId" class="form-control">
														<options items="${config.stratums}" itemValue="id" itemLabel="name"/>
													</select>厚
													<input type="number" size="1" path="stratum.floor[0].value" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short canvas-sensitive"/>米
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
								<select ng-model="roofAnchor" class="form-control">
									<options items="${config.observerInfos}" itemValue="id" itemLabel="name"/>
								</select>
							</td>
						</tr>
						<tr>
							<td>超前探眼情况</td>
							<td>
								<select ng-model="aheadHole" class="form-control">
									<options items="${config.observerInfos}" itemValue="id" itemLabel="name"/>
								</select>
							</td>
						</tr>						
						<tr>
							<td>掌子面煤岩层、瓦斯、涌水有无变化</td>
							<td>
								<select ng-model="tunnelInfo" class="form-control">
									<options items="${config.observerInfos}" itemValue="id" itemLabel="name"/>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="row">
					<div class="col-lg-3">
						汇报人： 
							<select ng-model="reporter" class="form-control">
								<options items="${config.teamMembers}" itemValue="id" itemLabel="name"/>
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
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/form.js"></script>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<div class="container" ng-controller="Form">
	<form class="form-inline" role="form" ng-submit="saveForm()">
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
						<joda:format value="${serverTime}" pattern="yyyy年MM月dd日" />
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
												<div ng-repeat="roof in roofs">
													<input type="text" value="{{roof.name}}" class="form-control input-short">厚
													<input type="text" value="{{roof.value}}" class="form-control input-short">米
													<span class="glyphicon glyphicon-minus" ng-click="deleteRoofLine($index)"></span>
												</div>
												<div>
													<select ng-model="roofId" class="form-control">
														<option ng-repeat="stratum in config.stratums" value="{{stratum.id}}">{{stratum.name}}</option>
													</select>厚
													<input type="number" size="1" ng-model="roofValue" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short"/>米
													<span class="glyphicon glyphicon-plus" ng-click="addRoofLine()"></span>
												</div>
											</td>
										</tr>
										<tr>
											<td>掌子面</td>
											<td id="tunnel">
												<div ng-repeat="tunnel1 in tunnelFaces">
													<input type="text" value="{{tunnel1.name}}" class="form-control input-short">厚
													<input type="text" value="{{tunnel1.value}}" class="form-control input-short">米
													<span class="glyphicon glyphicon-minus" ng-click="deleteTunnelLine($index)"></span>
												</div>
												<div>
													<select ng-model="tunnelFaceId" class="form-control">
														<option ng-repeat="stratum in config.stratums" value="{{stratum.id}}">{{stratum.name}}</option>
													</select>厚
													<input type="number" size="1" ng-model="tunnelFaceValue" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short"/>米
													<span class="glyphicon glyphicon-plus" ng-click="addTunnelLine()"></span>
												</div>
											</td>
										</tr>
										<tr>
											<td>底部</td>
											<td id="floor">
												<div ng-repeat="floor in floors">
													<input type="text" value="{{floor.name}}" class="form-control input-short">厚
													<input type="text" value="{{floor.value}}" class="form-control input-short">米
													<span class="glyphicon glyphicon-minus" ng-click="deleteFloorLine($index)"></span>
												</div>
												<div>
													<select ng-model="floorId" class="form-control">
														<option ng-repeat="stratum in config.stratums" value="{{stratum.id}}">{{stratum.name}}</option>
													</select>厚
													<input type="number" size="1" ng-model="floorValue" pattern="[0-9]+(\.[0-9]+)?" step="0.1" class="form-control number-short"/>米
													<span class="glyphicon glyphicon-plus" ng-click="addFloorLine()"></span>
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
									<option ng-repeat="info in config.infos" value="{{info.id}}">{{info.name}}</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>超前探眼情况</td>
							<td>
								<select ng-model="aheadHole" class="form-control">
									<option ng-repeat="info in config.infos" value="{{info.id}}">{{info.name}}</option>
								</select>
							</td>
						</tr>						
						<tr>
							<td>掌子面煤岩层、瓦斯、涌水有无变化</td>
							<td>
								<select ng-model="tunnelInfo" class="form-control">
									<option ng-repeat="info in config.infos" value="{{info.id}}">{{info.name}}</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="row">
					<div class="col-lg-3">
						汇报人： 
							<select ng-model="reporter" class="form-control">
								<option ng-repeat="person in config.team.mermbers" value="{{person.id}}">{{persion.name}}</option>
							</select>
					</div>
					<div class="col-lg-9">施工队组:{{config.team.name}}</div>
				</div>
				<div class="row" align="center">
					<input type="submit" value="提交" class="btn btn-primary">
				</div>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/form.js"></script>
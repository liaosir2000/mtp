<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<div class="mtp-body" ng-controller="Form" ng-init="loadConfig(${id})">
	<form class="form-inline" role="form" ng-submit="saveForm()">
		<div id="form">
				<div class="mtp-surface">
					<select ng-model="surfaceId" required class="form-control input-stratum" ng-change="selectSurface()">
						<option ng-repeat="surface in config.surfaces" value="{{surface.id}}">{{surface.name}}</option>
					</select>工作面地质信息卡
				</div>
				<div class="mtp-shift">
					{{config.serverTime}} <select ng-model="shiftId" required class="form-control">
						<option ng-repeat="shift in config.shifts" value="{{shift.id}}">{{shift.name}}</option>
					</select>班
				</div>
			<table class="table table-bordered" style="filter:alpha(opacity=60)">
				<tbody>
					<tr>
						<td>巷道名称</td>
						<td><select ng-model="tunnelId" required class="form-control input-stratum" ng-change="selectTunnel()">
								<option ng-repeat="tunnel in tunnels" value="{{tunnel.id}}">{{tunnel.name}}</option>
						</select></td>
						<td>煤层及顶底板柱状图</td>
					</tr>
					<tr>
						<td>观测点位置</td>
						<td>
							<div>
								<select ng-model="pointId" class="form-control input-stratum">
									<option ng-repeat="point in points" value="{{point.id}}">{{point.name}}</option>
								</select>前 <input ng-model="pointAhead" type="number" required pattern="[0-9]+(\.[0-9]+)?" step="0.1"
									class="form-control input-short" />
							</div>
						</td>
						<td rowspan="5">
							<div id="stratumImg">
								<div id="roof">
									<div ng-repeat="roof in roofs" class="img-line">
										<div class="img-left" ng-style="drawHeight(roofs, $index)">{{roof.name}}</div>
										<div class="img" ng-style="drawImg(roofs, $index)"></div>
										<div class="img-right" ng-style="drawHeight(roofs, $index)">厚{{roof.value}}米</div>
									</div>
									<!-- <div class="sep-line" ng-show='roofshow'>顶板</div> -->
								</div>
								<div id="tunnel">
									<div ng-repeat="tunnel1 in tunnelFaces" class="img-line">
										<div class="img-left" ng-style="drawHeight(tunnelFaces, $index)">{{tunnel1.name}}</div>
										<div class="img" ng-style="drawImg(tunnelFaces, $index)"></div>
										<div class="img-right" ng-style="drawHeight(tunnelFaces, $index)">厚{{tunnel1.value}}米</div>
									</div>
								</div>
								<!-- <div class="sep-line" ng-show='floorshow'></div> -->
								<div id="floor">
									<div ng-repeat="floor in floors" class="img-line">
										<div class="img-left" ng-style="drawHeight(floors, $index)">{{floor.name}}</div>
										<div class="img" ng-style="drawImg(floors, $index)"></div>
										<div class="img-right" ng-style="drawHeight(floors, $index)">厚{{floor.value}}米</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>岩（煤）层厚度</td>
						<td>
							<table class="table table-bordered innel-table stratum-table">
								<tbody>
									<tr>
										<td class="stratum-layer">顶部</td>
										<td>
											<div ng-repeat="roof in roofs" class="bg-success">
												<input type="text" value="{{roof.name}}" class="form-control input-stratum" readonly>厚 <input
													type="text" value="{{roof.value}}" class="form-control input-short" readonly>米 <span
													class="glyphicon glyphicon-minus" ng-click="deleteRoofLine($index)"></span>
											</div>
											<div>
												<select ng-model="roofId" class="form-control input-stratum">
													<option ng-repeat="stratum in config.stratums" value="{{stratum.id}}">{{stratum.name}}</option>
												</select>厚 <input type="number" size="1" ng-model="roofValue" pattern="[0-9]+(\.[0-9]+)?" step="0.1"
													class="form-control input-short" />米 <span class="glyphicon glyphicon-plus" ng-click="addRoofLine()"></span>
											</div>
										</td>
									</tr>
									<tr>
										<td class="stratum-layer">掌子面</td>
										<td>
											<div ng-repeat="tunnel1 in tunnelFaces" class="bg-success">
												<input type="text" value="{{tunnel1.name}}" class="form-control input-stratum" readonly>厚 <input
													type="text" value="{{tunnel1.value}}" class="form-control input-short" readonly>米 <span
													class="glyphicon glyphicon-minus" ng-click="deleteTunnelLine($index)"></span>
											</div>
											<div>
												<select ng-model="tunnelFaceId" class="form-control input-stratum">
													<option ng-repeat="stratum in config.stratums" value="{{stratum.id}}">{{stratum.name}}</option>
												</select>厚 <input type="number" size="1" ng-model="tunnelFaceValue" pattern="[0-9]+(\.[0-9]+)?" step="0.1"
													class="form-control input-short" />米 <span class="glyphicon glyphicon-plus" ng-click="addTunnelLine()"></span>
											</div>
										</td>
									</tr>
									<tr>
										<td class="stratum-layer">底部</td>
										<td>
											<div ng-repeat="floor in floors" class="bg-success">
												<input type="text" value="{{floor.name}}" class="form-control input-stratum" readonly>厚 <input
													type="text" value="{{floor.value}}" class="form-control input-short" readonly>米 <span
													class="glyphicon glyphicon-minus" ng-click="deleteFloorLine($index)"></span>
											</div>
											<div>
												<select ng-model="floorId" class="form-control input-stratum">
													<option ng-repeat="stratum in config.stratums" value="{{stratum.id}}">{{stratum.name}}</option>
												</select>厚 <input type="number" size="1" ng-model="floorValue" pattern="[0-9]+(\.[0-9]+)?" step="0.1"
													class="form-control input-short" />米 <span class="glyphicon glyphicon-plus" ng-click="addFloorLine()"></span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>顶板锚杆及锚索施工情况</td>
						<td><select ng-model="roofAnchor" class="form-control input-stratum">
								<option ng-repeat="info in config.infos" value="{{info.id}}">{{info.name}}</option>
						</select></td>
					</tr>
					<tr>
						<td>超前探眼情况</td>
						<td><select ng-model="aheadHole" class="form-control input-stratum">
								<option ng-repeat="info in config.infos" value="{{info.id}}">{{info.name}}</option>
						</select></td>
					</tr>
					<tr>
						<td>掌子面煤岩层、瓦斯、涌水有无变化</td>
						<td><select ng-model="tunnelInfo" class="form-control input-stratum">
								<option ng-repeat="info in config.infos" value="{{info.id}}">{{info.name}}</option>
						</select></td>
					</tr>
				</tbody>
			</table>
			<div class="mtp-bottom">
				<div>
					汇报人： <select ng-model="reporter" class="form-control input-stratum">
						<option ng-repeat="person in config.team.members" value="{{person.id}}">{{person.name}}</option>
					</select>
				</div>&nbsp;&nbsp;
				<div>施工队组：{{config.team.name}}</div>
				<input ng-model="teamName" type="hidden">
				<input ng-model="teamId" type="hidden">
			</div>
			<div align="center" class="mtp-form-submit">
				<input type="submit" value="提交" class="btn btn-primary">
			</div>
		</div>
	</form>
</div>
<script type="text/ng-template" id="formSaveDialog.html">
<div class="modal-body">
	<h3 style="text-aligh:center;">您的表单已经提交成功！</h3>
</div>
<div class="modal-footer">
	<button class="btn btn-primary" ng-click="dialogOk()">确定</button>
</div>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/form.js"></script>
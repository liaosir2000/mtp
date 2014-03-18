angular.module('mtp-app', ['ui.bootstrap']);

function Form($scope, $http, $modal) {
	
	$scope.addRoofLine = function() {
		if ($scope.editRoofValue && parseFloat($scope.editRoofValue) > 0) {
			$scope.selectRoofs = $scope.selectRoofs || [];
			$scope.selectRoofs.unshift({id:$scope.editRoofId, value:$scope.editRoofValue});
			$scope.editRoofId = "";
			$scope.editRoofValue = "";
			
//			if ($scope.roofs) {
//				$scope.roofshow = true;
//			} else {
//				$scope.roofshow = false;
//			}
		} else {
			$scope.editRoofValue = "";
		}
	};
	
	$scope.deleteRoofLine = function(index) {
		$scope.selectRoofs.splice(index, 1);
//		if ($scope.roofs) {
//			$scope.roofshow = true;
//		} else {
//			$scope.roofshow = false;
//		}
	};
	
	$scope.addTunnelLine = function() {
		if ($scope.editTunnelValue && parseFloat($scope.editTunnelValue) > 0) {
			$scope.selectTunnels = $scope.selectTunnels || [];
			$scope.selectTunnels.unshift({id:$scope.editTunnelId, value:$scope.editTunnelValue});
			$scope.editTunnelId = "";
			$scope.editTunnelValue = "";
		} else {
			$scope.editTunnelValue = "";
		}
	};
	
	$scope.deleteTunnelLine = function(index) {
		$scope.selectTunnels.splice(index, 1);
	};
	
	$scope.addFloorLine = function() {
		if ($scope.editFloorValue && parseFloat($scope.editFloorValue) > 0) {
			$scope.selectFloors = $scope.selectFloors || [];
			$scope.selectFloors.unshift({id:$scope.editFloorId, value:$scope.editFloorValue});
			$scope.editFloorId = "";
			$scope.editFloorValue = "";
//			if ($scope.floors) {
//				$scope.floorshow = true;
//			} else {
//				$scope.floorshow = false;
//			}
		} else {
			$scope.editFloorValue = "";
		}
	};
	
	$scope.deleteFloorLine = function(index) {
		$scope.selectFloors.splice(index, 1);
//		if ($scope.floors) {
//			$scope.floorshow = true;
//		} else {
//			$scope.floorshow = false;
//		}
	};
	

	
	$scope.loadConfig = function(formId) {
		$http.get("conf")
		.success(function(data, status, headers, config){
			$scope.config = data;
			$scope.surfaceId = $scope.config.surfaces[0].id;
			$scope.shiftId = data.shifts[0].id;
			$scope.roofAnchor = data.infos[0].id;
			$scope.aheadHole = data.infos[0].id;
			$scope.tunnelInfo = data.infos[0].id;
			if (data.team) {
				$scope.teamId = data.team.id;
				$scope.teamName = data.team.name;
				$scope.reporter = data.team.members[0].id;
			}
		});
		loadForm(formId);
	};

	
	loadForm = function(formId) {
		if (formId) {
			$http.get(formId)
			.success(function(data, status, headers, config){
				$scope.id = formId;
				$scope.surfaceId = data.surfaceId;
				$scope.tunnels = data.tunnels;
				$scope.points = data.points;
				$scope.shiftId = data.shiftId;
				$scope.tunnelId = data.tunnelId;
				$scope.pointId = data.pointId;
				$scope.pointAhead = data.pointAhead;
				$scope.roofs = data.stratum.roof;
				$scope.tunnelFaces = data.stratum.tunnel;
				$scope.floors = data.stratum.floor;
				$scope.roofAnchor = data.roofAnchor;
				$scope.aheadHole = data.aheadHole;
				$scope.tunnelInfo = data.tunnelInfo;
				$scope.teamId = data.teamId;
				$scope.teamName = data.teamName;
			});
		}
	};
	
	$scope.saveForm = function() {
		if (!$scope.surfaceId) {
			$scope.surfaceId_invalid = true;
			return false;
		}
		if (!$scope.tunnelId) {
			$scope.tunnelId_invalid = true;
			return false;
		}
		if (!$scope.pointId) {
			$scope.pointId_invalid = true;
			return false;
		}
		
		$http.post("save", {
			id:$scope.id,
			teamId:$scope.teamId,
			teamName:$scope.teamName,
			reporter:$scope.reporter,
			surfaceId:$scope.surfaceId,
			shiftId:$scope.shiftId,
			tunnelId:$scope.tunnelId,
			pointId:$scope.pointId,
			pointAhead:$scope.pointAhead,
			stratum:{
				roof:$scope.roofs||[],
				tunnel:$scope.tunnelFaces||[],
				floor:$scope.floors||[]
			},
			roofAnchor:$scope.roofAnchor,
			aheadHole:$scope.aheadHole,
			tunnelInfo:$scope.tunnelInfo
		}).success(function(data, status, headers, config){
			console.log(data);
			reset();
			$modal.open({
				templateUrl:"formSaveDialog.html",
				controller:dialogController
			});
		});
	};
	
	reset = function() {
		$scope.surfaceId = undefined;
		$scope.tunnelId = undefined;
		$scope.pointId = undefined;
		$scope.roofs = [];
		$scope.tunnelFaces = [];
		$scope.floors = [];
		$scope.pointAhead = undefined;
		$scope.shiftId = $scope.config.shifts[0].id;
		$scope.roofAnchor = $scope.config.infos[0].id;
		$scope.aheadHole = $scope.config.infos[0].id;
		$scope.tunnelInfo = $scope.config.infos[0].id;
	};
	
	$scope.drawImg = function(layer, index) {
		var stratum = layer[index];
		return {
			'background-image': 'url(../resources/img/' + stratum.stratumId +'.png)',
			height:stratum.value * 12 + "px",
			'line-height':stratum.value * 12 + "px",
			width:"100px"
		};
	};
	
	$scope.drawHeight = function(layer, index) {
		var stratum = layer[index];
		return {
			height:stratum.value * 12 + "px",
			'line-height':stratum.value * 12 + "px",
			width:"100px"
		};
	};
	
	$scope.$watch("surfaceId", function() {
		if (!$scope.config) {
			return false;
		}
		for (index in $scope.config.surfaces) {
			var surface = $scope.config.surfaces[index];
			if (surface.id == $scope.surfaceId) {
				$scope.tunnels = surface.tunnels;
				break;
			}
		}
	});
	
	$scope.$watch("tunnelId", function() {
		for (index in $scope.tunnels) {
			if ($scope.tunnels[index].id == $scope.tunnelId) {
				$scope.points = $scope.tunnels[index].points;
				break;
			}
		}
	});
	
	//绘图
	var draw = function(newValue, oldValue, scope) {
		console.log("drawing");
	};
	
	$scope.$watchCollection("selectRoofs", draw);
	$scope.$watchCollection("selectTunnels", draw);
	$scope.$watchCollection("selectFloors", draw);
	$scope.$watch("[editRoofId, editRoofValue, editTunnelId, editTunnelValue, editFloorId, editFloorValue]", draw, true);
};

dialogController = function($scope, $modalInstance) {
	$scope.dialogOk = function() {
		$modalInstance.close();
	};
};
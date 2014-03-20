angular.module('mtp-app', ['ui.bootstrap']);

function Form($scope, $http, $modal) {
	
	var getStratumName = function(id) {
		for(i in $scope.config.stratums) {
			if ($scope.config.stratums[i].id == id) {
				return $scope.config.stratums[i].name;
			}
		}
		return "";
	};
	
	$scope.addRoofLine = function() {
		if ($scope.editRoofValue && parseFloat($scope.editRoofValue) > 0) {
			$scope.selectRoofs = $scope.selectRoofs || [];
			$scope.selectRoofs.unshift({id:$scope.editRoofId, value:$scope.editRoofValue});
			$scope.editRoofId = "";
			$scope.editRoofValue = "";
		} else {
			$scope.editRoofValue = "";
		}
	};
	
	$scope.deleteRoofLine = function(index) {
		$scope.selectRoofs.splice(index, 1);
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
			$scope.selectFloors.push({id:$scope.editFloorId, value:$scope.editFloorValue});
			$scope.editFloorId = "";
			$scope.editFloorValue = "";
		} else {
			$scope.editFloorValue = "";
		}
	};
	
	$scope.deleteFloorLine = function(index) {
		$scope.selectFloors.splice(index, 1);
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
		var json = [];
		if ($scope.editRoofId && $scope.editRoofValue) {
			json.unshift({id:0, position:"顶板", thickness:$scope.editRoofValue,
				bgimg:"../resources/img/" + $scope.editRoofId +".png",
				name:getStratumName($scope.editRoofId)});
		}
		for(var i in $scope.selectRoofs) {
			if ($scope.selectRoofs[i].id && $scope.selectRoofs[i].value) {
				json.unshift({id:i, position:"顶板", thickness:$scope.selectRoofs[i].value,
					bgimg:"../resources/img/" + $scope.selectRoofs[i].id +".png",
					name:getStratumName($scope.selectRoofs[i].id)});
			}
		}
		if ($scope.editTunnelId && $scope.editTunnelValue) {
			json.unshift({id:0, position:"掌子面", thickness:$scope.editTunnelValue,
				bgimg:"../resources/img/" + $scope.editTunnelId +".png",
				name:getStratumName($scope.editTunnelId)});
		}
		for(var i in $scope.selectTunnels) {
			if ($scope.selectTunnels[i].id && $scope.selectTunnels[i].value) {
				json.unshift({id:i, position:"掌子面", thickness:$scope.selectTunnels[i].value,
					bgimg:"../resources/img/" + $scope.selectTunnels[i].id +".png",
					name:getStratumName($scope.selectTunnels[i].id)});
			}
		}
		for(var i in $scope.selectFloors) {
			if ($scope.selectFloors[i].id && $scope.selectFloors[i].value) {
				json.push({id:i, position:"底板", thickness:$scope.selectFloors[i].value,
					bgimg:"../resources/img/" + $scope.selectFloors[i].id +".png",
					name:getStratumName($scope.selectFloors[i].id)});
			}
		}
		if ($scope.editFloorId && $scope.editFloorValue) {
			json.push({id:0, position:"底板", thickness:$scope.editFloorValue,
				bgimg:"../resources/img/" + $scope.editFloorId +".png",
				name:getStratumName($scope.editFloorId)});
		}
		var chart = new createChar(json,".char_histogram");
	    chart.init();
	};
	
	$scope.$watch(function() { 
		  return angular.toJson([$scope.selectRoofs, $scope.selectTunnels, $scope.selectFloors]); 
	}, draw, false);
	$scope.$watch("[editRoofId, editRoofValue, editTunnelId, editTunnelValue, editFloorId, editFloorValue]", draw, true);
};

dialogController = function($scope, $modalInstance) {
	$scope.dialogOk = function() {
		$modalInstance.close();
	};
};
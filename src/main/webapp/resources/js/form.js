function Form($scope, $http) {
	
	$scope.selectSurface = function() {
		for (index in $scope.config.surfaces) {
			var surface = $scope.config.surfaces[index];
			if (surface.id == $scope.surfaceId) {
				$scope.tunnels = surface.tunnels;
				break;
			}
		}
	};
	
	$scope.selectTunnel = function() {
		for (index in $scope.tunnels) {
			if ($scope.tunnels[index].id == $scope.tunnelId) {
				$scope.points = $scope.tunnels[index].points;
				break;
			}
		}
	};
	
	$scope.addRoofLine = function() {
		for(index in $scope.config.stratums) {
			var stratum = $scope.config.stratums[index];
			if (stratum.id == $scope.roofId) {
				$scope.roofs = $scope.roofs||[];
				$scope.roofs.unshift({stratumId:stratum.id, name:stratum.name, value: $scope.roofValue});
				$scope.roofId = "";
				$scope.roofValue = "";
				break;
			}
		}
	};
	
	$scope.deleteTunnelLine = function(index) {
		$scope.tunnelFaces.splice(index, 1);
	};
	
	$scope.addTunnelLine = function() {
		for(index in $scope.config.stratums) {
			var stratum = $scope.config.stratums[index];
			if (stratum.id == $scope.tunnelFaceId) {
				$scope.tunnelFaces = $scope.tunnelFaces||[];
				$scope.tunnelFaces.unshift({stratumId:stratum.id, name:stratum.name, value: $scope.tunnelFaceValue});
				$scope.tunnelFaceId = "";
				$scope.tunnelFaceValue = "";
				break;
			}
		}
	};
	
	$scope.deleteFloorLine = function(index) {
		$scope.floors.splice(index, 1);
	};
	
	$scope.addFloorLine = function() {
		for(index in $scope.config.stratums) {
			var stratum = $scope.config.stratums[index];
			if (stratum.id == $scope.floorId) {
				$scope.floors = $scope.floors||[];
				$scope.floors.push({stratumId:stratum.id, name:stratum.name, value: $scope.floorValue});
				$scope.floorId = "";
				$scope.floorValue = "";
				break;
			}
		}
	};
	
	$scope.deleteRoofLine = function(index) {
		$scope.roofs.splice(index, 1);
	};
	
	loadConfig = function() {
		$http.get("conf")
		.success(function(data, status, headers, config){
			$scope.config = data;
			$scope.surfaceId = $scope.config.surfaces[0].id;
		});
	};
	
	loadConfig();
	
	$scope.saveForm = function() {
		$http.post("save", {
			teamId:$scope.config.team.id,
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
			alert("ok");
		});
	};
};
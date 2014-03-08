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
	
	loadConfig = function() {
		$http.get("conf")
		.success(function(data, status, headers, config){
			$scope.config = data;
			$scope.surfaceId = $scope.config.surfaces[0].id;
		});
	};
	
	loadConfig();
	
	$scope.saveForm = function() {
		
	};
};
angular.module('mtp-app', ['ui.bootstrap']);
function Surface($scope, $http) {
	$scope.createSurface = function(){
		if ($scope.name) {
			$http.post(".", {name:$scope.name})
			.success(function(data, status, headers, config){
				loadSurface();
			});
			$scope.name = "";
		}
	};
	
	$scope.deleteSurface = function(index) {
		$http.delete($scope.surfaces[index].id)
		.success(function(data, status, headers, config){
			$scope.selectSurfaceId = undefined;
			loadSurface();
			loadTunnel();
			loadPoint();
		});
	};
	
	$scope.selectSurface = function(index) {
		$scope.selectedSurfaceRow = index;
		$scope.selectSurfaceId = $scope.surfaces[index].id;
		$scope.selectTunnelId = undefined;
		$scope.selectTunnelRow = undefined;
		loadTunnel();
		loadPoint();
	};
	
	$scope.createTunnel = function() {
		if ($scope.tunnelName) {
			$http.post($scope.selectSurfaceId +"/tunnel", {name:$scope.tunnelName})
			.success(function(data, status, headers, config){
				$scope.tunnelName = "";
				loadTunnel();
			});
		}
	}
	
	$scope.deleteTunnel = function(index) {
		$http.delete($scope.selectSurfaceId + "/tunnel/" + $scope.tunnels[index].id)
		.success(function(data, status, headers, config){
			$scope.selectTunnelId = undefined;
			loadTunnel();
			loadPoint();
		});
	};
	
	$scope.selectTunnel = function(index) {
		$scope.selectTunnelId = $scope.tunnels[index].id;
		$scope.selectTunnelRow = index;
		loadPoint();
	};
	
	$scope.createPoint = function() {
		if ()
		$http.post($scope.selectSurfaceId + "/tunnel/" + $scope.selectTunnelId + "/point", 
				{name:$scope.pointName, x:$scope.x,y:$scope.y,z:$scope.z})
		.success(function(data, status, headers, config){
			$scope.pointName = "";
			$scope.x = "";
			$scope.y = "";
			$scope.z = "";
			loadPoint();
		});
	};
	
	loadSurface = function(){
		$http.get(".")
		.success(function(data, status, headers, config){
			$scope.surfaces = data;
		});
	};
	loadTunnel = function() {
		if (!$scope.selectSurfaceId) {
			$scope.tunnels = [];
		} else {
			$http.get($scope.selectSurfaceId + "/tunnel")
			.success(function(data, status, headers, config){
				$scope.tunnels = data;
			});
		}
	};
	loadPoint = function() {
		if (!$scope.selectTunnelId) {
			$scope.points = [];
		} else {
			$http.get($scope.selectSurfaceId + "/tunnel/" + $scope.selectTunnelId + "/point")
			.success(function(data, status, headers, config){
				$scope.points = data;
			});
		}
	};
	
	loadSurface();
};
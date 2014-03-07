function Surface($scope, $http) {
	$scope.createSurface = function(){
		$http.post("surface", {name:$scope.name})
		.success(function(data, status, headers, config){
			loadSurface();
		})
		.error(function(data, status, headers, config){
			
		});
		$scope.name = "";
	};
	
	$scope.deleteSurface = function(index) {
		$http.delete("surface/" + $scope.surfaces[index].id)
		.success(function(data, status, headers, config){
			$scope.selectSurfaceId = undefined;
			loadSurface();
			loadTunnel();
			loadPoint();
		});
	};
	
	$scope.selectSurface = function(index) {
		$scope.selectSurfaceId = $scope.surfaces[index].id;
		loadTunnel();
	};
	
	$scope.createTunnel = function() {
		$http.post("surface/" + $scope.selectSurfaceId +"/tunnel", {name:$scope.tunnelName})
		.success(function(data, status, headers, config){
			$scope.tunnelName = "";
			loadTunnel();
		});
	}
	
	$scope.deleteTunnel = function(index) {
		$http.delete("surface/" + $scope.selectSurfaceId + "/tunnel/" + $scope.tunnels[index].id)
		.success(function(data, status, headers, config){
			$scope.selectTunnelId = undefined;
			loadTunnel();
			loadPoint();
		});
	};
	
	$scope.selectTunnel = function(index) {
		$scope.selectTunnelId = $scope.tunnels[index].id;
		loadPoint();
	};
	
	$scope.createPoint = function() {
		$http.post("surface/" + $scope.selectSurfaceId + "/tunnel/" + $scope.selectTunnelId + "/point", 
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
		$http.get("surface")
		.success(function(data, status, headers, config){
			$scope.surfaces = data;
		});
	};
	loadTunnel = function() {
		if (!$scope.selectSurfaceId) {
			$scope.tunnels = [];
		} else {
			$http.get("surface/" + $scope.selectSurfaceId + "/tunnel")
			.success(function(data, status, headers, config){
				$scope.tunnels = data;
			});
		}
	};
	loadPoint = function() {
		if (!$scope.selectTunnelId) {
			$scope.points = [];
		} else {
			$http.get("surface/" + $scope.selectSurfaceId + "/tunnel/" + $scope.selectTunnelId + "/point")
			.success(function(data, status, headers, config){
				$scope.points = data;
			});
		}
	};
	
	loadSurface();
};
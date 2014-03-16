angular.module('mtp-app', ['ui.bootstrap']);
function Profile($scope, $http) {
	$scope.selectSurface = function(index) {
		$http.get("surface/" + $scope.surfaceId + "/tunnel")
		.success(function(data, status, headers, config){
			$scope.tunnels = data;
			if (data) {
				$scope.tunnelId = data[0].id;
			}
		});
	};
	
	$scope.$watch("tunnelId", function() {
		if ($scope.surfaceId && $scope.tunnelId) {
			$http.get("profile/" + $scope.surfaceId + "/" + $scope.tunnelId)
			.success(function(data, status, headers, config){
				var canvas = document.getElementById("profile");
				var ctx = canvas.getContext("2d");
				
			});
		}
	});
	
	loadSurface = function(){
		$http.get("surface")
		.success(function(data, status, headers, config){
			$scope.surfaces = data;
		});
	};
	
	loadSurface();
};
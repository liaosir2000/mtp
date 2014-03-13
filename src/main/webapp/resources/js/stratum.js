angular.module('mtp-app', ['ui.bootstrap']);
function Stratum($scope, $http) {
	$scope.createStratum = function(){
		if ($scope.name) {
			$http.post(".", {name:$scope.name})
			.success(function(data, status, headers, config){
				loadStratum();
			});
			$scope.name = "";
		}
	};
	
	$scope.deleteStratum = function(index) {
		$http.delete($scope.stratums[index].id)
		.success(function(data, status, headers, config){
			loadStratum();
		});
	};
	
	loadStratum = function(){
		$http.get(".")
		.success(function(data, status, headers, config){
			$scope.stratums = data;
		});
	};
	
	loadStratum();
};
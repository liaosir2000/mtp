function Shift($scope, $http) {
	$scope.createShift = function(){
		$http.post(".", {name:$scope.name})
		.success(function(data, status, headers, config){
			loadShift();
		});
		$scope.name = "";
	};
	
	$scope.deleteShift = function(index) {
		$http.delete($scope.shifts[index].id)
		.success(function(data, status, headers, config){
			loadShift();
		});
	};
	
	loadShift = function(){
		$http.get(".")
		.success(function(data, status, headers, config){
			$scope.shifts = data;
		});
	};
	
	loadShift();
};
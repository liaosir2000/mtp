function Info($scope, $http) {
	$scope.createInfo = function(){
		$http.post(".", {name:$scope.name})
		.success(function(data, status, headers, config){
			loadInfo();
		});
		$scope.name = "";
	};
	
	$scope.deleteInfo = function(index) {
		$http.delete($scope.infos[index].id)
		.success(function(data, status, headers, config){
			loadInfo();
		});
	};
	
	loadInfo = function(){
		$http.get(".")
		.success(function(data, status, headers, config){
			$scope.infos = data;
		});
	};
	
	loadInfo();
};
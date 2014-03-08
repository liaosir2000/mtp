function FormList($scope, $http) {
	
	loadFormList = function() {
		$http.get("list")
		.success(function(data, status, headers, config){
			$scope.forms = data.content;
		});
	};
	
	loadFormList();
};
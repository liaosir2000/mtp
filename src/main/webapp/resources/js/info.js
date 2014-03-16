angular.module('mtp-app', ['ui.bootstrap']);
function Info($scope, $http) {
	$scope.createInfo = function(){
		$http.post(".", {
			name:$scope.name,
			warn:$scope.warn,
			personId:$scope.persionId,
			personName:$scope.personName,
			personEmail:$scope.personEmail
			})
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
	
	$scope.$watch("warn", function(){
		if ($scope.warn) {
			$http.get("person").success(function(data, status, headers, config){
				$scope.persons = data;
			});
		} else {
			$scope.personId = "";
			$scope.personName = "";
			$scope.personEmail = "";
		};
	});
	
	$scope.$watch("personId", function(){
		for(index in $scope.persons) {
			if ($scope.persons[index].id == $scope.personId) {
				$scope.personName = $scope.persons[index].name;
				$scope.personEmail = $scope.persons[index].email;
			}
		}
	});
	
	loadInfo = function(){
		$http.get(".")
		.success(function(data, status, headers, config){
			$scope.infos = data;
		});
	};
	
	loadInfo();
};
angular.module('mtp-app', ['ui.bootstrap']);
function Team($scope, $http) {
	$scope.loadTeam = function() {
		$http.get(".")
		.success(function(data, status, headers, config){
			$scope.teams = data.groups;
		});
	};
	
	$scope.loginWith = function(teamId) {
		console.log(teamId);
	};
};
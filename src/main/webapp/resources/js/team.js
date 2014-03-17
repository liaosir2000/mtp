angular.module('mtp-app', ['ui.bootstrap']);
function Team($scope, $http) {
	$scope.loadTeam = function() {
		$http.get("/team/init")
		.success(function(data, status, headers, config){
			$scope.teams = data.groups;
		});
	};
};
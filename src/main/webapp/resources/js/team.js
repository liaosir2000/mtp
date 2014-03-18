angular.module('mtp-app', [ 'ui.bootstrap' ]);
function Team($scope, $http, $modal) {
	$scope.loadTeam = function() {
		$http.get(".").success(function(data, status, headers, config) {
			$scope.teams = data.groups;
		});
	};

	$scope.loginWith = function(teamId) {
		var modalInstance = $modal.open({
			templateUrl : "loginDialog.html",
			controller : loginController,
			scope:$scope
		});

		modalInstance.result.then(function() {
			console.log("hear");
		}, function() {
			console.info('Modal dismissed at: ' + new Date());
		});
	};
};

loginController = function($scope, $modalInstance) {
	$scope.dialogOk = function() {
		console.log($modalInstance.pwd);
		$modalInstance.close();
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('取消');
	};
	
	$scope.pwdInput = function() {
		console.log($scope.pwd);
	};
};
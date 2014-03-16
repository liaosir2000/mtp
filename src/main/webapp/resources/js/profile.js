angular.module('mtp-app', ['ui.bootstrap']);
function Profile($scope, $http) {
	$scope.selectSurface = function(index) {
		$http.get("surface/" + $scope.surfaceId + "/tunnel")
		.success(function(data, status, headers, config){
			$scope.tunnels = data;
			if (data && data[0]) {
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
				ctx.clearRect(0,0, canvas.width, canvas.height);
				ctx.scale(1, 1);
				ctx.strokeStyle = "#000000";
				ctx.lineWidth = 1;
				ctx.beginPath();
				ctx.moveTo(5, 0);
				ctx.lineTo(5, 140);
				ctx.lineTo(300, 140);
				ctx.stroke();
				ctx.closePath();
				ctx.strokeText("Z轴", 10, 10);
				ctx.strokeText("Y轴", 280, 130);
				
				if (data && data.forms) {
					var front;
					for (var i in data.forms) {
						if (!front) {
							front = data.forms[i];
						} else {
							var frontStratum = front.stratum;
							var currentStratum = data.forms[i].stratum;
							var step = 20;
							for(var j in frontStratum.roof) {
								var stratum = frontStratum.roof[j];
								var image = new Image();
								image.src = "resources/img/" + stratum.stratumId + ".png";
								image.onload = function () {
									var pp = ctx.createPattern(image, 'repeat');
									ctx.fillStyle = pp;
									ctx.beginPath();
									ctx.moveTo(j * 20, 140);
									ctx.lineTo(j * 20, 140 - parseFloat(stratum.value));
									if (currentStratum.roof && currentStratum.roof[j]) {
										ctx.lineTo((j + 1) * 20, 140 - parseFloat(currentStratum.roof[j].value));
									} else {
										ctx.lineTo((j + 1) * 20, 140);
									}
									ctx.fill();
								};
							}
							front = data.forms[i];
						}
					}
				}
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
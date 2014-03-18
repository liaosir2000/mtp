angular.module('mtp-app', ['ui.bootstrap']);

function Form($scope, $http, $modal) {
	
	$scope.addRoofLine = function() {
		if ($scope.editRoofValue && parseFloat($scope.editRoofValue) > 0) {
			$scope.selectRoofs = $scope.selectRoofs || [];
			$scope.selectRoofs.unshift({id:$scope.editRoofId, value:$scope.editRoofValue});
			$scope.editRoofId = "";
			$scope.editRoofValue = "";
			
//			if ($scope.roofs) {
//				$scope.roofshow = true;
//			} else {
//				$scope.roofshow = false;
//			}
		} else {
			$scope.editRoofValue = "";
		}
	};
	
	$scope.deleteRoofLine = function(index) {
		$scope.selectRoofs.splice(index, 1);
//		if ($scope.roofs) {
//			$scope.roofshow = true;
//		} else {
//			$scope.roofshow = false;
//		}
	};
	
	$scope.addTunnelLine = function() {
		if ($scope.editTunnelValue && parseFloat($scope.editTunnelValue) > 0) {
			$scope.selectTunnels = $scope.selectTunnels || [];
			$scope.selectTunnels.unshift({id:$scope.editTunnelId, value:$scope.editTunnelValue});
			$scope.editTunnelId = "";
			$scope.editTunnelValue = "";
		} else {
			$scope.editTunnelValue = "";
		}
	};
	
	$scope.deleteTunnelLine = function(index) {
		$scope.selectTunnels.splice(index, 1);
	};
	
	$scope.addFloorLine = function() {
		if ($scope.editFloorValue && parseFloat($scope.editFloorValue) > 0) {
			$scope.selectFloors = $scope.selectFloors || [];
			$scope.selectFloors.unshift({id:$scope.editFloorId, value:$scope.editFloorValue});
			$scope.editFloorId = "";
			$scope.editFloorValue = "";
//			if ($scope.floors) {
//				$scope.floorshow = true;
//			} else {
//				$scope.floorshow = false;
//			}
		} else {
			$scope.editFloorValue = "";
		}
	};
	
	$scope.deleteFloorLine = function(index) {
		$scope.selectFloors.splice(index, 1);
//		if ($scope.floors) {
//			$scope.floorshow = true;
//		} else {
//			$scope.floorshow = false;
//		}
	};
	

	
	$scope.loadConfig = function(formId) {
		$http.get("conf")
		.success(function(data, status, headers, config){
			$scope.config = data;
			$scope.surfaceId = $scope.config.surfaces[0].id;
			$scope.shiftId = data.shifts[0].id;
			$scope.roofAnchor = data.infos[0].id;
			$scope.aheadHole = data.infos[0].id;
			$scope.tunnelInfo = data.infos[0].id;
			if (data.team) {
				$scope.teamId = data.team.id;
				$scope.teamName = data.team.name;
				$scope.reporter = data.team.members[0].id;
			}
		});
		loadForm(formId);
	};

	
	loadForm = function(formId) {
		if (formId) {
			$http.get(formId)
			.success(function(data, status, headers, config){
				$scope.id = formId;
				$scope.surfaceId = data.surfaceId;
				$scope.tunnels = data.tunnels;
				$scope.points = data.points;
				$scope.shiftId = data.shiftId;
				$scope.tunnelId = data.tunnelId;
				$scope.pointId = data.pointId;
				$scope.pointAhead = data.pointAhead;
				$scope.roofs = data.stratum.roof;
				$scope.tunnelFaces = data.stratum.tunnel;
				$scope.floors = data.stratum.floor;
				$scope.roofAnchor = data.roofAnchor;
				$scope.aheadHole = data.aheadHole;
				$scope.tunnelInfo = data.tunnelInfo;
				$scope.teamId = data.teamId;
				$scope.teamName = data.teamName;
			});
		}
	};
	
	$scope.saveForm = function() {
		if (!$scope.surfaceId) {
			$scope.surfaceId_invalid = true;
			return false;
		}
		if (!$scope.tunnelId) {
			$scope.tunnelId_invalid = true;
			return false;
		}
		if (!$scope.pointId) {
			$scope.pointId_invalid = true;
			return false;
		}
		
		$http.post("save", {
			id:$scope.id,
			teamId:$scope.teamId,
			teamName:$scope.teamName,
			reporter:$scope.reporter,
			surfaceId:$scope.surfaceId,
			shiftId:$scope.shiftId,
			tunnelId:$scope.tunnelId,
			pointId:$scope.pointId,
			pointAhead:$scope.pointAhead,
			stratum:{
				roof:$scope.roofs||[],
				tunnel:$scope.tunnelFaces||[],
				floor:$scope.floors||[]
			},
			roofAnchor:$scope.roofAnchor,
			aheadHole:$scope.aheadHole,
			tunnelInfo:$scope.tunnelInfo
		}).success(function(data, status, headers, config){
			console.log(data);
			reset();
			$modal.open({
				templateUrl:"formSaveDialog.html",
				controller:dialogController
			});
		});
	};
	
	reset = function() {
		$scope.surfaceId = undefined;
		$scope.tunnelId = undefined;
		$scope.pointId = undefined;
		$scope.roofs = [];
		$scope.tunnelFaces = [];
		$scope.floors = [];
		$scope.pointAhead = undefined;
		$scope.shiftId = $scope.config.shifts[0].id;
		$scope.roofAnchor = $scope.config.infos[0].id;
		$scope.aheadHole = $scope.config.infos[0].id;
		$scope.tunnelInfo = $scope.config.infos[0].id;
	};
	
	$scope.drawImg = function(layer, index) {
		var stratum = layer[index];
		return {
			'background-image': 'url(../resources/img/' + stratum.stratumId +'.png)',
			height:stratum.value * 12 + "px",
			'line-height':stratum.value * 12 + "px",
			width:"100px"
		};
	};
	
	$scope.drawHeight = function(layer, index) {
		var stratum = layer[index];
		return {
			height:stratum.value * 12 + "px",
			'line-height':stratum.value * 12 + "px",
			width:"100px"
		};
	};
	
	$scope.$watch("surfaceId", function() {
		if (!$scope.config) {
			return false;
		}
		for (index in $scope.config.surfaces) {
			var surface = $scope.config.surfaces[index];
			if (surface.id == $scope.surfaceId) {
				$scope.tunnels = surface.tunnels;
				break;
			}
		}
	});
	
	$scope.$watch("tunnelId", function() {
		for (index in $scope.tunnels) {
			if ($scope.tunnels[index].id == $scope.tunnelId) {
				$scope.points = $scope.tunnels[index].points;
				break;
			}
		}
	});
	
	//绘图
	var draw = function(newValue, oldValue, scope) {
		var canvas = document.getElementById("histogram");
		var ctx = canvas.getContext("2d");
		var ratio = canvas.height * 0.8 / totalHeight;
		var deltaY = canvas.height * 0.1;
		var deltaX = (canvas.width - 100)/2;
		
		var totalHeight = 0;
		var roofs_h = [];
		var roof_h = 0;
		var tunnels_h = [];
		var tunnel_h = 0;
		var floors_h = [];
		var floor_h = 0;
		for(var i in $scope.selectRoofs) {
			if ($scope.selectRoofs[i].id && $scope.selectRoofs[i].value) {
				totalHeight = totalHeight + parseFloat($scope.selectRoofs[i].value);
			}
		}
		for(var i in $scope.selectTunnels) {
			if ($scope.selectTunnels[i].id && $scope.selectTunnels[i].value) {
				totalHeight = totalHeight + parseFloat($scope.selectTunnels[i].value);
			}
		}
		for(var i in $scope.selectFloors) {
			if ($scope.selectFloors[i].id && $scope.selectFloors[i].value) {
				totalHeight = totalHeight + parseFloat($scope.selectFloors[i].value);
			}
		}
		if ($scope.editRoofId && $scope.editRoofValue) {
			totalHeight = totalHeight + parseFloat($scope.editRoofValue);
		}
		if ($scope.editTunnelId && $scope.editTunnelValue) {
			totalHeight = totalHeight + parseFloat($scope.editTunnelValue);
		}
		if ($scope.editFloorId && $scope.editFloorValue) {
			totalHeight = totalHeight + parseFloat($scope.editFloorValue);
		}
		console.log("total height=" + totalHeight);
		if (totalHeight) {
			ctx.clearRect(0,0, canvas.width, canvas.height);
			var ratio = canvas.height * 0.8 / totalHeight;
			var deltaY = canvas.height * 0.1;
			var deltaX = (canvas.width - 100)/2;
			console.log("ratio=" + ratio + ", deltaX="+deltaX + ", deltaY=" + deltaY);
			
			//////////////////////////////////////////////////////////
			for(var i in $scope.selectRoofs) {
				if ($scope.selectRoofs[i].id && $scope.selectRoofs[i].value) {
					var h = getDrawHeight(parseFloat($scope.selectRoofs[i].value), ratio);
					roofs_h.unshift(deltaY.toFixed(0));
					deltaY = deltaY + h;
				}
			}
			if ($scope.editRoofId && $scope.editRoofValue) {
				var h = getDrawHeight(parseFloat($scope.editRoofValue), ratio);
				roof_h = deltaY.toFixed(0);
				deltaY = deltaY + h;
			}
			
			for(var i in $scope.selectTunnels) {
				if ($scope.selectTunnels[i].id && $scope.selectTunnels[i].value) {
					var h = getDrawHeight(parseFloat($scope.selectTunnels[i].value), ratio);
					tunnels_h.unshift(deltaY.toFixed(0));
					deltaY = deltaY + h;
				}
			}
			if ($scope.editTunnelId && $scope.editTunnelValue) {
				var h = getDrawHeight(parseFloat($scope.editTunnelValue), ratio);
				tunnel_h = deltaY.toFixed(0);
				deltaY = deltaY + h;
			}
			
			for(var i in $scope.selectFloors) {
				if ($scope.selectFloors[i].id && $scope.selectFloors[i].value) {
					var h = getDrawHeight(parseFloat($scope.selectFloors[i].value), ratio);
					floors_h.unshift(deltaY.toFixed(0));
					deltaY = deltaY + h;
				}
			}
			
			//
			for(i in $scope.selectRoofs) {
				var height = getDrawHeight($scope.selectRoofs[i].value, ratio);
				var image = new Image();
				image.src = "../resources/img/" + $scope.selectRoofs[i].id + ".png";
				image.onload = function () {
					var pp = ctx.createPattern(image, 'repeat');
					ctx.fillStyle = pp;
					ctx.beginPath();
					ctx.moveTo(deltaX, roofs_h[i]);
					ctx.lineTo(deltaX + 100, roofs_h[i]);
					ctx.lineTo(deltaX + 100, roofs_h[i] + height);
					ctx.lineTo(deltaX, roofs_h[i] + height);
					ctx.closePath();
					ctx.fill();
				};
			}
			//
			if ($scope.editRoofId && $scope.editRoofValue) {
				var height = getDrawHeight(parseFloat($scope.editRoofValue), ratio);
				var image = new Image();
				image.src = "../resources/img/" + $scope.editRoofId + ".png";
				image.onload = function () {
					var pp = ctx.createPattern(image, 'repeat');
					ctx.fillStyle = pp;
					ctx.beginPath();
					ctx.moveTo(deltaX, roof_h);
					ctx.lineTo(deltaX + 100, roof_h);
					ctx.lineTo(deltaX + 100, roof_h + height);
					ctx.lineTo(deltaX, roof_h + height);
					ctx.closePath();
					ctx.fill();
				};
			}
			//顶板完成
			for(i in $scope.selectTunnels) {
				var height = getDrawHeight($scope.selectTunnels[i].value, ratio);
				var image = new Image();
				image.src = "../resources/img/" + $scope.selectTunnels[i].id + ".png";
				image.onload = function () {
					var pp = ctx.createPattern(image, 'repeat');
					ctx.fillStyle = pp;
					ctx.beginPath();
					ctx.moveTo(deltaX, tunnels_h[i]);
					ctx.lineTo(deltaX + 100, tunnels_h[i]);
					ctx.lineTo(deltaX + 100, tunnels_h[i] + height);
					ctx.lineTo(deltaX, tunnels_h[i] + height);
					ctx.closePath();
					ctx.fill();
				};
			}
			//
			if ($scope.editTunnelId && $scope.editTunnelValue) {
				var height = getDrawHeight(parseFloat($scope.editTunnelValue), ratio);
				var image = new Image();
				image.src = "../resources/img/" + $scope.editTunnelId + ".png";
				image.onload = function () {
					var pp = ctx.createPattern(image, 'repeat');
					ctx.fillStyle = pp;
					ctx.beginPath();
					ctx.moveTo(deltaX, tunnel_h);
					ctx.lineTo(deltaX + 100, tunnel_h);
					ctx.lineTo(deltaX + 100, tunnel_h + height);
					ctx.lineTo(deltaX, tunnel_h + height);
					ctx.closePath();
					ctx.fill();
				};
			}
			//掌子面完成
			for(i in $scope.selectFloors) {
				var height = getDrawHeight($scope.selectFloors[i].value, ratio);
				var image = new Image();
				image.src = "../resources/img/" + $scope.selectFloors[i].id + ".png";
				image.onload = function () {
					var pp = ctx.createPattern(image, 'repeat');
					ctx.fillStyle = pp;
					ctx.beginPath();
					ctx.moveTo(deltaX, floors_h[i]);
					ctx.lineTo(deltaX + 100, floors_h[i]);
					ctx.lineTo(deltaX + 100, floors_h[i] + height);
					ctx.lineTo(deltaX, floors_h[i] + height);
					ctx.closePath();
					ctx.fill();
				};
			}
			//
			if ($scope.editFloorId && $scope.editFloorValue) {
				var height = getDrawHeight(parseFloat($scope.editFloorValue), ratio);
				var image = new Image();
				image.src = "../resources/img/" + $scope.editFloorId + ".png";
				image.onload = function () {
					var pp = ctx.createPattern(image, 'repeat');
					ctx.fillStyle = pp;
					ctx.beginPath();
					ctx.moveTo(deltaX, deltaY);
					ctx.lineTo(deltaX + 100, deltaY);
					ctx.lineTo(deltaX + 100, deltaY + height);
					ctx.lineTo(deltaX, deltaY + height);
					ctx.closePath();
					ctx.fill();
				};
			}
			
			
		}
	};
	
	var getDrawHeight = function(inputHeight, ratio) {
		var drawHeight = inputHeight * ratio;
		return drawHeight < 10 ? 10 : drawHeight;
	};
	
	$scope.$watch(function() { 
		  return angular.toJson([$scope.selectRoofs, $scope.selectTunnels, $scope.selectFloors]); 
	}, draw, false);
	$scope.$watch("[editRoofId, editRoofValue, editTunnelId, editTunnelValue, editFloorId, editFloorValue]", draw, true);
};

dialogController = function($scope, $modalInstance) {
	$scope.dialogOk = function() {
		$modalInstance.close();
	};
};
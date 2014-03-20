var dialogApp = angular.module('mtp-app', []);
dialogApp.factory('jqueryUI', function($window, $templateCache, $document, $compile) {
    return {
        wrapper : function(cssSelector, pluginName, options, templateName, dialogScope) {
            if (templateName) {
                var templateDom = $($templateCache.get(templateName));
                $document.append(templateDom);
                $compile(templateDom)(dialogScope);
            }
            $(cssSelector)[pluginName](options);
        },

        performAction : function(cssSelector, pluginName, action, options) {
            if (options) {
                $(cssSelector)[pluginName](action, options);
            } else {
                $(cssSelector)[pluginName](action);
            }

        }
    };
});

function Team($scope, $http, jqueryUI) {
    $scope.loadTeam = function() {
        $http.get(".").success(function(data, status, headers, config) {
            $scope.teams = data.groups;
        });
    };

    $scope.loginWith = function(teamId) {
        $scope.teamId = teamId;
        var options = {
            modal : true,
            buttons : {
                "登陆" : function() {
                    console.log("login team=" + $scope.teamId);
                    $(this).dialog("close");
                    $http.post("plogin", {
                        teamId : $scope.teamId,
                        password : $scope.pwd
                    }).success(function(data, status, headers, config) {
                        $scope.pwd = "";
                        window.location.href = "/mtp/form/edit?teamId=" + $scope.teamId;
                    }).error(function(data, status, headers, config) {
                        $scope.pwd = undefined;
                        $scope.message = "登陆失败，请确认输入正确的密码";
                    });
                }
            }
        };
        jqueryUI.wrapper('#dialogInHtml', 'dialog', options);
    };
};
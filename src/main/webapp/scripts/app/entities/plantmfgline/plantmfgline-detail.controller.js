'use strict';

angular.module('jtraceApp')
    .controller('PlantmfglineDetailController', function ($scope, $stateParams, Plantmfgline, Plant) {
        $scope.plantmfgline = {};
        $scope.load = function (id) {
            Plantmfgline.get({id: id}, function(result) {
              $scope.plantmfgline = result;
            });
        };
        $scope.load($stateParams.id);
    });

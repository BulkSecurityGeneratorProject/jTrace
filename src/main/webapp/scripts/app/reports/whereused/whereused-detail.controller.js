'use strict';

angular.module('jtraceApp')
    .controller('WhereusedDetailController', function ($scope, $stateParams, Bomline, Itemmtr, Itemctn) {
        $scope.bomline = {};
        $scope.load = function (id) {
            Bomline.get({id: id}, function(result) {
              $scope.bomline = result;
            });
        };
        $scope.load($stateParams.id);
    });
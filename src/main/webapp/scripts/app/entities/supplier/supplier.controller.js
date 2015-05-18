'use strict';

angular.module('jtraceApp')
    .controller('SupplierController', function ($scope, Supplier,Manufacturer, ParseLinks) {
        $scope.suppliers = [];
        $scope.manufacturers = Manufacturer.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Supplier.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.suppliers = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Supplier.update($scope.supplier,
                function () {
                    $scope.loadAll();
                    $('#saveSupplierModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Supplier.get({id: id}, function(result) {
                $scope.supplier = result;
                $('#saveSupplierModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Supplier.get({id: id}, function(result) {
                $scope.supplier = result;
                $('#deleteSupplierConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Supplier.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSupplierConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.supplier = {code: null, isenabled: null, address: null, remark: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

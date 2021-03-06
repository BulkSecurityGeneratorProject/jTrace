'use strict';

angular.module('jtraceApp')
    .factory('Imanufacturer', function ($resource) {
        return $resource('api/imanufacturers/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

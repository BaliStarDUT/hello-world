'use strict';
/**
 *
 */
angular.module("app").service('clusterSvc',
 ['$http','$q','baseUrl','constantsUrl','FormatUrl',
 function ($http,$q,baseUrl,constantsUrl,FormatUrl) {
    this.getNamespacelist = function(){
        var deferred = $q.defer();
        var url = FormatUrl(constantsUrl.listPod);
        $http.get(url).then(function(data) {
            deferred.resolve(data.data);
        },function(error){
            deferred.reject(error.data);
        });
        return deferred.promise
    };

}])

'use strict';
/**
 *
 */
app.controller('clusterCtrl', ['$scope','clusterSvc',
 function ($scope,clusterSvc) {
     $scope.namespaces = clusterSvc.getNamespacelist().then(function(data){
        console.log(data);
     },function(error){
        console.error(error);
     });
}])

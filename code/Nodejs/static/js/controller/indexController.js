app.controller("indexCtrl",["$scope","$location","$http","$timeout","$interval",
"$cookies","$cookieStore","$window",
function($scope,$location,$http,$timeout,$interval,$cookies,$cookieStore,$window){
    $scope.name = "James";
    $scope.time = new Date().toLocaleTimeString();
    console.log($scope.name);
    console.log($location.absUrl());
    // $http.get("/node_modules/express/Readme.md").then(function(response){
    //     console.log(response.data);
    // });
    $timeout(function(){
        $scope.name = "Yang";
    },5000);
    $interval(function(){
        $scope.time = new Date().toLocaleTimeString();
    },2000);
    console.log("All cookies:",$cookies.getAll());
    console.log("Put version cookie..");
    $cookies.put("version","1.4.4")
    console.log("Get version cookie:",$cookies.get("version"));
    console.log("All cookies:",$cookies.getAll());
    console.log("localStorage:",$window.localStorage.length)
    $window.localStorage.setItem("version","1.0.0")
    console.log("localStorage:",$window.localStorage.length)
    $window.localStorage.setItem("name","nodeJs")
    console.log("localStorage:",$window.localStorage)
    console.log("localStorage:",$window.localStorage.length)
    $scope.$watch('name',function(newV,oldV){
        console.log("name:",newV,oldV);
    });
    
}]);

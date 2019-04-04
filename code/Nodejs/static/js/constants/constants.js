angular.module('app').constant('constants',{
    'listPod': 'listPod'
}).service('FormatUrl',['constantsUrl',function(constantsUrl){
    var FormatUrl = function(url,formData){
        for(var i in formData){
            var reg = new RegExp("{"+i+"}","gim");
            url = url.replace(reg,formData[i]);
            reg = null;
        }
        return constantsUrl+url;
    };
    return FormatUrl;
}]);

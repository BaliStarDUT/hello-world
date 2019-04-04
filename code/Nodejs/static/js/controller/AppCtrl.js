'use strict';
/**
 * Main Controller
 */
app.controller('AppCtrl', ['$rootScope', '$scope','$state','$translate','$window',
'$document', '$timeout',
function ($rootScope, $scope,$state,$translate, $window, $document, $timeout) {
    // State not found
$rootScope.$on('$stateNotFound', function (event, unfoundState, fromState, fromParams) {
    //$rootScope.loading = false;
    console.log(unfoundState.to);
    // "lazy.state"
    console.log(unfoundState.toParams);
    // {a:1, b:2}
    console.log(unfoundState.options);
    // {inherit:false} + default options
});
//global function to scroll page up
$scope.toTheTop = function () {
    $document.scrollTopAnimated(0, 600);

};

// angular translate
// ----------------------

$scope.language = {
    // Handles language dropdown
    listIsOpen: false,
    // list of available languages
    available: {
        'en': 'English',
        'ch': 'Chinese'
    },
    // display always the current ui language
    init: function () {
        var proposedLanguage = $translate.proposedLanguage() || $translate.use();
        var preferredLanguage = $translate.preferredLanguage();
        // we know we have set a preferred one in app.config
        $scope.language.selected = $scope.language.available[(proposedLanguage || preferredLanguage)];
    },
    set: function (localeId, ev) {
        $translate.use(localeId);
        $scope.language.selected = $scope.language.available[localeId];
        $scope.language.listIsOpen = !$scope.language.listIsOpen;
    }
};

$scope.language.init();


    $rootScope.pageTitle = function () {
        return $rootScope.app.name + ' - ' + ($rootScope.currTitle || $rootScope.app.description);
    };

}]);

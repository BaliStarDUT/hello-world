var app = angular.module("app",['clip-two']);

app.run(['$rootScope','$state','$stateParams',function($rootScope,$state,$stateParams){

    // Set some reference to access them from any scope
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;

    // GLOBAL APP SCOPE
    // set below basic information
    $rootScope.app = {
        name: 'k8s-dashboard', // name of your project
        author: 'James', // author's name or company name
        description: 'k8s-dashboard based on clip-two', // brief description
        version: '1.0', // current version
        year: ((new Date()).getFullYear()), // automatic current year (for copyright information)
        isMobile: (function () {// true if the browser is a mobile device
            var check = false;
            if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
                check = true;
            };
            return check;
        })(),
        layout: {
            isNavbarFixed: true, //true if you want to initialize the template with fixed header
            isSidebarFixed: true, // true if you want to initialize the template with fixed sidebar
            isSidebarClosed: false, // true if you want to initialize the template with closed sidebar
            isFooterFixed: false, // true if you want to initialize the template with fixed footer
            theme: 'theme-1', // indicate the theme chosen for your project
            logo: 'images/logo.png', // relative path of the project logo
        }
    };
    // console.log($state.includes('app.ui'));
    // console.log($rootScope.$state.includes('app.dashboard'));
    // $rootScope.$state.$current.name = 'contacts.details.item.url';
    //
    // console.log($rootScope.$state.includes("*.details.*.*")); // returns true
    // $state.includes("*.details.**"); // returns true
    // $state.includes("**.item.**"); // returns true
    // $state.includes("*.details.item.url"); // returns true
    // $state.includes("*.details.*.url"); // returns true
    // $state.includes("*.details.*"); // returns undefined
    // $state.includes("item.**"); // returns undefined
}]);

// translate config
app.config(['$translateProvider',function ($translateProvider) {

    // prefix and suffix information  is required to specify a pattern
    // You can simply use the static-files loader with this pattern:
    $translateProvider.useStaticFilesLoader({
        prefix: 'i18n/',
        suffix: '.json'
    });

    // Since you've now registered more then one translation table, angular-translate has to know which one to use.
    // This is where preferredLanguage(langKey) comes in.
    $translateProvider.preferredLanguage('en');

    // Store the language in the local storage
    // $translateProvider.useLocalStorage();

}]);

// Angular-Loading-Bar
// configuration
app.config(['cfpLoadingBarProvider',function (cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeBar = true;
    // cfpLoadingBarProvider.includeSpinner = false;
    cfpLoadingBarProvider.latencyThreshold = 500;
    cfpLoadingBarProvider.spinnerTemplate = '<div><span class="fa fa-spinner">Loading...</div>';
}]);

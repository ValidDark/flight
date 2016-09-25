/* @ngInject */
export default

function routes($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/home");
    $stateProvider
        .state('home', {
                url: "/home",
								component: 'home'
                })
        }

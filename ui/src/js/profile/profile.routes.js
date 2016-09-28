/* @ngInject */
export default
function routes($stateProvider) {
					$stateProvider
					.state('profile', {
				          url: "/profile/{userName}",
									component: 'profileComponant',
                	resolve: {
                    user: function(gService, $transition$) {
                      return gService.findUser($transition$.params().userName)
                    }
									}
				        })
					}

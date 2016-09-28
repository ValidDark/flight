/* @ngInject */
export default
function routes($stateProvider) {
					$stateProvider
					.state('detail', {
				          url: "/detail",
									component: 'detailComponant',
                	resolve: {
										cities: function(gService){
											return gService.getCities()
										}
									}
				        })
					}

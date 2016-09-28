export default
class Controller {
    /* @ngInject */
    constructor($scope, $interval, $location, $mdDialog, $state, gService) {

        let ctrl = this
        ctrl.service = gService
        ctrl.loggedIn = gService.loggedIn
        ctrl.user = gService.lUser
        ctrl.searched = false
        ctrl.flights = []
        ctrl.itineraries = []
        ctrl.oCities = [] //flight origin cities
        ctrl.dCities = [] //flight destination cities

        ctrl.departure = function(flight) {
            let open = 8 //what time the airport opens
            if ((flight.offset + open) > 12)
                return "" + (flight.offset - 12 + open) + ":00 PM"
            else {
                return "" + (flight.offset + open) + ":00 AM"
            }
        }

        ctrl.arrival = function(flight) {
            let open = 8 + flight.flightTime //what time the airport opens + the flight duration.
            if ((flight.offset + open) > 12)
                return "" + (flight.offset - 12 + open) + ":00 PM"
            else {
                return "" + (flight.offset + open) + ":00 AM"
            }
        }

        ctrl.getDetail = function(item) {
            console.log("get detail test!")
            console.log("is flight?")
            console.log(!ctrl.itineraries.includes(item))

            if (!ctrl.itineraries.includes(item)) {
                let newItem = {
                    "id": null,
                    "flights": [item],
                    "owner": null,
                    "flightTime": item.flightTime,
                    "totalDelay": 0
                }
            gService.detail = newItem
            }
            else {
            gService.detail = item
            }
            $state.go('detail')
        }

        ctrl.search = function(city1, city2) {
            ctrl.searched = true
            gService.searchFlights(city1, city2).then(function(response) {

                ctrl.itineraries = response.data

            })
            console.dir(ctrl.itineraries)
        }

        ctrl.cancelSearch = function() {
            ctrl.searched = false
            console.log("test, canceled")
        }

        ctrl.getCities = function() {

            ctrl.oCities = []
            ctrl.dCities = []

            ctrl.flights.forEach(e => {
                if (!ctrl.oCities.includes(e.origin)) {
                    ctrl.oCities.push(e.origin)
                }
                if (!ctrl.dCities.includes(e.destination)) {
                    ctrl.dCities.push(e.destination)
                }
            })

        }


        ctrl.queryoSearch = function(query) {
            var results = query ? ctrl.oCities.filter(createFilterFor(query)) : ctrl.oCities,
                deferred;
            return results;
        }

        ctrl.querydSearch = function(query) {
            var results = query ? ctrl.dCities.filter(createFilterFor(query)) : ctrl.dCities,
                deferred;
            return results;
        }

        function createFilterFor(query) {
            var lowercaseQuery = angular.lowercase(query);

            return function filterFn(flight) {
                return (angular.lowercase(flight).indexOf(lowercaseQuery) === 0);
            };
        }


        ctrl.refresh = function() {

            var serverData

            gService.allFlights().then(function(response) {

                serverData = response.data

            })


            setTimeout(function() {

                if (serverData !== ctrl.flights) {
                    ctrl.flights = serverData;
                    ctrl.getCities()
                    if (ctrl.searched) {
                        //ctrl.search()
                    }
                }
            }, 333)



        }

        ctrl.intervalPromise = $interval(function() {
            ctrl.refresh();
        }, 1000);

        // initial load of data
        ctrl.refresh();
    }


}

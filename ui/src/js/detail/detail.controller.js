export default
class Controller {
    /* @ngInject */
    constructor($scope, $interval, $location, $mdDialog, gService) {

        let ctrl = this
        ctrl.service = gService
        ctrl.item = gService.detail
        ctrl.badInfo = (jQuery.isEmptyObject(ctrl.item))
        ctrl.alreadyBooked = false

        console.log("-------------------------------------")
        console.log(gService.lUser)
        console.log("-----=-=------------------=-=--------")
        console.log(ctrl.item)


        /////////////////////////////////////////////////
        ////////////////Color Stuff//////////////////////

        ctrl.Color = [200, 100, 0] //rgb

        ctrl.addToColor = function(current) {
            let added = 56;
            if (current + added >= 255) {
                return current + added - 255
            } else {
                return current + added
            }
        }

        ctrl.newColor = function() {
            ctrl.Color = [ctrl.addToColor(ctrl.Color[0]), ctrl.addToColor(ctrl.Color[1]), ctrl.addToColor(ctrl.Color[2])]
        }

        ctrl.colorToString = function() {
            let result = "rgb(" + ctrl.Color[0] + "," + ctrl.Color[1] + "," + ctrl.Color[2] + ")"
            return result
        }

        ctrl.colorStyle = function(flight) {
          return "background-color:"+flight.color+"!important;width:50%"
        }

        ctrl.item.flights.forEach(function(flight) {
            flight.color = ctrl.colorToString()
            ctrl.newColor()
        })


        /////////////////////////////////////////////////
        ////////////////Info Stuff///////////////////////

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

        /////////////////////////////////////////////////
        /////////////////Map Stuff///////////////////////

        ctrl.zoom = 6
        ctrl.center = [35.5175, -86.5804]
        ctrl.markers = []
        ctrl.paths = []
        ctrl.cities = this.cities.data

        console.log("cities")
        console.log(ctrl.cities)
        console.log(ctrl.zoom)

        ctrl.addMarker = function(city) { //function to add a marker
            this.markers.push({
                position: [city.latitude, city.longitude]
            })
        }

        ctrl.addPath = function(city1, city2, color) {
            this.paths.push({
                path: [
                    [city1.latitude, city1.longitude],
                    [city2.latitude, city2.longitude]
                ],
                strokeColor: color,
                strokeOpacity: 1.0,
                strokeWeight: 3,
                geodesic: true
            })
        }


        ctrl.cities.forEach(function(city) { //add markers for each city involved in the flight.

            if ((ctrl.item.flights.map(e => e.origin).includes(city.city)) || (ctrl.item.flights.map(e => e.destination).includes(city.city))) {
                ctrl.addMarker(city)
            }
        })


        ctrl.item.flights.forEach(function(flight) {
            let city1 = ctrl.cities[ctrl.cities.map(e => e.city).indexOf(flight.origin)]
            let city2 = ctrl.cities[ctrl.cities.map(e => e.city).indexOf(flight.destination)]

            ctrl.addPath(city1, city2, flight.color)
        })

        /////////////////////////////////////////////////
        ///////////////Booking Stuff/////////////////////



        // ctrl.bookFlight = function() {
        //
        //   console.log("------------------------")
        //
        //   ctrl.item.owner = gService.lUser
        //   console.log(gService.lUser)
        //   console.log(ctrl.item)
        //   delete ctrl.item.id
        //   gService.saveItinerary(ctrl.item)
        //
        //
        //   // ctrl.item.flights.forEach(function(flight) {
        //   //     let temp = flight.color
        //   //     delete flight.color
        //   //     delete flight.itinerary
        //   //     gService.saveFlight(flight)
        //   //     flight.color = temp
        //   // })
        //
        // }

    }
}

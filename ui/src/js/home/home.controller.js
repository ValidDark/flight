

export default
class Controller {
    /* @ngInject */
    constructor($scope, $interval, $location, $mdDialog, gService) {

        let ctrl = this
        this.loggedIn = gService.loggedIn
        this.user = gService.lUser
        let flights = [{origin: "deez", destination: "nutz"}]
        console.log("A")

        let test = "A"

        this.refresh = function() {

            var serverData

            gService.allFlights().then(function(response) {

            serverData = response.data

          })


            setTimeout(function(){

                  if (serverData !== flights){
                     flights = serverData;
                  }
            }, 100);

//            console.dir(flights)

        };

        this.intervalPromise = $interval(function(){
            ctrl.refresh();
        }, 1000);

        // initial load of data
        this.refresh();
      }


    }

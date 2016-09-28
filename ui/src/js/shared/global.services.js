export default
class HomeService {
  /* @ngInject */
  constructor ($http, server) {
    this.$http = $http
    this.server = server
    this.loggedIn = false
    this.lUser = {} //the logged in user
    this.detail = {} //object whos details need to be passed around
  }


  findUser (username) {
    console.log('doing get on..')
    console.log(this.server + 'user/' + username)
    return this.$http
    .get(this.server + '/user/' + username)
  }

  searchFlights (city1, city2) {
    console.log('doing get on..')
    console.log(this.server + '/itinerary/' + city1 + '/' + city2)
    return this.$http
    .get(this.server + '/itinerary/' + city1 + '/' + city2)



  }

  newUser (user) {
	      this.$http
	      .post(this.server + '/user/', user)

	  }

  deleteUser (id, user) {
	    return () => {
	      this.$http
	      .delete(this.server + 'api/users/' + id, user)
	    }
	  }

  allFlights() {
    return this.$http
    .get(this.server + '/flights/')
  }

  getCities() {
    return this.$http
    .get(this.server + '/location/')
  }

  saveFlight = function(flight) {
        return this.$http
        .post(this.server + '/flights/', flight)

    }

    saveItinerary = function(itinerary) {
        return this.$http
        .post(this.server + '/itinerary/', itinerary)

      }
}

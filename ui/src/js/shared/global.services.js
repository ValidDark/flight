export default
class HomeService {
  /* @ngInject */
  constructor ($http, server) {
    this.$http = $http
    this.server = server
    this.loggedIn = false;
    this.lUser = {} //the logged in user
  }


  finduser (username) {
    console.log('doing get on..')
    console.log(this.server + 'user/' + username)
    return this.$http
    .get(this.server + '/user/' + username)
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

}

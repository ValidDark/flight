import logintemp from './login/login.html'
import registertemp from './register/register.html'



export default
class Controller {
    /* @ngInject */
    constructor($scope, $mdDialog, gService, $location) {

        var ctrl = this

        var bcrypt = require('bcryptjs');

        this.loggedIn = gService.loggedIn
        this.user = gService.lUser

        let enteredPassword = ''


        $scope.status = '';
        $scope.customFullscreen = false;


        $scope.showLogin = function(ev) {
            $mdDialog.show({
                    templateUrl: logintemp,
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    controller: DialogController,
                    clickOutsideToClose: true
                })
                .then(function() {
                    gService.loggedIn = true
                    console.log(gService.loggedIn)
                    console.log("user is:")
                    console.log(gService.lUser)
                    ctrl.loggedIn = gService.loggedIn
                    ctrl.user = gService.lUser
                });
        };

        $scope.showRegister = function(ev) {
            $mdDialog.show({
                    templateUrl: registertemp,
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    controller: DialogController,
                    clickOutsideToClose: true
                })
                .then(function() {
                    gService.loggedIn = true
                    console.log(gService.loggedIn)
                    console.log("user is:")
                    console.log(gService.lUser)
                    ctrl.loggedIn = gService.loggedIn
                    ctrl.user = gService.lUser
                });
        };



        $scope.logout = function() {
            gService.loggedIn = false
            ctrl.loggedIn = false
            gService.user = {}
            ctrl.user = {}
        };






        function DialogController($scope, $mdDialog) {
            $scope.hide = function() {
                $mdDialog.hide();
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };

            $scope.new = function(user) {


                user.password = bcrypt.hashSync(user.password, 8);


                gService.newUser(user)

                gService.lUser = user

                $mdDialog.hide();
            };

            $scope.login = function(user) {

                console.log(user)
                console.log(user.userName)

                gService.finduser(user.userName).then(function(response) {

                    console.log(response.data)


                    let found = response.data;


                    if (found != null) {
                        console.log("user found!")
                        console.log(found)

                        if (bcrypt.compareSync(user.password, found.password)) {
                            console.log("Logged in!")
                            user = found
                            gService.lUser = user
                            console.log(gService.lUser)
                            $mdDialog.hide();
                        } else {
                            console.log("password didn't match!!")
                            console.log(users[found].password)
                        }
                    } else {
                        console.log("not found!")
                    }



                })





            };


        }









    }

}

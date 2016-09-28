

export default
class Controller {
    /* @ngInject */
    constructor($scope, $interval, $location, $mdDialog, gService) {

      let ctrl = this
      ctrl.loggedIn = gService.loggedIn
      ctrl.service = gService
      ctrl.user = gService.lUser

      console.log(ctrl.loggedIn)
      console.log(ctrl.user)


      ctrl.getDetail = function(item) {

          gService.detail = item
          $state.go('detail')
      }



    }
}

import templateUrl from './detail.template.html'
import detailController from './detail.controller.js'

export default {
    templateUrl,
    controllerAs: 'ctrl',
    controller: detailController,
    bindings: {
        cities: '<'
    }
}

import detailComponant from './detail.component'
import detailRoutes from './detail.routes'

export default
  angular
    .module('app.detail', ['ngMap'])
    .component('detailComponant', detailComponant)
    .config(detailRoutes)
    .name

import homeComponant from './home.component'
import homeRoutes from './home.routes'

export default
  angular
    .module('app.home', [])
    .component('homeComponant', homeComponant)
    .config(homeRoutes)
    .name

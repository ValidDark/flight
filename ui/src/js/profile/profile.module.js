import profileComponant from './profile.component'
import profileRoutes from './profile.routes'

export default
  angular
    .module('app.profile', [])
    .component('profileComponant', profileComponant)
    .config(profileRoutes)
    .name

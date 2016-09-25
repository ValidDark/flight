import home from './home.component'
import routes from './home.routes'

export default
  angular
    .module('app.home', [])
    .component('home', home)
    .config(routes)
    .name

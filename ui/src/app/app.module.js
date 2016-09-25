import uiRoute from 'angular-ui-router'
import ngMaterial from 'angular-material'
import 'angular-material/angular-material.css'
import 'bcryptjs'

import topbar from '../js/topbar/topbar.component'
import home from '../js/home/home.module'
import apiUrl from './api.url'


import gService from '../js/shared/global.services'


export default
  angular
    .module('flight', [uiRoute, ngMaterial, home])
    .service('gService', gService)
    .constant('server', apiUrl)
    .component('topbar', topbar)
    .name

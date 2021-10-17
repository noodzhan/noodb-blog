import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import '../public/assert/style/public.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import axios from 'axios'
import store from '@/store'

Vue.config.productionTip = false
Vue.use(Antd)
Vue.use(mavonEditor)
Vue.prototype.$http = axios
Vue.prototype.$appUrl = process.env.NODE_ENV === 'development' ? process.env.VUE_APP_URL : ''

Vue.prototype.autoPrefix = function () {
  if (process.env.NODE_ENV === 'development') {
    return '/api'
  } else if (process.env.NODE_ENV === 'production') {
    return '/api'
  }
}
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

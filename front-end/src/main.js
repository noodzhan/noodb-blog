import Vue from 'vue'
import App from './App.vue'
import router from '@/router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import '../public/assert/style/public.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.config.productionTip = false
Vue.use(Antd)
Vue.use(mavonEditor)
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

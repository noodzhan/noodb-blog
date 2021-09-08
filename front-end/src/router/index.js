import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home'
import Profile from '@/views/Profile'
import Article from '@/views/Article'
import Edit from '@/views/Edit'

Vue.use(VueRouter)

const routers = [
  { path: '/', redirect: '/home' },
  { path: '/profile', component: Profile },
  { path: '/blog', component: Article },
  { path: '/home', component: Home },
  { path: '/edit/*', component: Edit }
]
const router = new VueRouter({
  mode: 'history',
  base: '/',
  scrollBehavior: () => ({ y: 0 }),
  routes: routers
})
export default router

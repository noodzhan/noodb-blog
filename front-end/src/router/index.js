import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home'
import Profile from '@/views/Profile'
import Article from '@/views/Article'
import Edit from '@/views/Edit'
import Login from '@/views/Login'
import store from '@/store'
const routerPushProxy = VueRouter.prototype.push

VueRouter.prototype.push = function (location) {
  return routerPushProxy.call(this, location).catch((error) => { console.warn(error) })
}

Vue.use(VueRouter)

const routers = [
  { path: '/', redirect: '/home', meta: { title: 'noodb个人博客' } },
  { path: '/login', component: Login },
  { path: '/profile', component: Profile },
  { path: '/blog/:articleId', component: Article },
  { path: '/blog/edit/:articleId', component: Edit },
  { path: '/home', component: Home, meta: { title: 'noodb个人博客' } }
]
const router = new VueRouter({
  mode: 'history',
  base: '/',
  scrollBehavior: () => ({ y: 0 }),
  routes: routers
})
const mustLoginUrl = ['blog/edit']
router.beforeEach((to, from, next) => {
  // nextHomeIfCondition(to, from, next)
  let forwardLogin = false
  mustLoginUrl.forEach(url => {
    if (to.path.includes(url)) {
      forwardLogin = true
    }
  })
  if (forwardLogin) {
    if (store.state.isLogin || window.isDev) {
      next()
    } else {
      store.commit('setLastRouteHistory', to)
      next('/login')
    }
  } else {
    next()
  }
})

/**
 * 判断是否跳转到首页
 * @param to
 * @param from
 * @param next
 */
// function nextHomeIfCondition (to, from, next) {
//   let isIllegalRoute = false
//   routers.forEach(item => {
//     if (!to.path.includes(item.path)) {
//       isIllegalRoute = true
//     }
//   })
//   if (isIllegalRoute) {
//     next('/')
//   }
// }

export default router

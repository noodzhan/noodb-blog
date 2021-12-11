import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    isLogin: false,
    userInfo: {},
    lastRouteHistory: {}
  },
  mutations: {
    setLoginFlag: (state, payload) => {
      state.isLogin = payload
    },
    setUserInfo: (state, payload) => {
      state.userInfo = payload
    },
    setLastRouteHistory: (state, payload) => {
      state.lastRouteHistory = payload
    }

  }
})
export default store

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    isLogin: false,
    userInfo: {}
  },
  mutations: {
    setLoginFlag: (state, payload) => {
      state.isLogin = payload
    },
    setUserInfo: (state, payload) => {
      state.userInfo = payload
    }
  }
})
export default store

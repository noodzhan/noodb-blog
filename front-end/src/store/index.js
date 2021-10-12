import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    openLoginModal: false
  },
  mutations: {
    notifyOpenLoginModal: state => {
      state.openLoginModal = true
    }
  }
})
export default store

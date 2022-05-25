export const state = () => ({
    isLogin: false,
    userInfo: {},
    lastRouteHistory: {}
})

export const mutations = {
    setLoginFlag: (state, payload) => {
        state.isLogin = payload;
    },
    setUserInfo: (state, payload) => {
        state.userInfo = payload;
    },
    setLastRouteHistory: (state, payload) => {
        state.lastRouteHistory = payload;
    }
}

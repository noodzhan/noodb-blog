export default function ({store, route}) {
    if (!route.fullPath.includes('login')) {
        store.commit('setLastRouteHistory', route.fullPath);
    }
}

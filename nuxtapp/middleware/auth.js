export default function ({store, redirect}) {
    if (!store.state.isLogin) {
        return redirect('/login')
    }
}

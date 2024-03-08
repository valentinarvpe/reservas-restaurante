export default function redirectToRoute({store, redirect, req, res}){
    if (store.state.user.rol == '') {
        redirect("/")
    }
    return;
}
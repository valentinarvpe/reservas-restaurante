export default ({ app, redirect  }) => {
    try {
        const cookieRol = app.$cookies.get('user').rol
        if (cookieRol !== 'ROLE_ADMIN') {
            redirect('/')
        } 
    } catch (error) {
        redirect('/')
    }

}
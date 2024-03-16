export const state = () => ({
    rol: '',
    usuario:{},
    isAuth: false,
  })
  
  export const mutations = {
    setRol(state, user_rol ) {
        state.rol = user_rol
    },

    setUsuario(state, usuario) {
      state.usuario = usuario
    },
    setIsAuth(state, auth) {
      state.isAuth = auth
    }
}

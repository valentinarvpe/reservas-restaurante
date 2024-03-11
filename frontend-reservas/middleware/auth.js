export default function ({ app, redirect }) {
    const token = app.$cookies.get('user').token; // Suponiendo que estás utilizando el módulo `cookie-universal-nuxt` para manejar cookies
    console.log(token);

  }
  
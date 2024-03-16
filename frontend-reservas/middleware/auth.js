export default function ({ app, redirect }) {
    const token = app.$cookies.get('user').token; 
    console.log(token);

  }
  
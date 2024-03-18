import colors from 'vuetify/es5/util/colors'
require('dotenv').config()
export default {
  router: {
   // middleware: ['auth']
  },
  server: {
    port: 3000,
    host: '0.0.0.0'
  }, 
  
  loading: '~/components/LoadingBar.vue',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    titleTemplate: '%s - frontend-reservas',
    title: 'frontend-reservas',
    htmlAttrs: {
      lang: 'en'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/logo_hat.png' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    '@/assets/main.css'
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/vuetify
    '@nuxtjs/vuetify',
  ],

  ssr: true,
  target: 'server',
  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/toast',
    '@nuxtjs/auth-next',
    'cookie-universal-nuxt',
  ],

  axios: {
    baseURL: process.env.API_URL || 'http://localhost:8080',// URL base de tu API
    //baseURL: 'http://157.245.127.16:8080',
    withCredentials: true,
    headers: {
      Authorization: process.env.TOKEN,
    },
    // proxy: true
  },

  toast: {
    position: 'top-right',
    iconPack: 'mdi',
    register: [ // Register custom toasts
      {
        name: 'myerror',
        message: 'as',
        options: {
          type: 'error',
          icon: 'mdi-check',
          theme: 'outline',
          duration: 2000
        }
      }
    ]
  },

  // Vuetify module configuration: https://go.nuxtjs.dev/config-vuetify
  vuetify: {
    customVariables: ['~/assets/variables.scss'],
    theme: {
      dark: false,
      themes: {
        light: {
          primary: colors.blue.darken2,
          accent: colors.grey.darken3,
          secondary: colors.amber.darken3,
          info: colors.teal.lighten1,
          warning: colors.amber.base,
          error: colors.deepOrange.accent4,
          success: colors.green.accent4
        },
        colors: {
          primary: colors.red.darken1, // #E53935
          secondary: colors.red.lighten4, // #FFCDD2
        }
      }
    }
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    publicPath: '/dist/'
  }
}

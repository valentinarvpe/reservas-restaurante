<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" :clipped="clipped" fixed app v-if="auth">
      <v-list>
        <v-list-item v-for="(item, i) in items" :key="i" :to="item.to" router exact>
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar :elevation="6" :clipped-left="clipped" app>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" v-if="auth" />
      <v-btn icon to="/">
        <v-avatar :tile="true" class="ml-4">
          <img :src="require('@/assets/images/logo_hat.png')" alt="logo">
        </v-avatar>
      </v-btn>
      <v-toolbar-title class="ml-4">{{ title }}</v-toolbar-title>
      <v-spacer />
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-btn icon to="/registro" v-if="!auth" v-on="on">
            <v-icon>mdi-table-chair</v-icon>
          </v-btn>
        </template>
        <span>Reservar</span>
      </v-tooltip>
      <v-btn v-if="auth" color="secondary" rounded outlined dark @click="salir()">
        Salir
        <v-icon class="ml-2">
          mdi-logout
        </v-icon>
      </v-btn>
      <LoginComponent v-if="!auth" @rol_user="recibirEvento">
      </LoginComponent>
    </v-app-bar>
    <v-main>
      <Nuxt />
    </v-main>
    <v-footer class="text-center d-flex flex-column">
      <v-divider></v-divider>
      <span>&copy; {{ new Date().getFullYear() }}</span>
      <span>&copy; valentinarvpe </span>
    </v-footer>
  </v-app>

</template>

<script>
export default {
  name: 'DefaultLayout',
  data() {
    return {
      clipped: true,
      auth: false,
      drawer: false,
      fixed: true,
      items: [
        {
          icon: 'mdi-chart-bubble',
          title: 'Dashboard',
          to: '/dashboard'
        }
      ],
      title: 'Hat'
    }
  },

  watch:{
    auth : function (val) {
      this.auth = val
    }
  },

  mounted() {
    this.auth = this.$cookies.get('isAuth');
  },

  methods: {
    recibirEvento(value) {
      this.auth  = value;
      this.$router.push('/dashboard');
    },

    salir() {
      window.localStorage.setItem('isAuth', false);
      localStorage.removeItem("token");
      this.$cookies.removeAll()
      this.$nuxt.$router.go();
    }
  }
}
</script>

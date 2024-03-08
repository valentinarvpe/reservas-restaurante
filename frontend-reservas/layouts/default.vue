<template>
  <v-app>
    <v-navigation-drawer v-model="drawer":clipped="clipped" fixed app
      v-if="rol == 'ADMIN'">
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
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" v-if="rol == 'ADMIN'" />
      <v-btn icon to="/">
        <v-avatar :tile="true" class="ml-4">
          <img :src="require('@/assets/images/logo_hat.png')" alt="logo">
        </v-avatar>
      </v-btn>
      <v-toolbar-title class="ml-4">{{ title }}</v-toolbar-title>
      <v-spacer />
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-btn icon to="/registro" v-if="rol != 'ADMIN'" v-on="on">
            <v-icon>mdi-table-chair</v-icon>
          </v-btn>
        </template>
        <span>Reservar</span>
      </v-tooltip>
      <v-btn v-if="rol == 'ADMIN'" color="secondary" rounded outlined dark @click="salir()">
        Salir
        <v-icon class="ml-2">
          mdi-logout
        </v-icon>
      </v-btn>
      <LoginComponent v-if="rol != 'ADMIN'" @rol_user="recibirEvento">
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
      drawer: false,
      rol: '',
      fixed: true,
      items: [
        {
          icon: 'mdi-chart-bubble',
          title: 'Dashboard',
          to: '/reservas'
        }
      ],
      title: 'Chef Hat'
    }
  },

  watch: {
    rol(val) {
      console.log(val);
    }
  },

  methods: {
    recibirEvento(value) {
      this.rol = value;
      this.$router.push('/reservas');
    },

    salir() {
      this.rol = '';
      this.$router.push('/');
    }
  }
}
</script>

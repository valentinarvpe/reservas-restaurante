<template>
  <v-container fluid>
    <v-row>
      <v-col cols="7" md="7" class="ma-0 pa-0">
        <!-- Imagen del restaurante -->
        <div class="d-flex justify-center">
          <img :src="require('@/assets/images/cocinalogin.jpg')" alt="Restaurante"
            style="max-width: 100%; height: auto;" aspect-ratio="1.7">
        </div>
      </v-col>
      <v-col cols="5" md="5" class="ma-0 pa-0" style="border:none">
        <!-- Formulario de login -->
        <v-card class="mx-auto ma-8" max-width="600" height="800px" outlined style="border:none">
          <v-card-title class="justify-center ma-0 pa-0" style="line-height: 5rem;">
            <v-icon class="mr-2" color="orange">
              mdi-account-heart
            </v-icon>
            <h1 class="roboto-bold">
              Login
            </h1>
          </v-card-title>
          <v-card-text>
            <v-form @submit.prevent="login" class="ma-10">
              <v-row>
                <v-col cols="12">
                  <v-text-field prepend-icon="mdi-account-key" :rules="emailRules" v-model="usuario.email"
                    label="Email"></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field prepend-icon="mdi-lock-off" v-model="usuario.clave" label="Contraseña" :rules="claveRules"
                    type="password"></v-text-field>
                </v-col>
                <v-btn type="submit" color="secondary" rounded block x-large class="mr-4">
                  Aceptar
                  <v-icon class="ml-2">
                    mdi-check-circle
                  </v-icon>
                </v-btn>
              </v-row>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      usuario: {
        email: '',
        clave: '',
      },
      emailRules: [
        v => !!v || 'E-mail es obligatorio',
        v => /.+@.+\..+/.test(v) || 'E-mail debe ser valido',
      ],
      claveRules: [
        v => !!v || 'La contraseña es obligatoria'
      ]
    }
  },
  methods: {
    async login() {
      await this.$axios.$post("/login", this.usuario).
        then(resp => {
          this.$axios.defaults.headers.common['Authorization'] = 'Bearer ' + resp.token;
          this.$emit('rol_user', true);
          this.$cookies.set('user', resp, {
            path: '/'
          })
          this.$cookies.set('isAuth', true, {
            path: '/'
          })
          window.localStorage.setItem('isAuth', true);
          window.localStorage.setItem('token', resp.token);
          this.dialog = false;
          this.$store.commit('user/setIsAuth', true);
          this.$router.push("/dashboard");
          this.$toast.success(resp.message, {
            icon: 'mdi-check-circle', duration: 2000
          })
        })
        .catch(() =>
          this.$toast.error('Error al autenticarse ',
            { icon: 'mdi-alert-circle', duration: 2000 })
        );
    }
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Bree+Serif&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto+Serif:ital,opsz,wght@0,8..144,100..900;1,8..144,100..900&family=Roboto+Slab:wght@100..900&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap');

.roboto-serif {
  font-family: "Roboto Serif", serif;
  font-optical-sizing: auto;
  font-weight: 600;
  font-style: normal;
  font-variation-settings:
    "wdth" 100,
    "GRAD" 0;
}

.titulo {
  text-justify: auto;
  font-family: "Roboto", sans-serif;
  font-weight: 100;
  font-style: normal;
}

.roboto-bold {
  font-family: "Roboto", sans-serif;
  font-weight: 800;
  font-size: 250%;
  font-style: normal;
  text-align: center;
  background: -webkit-linear-gradient(yellow, red);
  background-clip: text;
  -webkit-text-fill-color: transparent;
  color: tomato;
}
</style>
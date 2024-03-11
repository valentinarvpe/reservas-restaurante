<template>
    <div class="text-xs-center">
        <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on }">
                <v-btn color="secondary" rounded outlined dark v-on="on">
                    Ingresar
                    <v-icon class="ml-2">
                        mdi-login
                    </v-icon>
                </v-btn>
            </template>

            <v-card>
                <v-card-title class="headline grey lighten-2" primary-title>
                    <v-icon class="mr-3">mdi-account-circle</v-icon>
                    Ingresar
                </v-card-title>

                <v-card-text>
                    <v-form ref="form" class="ma-2" v-model="valido" lazy-validation>
                        <v-text-field type="email" prepend-icon="mdi-email-box" v-model="email" :rules="emailRules"
                            label="E-mail" required></v-text-field>

                        <v-text-field type="password" prepend-icon="mdi-lock" v-model="clave" :rules="claveRules"
                            label="Contraseña" required></v-text-field>
                    </v-form>
                </v-card-text>

                <v-divider></v-divider>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn :disabled="!valido" color="secondary" block @click="login()">
                        Aceptar
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            dialog: false,
            valido: true,
            clave: '',
            email: '',
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
            let email = this.email;
            let clave = this.clave;
            const user = { email, clave };
            await this.$axios.$post("/login", user).
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
                    this.$router.push("/dashboard");
                    this.$toast.success(resp.message, {
                        icon: 'mdi-check-circle', duration: 2000
                    })
                })
                .catch(()=>
                    this.$toast.error('Error al autenticarse ',
                        { icon: 'mdi-alert-circle', duration: 2000 })
                );
        }
    }
}
</script>
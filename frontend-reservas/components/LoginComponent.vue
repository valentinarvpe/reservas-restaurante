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
                    <v-form ref="form" class="ma-2" v-model="valid" lazy-validation>
                        <v-text-field 
                        type="email"
                        prepend-icon="mdi-email-box" v-model="email" label="E-mail"
                            required></v-text-field>

                        <v-text-field 
                        type="password"
                        prepend-icon="mdi-lock" v-model="clave" label="Contraseña"
                            required></v-text-field>
                    </v-form>
                </v-card-text>

                <v-divider></v-divider>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="secondary" block @click="login()">
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
            valid: false,
            clave: '',
            email: ''
        }
    },
    methods: {
        async login() {
            const ENDPOINT_PATH = "http://localhost:8080/";
            try {
                let email = this.email;
                let clave = this.clave;
                const user = { email, clave };
                const resp = await this.$axios.$post(ENDPOINT_PATH + "login", user);
                if (resp.mensaje == 'Ok') {
                    this.dialog = false;
                    this.$router.push("/reservas");
                    this.$emit('rol_user', resp.rol);
                    this.$store.commit('user/setRol', resp.rol)
                    this.$toast.success(`Bienvenido ${email}`, {
                        icon: 'mdi-check-circle', duration: 2000
                    })
                } else {
                    this.$toast.error('Error al autenticarse',
                    {icon:'mdi-alert-circle', duration: 2000 })
                }
            } catch (error) {
                this.$toast.error('Error al autenticarse',
                    {icon:'mdi-alert-circle', duration: 2000 })
            }

        }
    }
}
</script>
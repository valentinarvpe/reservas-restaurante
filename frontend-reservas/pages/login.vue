<!-- Login.vue -->

<template>
    <v-container fluid class="pa-0">
        <v-img :src="require('@/assets/images/login.svg')" width="2000px" height="600px" contain aspect-ratio="1.7" >

            <v-container style="display: flex; justify-content:flex-end;">
                <v-card outlined height="700px" width="400px">
                    <v-card-title class="headline grey lighten-2 mb-8">Iniciar sesion</v-card-title>
                    <v-card-text>
                        <v-form @submit.prevent="login">
                            <v-text-field v-model="email" label="Correo electrónico" outlined></v-text-field>
                            <v-text-field v-model="password" label="Contraseña" type="password" outlined></v-text-field>
                            <v-btn type="submit" color="primary">Iniciar sesión</v-btn>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-container>
        </v-img>

    </v-container>

</template>

<script>
export default {
    name: 'login',
    data() {
        return {
            email: '',
            password: ''
        }
    },
    methods: {
        login() {
            this.$router.push('/dashboard');
        }
    }
}
</script>

<style scoped>
.login-container {
    justify-content: center;
    display: flex;
    height: 100vh;
}


.login-card {
    width: 100%;
    max-width: 400px;
}

.login-form {
    padding: 24px;
}
</style>

<template>
    <div class="text-xs-center">
        <v-dialog v-model="dialog" width="1000">
            <template v-slot:activator="{ on }">
                <v-btn rounded large class="mb-16" color="secondary" v-on=on>
                    <span class= "ma-8 ">
                        ¡Realiza tu reserva ahora!
                    </span>
                    <v-icon class="ml-3">mdi-calendar-cursor-outline</v-icon>
                </v-btn>
            </template>

            <v-card>
                <v-card-title class="headline grey lighten-2 mb-0" primary-title>
                    <v-icon class="mr-1">mdi-table-chair</v-icon>
                    Generar Reserva
                    <v-spacer></v-spacer>
                    <v-btn large icon @click="dialog = false">
                        <v-icon>mdi-close-circle</v-icon>
                    </v-btn>
                </v-card-title>
                <v-card-text>
                    <FormularioComponent @cerrado="cerrar"></FormularioComponent>
                </v-card-text>
                <v-divider></v-divider>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import FormularioComponent from './FormularioComponent.vue';

export default {
    data() {
        return {
            dialog: false
        }
    },
    methods: {
        cerrar(value){
        this.dialog = value;
    }   
    }
}
</script>
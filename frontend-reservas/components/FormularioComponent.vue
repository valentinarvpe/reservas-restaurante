<template>
    <v-form @submit.prevent class="ma-3">
        <v-row>
            <v-col cols="12" sm="6" md="6">
                <v-text-field prepend-icon="mdi-account" v-model="reserva.usuario.nombres" :rules="rules"
                    label="Nombre"></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
                <v-text-field v-model="reserva.usuario.apellidos" :rules="rules" label="Apellidos"></v-text-field>
            </v-col>
            <v-col cols="12" sm="4" md="4">
                <v-select :items="tipos_documentos" item-text="text" item-value="value"
                    prepend-icon="mdi-card-account-details" v-model="reserva.usuario.tipo_documento" :rules="rules"
                    label="Tipo de documento"></v-select>
            </v-col>
            <v-col cols="12" sm="8" md="8">
                <v-text-field v-model="reserva.usuario.identificacion" :rules="rules"
                    label="Identificación"></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
                <v-text-field prepend-icon="mdi-email-box" v-model="reserva.usuario.email" :rules="rules"
                    label="Email"></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
                <v-menu v-model="menu1" :close-on-content-click="false" :nudge-right="40" transition="scale-transition"
                    offset-y min-width="auto">
                    <template v-slot:activator="{ on, attrs }">
                        <v-text-field 
                        locale="ES"
                        v-model="reserva.fechaReserva" label="Fecha de reserva"
                            prepend-icon="mdi-calendar" readonly v-bind="attrs" v-on="on"></v-text-field>
                    </template>
                    <v-date-picker v-model="reserva.fechaReserva" @input="menu1 = false"></v-date-picker>
                </v-menu>
            </v-col>
            <v-col cols="12" sm="8" md=8>
                <v-select :items="tipos_reservas" prepend-icon="mdi-silverware" v-model="reserva.tipoReserva"
                    :rules="rules" label="Tipo de reserva"></v-select>
            </v-col>
            <v-col cols="12" sm="4" md=4>
                <v-text-field
                    type="number"
                    prepend-icon="mdi-account-group" v-model="reserva.cantidadPersonas" :rules="rules"
                    label="Cantidad de personas"></v-text-field>
            </v-col>
            <v-col cols="12" sm="12" md=12>
                <v-textarea rows="2" prepend-icon="mdi-comment" v-model="reserva.observaciones" :rules="rules"
                    label="Observaciones"></v-textarea>
            </v-col>
            <v-col cols="12" sm="8" md=6>
                <v-btn v-if="reserva.id != null" class="mt-2" type="submit" color="success" rounded large block
                    @click="confirmarReserva(reserva.id)">Confirmar</v-btn>
            </v-col>
            <v-col cols="12" sm="8" md=6>
                <v-btn class="mt-2" type="submit" color="primary" rounded large block @click="creaReserva()">{{ titulo
                    }}</v-btn>
            </v-col>
        </v-row>
    </v-form>

</template>

<script>
import formulario from '../models/formulario'
export default {
    model: formulario,
    props: {
        tituloEdit: String,
        reservaEditada: Object
    },
    data() {
        return {
            menu1: false,
            titulo: 'Guardar',
            reserva: {
                usuario: {
                    nombres: '',
                    apellidos: '',
                    tipo_documento: 'CC',
                    identificacion: '',
                    email: '',
                },
                fechaReserva: new Date().toISOString().substr(0, 10),
                observaciones: '',
                tipoReserva: ''
            },
            tipos_documentos: [],
            tipos_reservas: [],
            rules: [v => !!v || 'Campo obligatorio']
        }
    },

    watch: {
        reservaEditada() {
            this.reserva = this.reservaEditada.id > 0 ? this.reservaEditada : this.reserva
            this.titulo = this.tituloEdit
        },

        tituloEdit() {
            this.titulo = this.tituloEdit != '' ? "Actualizar" : "Guardar"
        }
    },

    created() {
        if (this.reservaEditada != null) {
            this.reserva = this.reservaEditada
            this.titulo = this.tituloEdit != '' ? "Actualizar" : "Guardar"
        }
    },
    mounted() {
        this.tipos_documentos = formulario.tipos_documentos;
        this.tipos_reservas = formulario.tipos_reservas;
    },

    methods: {
        async creaReserva() {
            const id = this.reserva.id
            if (id != null) {
                formulario.actualizar(id, this.reserva)
                    .then(resp => {
                        this.$toast.success(`Actualizado correctamente`, {
                            icon: 'mdi-check-circle', duration: 2000
                        })
                        this.$emit('cerrado', false);
                        this.$router.push('/dashboard');
                    }).catch(() => {
                        this.$toast.error('Error al actualizar',
                            { icon: 'mdi-alert-circle', duration: 2000 })
                    })
            } else {
                try {
                    formulario.guardar(this.reserva)
                        .then(resp => {
                            this.$toast.success("Guardado correctamente", {
                                icon: 'mdi-check-circle', duration: 2000
                            })
                            this.$router.push('/');
                        })

                } catch (error) {
                    this.$toast.error('Error al guardar',
                        { icon: 'mdi-alert-circle', duration: 2000 })
                }

            }
            this.$emit('cerrado', false);
        },

        async confirmarReserva(id) {
            this.$axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("token");
            try {
                await this.$axios.$put(`/api/reservas/actualizaEstado/${id}?estado=${true}`)
                    .then(resp => {
                        this.$toast.success('Confirmada correctamente', { icon: 'mdi-check-circle', duration: 2000 })
                    })
                this.$toast.success('Enviando correo', { icon: 'mdi-send', duration: 2500 })
                await this.$axios.$post("/api/correo/enviar", this.reserva)
                    .then(resp => {
                        this.$toast.success('Correo de confirmación enviado',
                            { icon: 'mdi-check-circle', duration: 2000 })
                    })

            } catch (error) {
                this.$toast.error('Error al enviar correo',
                    { icon: 'mdi-alert-circle', duration: 2000 })
            }
            this.$emit('cerrado', false);
        }
    }
}
</script>

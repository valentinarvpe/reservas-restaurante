<template>
    <v-form @submit.prevent class="ma-3">
        <v-row>
            <v-col cols="12" sm="8" md="6">
                <v-text-field prepend-icon="mdi-account" v-model="reserva.nombres" :rules="rules"
                    label="Nombre"></v-text-field>
            </v-col>
            <v-col cols="12" sm="8" md="6">
                <v-text-field v-model="reserva.apellidos" :rules="rules" label="Apellidos"></v-text-field>
            </v-col>
            <v-col cols="12" sm="8" md="4">
                <v-select :items="tipos_documentos" item-text="text" item-value="value"
                    prepend-icon="mdi-card-account-details" v-model="reserva.tipo_documento" :rules="rules"
                    label="Tipo de documento"></v-select>
            </v-col>
            <v-col cols="12" sm="8" md="8">
                <v-text-field v-model="reserva.identificacion" :rules="rules" label="Identificación"></v-text-field>
            </v-col>
            <v-col cols="12" sm="8" md="6">
                <v-text-field prepend-icon="mdi-email-box" v-model="reserva.email" :rules="rules"
                    label="Email"></v-text-field>
            </v-col>
            <v-col cols="12" sm="8" md="6">
                <v-menu ref="menu1" v-model="menu1" :close-on-content-click="false" :nudge-right="40"
                    transition="scale-transition" offset-y max-width="290px" min-width="290px">
                    <template v-slot:activator="{ on }">
                        <v-text-field v-model="reserva.fecha_reserva" label="Fecha de reserva" hint="YYYY/MM/DD"
                            persistent-hint prepend-icon="mdi-calendar-range" v-on="on">
                        </v-text-field>
                    </template>
                    <v-date-picker v-model="reserva.fecha_reserva" no-title @input="menu1 = false"></v-date-picker>
                </v-menu>
            </v-col>
            <v-col cols="12" sm="8" md=8>
                <v-select :items="tipos_reservas" prepend-icon="mdi-silverware" v-model="reserva.tipo_reserva"
                    :rules="rules" label="Tipo de reserva"></v-select>
            </v-col>
            <v-col cols="12" sm="8" md=4>
                <v-text-field prepend-icon="mdi-account-group" v-model="reserva.cantidad_personas" :rules="rules"
                    label="Cantidad de personas"></v-text-field>
            </v-col>
            <v-col cols="12" sm="8" md=12>
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
export default {
    props: {
        tituloEdit: String,
        reservaEditada: Object
    },
    data() {
        return {
            date: new Date().toISOString().substr(0, 10),
            dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
            menu1: false,
            titulo: 'Guardar',
            reserva: {
                nombres: '',
                apellidos: '',
                tipo_documento: 'CC',
                identificacion: '',
                email: '',
                fecha_reserva: new Date().toISOString().substr(0, 10),
                observaciones: '',
                tipo_reserva: ''
            },
            tipos_documentos: [
                { text: 'Cédula', value: 'CC' },
                { text: 'Nit', value: 'NIT' }
            ],
            tipos_reservas: ['Cena', 'Almuerzo', 'Onces', 'Cumpleaños', 'Ocasión especial'],
            rules: []
        }
    },
    computed: {
        computedDateFormatted() {
            return this.formatDate(this.date)
        }
    },

    watch: {
        date(val) {
            this.dateFormatted = this.formatDate(this.date)
        },
        reservaEditada() {
            this.reserva = this.reservaEditada.id > 0 ? this.reservaEditada : this.reserva
            this.titulo = this.tituloEdit
        },

        tituloEdit() {
            this.titulo = this.tituloEdit != '' ? "Actualizar" : "Guardar"
        }
    },

    created() {
        //this.$toast.global.myerror('Error') ;
        if (this.reservaEditada != null) {
            this.reserva = this.reservaEditada
            this.titulo = this.tituloEdit != '' ? "Actualizar" : "Guardar"
        }
    },

    methods: {
        formatDate(date) {
            if (!date) return null
            const [year, month, day] = date.split('-')
            return `${month}/${day}/${year}`
        },

        parseDate(date) {
            if (!date) return null
            const [month, day, year] = date.split('/')
            return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
        },

        async creaReserva() {
            const id = this.reserva.id
            if (id != null) {
                try {
                    let resp = await this.$axios.$put(`http://localhost:8080/api/reservas/${id}`, this.reserva);
                    this.$toast.success(`Ok ${resp}`, {
                        icon: {
                            name: 'mdi-check-circle'
                        }, duration: 2000
                    })
                    this.$router.push('/reservas');
                } catch (error) {
                    this.$toast.error('Error al actualizar',
                        { icon:'mdi-alert-circle', duration: 2000 })
                }
            } else {
                try {
                    let resp = await this.$axios.$post('http://localhost:8080/api', this.reserva);
                    this.$toast.success(`Ok ${resp}`, {
                        icon: {
                            name: 'mdi-check-circle'
                        }, duration: 2000
                    })
                    this.$router.push('/');
                } catch (error) {
                    this.$toast.error('Error al guardar',
                        { icon:'mdi-alert-circle', duration: 2000 })
                }

            }
            this.$emit('cerrado', false);
        },

        async confirmarReserva(id) {
            let estado = true;
            try {
                let resp = await this.$axios.$put(`http://localhost:8080/api/reservasEstado/${id}?estado=${estado}`)
                this.$toast.success('Confirmada correctamente',
                    {
                        icon: 'mdi-check-circle', duration: 2000
                    })
            } catch (error) {
                this.$toast.error('Error al confirmar',
                    {  icon:'mdi-alert-circle', duration: 2000 })
            }
            this.$emit('cerrado', false);

        }
    }
}
</script>

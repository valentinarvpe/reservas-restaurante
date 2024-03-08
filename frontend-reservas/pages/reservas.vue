<template>
  <div>
    <v-toolbar flat class="grey lighten-2 ma-0">
      <v-toolbar-title>Dashboard</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
      <v-dialog v-model="dialogo" max-width="800px">
        <v-card>
          <v-card-title class="headline grey lighten-2">
            <span class="headline">{{ formTitle }}</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <FormularioComponent @cerrado="recibirEvento" :tituloEdit="tituloEdit" :reservaEditada="reservaEditada">
                </FormularioComponent>
              </v-layout>
            </v-container>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-card>
      <v-card-title>
        Reservas
        <v-spacer></v-spacer>
        <v-text-field v-model="buscar" append-icon="mdi-magnify" label="Buscar" single-line hide-details></v-text-field>
      </v-card-title>
      <v-card-text>
        <v-data-table :search="buscar" :headers="cabecera" :items="reservas" class="elevation-1" show-select
          :loading="loading">
          <template v-slot:loading>
            <v-skeleton-loader type="table-row@10"></v-skeleton-loader>
          </template>

          <template v-slot:items="props">
            <td class="text-xs-right">{{ props.item.nombres }}</td>
            <td class="text-xs-right">{{ props.item.apellidos }}</td>
            <td class="text-xs-right">{{ props.item.email }}</td>
            <td class="text-xs-right">{{ props.item.fecha_reserva }}</td>
            <td class="text-xs-right">{{ props.item.cantidad_personas }}</td>
          </template>

          <template v-slot:item.estado="{ item }">
            <v-tooltip bottom>
              <template v-slot:activator="{ on }">
                <v-icon class="me-2" size="x-large" v-if="item.estado" color="success">
                  mdi-check-circle
                </v-icon>
                <v-icon class="me-2" size="x-large" v-else="item.estado" color="warning">
                  mdi-alert-circle-outline
                </v-icon>
              </template>
              <span v-if="item.estado">Confirmado</span>
              <span v-else="item.estado">Sin confirmar</span>
            </v-tooltip>

          </template>

          <template v-slot:item.actions="{ item }">
            <v-icon class="me-2" size="small" @click="editar(item)">
              mdi-pencil
            </v-icon>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import reserva from '../models/reserva'
export default {
  //middleware: 'redirectToRow',
  data: () => ({
    dialogo: false,
    loading: false,
    tituloEdit: '',
    cabecera: [
      { text: 'Nombres', value: 'nombres' },
      { text: 'Apellidos', value: 'apellidos' },
      { text: 'Tipo Doc.', value: 'tipo_documento' },
      { text: 'Identificacion', value: 'identificacion' },
      { text: 'E-mail', value: 'email' },
      { text: 'Fecha reserva', value: 'fecha_reserva' },
      { text: 'Tipo reserva', value: 'tipo_reserva' },
      { text: 'Cant. Personas', value: 'cantidad_personas' },
      { text: 'Observaciones', value: 'observaciones' },
      { text: 'Estado', value: 'estado' },
      { text: 'Acciones', value: 'actions', sortable: false, align: 'center' }
    ],
    buscar: '',
    reservas: [],
    editedIndex: -1,
    reservaEditada: {
      nombres: '',
      apellidos: '',
      tipo_documento: 'CC',
      identificacion: '',
      email: '',
      fecha_reserva: '',
      observaciones: '',
      tipo_reserva: '',
      estado: null
    },
    defaultItem: {
      nombres: '',
      apellidos: '',
      tipo_documento: 'CC',
      identificacion: '',
      email: '',
      fecha_reserva: '',
      observaciones: '',
      tipo_reserva: '',
      estado: null
    }
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'Editar Registro' : 'Editar Registro'
    }
  },

  watch: {
    dialogo(val) {
      val || this.cerrar()
    }
  },

  created() {
    this.initialize()
  },

  methods: {
    initialize() {
      //console.log(reserva.asyncData());
      this.getReservas();
    },

    async getReservas() {
      this.reservas = await this.$axios.$get('http://localhost:8080/api')
    },

    editar(item) {
      this.editedIndex = this.reservas.indexOf(item)
      this.reservaEditada = Object.assign({}, item)
      this.tituloEdit = "Actualizar"
      this.dialogo = true
    },

    cerrar() {
      this.dialogo = false;
      this.refrescar();
    },

    recibirEvento(value) {
      this.dialogo = value;
      this.refrescar();
    },

    refrescar() {
      this.loading = true;
      this.getReservas();
      setTimeout(() => {
        this.loading = false
      }, 1000)
    }
  }
}
</script>
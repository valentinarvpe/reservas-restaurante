<template>
  <div>
      <v-dialog v-model="dialogo" max-width="800px">
        <v-card>
          <v-card-title class="headline grey lighten-2">
            <span class="headline">Editar registro</span>
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
    <v-card>
      <v-card-title>
        Reservas
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn color="success" icon v-on="on" @click="cargarConfirmadas()">
              <v-icon>
                mdi-check-circle
              </v-icon>
            </v-btn>
          </template>
          <span>Ver todas las confirmadas</span>
        </v-tooltip>
        <v-tooltip bottom>
          <template v-slot:activator="{ on }">
            <v-btn color="warning" icon v-on="on" @click="getReservas()">
              <v-icon>
                mdi-alert-circle
              </v-icon>
            </v-btn>
          </template>
          <span>Ver todas las confirmadas</span>
        </v-tooltip>
        <v-spacer></v-spacer>
        <v-text-field v-model="buscar" append-icon="mdi-magnify" label="Buscar" single-line hide-details></v-text-field>
      </v-card-title>
      <v-card-text>
        <v-data-table 
          :search="buscar"
          :expanded.sync="expanded"
           :headers="cabecera" 
           :items="reservas" 
           show-expand
           class="ma-0 elevation-1"
          :loading="loading">
          <template v-slot:loading>
            <v-skeleton-loader type="table-row@10"></v-skeleton-loader>
          </template>
          <template v-slot:expanded-item="{ headers, item }">
            <td :colspan="headers.length">
              Observaciones: {{ item.observaciones }}
            </td>
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
                  mdi-check-circle-outline
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
            <v-icon class="me-2" size="large" @click="editar(item)" :disabled="item.estado">
              mdi-pencil
            </v-icon>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  middleware: 'redirectToRow',
  data: () => ({
    dialogo: false,
    loading: false,
    expanded:[],
    tituloEdit: '',
    confirmadas: false,
    path: 'http://localhost:8080/api/reservasEstados?estado=',
    cabecera: [
      { text: 'Nombres', value: 'nombres', width: '10%' },
      { text: 'Apellidos', value: 'apellidos', width: '10%' },
      { text: 'Doc', value: 'tipo_documento', width: '10%' },
      { text: 'Id', value: 'identificacion', width: '10%' },
      { text: 'E-mail', value: 'email', width: '10%' },
      { text: 'Fecha reserva', value: 'fecha_reserva', width: '15%' },
      { text: 'Tipo', value: 'tipo_reserva', width: '10%' },
      { text: 'Cant', value: 'cantidad_personas', width: '8%' },
      { text: 'Est.', value: 'estado', width: '8%' },
      { text: 'Editar', value: 'actions', sortable: false, width: '5%' }
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
    }
  }),

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
      this.getReservas();
    },

    async getReservas() {
      this.reservas = await this.$axios.$get(`${this.path}${false}`)
    },

    async cargarConfirmadas() {
      this.confirmadas = true;
      this.reservas = await this.$axios.$get(`http://localhost:8080/api/reservasEstados?estado=${true}`)
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
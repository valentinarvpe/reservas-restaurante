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
            <v-btn color="warning" icon v-on="on" @click="cargarDefault()">
              <v-icon>
                mdi-alert-circle
              </v-icon>
            </v-btn>
          </template>
          <span>Ver todas las no confirmadas</span>
        </v-tooltip>
        <v-spacer></v-spacer>
        <v-text-field v-model="buscar" append-icon="mdi-magnify" label="Buscar" single-line hide-details></v-text-field>
      </v-card-title>
      <v-card-text>
        <v-data-table :search="buscar" :expanded.sync="expanded" :headers="cabecera" :items="reservas" show-expand
          class="ma-0 elevation-1" :loading="loading">
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
            <td class="text-xs-right">{{ props.item.fechaReserva }}</td>
            <td class="text-xs-right">{{ props.item.cantidadPersonas }}</td>
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
import reserva from '../models/reserva';

export default {
  model: reserva,
  middleware: 'redirecciona',
  data: () => ({
    dialogo: false,
    auth: false,
    loading: false,
    expanded: [],
    tituloEdit: '',
    cabecera: [
      { text: 'Nombres', value: 'usuario.nombres', width: '10%' },
      { text: 'Apellidos', value: 'usuario.apellidos', width: '10%' },
      { text: 'Doc', value: 'usuario.tipo_documento', width: '10%' },
      { text: 'Id', value: 'usuario.identificacion', width: '10%' },
      { text: 'E-mail', value: 'usuario.email', width: '10%' },
      { text: 'Fecha reserva', value: 'fechaReserva', width: '15%' },
      { text: 'Tipo', value: 'tipoReserva', width: '10%' },
      { text: 'Cant', value: 'cantidadPersonas', width: '8%' },
      { text: 'Est.', value: 'estado', width: '8%' },
      { text: 'Editar', value: 'actions', sortable: false, width: '5%' }
    ],
    buscar: '',
    reservas: [],
    editedIndex: -1,
    reservaEditada: {
      usuario: {
        nombres: '',
        apellidos: '',
        tipo_documento: 'CC',
        identificacion: '',
        email: '',
      },
      fechaReserva: '',
      observaciones: '',
      tipoReserva: '',
      estado: null
    }
  }),

  watch: {
    dialogo(val) {
      val || this.cerrar()
    }
  },
  beforeMount() {
    this.auth = localStorage.getItem("isAuth");
    if (this.auth) {
      //this.$axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("token") ;
      this.cargarDefault();
    } else {
      this.$router.push('/');
    }
  },

  methods: {
    async cargarDefault() {
      this.reservas = await reserva.getReservas(false);
    },

    async cargarConfirmadas() {
      this.reservas = await await reserva.getReservas(true);
    },

    editar(item) {
      this.editedIndex = this.reservas.indexOf(item);
      this.reservaEditada = Object.assign({}, item);
      this.tituloEdit = "Actualizar";
      this.dialogo = true;
    },

    cerrar() {
      this.dialogo = false;
      this.refrescar();
    },

    recibirEvento(value) {
      this.refrescar();
      this.dialogo = value;
    },

    refrescar() {
      this.cargarDefault();
      this.loading = true;
      setTimeout(() => {
        this.loading = false
      }, 900)
    }
  }
}
</script>
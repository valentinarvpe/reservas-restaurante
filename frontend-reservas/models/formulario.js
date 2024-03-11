import axios from 'axios';
axios.defaults.baseURL = 'http://157.245.127.16:8080';
/*if (process.client) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("token");
}
*/
export default {
    tipos_documentos: [
        { text: 'Cédula', value: 'CC' },
        { text: 'Nit', value: 'NIT' }
    ],
    tipos_reservas: ['Cena', 'Almuerzo', 'Onces', 'Cumpleaños', 'Ocasión especial'],

    async actualizar(id, reserva) {
        const resp = await axios.put(`/api/reservas/${id}`, reserva,
        {
            headers:{
                Authorization:'Bearer ' + localStorage.getItem("token")
            }
        }
        );
        return resp;
    },

    async guardar(reserva) {
        return await axios.post('/api/reservas', reserva);
    }
}
const ENDPOINT_PATH = "/api/reservas/porEstado?estado=";
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8080';
if (process.client) {
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem("token") ;
}
export default {
   async getReservas(estado){
    const response = await axios.get(`${ENDPOINT_PATH}${estado}`);
     return response.data;
    }
};
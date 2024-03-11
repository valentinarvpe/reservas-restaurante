import axios from 'axios';
const ENDPOINT_PATH = "/api/reservas/porEstado?estado=";
axios.defaults.baseURL = 'http://localhost:8080';
export default {
    async login(usuario) {
        const response = await axios.post("/login", usuario)
        return response.data;
    }
};
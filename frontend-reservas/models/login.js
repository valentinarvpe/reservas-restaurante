import axios from 'axios';
axios.defaults.baseURL = process.env.API_URL || 'http://localhost:8080';
export default {
    async login(usuario) {
        const response = await axios.post("/login", usuario)
        return response.data;
    }
};
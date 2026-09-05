import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8082', // Porta onde o seu Spring Boot roda
});

export default api;
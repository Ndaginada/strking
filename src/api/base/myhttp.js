import  axios from "axios";

const myhttp = axios.create({
    baseURL: 'http://gateway:3333'
});

export default myhttp
import  axios from "axios";

/**
 * axios
 * @type {AxiosInstance}
 */
const myhttp = axios.create({
    baseURL: 'http://gateway:3333'
});
//统一在请求头中加 Authorization
myhttp.interceptors.request.use(
    config => {
        const token = window.localStorage.getItem("strking_token");
        if(token){
            config.headers['Authorization'] = token;
        }
        return config;
    }, error => {
    console.log(error);
});
myhttp.interceptors.response.use(
    res => {

       return res;

    }, error => {
    console.log(error);
});
export default myhttp
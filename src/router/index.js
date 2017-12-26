import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/components/Login'
import Register from '@/components/Register'
Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path:'/register',
        name:'Register',
        component:Register
     }
    ]
});
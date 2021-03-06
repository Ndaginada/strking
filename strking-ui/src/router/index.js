import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/components/Login'
import Register from '@/components/Register'
import HomePage from '@/components/HomePage'
import NotFoundComponent from '@/components/NotFoundComponent'
import ArticleDetail from '@/components/home/ArticleDetail'
import Me_ChangeInfo from '@/components/home/Me_ChangeInfo'
import Me_ChangePass from '@/components/home/Me_ChangePass'
import ArticleIndex from '@/components/home/ArticleIndex'
Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '*',
            component: NotFoundComponent
        }, {
            path: '/',
            name: 'Login',
            component: Login
        }, {
            path: '/login',
            name: 'Login',
            component: Login
        }, {
            path: '/register',
            name: 'Register',
            component: Register
        }, {
            path: '/home',
            name: 'HomePage',
            component: HomePage,
            meta: {
                requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
            },
        }, {
            path: "/articlDetail",
            name: 'ArticleDetail',
            component: ArticleDetail,
            meta: {
                requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
            }
        }, {
            path: "/changeInfo",
            name: 'Me_ChangeInfo',
            component: Me_ChangeInfo,
            meta: {
                requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
            }
        }, {
            path: "/changePass",
            name: 'Me_ChangePass',
            component: Me_ChangePass,
            meta: {
                requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
            }
        }

    ]
});
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
        const token = window.localStorage.getItem("strking_token");
        console.log(token);
        if (token) {
            next();
        }
        else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
            })
        }
    }
    else {
        next();
    }
});
export default router
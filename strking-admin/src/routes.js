import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import UserList from './views/nav1/UserList.vue'
import Form from './views/nav1/Form.vue'
import adminList from './views/nav1/adminList.vue'
import ArticleList from './views/nav2/ArticleList.vue'
import addRecommend from './views/nav2/addRecommend.vue'
import RecommendList from './views/nav2/RecommendList.vue'
import TypeList from './views/nav3/TypeList.vue'
import echarts from './views/charts/echarts.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
        name: '用户管理',
        // iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/main', component: Main, name: '主页', hidden: true },
            { path: '/userList', component: UserList, name: '普通用户信息管理' },
            { path: '/addAdmin', component: Form, name: '创建管理员' },
             { path: '/adminList', component: adminList, name: '管理员列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
        name: '内容管理',
        children: [
            { path: '/articleList', component: ArticleList, name: '文章列表' },
            { path: '/addRecommend', component: addRecommend, name: '添加推荐视频' },
            { path: '/recommendList', component: RecommendList, name: '推荐视频列表' }
        ]
    },
    {
        path: '/',
        component: Home,
        requireAuth: true,  // 添加该字段，表示进入这个路由是需要登录的
        name: '',
        leaf: true,//只有一个节点
        children: [
            { path: '/typeList', component: TypeList, name: '分类管理' }
        ]
    },
    // {
    //     path: '/',
    //     component: Home,
    //     name: 'Charts',
    //     iconCls: 'fa fa-bar-chart',
    //     children: [
    //         { path: '/echarts', component: echarts, name: 'echarts' }
    //     ]
    // },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;
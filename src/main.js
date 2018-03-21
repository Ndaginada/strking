import Vue from 'vue';
import App from './app';
import MintUI from "mint-ui";
import 'mint-ui/lib/style.css';
import router from './router' // 路由

import {
    Field,
    Indicator,
    Toast,
    Cell,
    Swipe,
    SwipeItem,
    Tabbar,
    TabItem,
    MessageBox,
    Loadmore,
    InfiniteScroll,
    Lazyload,
    CellSwipe,
    Badge,
    Spinner
} from 'mint-ui';

import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui';
Vue.use(ElementUI);


Vue.use(MintUI);
Vue.prototype.Indicator = Indicator;
Vue.prototype.Toast = Toast;

Vue.component(Field.name, Field);
Vue.component(Cell.name, Cell);
Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);
Vue.component(Tabbar.name, Tabbar);
Vue.component(TabItem.name, TabItem);
Vue.component(Loadmore.name, Loadmore);
Vue.component(CellSwipe.name, CellSwipe);
Vue.component(Badge.name, Badge);
Vue.component(Spinner.name, Spinner);
Vue.use(InfiniteScroll);
Vue.use(Lazyload);

let theVue = new Vue({ // eslint-disable-line
    el: '#app',
    router,
    render: h => h(App)
});

//全局改变vue
window.GloableVue = theVue;

import Vue from 'vue';
import App from './app';
import MintUI from "mint-ui";
import 'mint-ui/lib/style.css';
import router from './router' // 路由

import {Field,Indicator,Toast,Cell,Swipe,SwipeItem,Tabbar, TabItem} from 'mint-ui';

Vue.use(MintUI);
Vue.prototype.Indicator = Indicator;
Vue.prototype.Toast = Toast;

Vue.component(Field.name, Field);
Vue.component(Cell.name, Cell);
Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);
Vue.component(Tabbar.name, Tabbar);
Vue.component(TabItem.name, TabItem);

let theVue = new Vue({ // eslint-disable-line
    el: '#app',
    router,
    render: h => h(App)
});

//全局改变vue
window.GloableVue = theVue;

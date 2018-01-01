import Vue from 'vue';
import App from './app';
import MintUI from "mint-ui";
import 'mint-ui/lib/style.css';
import router from './router' // 路由

import {Field,Indicator,Toast,Cell} from 'mint-ui';

Vue.use(MintUI);
Vue.prototype.Indicator = Indicator;
Vue.prototype.Toast = Toast;

Vue.component(Field.name, Field);
Vue.component(Cell.name, Cell);

new Vue({ // eslint-disable-line
    el: '#app',
    router,
    render: h => h(App)
});

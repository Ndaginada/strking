import Vue from 'vue';
import App from './app';
import MintUI from 'mint-ui';
import 'mint-ui/lib/style.css';
import router from './router' // 路由

import { Field } from 'mint-ui';

Vue.use(MintUI);
Vue.component(Field.name, Field);

new Vue({ // eslint-disable-line
    el: '#app',
    router,
    render: h => h(App)
});

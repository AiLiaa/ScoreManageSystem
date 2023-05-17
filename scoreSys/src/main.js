import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'

import global_msg from './global/global.js' //将global.js引入
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.prototype.$global_msg = global_msg;//使用$global_msg作为global_msg的原型名
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use( VueAxios, axios)

//使用axios
Vue.prototype.$axios = axios;

//超时设置
axios.defaults.timeout = 8000;

axios.defaults.headers = {
  'Content-Type': 'application/x-www-form-urlencoded',
  Accept: 'application/json',
  //'Content-Type': 'multipart/form-data'
};

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

import Vue from 'vue'
import App from './App.vue'

//axios导入
import axios from "axios";
Vue.prototype.$axios = axios;

//vue-router导入
import VueRouter from "vue-router";
Vue.use(VueRouter);
import router from './router/index';

//element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

//关闭生产提示
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')

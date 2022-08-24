import Vue from 'vue'
import App from './App.vue'
// axios、request请求
import request from "@/utils/request"

//axios导入
import axios from "axios";
axios.defaults.baseURL = 'http://localhost:8081';//服务器地址
Vue.prototype.$axios = axios;

//vue-router导入
import VueRouter from "vue-router";
Vue.use(VueRouter);
//引入路由器
import router from './router/index';

//element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

//mavon-editor
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)

//vue-video-player导入
import VideoPlayer from 'vue-video-player'
require('vue-video-player/node_modules/video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')
Vue.use(VideoPlayer)

//关闭生产提示
Vue.config.productionTip = false
Vue.prototype.request=request
new Vue({
  render: h => h(App),
  router: router,
  beforeCreate() {
    Vue.prototype.$bus = this;
  }
}).$mount('#app')

import VueRouter from 'vue-router'

//用户主页
import Home from "@/components/user/Home";

const router = new VueRouter({
    routes: [
        {
            //用户主页
            name: 'home',
            path: '/home',
            component: Home,
            meta: {title: '欢迎访问在线教育系统!', changeTitle: true}
        }
    ]
});


//更新网页标题
router.afterEach((to, from) => {
    if (to.meta.changeTitle) {
        document.title = to.meta.title || '欢迎访问在线教育系统!';
    }
})


export default router;
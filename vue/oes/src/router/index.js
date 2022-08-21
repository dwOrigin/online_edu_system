import VueRouter from 'vue-router';
import Vue from "vue";

//用户主页
import Home from "@/components/user/Home";
import PersonalCenter from "@/components/user/personal/PersonalCenter";
import CourserSearchLayout from "@/components/user/course/CourserSearchLayout";
import CourseRecommend from "@/components/user/course/CourseRecommend";
import CourseMainLayout from "@/components/user/course/CourseMainLayout";
import TeacherCenter from "@/components/user/Teacher/TeacherCenter";
import TeacherPage from "@/components/user/Teacher/TeacherPage";
import QAPMain from "@/components/user/qap/QAPMain";

const router = new VueRouter({
    routes: [
        {
            //用户主页
            name: 'home',
            path: '/home',
            component: Home,
            meta: {title: '欢迎访问在线教育系统!', changeTitle: true},
            children:[
                {
                    //个人中心
                    name: 'personal',
                    path: '/home/personal',
                    component: PersonalCenter,
                    meta: {title: '个人中心', changeTitle: true},
                    beforeEnter(to, from, next){
                        console.log('路由到个人中心');
                        //必须先登录才能跳转
                        let user = window.localStorage.getItem('user');
                        if(user != null){
                            next();
                        }else{
                            Vue.prototype.$message.info('请先登录或注册账号');
                        }
                    }
                },
                {
                    //搜索结果页面
                    name: 'search',
                    path: '/home/search/:select?/:searchKey?',
                    component: CourserSearchLayout,
                    meta: {title: '搜索课程', changeTitle: true}
                },
                {
                    //主页推荐课程页面 || 登录注册
                    name: 'recommend',
                    path: '/home/recommend',
                    component: CourseRecommend,
                    meta: {changeTitle: false}
                },
                {
                    //课程播放页面
                    name: 'course',
                    path: 'home/course/:courseId?',
                    component: CourseMainLayout,
                    meta: {title: '课程播放', changeTitle: true}
                },
                {
                    //讲师中心页面
                    name: 'teacher',
                    path: 'home/teacher',
                    component: TeacherCenter,
                    meta: {title: '讲师中心', changeTitle: true}
                },
                {
                    //单独讲师页面
                    name: 'teacherPage',
                    path: 'home/teacherPage/:teacherId?',
                    component: TeacherPage,
                    meta: {changeTitle: false}
                },
                {
                    //问答和文章页面
                    name: 'qap',
                    path: 'home/qap',
                    component: QAPMain,
                    meta: {title: '问答和文章', changeTitle: true}
                }
            ]
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
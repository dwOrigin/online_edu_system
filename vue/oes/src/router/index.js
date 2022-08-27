import VueRouter from 'vue-router';
import Vue from "vue";

import Home from "@/components/user/Home";
import PersonalCenter from "@/components/user/personal/PersonalCenter";
import CourserSearchLayout from "@/components/user/course/CourserSearchLayout";
import CourseRecommend from "@/components/user/course/CourseRecommend";
import CourseMainLayout from "@/components/user/course/CourseMainLayout";
import TeacherCenter from "@/components/user/Teacher/TeacherCenter";
import TeacherPage from "@/components/user/Teacher/TeacherPage";
import QAPMain from "@/components/user/qap/QAPMain";
import PInfo from "@/components/user/personal/PInfo";
import History from "@/components/user/personal/History";
import Star from "@/components/user/personal/Star";
import MessageCenter from "@/components/user/personal/MessageCenter";
import MyQuestion from "@/components/user/personal/MyQuestion";
import Article_Manage from "@/views/Article_Manage";
import Lesson_Manage from "@/views/Lesson_Manage";
import Member_Manage from "@/views/Member_Manage";
import QA_Manage from "@/views/QA_Manage";
import Teacher_Manage from "@/views/Teacher_Manage";
import Notice_Manage from"@/views/Notice_Manage";
import Add_Article_Manage from"@/views/Add_Article_Manage";
import Add_Lesson_Manage from "@/views/Add_Lesson_Manage";
import Modify_Lesson_Manage from "@/views/Modify_Lesson_Manage";
import Modify_Article_Manage from "@/views/Modify_Article_Manage";
import Modify_QA_Manage from "@/views/Modify_QA_Manage";

import QuestionPage from "@/components/user/qap/QuestionPage";
import PassagePage from "@/components/user/qap/PassagePage";


const router = new VueRouter({
    routes: [
        {
            path:'/',
            redirect:'home'
        },
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
                        //必须先登录才能跳转
                        let user = window.localStorage.getItem('user');
                        if(user != null){
                            next();
                        }else{
                            Vue.prototype.$message.info('请先登录或注册账号');
                        }
                    },
                    children:[
                        {path:'/home/personal/pinfo',component:PInfo,meta: {title: '个人中心', changeTitle: true}},
                        {path:'/home/personal/history',component:History,meta: {title: '个人中心', changeTitle: true}},
                        {path:'/home/personal/star',component:Star,meta: {title: '个人中心', changeTitle: true}},
                        {path:'/home/personal/message',component:MessageCenter,meta: {title: '个人中心', changeTitle: true}},
                        {path:'/home/personal/question',component:MyQuestion,meta: {title: '个人中心', changeTitle: true}}
                    ]
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
                    meta: {title: '欢迎访问在线教育系统!', changeTitle: true},
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
                    path: 'home/qap/:select?',
                    component: QAPMain,
                    meta: {title: '问答和文章', changeTitle: true}
                },
                {
                    //问题详情页面
                    name: 'questionPage',
                    path: 'home/questionPage',
                    component: QuestionPage,
                    meta: {changeTitle: false}
                },
                {
                    //文章详情页面
                    name: 'passagePage',
                    path: 'home/passagePage',
                    component: PassagePage,
                    meta: {changeTitle: false}
                }
            ]
        },


        {
            name:'article_manage',
            path:'/article_manage',
            component:Article_Manage,
            meta:{title:'文章管理', changeTitle: true}
        },
        {
            name:'lesson_manage',
            path:'/lesson_manage',
            component:Lesson_Manage,
            meta:{title:'课程管理', changeTitle: true}
        },
        {
            name:'member_manage',
            path:'/member_manage',
            component:Member_Manage,
            meta:{title:'用户管理', changeTitle: true}
        },
        {
            name:'qa_manage',
            path:'/qa_manage',
            component:QA_Manage,
            meta:{title:'问答管理', changeTitle: true}
        },
        {
            name:'teacher_manage',
            path:'/teacher_manage',
            component:Teacher_Manage,
            meta:{title:'讲师管理', changeTitle: true}
        },
        {
            name:'notice_manage',
            path:'/notice_manage',
            component:Notice_Manage,
            meta:{title:'通知管理',changeTitle:true}
        },
        {
            name:'add_article_manage',
            path:'/add_article_manage',
            component:Add_Article_Manage,
            meta:{title:'增加文章',changeTitle:true}
        },
        {
            name:'add_lesson_manage',
            path:'/add_lesson_manage',
            component:Add_Lesson_Manage,
            meta:{title:'增加课程',changeTitle:true}
        },
        {
            name:'modify_lesson_manage',
            path:'/modify_lesson_manage',
            component:Modify_Lesson_Manage,
            meta:{title:'课程详情',changeTitle:true}
        },
        {
            name:'modify_article_manage',
            path:'/modify_article_manage',
            component:Modify_Article_Manage,
            meta:{title:'文章详情',changeTitle:true}
        },
        {
            name:'modify_qa_manage',
            path:'/modify_qa_manage',
            component:Modify_QA_Manage,
            meta:{title:'问答详情',changeTitle:true}

        }
    ]
});


//更新网页标题
router.afterEach((to, from) => {
    if (to.meta.changeTitle) {
        document.title = to.meta.title ;
    }
})


export default router;

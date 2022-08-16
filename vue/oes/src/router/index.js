import VueRouter from 'vue-router'

//用户主页
import Home from "@/components/user/Home";
import Article_Manage from "@/views/Article_Manage";
import Lesson_Manage from "@/views/Lesson_Manage";
import Member_Manage from "@/views/Member_Manage";
import QA_Manage from "@/views/QA_Manage";
import Teacher_Manage from "@/views/Teacher_Manage";


const router = new VueRouter({
    routes: [
        {
            path:'/',
            redirect:'member_manage'
        },
        {
            //用户主页
            name: 'home',
            path: '/home',
            component: Home,
            meta: {title: '欢迎访问在线教育系统!', changeTitle: true}
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
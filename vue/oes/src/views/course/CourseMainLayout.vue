<template>
  <div class="main">
    <el-col :span="15" :offset="1">
      <play-header></play-header>
      <play-window></play-window>
      <play-window-footer></play-window-footer>
    </el-col>
    <el-col :span="7" :offset="1" style="overflow: visible">
      <play-teacher-header></play-teacher-header>
      <play-list></play-list>
    </el-col>
  </div>
</template>

<script>
import PlayHeader from "@/views/course/PlayHeader";
import PlayTeacherHeader from "@/views/course/PlayTeacherHeader";
import PlayWindow from "@/views/course/PlayWindow";
import PlayList from "@/views/course/PlayList";
import PlayWindowFooter from "@/views/course/PlayWindowFooter";

export default {
  name: "CourseMainLayout",
  components: {
    PlayHeader,
    PlayTeacherHeader,
    PlayWindow,
    PlayList,
    PlayWindowFooter,
  },
  methods:{
     refreshCourse(){
      // let promise = this.$axios({
      //     url: '',
      //     method: 'post',
      //     data:{
      //       id: this.$route.params.courseId
      //     }
      // })
      let promise = new Promise((a)=>{
          a({
              data:{
                course:{
                  teacherId: 5555,
                  title: '【原神】胡桃新皮肤！超美夏日礼服——「朝露晴空」',
                  viewCnt: '171.9万',
                  commentCnt: '1163',
                  grade: 3,//评分, 满分为5
                  courseId: 9898,
                  updateTime: '2022-06-18 11:30:00',
                  //分集信息
                  chapters:[
                    {title: '这是第1集', url: 'https://klxxcdn.oss-cn-hangzhou.aliyuncs.com/histudy/hrm/media/bg2.mp4', order: 1, length: '12:16'},
                    {title: '这是第2集', url: 'https://klxxcdn.oss-cn-hangzhou.aliyuncs.com/histudy/hrm/media/bg2.mp4', order: 2, length: '18:19'},
                    {title: '这是第3集', url: 'https://klxxcdn.oss-cn-hangzhou.aliyuncs.com/histudy/hrm/media/bg2.mp4', order: 3, length: '18:36'}
                  ],
                  //课程介绍
                  intro: '换句话来说，你首先需要做的就是：明确机构/课程定位和目标客户人群。任何机构想要获得增长， 关键就是了解自己的客户。他们的痛点是什么？你的课程方案可以解决他们什么样的教育问题？ 这些内容都需要映射在你的课程介绍中。在写课程名称时，只需要组合课程的核心内容和适用对象， 外加语言的修饰和润色即可（务必简介易懂）。如果机构伙伴们对课程还没有准确的定位， 首先要思考定位及想覆盖的客户人群。尽量用简洁的短语搭配来覆盖目标人群，如“专升本”、“职场新人”、 “雅思零基础”、“摩托驾照周末班”、“10到15岁”等'
                }
              }
          });
      });
      promise.then((res)=>{
        this.$bus.$emit('courseChanged', res.data.course);
      }).catch((err)=>{
          this.$message.error('你的网络迷路了, 课程加载失败!');
      });
    }
  },
  mounted() {
    this.refreshCourse();
  }
}
</script>

<style scoped>
.main {
  /*播放页面宽度需要大一点(主页面1100px+200px)*/
  width: 1300px;
  margin: 20px auto;
  height: 800px;
}
</style>
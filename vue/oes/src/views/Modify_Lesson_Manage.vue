<template>
<div id="main" class="fragment-container">
  <div class="btn">
      <el-button type="primary" round @click="returnLesson">返回</el-button>
    </div>   
 <course-main-layout></course-main-layout>
</div>
</template>

<script>
import CourseMainLayout from "../views/course/CourseMainLayout.vue";
export default {
  name: "Modify_Lesson_Manage",
  components:{
    CourseMainLayout,
  },
  methods:{
    returnLesson(){
        this.$router.push('/lesson_manage')
    },
     refreshCourse(){
      let promise = this.$axios({
          url: '/course/getById',
          method: 'get',
          params:{
            id: this.$route.params.courseId
          }
      })
      promise.then((res)=>{
        this.$bus.$emit('courseChanged1', res.data);
      }).catch((err)=>{
          this.$message.error('你的网络迷路了, 课程加载失败!');
      });
    }
  },
  mounted(){
    this.refreshCourse();
  }
}
</script>

<style scoped>
#main{
  width: 100%;
}
.btn{
    margin-top: 20px;
    margin-left: 50px;
}
.fragment-container{
  height: 100vh;
  overflow-y: scroll;
  max-width: 100vw;
  margin: 0 auto;
}
</style>
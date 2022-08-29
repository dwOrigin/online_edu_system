<template>
  <div class="play-recommend-card">
    <div style="margin: 10px auto">相关课程推荐</div>
    <course-card v-for="obj in courses" direction="row" :course="obj">
    </course-card>
  </div>
</template>

<script>
import CourseCard from "@/components/user/course/CourseCard";
import axios from "axios";

export default {
  name: "PlayRecommend",
  components: {
    CourseCard
  },
  data(){
      return {
        courses: [],
        course:{}
      };
  },
  mounted() {
    this.$bus.$on('courseChanged', (course) => {
       this.course=course;
        //通过课程id 获取<10门推荐课程
        let promise = this.$axios({
            url: '/course/typeRecommend',
            method: 'get',
            params: {courseId: course.courseId}
        });
        promise.then((res)=>{
          console.log(res.data);
          //测试用, 要删
          this.courses = res.data;
        }).catch((err)=>{
            this.$message.error('你的网络迷路了');
        });
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged');
  }
}
</script>

<style scoped>
.play-recommend-card {
  display: flex;
  flex-direction: column;
  margin-top: 90px;
  border-radius: 5px;
  height: max-content;
  /*background-color: teal;*/
}
</style>
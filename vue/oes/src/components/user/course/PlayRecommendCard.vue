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
        courses: []
      };
  },
  mounted() {
    this.$bus.$on('courseChanged', (course) => {
        //通过课程id 获取<10门推荐课程
        // let promise = this.$axios({
        //     url: '',
        //     method: '',
        //     data: {courseId: course.courseId}
        // };
        let promise = new Promise((a)=>{
            a({
                data:{
                  courses:[
                    {
                      id: 9999,
                      imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                      title: '[量子] 一秒钟一个单词 一小时考过四级! 你值得拥有',
                      teacher: 'bilbil大学',
                      chapterNum: 16,
                      studentNum: 10086,
                      likeRate: 100
                    }
                  ]
                }
            });
        });
        promise.then((res)=>{
          //测试用, 要删
          let list = res.data.courses;
          list = [...list, ...list];
          this.courses = list;
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
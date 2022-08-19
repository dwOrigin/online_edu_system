<template>
<div id="main">
  <router-view></router-view>
</div>
</template>

<script>
import QAPMain from "@/components/user/qap/QAPMain";
import CourseRecommend from "@/components/user/course/CourseRecommend";
import courserSearchLayout from "@/components/user/course/CourserSearchLayout";
import CourseMainLayout from "@/components/user/course/CourseMainLayout";
import PersonalCenter from "@/components/user/personal/PersonalCenter";
import TeacherCenter from "@/components/user/Teacher/TeacherCenter";
import TeacherPage from "@/components/user/Teacher/TeacherPage";
import QuestionPage from "@/components/user/qap/QuestionPage";
import PassagePage from "@/components/user/qap/PassagePage";
export default {
  name: "HomeMain",
  components:{
  },
  mounted() {
    // this.$router.replace({
    //   name: 'recommend'
    // });
  }
}
</script>

<style scoped>
#main{
  width: 100%;
}
</style>
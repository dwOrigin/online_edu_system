<template>
<div id="main">
  <course-recommend></course-recommend>
<!--<courser-search-layout></courser-search-layout>-->
<!--  <course-main-layout></course-main-layout>-->
<!--<q-a-p-main></q-a-p-main>-->
<!--  <personal-center></personal-center>-->
<!--  <teacher-center></teacher-center>-->
<!--  <teacher-page></teacher-page>-->
<!--  <question-page></question-page>-->
<!--  <passage-page></passage-page>-->
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
    CourseMainLayout,
    CourseRecommend,
    courserSearchLayout,
    QAPMain,
    PersonalCenter,
    TeacherCenter,
    TeacherPage,
    QuestionPage,
    PassagePage
  }
}
</script>

<style scoped>
#main{
  width: 100%;
}
</style>
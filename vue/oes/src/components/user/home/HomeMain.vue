<template>
<div id="main">
<!--  <course-recommend></course-recommend>-->
<!--<courser-search-layout></courser-search-layout>-->
<!--  <course-main-layout></course-main-layout>-->
<!--<q-a-p-main></q-a-p-main>-->
  <personal-center></personal-center>
</div>
</template>

<script>
import QAPMain from "@/components/user/qap/QAPMain";
import CourseRecommend from "@/components/user/course/CourseRecommend";
import courserSearchLayout from "@/components/user/course/CourserSearchLayout";
import CourseMainLayout from "@/components/user/course/CourseMainLayout";
import PersonalCenter from "@/components/user/personal/PersonalCenter";
export default {
  name: "HomeMain",
  components:{
    CourseMainLayout,
    CourseRecommend,
    courserSearchLayout,
    QAPMain,
    PersonalCenter
  }
}
</script>

<style scoped>
#main{
  width: 100%;
}
</style>
<template>
<div id="main">
 <course-main-layout></course-main-layout>
</div>
</template>

<script>
import CourseMainLayout from "../views/course/CourseMainLayout.vue";
export default {
  name: "Modify_Lesson_Manage",
  components:{
    CourseMainLayout,
  }
}
</script>

<style scoped>
#main{
  width: 100%;
}
</style>
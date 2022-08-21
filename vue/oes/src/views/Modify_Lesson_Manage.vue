<template>
<div id="main">
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
    }
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
</style>
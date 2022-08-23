<template>
  <div class="play-recommend-card">
    <div style="margin: 10px auto">相关课程推荐</div>
    <course-card v-for="obj in 10" :key="obj" direction="row">
    </course-card>
  </div>
</template>

<script>
import CourseCard from "@/components/user/course/CourseCard";

export default {
  name: "PlayRecommend",
  components: {
    CourseCard
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
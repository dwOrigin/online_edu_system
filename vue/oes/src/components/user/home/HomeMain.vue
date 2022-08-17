<template>
<div id="main">
<!--  <course-search-result></course-search-result>-->
  <course-recommend></course-recommend>
</div>
</template>

<script>
import CourseRecommend from "@/components/user/course/CourseRecommend";
import CourseSearchResult from "@/components/user/course/CourseSearchResult";
export default {
  name: "HomeMain",
  components:{
    CourseRecommend,
    CourseSearchResult
  }
}
</script>

<style scoped>
#main{
  margin: 0 auto;
  width: 1100px;
}
</style>
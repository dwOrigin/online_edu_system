<template>
<div class="main">
  <bread-crumb></bread-crumb>
  <course-search-result></course-search-result>
</div>
</template>

<script>
import courseSearchResult from "@/components/user/course/CourseSearchResult";
import BreadCrumb from "@/components/user/home/BreadCrumb";
export default {
  name: "CourserSearchLayout",
  components:{
    courseSearchResult,
    BreadCrumb
  }
}
</script>

<style scoped>
.main{
  margin: 20px auto;
  width: 1100px;
}
</style>
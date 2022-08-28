<template>
  <div class="main">
    <course-search-result></course-search-result>
  </div>
</template>

<script>
import courseSearchResult from "@/components/user/course/CourseSearchResult";

export default {
  name: "CourserSearchLayout",
  components: {
    courseSearchResult,
  },
  mounted() {
    this.$bus.$emit('searchInputChanged',this.$route.params.select, this.$route.params.searchKey);
  }
}
</script>

<style scoped>
.main {
  margin: 20px auto;
  width: 1100px;
}
</style>
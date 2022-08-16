<template>
<div class="history">
  <div class="header">
    <el-input placeholder="搜索历史记录" v-model="input3" class="input-with-select">
      <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
  </div>
  <div class="content">
    <course-record v-for="obj in 10" :key="obj"></course-record>
  </div>
</div>
</template>

<script>
import CourseRecord from "@/components/user/personal/CourseRecord";
export default {
  name: "History",
  components: {
    CourseRecord
  }
}
</script>

<style scoped>
.header{
  display: flex;
}
</style>
<template>
  <div class="teacher-center">
    <div class="header">
      <div class="searchInput">
        <el-input placeholder="搜索你喜欢的讲师" v-model="input3" class="input-with-select">
          <el-button slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </div>
      <div class="space-between">
        <div>全部讲师</div>
        <div style="font-weight: lighter; color: #99a9bf; font-size: small">共找到
          <span style="font-weight: bold; color: teal">321</span>个搜索结果
        </div>
      </div>
    </div>
    <div class="search-result">
      <teacher-card v-for="obj in 18" :key="obj"></teacher-card>
    </div>
    <div class="page">
      <el-pagination
          background
          layout="prev, pager, next"
          :total="1000">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import TeacherCard from "@/components/user/Teacher/TeacherCard";
export default {
  name: "TeacherCenter",
  components:{
    TeacherCard
  }
}
</script>

<style scoped>
.page{
  text-align: center;
}
.search-result{
  display: flex;
  flex-wrap: wrap;
  margin: 40px auto 20px auto;
  /*background-color: teal;*/
  justify-content: center;
}
.searchInput{
  width: 50%;
  margin: 0 auto;
}
.space-between{
  margin-top: 50px;
  display: flex;
  justify-content: space-between;
}
.header {
  margin: 10px auto;
}

.teacher-center {
  width: 1000px;
  margin: 50px auto 0 auto;
}
</style>
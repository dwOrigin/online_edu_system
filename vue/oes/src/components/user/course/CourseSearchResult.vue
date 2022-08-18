<template>
<!--  <div style="width: 1100px; margin: 0 auto;">-->
    <div class="main">
      <div class="space-between">
        <div><h5 style="color: #409EFF">搜索结果</h5></div>
        <div style="font-weight: lighter; color: #99a9bf; font-size: small">共找到
          <span style="font-weight: bold; color: teal">321</span>个搜索结果
        </div>
      </div>
      <div>
        <el-link :underline="false" class="sort-tag" style="margin-left: 0" href="#">综合</el-link>
        <el-link :underline="false" class="sort-tag" href="#">好评率</el-link>
        <el-link :underline="false" class="sort-tag" href="#">人气</el-link>
      </div>
      <div class="result-table">
        <course-card v-for="course in 20" :key="course">
        </course-card>
      </div>
      <div >
        <el-pagination
            background
            class="page-controller"
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>
      </div>

    </div>
<!--  </div>-->

</template>

<script>
import CourseCard from "@/components/user/course/CourseCard";
export default {
  name: "CourseSearchResult",
  components:{
    CourseCard
  }
}
</script>

<style scoped>
.page-controller{
  margin: 30px auto;
  width: 500px;
  display: flex;
  justify-content: center;
}
.result-table{
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: left;
}
.sort-tag{
  margin: 20px 20px;
}
.space-between{
  display: flex;
  justify-content:space-between;
  margin: 10px auto;
}
.main{
  margin-top: 20px;
}
</style>
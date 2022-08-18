<template>
  <div class="teacher-page">
    <div class="header">
      <el-avatar
          style="margin: 20px"
          :size="70"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
      <div>
        <div>
          李灵灵&nbsp;&nbsp;&nbsp;
          <el-tag type="warning" size="mini">金牌讲师</el-tag>
        </div>
        <div style="font-size: x-small; color: #999999; margin-top: 20px">
          我叫李玲玲, 不仅是一个好僵尸, 还是一个好僵尸. 如果你喜欢的话, 亦可以叫我好僵尸
        </div>
      </div>
    </div>
    <div class="body">
      <div style="margin: 50px; font-size: large; color: teal; font-style: oblique">Ta的课程</div>
      <div class="result-table">
        <course-card v-for="course in 20" :key="course">
        </course-card>
      </div>
    </div>
    <div class="footer">
      <el-pagination
          background
          layout="prev, pager, next"
          :total="1000">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import CourseCard from "@/components/user/course/CourseCard";
export default {
  name: "TeacherPage",
  components:{
    CourseCard
  }
}
</script>

<style scoped>
.footer{
  text-align: center;
  margin-top: 50px;
}
.result-table{
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
.header {
  display: flex;
  align-items: center;
  /*background-color: #409EFF;*/
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.teacher-page {
  width: 1000px;
  margin: 30px auto 0 auto;
  /*background-color: teal;*/
}
</style>
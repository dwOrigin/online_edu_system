<template>
  <div class="main">
    <el-row>
      <!--  走马灯-->
      <el-col :span="15" :offset="1">
        <el-carousel height="320px" style="border-radius: 6px">
          <el-carousel-item v-for="item in 4" :key="item">
            <el-image
                style="width: 100%; height: 100%"
                src="https://img.alicdn.com/imgextra/i1/6000000007842/O1CN01awUHxQ27ngiY2gNm5_!!6000000007842-0-octopus.jpg"
                fit="fill"></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-col>
      <!--  个人标签-->
      <el-col class="user-card" :span="6" :offset="1">
        <user-card></user-card>
      </el-col>
    </el-row>
    <el-row class="hot-title">
      <div style="flex-grow: 1">热门课程推荐</div>
      <el-link :underline="false" type="primary">换一批<i class="el-icon-refresh"></i></el-link>
<!--      <div class="myRefresh">换一批<i class="el-icon-refresh"></i></div>-->
    </el-row>
    <el-row class="divider"></el-row>
    <el-row class="myHot">
        <course-card v-for="obj in 10" :key="obj"></course-card>
    </el-row>
  </div>
</template>

<script>
import UserCard from "@/components/user/course/UserCard";
import CourseCard from "@/components/user/course/CourseCard";

export default {
  name: "CourseRecommend",
  components: {
    UserCard,
    CourseCard
  }
}
</script>

<style scoped>
.myHot{
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}
/*.myRefresh:hover {*/
/*  color: red;*/
/*}*/

.hot-title {
  margin-top: 50px;
  display: flex;
  justify-content: space-between;
}

.divider {
  height: 5px;
  width: 100%;
  margin: 5px auto;
  background-color: #99a9bf;
}

.main {
  margin-top: 10px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item {
  background-color: #99a9bf;
}

.user-card {
  height: 320px;
}
</style>
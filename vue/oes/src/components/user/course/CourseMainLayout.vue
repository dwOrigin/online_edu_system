<template>
  <div class="main">
    <el-col :span="15" :offset="1">
      <play-header></play-header>
      <play-window></play-window>
      <play-window-footer></play-window-footer>
    </el-col>
    <el-col :span="7" :offset="1" style="overflow: visible">
      <play-teacher-header></play-teacher-header>
      <play-list></play-list>
      <play-recommend-card></play-recommend-card>
    </el-col>
  </div>
</template>

<script>
import PlayHeader from "@/components/user/course/PlayHeader";
import PlayTeacherHeader from "@/components/user/course/PlayTeacherHeader";
import PlayWindow from "@/components/user/course/PlayWindow";
import PlayList from "@/components/user/course/PlayList";
import PlayWindowFooter from "@/components/user/course/PlayWindowFooter";
import PlayRecommendCard from "@/components/user/course/PlayRecommendCard";

export default {
  name: "CourseMainLayout",
  components: {
    PlayHeader,
    PlayTeacherHeader,
    PlayWindow,
    PlayList,
    PlayWindowFooter,
    PlayRecommendCard
  }
}
</script>

<style scoped>
.main {
  /*播放页面宽度需要大一点(主页面1100px+200px)*/
  width: 1300px;
  margin: 20px auto;
  height: 800px;
}
</style>
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
    </el-col>
  </div>
</template>

<script>
import PlayHeader from "@/views/course/PlayHeader";
import PlayTeacherHeader from "@/views/course/PlayTeacherHeader";
import PlayWindow from "@/views/course/PlayWindow";
import PlayList from "@/views/course/PlayList";
import PlayWindowFooter from "@/views/course/PlayWindowFooter";

export default {
  name: "CourseMainLayout",
  components: {
    PlayHeader,
    PlayTeacherHeader,
    PlayWindow,
    PlayList,
    PlayWindowFooter,
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
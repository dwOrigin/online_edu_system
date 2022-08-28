<template>
  <div class="play-title">
    <div>
      <span style="font-size: large;">{{ course.courseName }}</span>
    </div>
    <div class="course-info" style="margin-top: 10px;">
      <div style="margin-left: 20px; margin-right: 20px;">
        <i class="el-icon-video-play"></i>
        {{ course.pageViewcount }}
      </div>
      <div style="margin-right: 20px">
        <i class="el-icon-chat-dot-square"></i>
        {{ course.commentNum }}
      </div>
      <div style="margin-right: 20px">
        <i class="el-icon-time"></i>
        {{ course.addTime.split('T')[0]}}&nbsp;&nbsp;{{ course.addTime.split('T')[1] }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlayHeader",
  data() {
    return {
      course: {}
    };
  },
  mounted() {
    this.$bus.$on('courseChanged', (data) => {
      this.course = data;
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged');
  }
}
</script>

<style scoped>
.play-title {
  margin-top: 20px;
  margin-bottom: 20px;
}

.course-info {
  display: flex;
  flex-wrap: nowrap;
  flex-direction: row;
  font-size: small;
  color: #99a9bf;
}

</style>
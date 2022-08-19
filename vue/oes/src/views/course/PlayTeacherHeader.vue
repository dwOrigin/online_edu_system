<template>
<div class="teacher-header">
  <div class="large-avatar">
    <a href="#">
      <el-avatar
          :size="60"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
    </a>
  </div>
  <div>
    <div style="font-weight: lighter; margin: 20px 0 5px  0">
      闻道有先后
    </div>
    <div style="font-size: x-small; color: #99a9bf;">
     金牌讲师 网络段子手
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "PlayTeacherHeader"
}
</script>

<style scoped>
.large-avatar{
  margin: 10px;
}
.teacher-header{
  display: flex;
  border-radius: 5px;
  border: 1px solid #409EFF;
}

</style>
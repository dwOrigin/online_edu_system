<template>
<div class="teacher-card">
  <div>
    <el-avatar
        :size="60"
        style="margin: auto 20px"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
  </div>
  <div>
    <div>李灵灵&nbsp;
      <el-tag type="warning" size="mini">金牌讲师</el-tag>
    </div>
    <div style="font-size: xx-small; margin-top: 10px; "
    >我叫李灵灵,是一个好僵尸</div>
  </div>
</div>
</template>

<script>
export default {
  name: "TeacherCard"
}
</script>

<style scoped>
.teacher-card:hover{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transform: perspective(800px) translateZ(10px);
  transition: 0.3s;
  cursor: pointer;
}
.teacher-card{
  width: 300px;
  /*background-color: #409EFF;*/
  height: 100px;
  background-color: #F5F5F5;
  margin: 10px;
  display: flex;
  align-items: center;
}
</style>
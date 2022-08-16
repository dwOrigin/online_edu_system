<template>
  <div class="main">
    <el-image
        style="width: 100%; height: 120px"
        src="https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356"
        fit="fill">
    </el-image>
    <span style="max-height: 40px;
          overflow: hidden;
          font-size: x-small;">
      [量子速学] 一秒钟一个单词 一小时考过四级! 你值得拥有</span>
    <div style="font-size: xx-small; color: #999999">
      共16节 | bilbil大学
    </div>
    <div style="font-size: xx-small; color: #999999" class="footer">
      <div>
        <span style="color: teal">10086</span>人在学
      </div>
      <div>
        好评率<span style="color: teal">100%</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CourseCard"
}
</script>

<style scoped>
.footer{
  display: flex;
  justify-content: space-between;
}
.main {
  width: 200px;
  height: 200px;
  margin: 10px 10px;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
}
.main:hover{
  box-shadow: 1px 1px 10px #bebec1;
  transform: translateY(-3px) translateZ(100px);
  transition: all 0.3s;
}
</style>
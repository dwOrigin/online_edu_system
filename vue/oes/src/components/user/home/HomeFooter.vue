<template>
<div class="main">
<div class="column">
  <div style="font-size: x-small">
    <span>
      ||   本网站由 疯狂星期四 小组负责开发和维护   ||
    </span>
  </div>
  <div style="font-size: x-small">
    <span>本项目为<el-link type="primary"
                       target="_blank"
                       href="https://www.seu.edu.cn/">东南大学</el-link>软件学院2022年
      <el-link type="primary"
               target="_blank"
               href="http://www.chinahitech.com.cn/">中国高科</el-link>实训项目</span>
  </div>
</div>
</div>
</template>

<script>
export default {
  name: "HomeFooter"
}
</script>

<style scoped>
.main{
  display: flex;
  flex-basis: 100vw;
  background-color: #4a4b4c;
  min-width: 800px;
  justify-content: center;
  margin-top: 50px;
}
.column{
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #999;
}
</style>
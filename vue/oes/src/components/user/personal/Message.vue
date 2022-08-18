<template>
<div class="message">
  <div class="time">
    2022年7月27日&nbsp;&nbsp;16:27
  </div>
  <div class="message-box">
    <el-avatar
        :size="40"
        style="margin: 0 10px"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
    <div class="message-content">
      今天天气真不错! 今天天气真不错!今天天气真不错!今天天气真不错!
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "Message"
}
</script>

<style scoped>
.message-content{
  width: 70%;
  background-color: #F4F5F7;
  padding: 10px 20px;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  border-top-right-radius: 15px;
}
.message-box{
  display: flex;
}
.time{
  text-align: center;
  color: #BFBFC0;
  font-size: x-small;
  margin: 20px 0;
}

</style>
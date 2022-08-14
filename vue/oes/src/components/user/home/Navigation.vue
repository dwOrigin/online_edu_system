<template>
  <div class="nav">
    <div class="nav-content">
      <div class="left-content">
        <nav-left></nav-left>
      </div>
      <div class="right-content">
        <nav-right></nav-right>
      </div>
    </div>
  </div>
</template>

<script>
import NavLeft from './NavLeft';
import NavRight from  './NavRight';
export default {
  name: "Navigation",
  components: {
    NavLeft,
    NavRight
  }
}
</script>

<style scoped>
.nav {
  z-index: 100;
  background-color: white;
  display: block;
  width: 1500px;
  margin: 0 auto;
  height: 80px;
  /*导航栏固定最上方*/
  position: sticky;
  top: 0;
  border-bottom: 1px solid #e5e5e5;
}

.nav-content {
  /*内容水平居中并分为两侧, 两侧分别水平靠左, 靠右, 垂直居中*/
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  margin: 0 auto;
  width: 70%;
}
.left-content, .right-content{
  height: 100%;
  display: flex;
  align-items: center;
}
.left-content{
}
.right-content{
}
</style>
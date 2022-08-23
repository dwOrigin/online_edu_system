<template>
  <div class="friend-card">
    <el-avatar
        style="margin: 0 10px 0 20px"
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png">

    </el-avatar>
    <span style="font-weight: lighter; font-size: x-small">
      系统消息
  </span>
    <el-badge is-dot style="margin-left: 5px"></el-badge>

    <el-button type="info"
               size="mini"
               class="appear-on-hover"
               icon="el-icon-close" circle></el-button>
  </div>
</template>

<script>
export default {
  name: "FriendCard"
}
</script>

<style scoped>
.friend-card:hover .appear-on-hover {
  left: 20px;
}

.appear-on-hover {
  position: relative;
  left: 100px;
  transition: all 0.4s;
}

.friend-card:hover {
  background-color: #e4e5e6;
}

.friend-card {
  overflow: hidden;
  display: flex;
  align-items: center;
  flex-grow: 0;
  flex-shrink: 0;
  height: 80px;
}

</style>
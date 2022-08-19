<template>
  <div class="li">
    <a href="#" class="play-list-item">
      <div style="margin-left: 15px">
      <span>
        P1&nbsp;&nbsp;&nbsp;这是一节无聊的课的标题
      </span>
      </div>
      <div style="color: #9499A0; font-size: x-small">12:36</div>
    </a>
  </div>
</template>

<script>
export default {
  name: "PlayListItem"
}
</script>

<style scoped>
.li {
  margin: 7px 5px;
  border-radius: 5px;
}

.li:hover {
  background-color: white;
}

.play-list-item:hover {
  text-decoration: #409EFF;
}

.play-list-item {
  align-items: center;
  text-decoration: none;
  display: flex;
  width: 96%;
  height: 30px;
  justify-content: space-between;
  font-size: small;
  color: black
}

.play-list-item:link, .play-list-item:visited {
  text-decoration: none;
}
</style>
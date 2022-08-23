<template>
  <div ref="pli" class="li">
    <div @click="chapterChanged(chapter.url, chapter.order)"
         class="play-list-item">
      <div style="margin-left: 15px">
      <span>
        &nbsp;&nbsp;&nbsp;{{ chapter.title }}
      </span>
      </div>
      <div style="color: #9499A0; font-size: x-small">{{ chapter.length }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlayListItem",
  props: {
    chapter: {}
  },
  methods: {
    //播放的集数改变
    chapterChanged(url, order) {
      this.$bus.$emit('chapterChanged', url, order);
    }
  },
  mounted() {
    this.$bus.$on('chapterChanged', (url, order) => {
      if (order === this.chapter.order) {
        this.$refs.pli.className = 'myActive';
      } else {
        this.$refs.pli.className = 'li';
      }
    });
  },
  beforeDestroy() {
    this.$bus.$off('chapterChanged');
  }
}
</script>

<style scoped>
.myActive {
  background-color: white;
  margin: 7px 5px;
  border-radius: 5px;
}

.li {
  margin: 7px 5px;
  border-radius: 5px;
}

.li:hover {
  background-color: white;
}

.play-list-item:hover {
  cursor: pointer;
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
<template>
  <div class="play-list">
    <div class="list-title">
      <div>课程选集<span style="font-size: x-small; color: #9499A0">
      ({{ curChapter }}/{{ chapters.length }})
    </span></div>
      <div><span style="color: #9499A0"> 自动连播 </span>
        <el-switch
            v-model="autoPlayNext"
            active-color="#13ce66"
            inactive-color="#9499A0">
        </el-switch>
      </div>
    </div>
    <div class="list-body">
      <play-list-item
          :chapter="chapter"
          v-for="chapter in chapters">
      </play-list-item>
    </div>
  </div>
</template>

<script>
import PlayListItem from "@/components/user/course/PlayListItem";

export default {
  name: "PlayList",
  components: {
    PlayListItem
  },
  data() {
    return {
      chapters: [],
      autoPlayNext: true,
      curChapter: 1//当前在播放的集数
    }
  },
  methods: {},
  mounted() {
    this.$bus.$on('chapterChanged', (url, order) => {
      this.curChapter = order;
    });
    this.$bus.$on('courseChanged', (data) => {
      this.chapters = data.chapters;
      this.$bus.$emit('chapterChanged', data.chapters[0].url, data.chapters[0].order, false);
    });
    this.$bus.$on('playEnd', () => {
      if(this.autoPlayNext && this.curChapter !== this.chapters.length){
        this.$bus.$emit('chapterChanged', this.chapters[this.curChapter].url, ++this.curChapter);
      }
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged');
    this.$bus.$off('chapterChanged');
    this.$bus.$off('playEnd');
  }

}
</script>

<style scoped>
.list-title {
  font-size: small;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  position: sticky;
  background-color: #F1F2F3;
  top: 0;
}

.play-list {
  overflow: auto;
  background-color: #F1F2F3;
  width: 100%;
  height: 500px;
  margin-top: 20px;
  border-radius: 10px;
}
</style>
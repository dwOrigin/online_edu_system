<template>
  <div class="play-list">
    <div class="list-title">
      <div>课程选集<span style="font-size: x-small; color: #9499A0">
          ({{ curChapter }}/{{ chapters.length }})
        </span></div>
      <div><span style="color: #9499A0"> 自动连播 </span>
        <el-switch v-model="autoPlayNext" active-color="#13ce66" inactive-color="#9499A0">
        </el-switch>
      </div>
    </div>
    <div class="list-body">
      <play-list-item :chapter="chapter" v-for="chapter in chapters">
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
      curChapter: 1,//当前在播放的集数
      course: {}
    }
  },
  methods: {},
  mounted() {
    this.$bus.$on('chapterChanged', (url, order) => {
      this.curChapter = order;
    });
    this.$bus.$on('courseChanged', (data) => {
      this.course = data;
      let promise = this.$axios({
        url: '/coursevideo/findbycourseid',
        method: 'get',
        params: {
          courseId: this.course.courseId
        }
      });
      promise.then((res) => {
        this.chapters = res.data;
        console.log(res.data);
        console.log(this.chapters);
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      this.$bus.$emit('chapterChanged', this.chapters[0].videoLink, 1, false);
    });
    this.$bus.$on('playEnd', () => {
      if (this.autoPlayNext && this.curChapter !== this.chapters.length) {
        this.$bus.$emit('chapterChanged', this.chapters[this.curChapter].videoLink, ++this.curChapter);
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
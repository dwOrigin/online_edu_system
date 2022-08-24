<template>
  <div class="play-window">
    <video-player
        class="video-player vjs-custom-skin"
        ref="videoPlayer"
        :playsinline="true"
        :options="playerOptions"
        @canplaythrough="onCanplay"
        @ended="onPlayEnd"
    >
    </video-player>
  </div>
</template>

<script>
export default {
  name: "PlayWindow",
  data() {
    return {
      autoP: true,
      playerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
        autoplay: true, // 是否自动播放
        muted: false, // 是否静音
        loop: false, // 是否开启循环播放
        preload: "auto", // 自动预加载
        language: "zh-CN", // 语言
        aspectRatio: "8:5", // 播放器高宽占比
        fluid: true, // 按比例缩放以适应其容器。
        sources: [],
        notSupportedMessage: "此视频暂无法播放，请稍后再试",
        controlBar: {
          timeDivider: true, // 是否显示当前时间和持续时间的分隔符，"/"
          durationDisplay: true, // 是否显示持续时间
          remainingTimeDisplay: false, // 是否显示剩余时间
          fullscreenToggle: true, // 是否显示全屏按钮
        }
      }
    }
  },
  methods:{
    onPlayEnd(){
      this.$bus.$emit('playEnd');
    },
    onCanplay(){
      if(!this.autoP){
        this.$refs.videoPlayer.player.pause();
      }
    }
  },
  mounted() {
    this.$bus.$on('chapterChanged', (url, order, auto) => {
      this.playerOptions.sources[0] = {
        src: url,
        type: 'video/mp4'
      };
      this.autoP = true;
      if(auto !== undefined){
        this.autoP = false;
      }
      this.$refs.videoPlayer.player.src(url);
    });
    // this.$bus.$on('courseChanged', (data) => {
    //   this.playerOptions.sources[0] = {};
    //   this.playerOptions.sources[0].src = data.chapters[0].url;
    //   this.playerOptions.sources[0].type = 'video/mp4';
    // });
  },
  beforeDestroy() {
    this.$bus.$off('chapterChanged');
    // this.$bus.$off('courseChanged');
  }
}

</script>

<style scoped>
.play-window {
  width: 100%;
  height: 500px;
}
</style>
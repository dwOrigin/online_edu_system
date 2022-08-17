<template>
  <div class="play-window">
    <video-player
        class="video-player vjs-custom-skin"
        ref="videoPlayer"
        :playsinline="true"
        :options="playerOptions"
    >
    </video-player>
  </div>
</template>

<script>
export default {
  name: "PlayWindow",
  data() {
    return {
      playerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
        autoplay: false, // 是否自动播放
        muted: true, // 是否静音
        loop: true, // 是否开启循环播放
        preload: "auto", // 自动预加载
        language: "zh-CN", // 语言
        aspectRatio: "8:5", // 播放器高宽占比
        fluid: true, // 按比例缩放以适应其容器。
        sources: [
          {
            type: "video/mp4", // 类型
            src:
                "./test.mp4", // url地址
          },
        ],
        notSupportedMessage: "此视频暂无法播放，请稍后再试",
        controlBar: {
          timeDivider: true, // 是否显示当前时间和持续时间的分隔符，"/"
          durationDisplay: true, // 是否显示持续时间
          remainingTimeDisplay: false, // 是否显示剩余时间
          fullscreenToggle: true, // 是否显示全屏按钮
        }
      }
    }
  }
}

</script>

<style scoped>
.play-window {
  width: 100%;
  height: 500px;
}
</style>
<template>
  <div class="upload-box">
    <div class="avatar-uploader-box">
      <!-- 视频预览 -->
      <div :key="index" class="video-preview" v-for="(item, index) in videoList">
        <video v-if="item.videoLink" :src="`${videoBaseUrl}${item.videoLink}`" @mouseover.stop="item.isShowPopup = true" class="avatar">
          您的浏览器不支持视频播放
        </video>
        <video v-else :src="item.url" @mouseover.stop="item.isShowPopup = true" class="avatar">
          您的浏览器不支持视频播放
        </video>
        <!-- 显示查看和删除的按钮弹窗 -->
        <div
          @mouseleave="item.isShowPopup = false"
          class="avatar-uploader-popup"
          v-show="(item.videoLink || item.url) && item.isShowPopup"
        >
          <i @click="previewVideo(item)" class="el-icon-zoom-in"></i>
          <i @click="deleteVideo(index)" class="el-icon-delete"></i>
        </div>
      </div>

      <!-- 方框样式 -->
      <el-upload
        :action="actionUrl"
        :auto-upload="false"
        :on-change="handleAvatarChange"
        :show-file-list="false"
        class="avatar-uploader"
        ref="avatarUploader"
        v-show="uploadShow"
      >
        <span
          element-loading-background="rgba(0, 0, 0, 0.8)"
          element-loading-spinner="el-icon-loading"
          element-loading-text="上传中"
          style="display:block;"
          v-loading="loading"
        >
          <i class="el-icon-plus avatar-uploader-icon"></i>
        </span>
      </el-upload>
      <!-- 上传之后变成空白，鼠标移过去其实已上传，不会改 -->

      <!-- 上传提示文字样式 -->
      <div class="upload-tip">
        <slot></slot>
      </div>
    </div>

    <!-- 查看视频 -->
    <el-dialog :visible.sync="dialogVisible" append-to-body center title="视频查看" :before-close="handleClose">
      <video :src="videoSrc" ref="video" controls alt width="100%">您的浏览器不支持视频播放</video>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'UploadVideo',
  components: {},
  props: {
    videos: {
      type: Array,
      default: function() {
        return []
      }
    },
    numLimit: {
      // 最大允许上传个数
      type: Number,
      default: 1
    },
    sizeLimit: {
      // 最大单文件大小
      type: Number,
      default: 50
    },
    videoBaseUrl: {
      // 已上传视频服务器路径
      type: String,
      required: true
    }
  },
  data() {
    return {
      loading: false,
      dialogVisible: false,
      videoList: [],
      videoSrc: '',
      actionUrl: ''
    }
  },
  computed: {
    uploadShow() {
      return this.videos.length < this.numLimit
    }
  },
  watch: {
    videos: {
      handler() {
        const isArray = Array.isArray(this.videos)
        if (isArray && this.videos.length > 0) {
          this.videos.map(item => {
            item.isShowPopup = false
          })
          this.videoList = JSON.parse(JSON.stringify(this.videos))
        } else {
          this.videoList = []
        }
      },
      deep: true,
      immediate: true
    }
  },
  created() {},
  methods: {
    // 上传之前
    beforeAvatarUpload(file) {
      return new Promise((resolve, reject) => {
        if (['video/mp4', 'video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb', 'video/mov'].indexOf(file.raw.type) == -1) {
          // eslint-disable-next-line prefer-promise-reject-errors
          return reject('请上传正确的视频格式!')
        }

        if (file.size / 1024 / 1024 > this.sizeLimit) {
          // eslint-disable-next-line prefer-promise-reject-errors
          return reject(`视频大小不能超过${this.sizeLimit}M!`)
        }

        if (this.videoList.length === this.numLimit) {
          // eslint-disable-next-line prefer-promise-reject-errors
          return reject(`最多上传${this.numLimit}个视频`)
        }

        resolve('符合表单规则')
      })
    },
    // 上传改变
    async handleAvatarChange(file, fileList) {
      try {
        await this.beforeAvatarUpload(file)
        this.uploadImgApi(file)
      } catch (e) {
        this.$message.warning(JSON.stringify(e))
      }
    },
    // 上传视频准备
    uploadImgApi(data) {
      console.log(data, 'upload**')
      const videoSrc = URL.createObjectURL(data.raw)
      this.videoList.push({
        videoFile: data,
        url: videoSrc,
        isShowPopup: false
      })
      this.$emit('change', this.videoList)
    },
    // 预览视频
    previewVideo(data) {
      if (data.videoLink) {
        this.videoSrc = `${this.videoBaseUrl}${data.videoLink}`
      } else {
        this.videoSrc = data.url
      }
      this.dialogVisible = true
    },
    // 删除视频
    deleteVideo(index) {
      this.$emit('delete', index)
    },
    handleClose() {
      const video = document.getElementsByTagName('video')[1]
      if (!video.paused) {
        video.currentTime = 0
        video.pause()
      }
      this.dialogVisible = false
    }
  }
}
</script>

<style lang="scss">
$width: 180px;
$height: 180px;

.upload-box {
  .avatar-uploader-box {
    position: relative;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    padding-bottom: 20px;
    min-width: 350px;
    .avatar-uploader {
      .el-upload {
        width: $width;
        height: $height;
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        &:hover {
          border-color: #409eff;
        }
        .el-loading-spinner {
          width: $width;
          height: $height;
          position: relative;
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          margin-top: 0;
          top: 0;
          .el-loading-text {
            margin: 0;
          }
        }
      }
      .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: $width;
        height: $height;
        line-height: $height;
        text-align: center;
      }
    }
    .video-preview {
      position: relative;
      padding-right: 20px;
      .avatar {
        width: $width;
        height: $height;
        display: block;
        border-radius: 6px;
        background: #000000;
      }
      .avatar-uploader-popup {
        position: absolute;
        top: 0;
        left: 0;
        z-index: 2;
        width: $width;
        height: $height;
        background: rgba($color: #000000, $alpha: 0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        color: #fff;
        font-size: 20px;
        border-radius: 6px;
        i {
          width: 30%;
          text-align: center;
          padding: 0 5%;
          font-size: 24px;
        }
      }
    }
    .upload-tip {
      position: absolute;
      bottom: 0;
      left: 0;
      font-size: 12px;
      color: #606266;
      max-width: 350px;
      line-height: 20px;
    }
  }
}
</style>


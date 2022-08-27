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
    <br>
    <div class="btn">
      <el-button type="primary" @click="addNew" style="margin-right:20px;">增加视频</el-button>
      <el-dialog title="增加视频" :visible.sync="dialogFormVisible" append-to-body>
        <el-form ref="form" :model="form" label-width="100px">
          <el-form-item label="标题" prop="title">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="时长" prop="title">
            <el-input v-model="form.time"></el-input>
          </el-form-item>
          <el-form-item label="上传视频">
            <el-upload class="avatar-uploader" action="http://localhost:8081/file/upload" :data="{ filetype: 'video' }"
              :show-file-list="false" :on-success="handlevideoSuccess" :on-progress="uploadVideoProcess">
              <video width="100%" v-if="form.videoLink" controls="controls" :key="menuKey">
                <source :src="form.videoLink" type="video/mp4" />
              </video>
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              <el-progress v-if="videoFlag == true" type="line" :percentage="videoUploadPercent"
                style="margin-top: 30px"></el-progress>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">上传视频</el-button>
            <el-button @click="resetForm">取消</el-button>
            <!-- 居中不了，去死吧 -->
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-button type="primary" @click="deleteOld">删除视频</el-button>
      <!-- 没想好怎么写 -->
    </div>
  </div>
</template>

<script>
import PlayListItem from "@/views/course/PlayListItem";
import UploadVideo from "@/views/UploadVideo";
export default {
  name: "PlayList",
  components: {
    PlayListItem,
    UploadVideo
  },
  data() {
    return {
      menuKey: 1,
      videoFlag: false,  // 进度条相关的
      videoUploadPercent: 0,  // 进度条相关的
      chapters: [],
      autoPlayNext: true,
      curChapter: 1,//当前在播放的集数
      course: {},
      videoInfo: {
        allVideo: [],
        deleteVideo: []
      },
      form: {
        name: '',
        videoLink: '',
        time: '',
        courseId: 0,
      },
      dialogFormVisible: false
    }
  },
  methods: {
    addNew() {
      this.dialogFormVisible = true
    },
    onSubmit() {
      this.form.courseId = this.course.courseId;
      let promise = this.$axios({
        url: '/coursevideo/addCourseVideo',
        method: 'post',
        data: this.form
      });
      promise.then((res) => {
        if (res.data.code == "200") {
          this.$message.success("增加成功");
          this.$bus.$emit('courseChanged1', this.course);
        } else {
          this.$message.error("增加失败");
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      // this.request.post('/coursevideo/addCourseVideo', this.form)
      //   .then((res) => {
      //     if (res.code == "200") {
      //       this.$message.success("增加成功");
      //     } else {
      //       this.$message.error("增加失败");
      //     }
      //   })
      this.resetForm();
    },
    handlevideoSuccess(res, file) {
      // 进度条恢复到初始状态
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      ++this.menuKey;
      // console.log(res)
      this.form.videoLink = res;
      // var audioElement = new Audio(res);
      // var duration;
      // //扩展 onloadedmetadata 视频加载完执行，onload图片加载完执行
      // audioElement.onloadedmetadata = () => {
      //   duration = parseInt(audioElement.duration); //时长为秒，取整
      // };
      // this.course.timeLong=duration;
    },
    uploadVideoProcess(event, file, fileList) {
      this.videoFlag = true;
      this.videoUploadPercent = parseInt(file.percentage);
    },
    resetForm() {
      this.form.courseId = '';
      this.form.name = '';
      this.form.time = '';
      this.form.videoLink = '';
      this.dialogFormVisible = false;
      // 不知道怎么把视频删了
    },
    // getVideo(event) {
    //   this.videoInfo.allVideo = event
    // },
    // deleteVideo(index) {
    //   const video = this.videoInfo.allVideo[index]
    //   if (video.videoLink) {
    //     this.videoInfo.deleteVideo.push(video)
    //   }
    //   this.videoInfo.allVideo.splice(index, 1)
    // },
    // uploadFiles() {
    //   const uploadList = []
    //   this.videoInfo.allVideo.map(item => {
    //     console.log(item, 'video')
    //     const videoFile = new FormData()
    //     if (!item.videoLink) {
    //       videoFile.append('file', item.videoFile.raw)
    //       videoFile.append('fileTag', 'video')
    //       uploadList.push(
    //         new Promise((resolve, reject) => {
    //           return ImgServe.uploadSingleFile(videoFile).then(res => {  //服务器接口
    //             if (res.data.code === 200) {
    //               resolve(res.data.data)
    //             } else {
    //               this.$message('上传视频失败！')
    //             }
    //           })
    //         })
    //       )
    //     }
    //   })
    //   return Promise.all(uploadList)
    // },
    deleteOld() {
      let promise = this.$axios({
        url: '/coursevideo/deleteCourseVideo',
        method: 'get',
        params: {
          id: this.chapters[this.curChapter - 1].videoId
        }
      });
      promise.then((res) => {
        if (res.data) {
          this.$message.success("删除成功");
          this.$bus.$emit('courseChanged1', this.course);

        } else {
          this.$message.error("删除失败");
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.$bus.$on('chapterChanged1', (url, order) => {
      this.curChapter = order;
    });
    this.$bus.$on('courseChanged1', (data) => {
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
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      this.$bus.$emit('chapterChanged1', this.chapters[0].videoLink, 1, false);
    });
    this.$bus.$on('playEnd1', () => {
      if (this.autoPlayNext && this.curChapter !== this.chapters.length) {
        this.$bus.$emit('chapterChanged1', this.chapters[this.curChapter].videoLink, ++this.curChapter);
      }
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged1');
    this.$bus.$off('chapterChanged1');
    this.$bus.$off('playEnd1');
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

.btn {
  text-align: center;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 16px;
  color: #8c939d;
  width: 350px;
  height: 30px;
  line-height: 30px;
  text-align: center;
}

.avatar {
  width: 350px;
  height: auto;
  display: block;
}

.title {
  font-size: 18px;
  margin-bottom: 20px;
}
</style>
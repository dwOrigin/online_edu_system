<template>
  <div class="homeBox">
    <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="3" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
          <el-menu-item index="1" @click="gotoMember">
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="2" @click="gotoTeacher">
            <span slot="title">讲师管理</span>
          </el-menu-item>
          <el-menu-item index="3" @click="gotoLesson">
            <span slot="title">课程管理</span>
          </el-menu-item>
          <el-menu-item index="4" @click="gotoArticle">
            <span slot="title">文章管理</span>
          </el-menu-item>
          <el-menu-item index="5" @click="gotoQA">
            <span slot="title">问答管理</span>
          </el-menu-item>
          <el-menu-item index="6" @click="gotoNotice">
            <span slot="title">通知管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="text-align: left; font-size: 25px">
          <span>管理员</span>
        </el-header>
        <el-main>
          <el-form ref="form" :model="form" label-width="100px">
            <el-row>
              <!-- <el-col span="10">
                <el-form-item label="课程ID" prop="courseId">
                  <el-input v-model="form.courseId"></el-input>
                </el-form-item>
              </el-col> -->
              <el-col span="10">
                <el-form-item label="老师ID" prop="teacherId">
                  <el-input v-model="form.teacherId"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col span="10">
                <el-form-item label="课程名称" prop="courseName">
                  <el-input v-model="form.courseName"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- <el-row>
              <el-col span="10">
                <el-form-item label="课程时间长度" prop="timeLong">
                  <el-input v-model="form.timeLong"></el-input>
                </el-form-item>
              </el-col>
            </el-row> -->
            <el-row>
              <el-col span="10">
                <el-form-item label="简介" prop="title">
                  <el-input v-model="form.title"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择文章类型">
                <el-option label="美女" value="shanghai"></el-option>
                <el-option label="高中生" value="beijing"></el-option>
              </el-select>
            </el-form-item>
            <el-row>
              <el-col span="10">
                <el-form-item label="封面" prop="logo">
                  <el-avatar shape="square" :size="60" :src="form.logo"></el-avatar>
                  <el-link @click="changeAvatar" :underline="false" type="primary" style="margin: 0 30px">修改
                  </el-link>
                </el-form-item>
              </el-col>
            </el-row>
            <el-dialog :visible.sync="avatar" append-to-body :close-on-click-modal="false" lock-scroll top="25vh" center
              width="max-content" modal-append-to-body>
              <el-upload class="avatar-uploader" action="http://localhost:8081/file/upload" :drag="true"
                :show-file-list="false" :data="{ filetype: 'picture' }" :on-success="handleAvatarSuccess">
                <img v-if="form.logo" :src="form.logo" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-dialog>
            <!-- <el-form-item label="上传视频">
              <upload-video :videos="videoInfo.allVideo" :videoBaseUrl="IMAGEURL" @delete="deleteVideo"
                @change="getVideo">
              </upload-video>
            </el-form-item> -->
            <el-form-item label="上传视频">
              <div class="img__con">
                <el-upload class="avatar-uploader" action="http://localhost:8081/file/upload"
                  :data="{ filetype: 'video' }" :show-file-list="false" :on-success="handlevideoSuccess"
                  :on-progress="uploadVideoProcess">
                  <video width="100%" v-if="video.videoLink" controls="controls" :key="menuKey">
                    <source :src="video.videoLink" type="video/mp4" />
                  </video>
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  <el-progress v-if="videoFlag == true" type="line" :percentage="videoUploadPercent"
                    style="margin-top: 30px"></el-progress>
                </el-upload>
              </div>
            </el-form-item>
            <el-form-item> </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit()">上传课程</el-button>
              <el-button @click="resetForm()">重置</el-button>
              <!-- 居中不了，去死吧 -->
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import UploadVideo from "@/views/UploadVideo.vue"
export default {
  components: { UploadVideo },
  activated: function () {
    this.getCase()
  },
  data() {
    return {
      menuKey: 1,
      avatar: false,
      videoFlag: false,  // 进度条相关的
      videoUploadPercent: 0,  // 进度条相关的
      form: {
        courseId: '',
        teacherId: '',
        courseName: '',
        isAvailable: 1,
        // timeLong: '',
        title: '',
        logo: '',
        type: '',
      },
      video: {
        videoLink: '',
        courseId: '',
        courseRank: 1,
      },
      // videoInfo: {
      //   allVideo: [],
      //   deleteVideo: []
      // }
    };
  },
  methods: {
    gotoMember() {
      this.$router.push('/member_manage')
    },
    gotoTeacher() {
      this.$router.push('/teacher_manage')
    },
    gotoLesson() {
      this.$router.push('/lesson_manage')
    },
    gotoArticle() {
      this.$router.push('/article_manage')
    },
    gotoQA() {
      this.$router.push('/qa_manage')
    },
    gotoNotice() {
      this.$router.push('/notice_manage')
    },
    onSubmit() {
      this.request.post("/course/add", this.form)
        .then((res) => {
          if (res.code == '200') {
            this.video.courseId=res.data;
            this.request.post("/coursevideo/save", this.video);
            this.$message.success("上传成功");
            this.resetForm();
          } else {
            this.$message.error("上传失败");
          }
        })
    },
    resetForm() {
      let link = this.video.videoLink;
      this.video.courseId = '';
      this.video.videoLink = '';
      this.request.get("/file/removeFile", {
        params: {
          url: this.form.logo
        }
      }).then((res) => {
      })
      this.request.get("/file/removeFile", {
        params: {
          url: link
        }
      }).then((res) => {
      })
      this.form.courseId = '';
      this.form.courseName = '';
      this.form.title='';
      this.form.logo='';
      this.form.teacherId='';
      this.form.type='';
    },
    getVideo(event) {
      this.videoInfo.allVideo = event
    },
    deleteVideo(index) {
      const video = this.videoInfo.allVideo[index]
      if (video.videoLink) {
        this.videoInfo.deleteVideo.push(video)
      }
      this.videoInfo.allVideo.splice(index, 1)
    },
    changeAvatar() {
      this.avatar = true;
    },
    handleAvatarSuccess(res, file) {
      this.form.logo = res;
    },
    handlevideoSuccess(res, file) {
      // 进度条恢复到初始状态
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      ++this.menuKey;
      // console.log(res)
      this.video.videoLink = res;
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
    // }
  }
}
</script>

<style lang="scss" scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

html,
body,
#app,
.homeBox,
.el-container {
  padding: 0px;
  margin: 0px;
  height: 100%;
}

.editPage__video {
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
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

  .img__con {
    .el-button {
      width: 100%;
      margin: 10px 0 20px 0;
    }
  }
}
</style>

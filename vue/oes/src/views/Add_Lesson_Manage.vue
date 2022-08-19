<template>
   <div class="homeBox">
   <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu
      default-active="3"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose">
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
    <el-header  style="text-align: left; font-size: 25px">
      <span>管理员</span>
    </el-header>
    <el-main>
        <el-form ref="form" :model="form" label-width="100px">
        <el-row>
            <el-col span="10">
                 <el-form-item label="课程ID" prop="courseId">
    <el-input v-model="form.courseId"></el-input>
  </el-form-item>
            </el-col>
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
            <el-col span="10">
               <el-form-item label="课程时间长度" prop="timeLong">
     <el-input v-model="form.timeLong"></el-input>
  </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col span="10">
              <el-form-item label="标题" prop="title">
    <el-input v-model="form.title"></el-input>
  </el-form-item>
            </el-col>
            <el-col span="10">
                 <el-form-item label="封面logo" prop="logo">
    <el-input v-model="form.logo"></el-input>
  </el-form-item>
            </el-col>
        </el-row>
  <el-form-item label="类型" prop="type">
    <el-select v-model="form.type" placeholder="请选择文章类型">
      <el-option label="美女" value="shanghai"></el-option>
      <el-option label="高中生" value="beijing"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="上传视频">
<upload-video
  :videos="videoInfo.allVideo"
  :videoBaseUrl="IMAGEURL"
  @delete="deleteVideo"
  @change="getVideo" >
</upload-video>
  </el-form-item>

   <el-form-item>  </el-form-item>
  <el-form-item>
       <el-button type="primary" @click="onSubmit('form');uploadFiles()">上传课程</el-button>
    <el-button @click="resetForm('form')">重置</el-button> 
   <!-- 居中不了，去死吧 -->
  </el-form-item>
</el-form>       
    </el-main>
  </el-container>
</el-container>
</div>
</template>

<script>
import UploadVideo from"@/views/UploadVideo.vue"
export default{
    components:{UploadVideo},
    activated: function() {
 this.getCase()
 },
  data() {
      return {
       form: {
          courseId:'',
          teacherId:'',
          courseName:'',
          isAvailable:'',
          timeLong:'',
          title:'',
          logo:'',
          type:'',
        },
        videoInfo:{
            allVideo:[],
            deleteVideo:[]
        }
      };     
  },
     methods:{
        gotoMember(){
            this.$router.push('/member_manage')
        },
        gotoTeacher(){
            this.$router.push('/teacher_manage')
        },
        gotoLesson(){
            this.$router.push('/lesson_manage')
        },
        gotoArticle(){
            this.$router.push('/article_manage')
        },
        gotoQA(){
            this.$router.push('/qa_manage')
        },
        gotoNotice(){
            this.$router.push('/notice_manage')
        },
        onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('添加课程成功!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        this.resetForm(formName);
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
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
uploadFiles() {
  const uploadList = []
  this.videoInfo.allVideo.map(item => {
        console.log(item, 'video')
        const videoFile = new FormData()
        if (!item.videoLink) {
          videoFile.append('file', item.videoFile.raw)
          videoFile.append('fileTag', 'video')
          uploadList.push(
            new Promise((resolve, reject) => {
              return ImgServe.uploadSingleFile(videoFile).then(res => {  //服务器接口
                if (res.data.code === 200) {
                  resolve(res.data.data)
                } else {
                  this.$message('上传视频失败！')
                }
              })
            })
          )
        }
      })
      return Promise.all(uploadList)
}
     }
}
</script>

<style>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
  html,body,#app,.homeBox,.el-container{
    padding:0px;
    margin:0px;
    height:100%;
  }
</style>
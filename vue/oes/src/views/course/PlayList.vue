<template>
<div>
  <div class="play-list">
  <div class="list-title">
    <div>课程选集<span style="font-size: x-small; color: #9499A0"> (1/168) </span></div>
    <div><span style="color: #9499A0"> 自动连播 </span>
      <el-switch
        v-model="autoPlayNext"
        active-color="#13ce66"
        inactive-color="#9499A0">
    </el-switch>
    </div>
  </div>
  <div class="list-body">
    <play-list-item v-for="chapter in 100" :key="chapter"></play-list-item>
  </div>
</div>
<br>
 <div class="btn">
    <el-button type="primary" @click="addNew" style="margin-right:20px;" >增加视频</el-button>
    <el-dialog title="增加视频" :visible.sync="dialogFormVisible" append-to-body>
    <el-form ref="form" :model="form" label-width="100px">
       <el-form-item label="标题" prop="title">
    <el-input v-model="form.title"></el-input>
  </el-form-item>
  <el-form-item label="上传视频">
<upload-video ref="myvideo"
  :videos="videoInfo.allVideo"
  :videoBaseUrl="IMAGEURL"
  @delete="deleteVideo"
  @change="getVideo" >
</upload-video>
  </el-form-item>
   <el-form-item>
       <el-button type="primary" @click="onSubmit('form');uploadFiles()">上传视频</el-button>
    <el-button @click="resetForm('form')">取消</el-button> 
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
import PlayListItem from "@/components/user/course/PlayListItem";
import UploadVideo from "@/views/UploadVideo";
export default {
  name: "PlayList",
  components: {
    PlayListItem,
    UploadVideo
  },
  data(){
    return {
      autoPlayNext: true,
       videoInfo:{
            allVideo:[],
            deleteVideo:[]
        },
        form:{
          title:''
        },
        dialogFormVisible:false
    }    
  },
  methods:{
    addNew(){
    this.dialogFormVisible=true
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
        this.dialogFormVisible=false;
        // 不知道怎么把视频删了
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
},
deleteOld(){
   alert('删除成功')
}
  }

}
</script>

<style scoped>
.list-title{
  font-size: small;
  padding: 20px;
  display: flex;
  justify-content: space-between;
  position: sticky;
  background-color: #F1F2F3;
  top: 0;
}
.play-list{
  overflow: auto;
  background-color: #F1F2F3;
  width: 100%;
  height: 500px;
  margin-top: 20px;
  border-radius: 10px;
}
.btn{
  text-align: center;
}
</style>
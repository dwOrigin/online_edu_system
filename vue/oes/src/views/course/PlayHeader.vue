<template>
  <div class="play-title">
    <div>
      <span style="font-size: large;">【原神】胡桃新皮肤！超美夏日礼服——「朝露晴空」</span>
      <el-button type="primary" round style="float:right" @click="changeName">修改标题</el-button>
      <el-dialog title="修改课程名称" :visible.sync="dialogFormVisible" append-to-body>
    <el-form ref="form" :model="form" label-width="100px">
       <el-form-item label="课程名称" prop="courseName">
    <el-input v-model="form.courseName"></el-input>
  </el-form-item>
   <el-form-item>
       <el-button type="primary" @click="onSubmit('form')">确认</el-button>
    <el-button @click="dialogFormVisible=false">取消</el-button> 
   <!-- 居中不了，去死吧 -->
  </el-form-item>
    </el-form>
    </el-dialog>
    </div>
    <div class="course-info" style="margin-top: 10px;">
      <div style="margin-left: 20px; margin-right: 20px;">
        <i class="el-icon-video-play"></i>
        171.9万
      </div>
      <div style="margin-right: 20px">
        <i class="el-icon-chat-dot-square"></i>
        1163
      </div>
      <div style="margin-right: 20px">
        <i class="el-icon-time"></i>
        2022-06-18 11:30:00
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlayHeader",
  data(){
    return{
      dialogFormVisible:false,
      form:{
        courseName:'【原神】胡桃新皮肤！超美夏日礼服——「朝露晴空」'
      }
    }
  },
  methods:{
    changeName(){
      this.dialogFormVisible=true
    },
    onSubmit(form){
      this.dialogFormVisible=false;
      this.$notify({
          title: '成功',
          message: '信息修改成功',
          type: 'success'
        });
    },
  }
  
}
</script>

<style scoped>
.play-title{
  margin-top: 20px;
  margin-bottom: 20px;
}
.course-info{
  display: flex;
  flex-wrap: nowrap;
  flex-direction: row;
  font-size: small;
  color: #99a9bf;
}

</style>
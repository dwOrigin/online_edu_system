<template>
  <div class="play-title">
    <div>
      <span style="font-size: large;">{{ course.courseName }}</span>
      <el-button type="primary" round style="float:right" @click="changeName">修改标题</el-button>
      <el-dialog title="修改课程名称" :visible.sync="dialogFormVisible" append-to-body>
        <el-form ref="form" :model="form" label-width="100px">
          <el-form-item label="课程名称" prop="courseName">
            <el-input v-model="form.courseName"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('form')">确认</el-button>
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <!-- 居中不了，去死吧 -->
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
    <div class="course-info" style="margin-top: 10px;">
      <div style="margin-left: 20px; margin-right: 20px;">
        <i class="el-icon-video-play"></i>
        {{ course.pageViewcount }}
      </div>
      <div style="margin-right: 20px">
        <i class="el-icon-chat-dot-square"></i>
        {{ course.commentNum }}
      </div>
      <div style="margin-right: 20px">
        <i class="el-icon-time"></i>
        {{ course.addTime.split('T')[0] }}&nbsp;{{ course.addTime.split('T')[1] }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlayHeader",
  data() {
    return {
      course: {},
      dialogFormVisible: false,
      form: {
        courseName: ''
      },
    };
  },
  mounted() {
    this.$bus.$on('courseChanged1', (data) => {
      this.course = data;
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged1');
  },
  methods: {
    changeName() {
      this.dialogFormVisible = true
    },
    onSubmit(form) {
      this.dialogFormVisible = false;
      this.course.courseName=this.form.courseName;
      this.request.post("/course/update",this.course)
      .then((res)=>{
        if(res.code=="200"){
          this.$message.success("更新成功");
        }else{
          this.$message.error("更新失败");
        }
      })
    },
  }
}
</script>

<style scoped>
.play-title {
  margin-top: 20px;
  margin-bottom: 20px;
}

.course-info {
  display: flex;
  flex-wrap: nowrap;
  flex-direction: row;
  font-size: small;
  color: #99a9bf;
}
</style>
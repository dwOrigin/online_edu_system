<template>
  <div class="play-window-footer">
    <div class="comment-and-info-card">
      <el-tabs v-model="activeName">
        <el-tab-pane name="intro">
          <div slot="label" class="title-tag">课程介绍</div>
          <el-form :model="form">
            <el-form-item prop="introduce">
              <el-input type="textarea" v-model="form.introduce" :autosize="{ minRows: 10 }"></el-input>
            </el-form-item>
            <el-form-item class="btn">
              <el-button type="primary" @click="submitForm">确认修改</el-button>
              <el-button @click="resetForm">取消</el-button>
            </el-form-item>
          </el-form>
          <!-- <div style="margin: 20px; font-weight: lighter; font-size: small; line-height: 33px;">
            {{ course.title }}
          </div> -->
        </el-tab-pane>
        <el-tab-pane name="comment">
          <div slot="label" class="title-tag">用户评论</div>
          <course-comment-list></course-comment-list>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import CourseCommentList from "@/views/course/CourseCommentList";

export default {
  name: "PlayWindowFooter",
  components: {
    CourseCommentList
  },
  data() {
    return {
      course: {},
      activeName: 'comment',
      form: {
        introduce: ''
      }
    };
  },
  methods: {
    submitForm() {
      this.course.title=this.form.introduce;
      this.request.post("/course/update",this.course)
      .then((res)=>{
        console.log(res);
        if(res.code=="200"){
          this.$message.success("修改成功");
          this.form.introduce='';
        }else{
          this.$message.error("修改失败");
        }
      })
    },
    resetForm(){
      this.form.introduce=this.course.title;
    },
  },
  mounted() {
    this.$bus.$on('courseChanged1', (data) => {
      this.course = data;
      this.form.introduce=this.course.title;
      let promise = this.$axios({
        url: '/records/orCollectedCourse',
        method: 'get',
        params: {
          userId: this.user.userId,
          courseId: this.course.courseId,
        }
      });
      promise.then((res) => {
        if (res.data == 1) {
          this.stared = true;
        } else {
          this.stared = false;
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged1');
  }
}
</script>

<style scoped>
.star-score {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

p {
  font-size: medium;
  line-height: 30px;
  color: #0f1419;
  word-break: break-word;
  margin: 10px auto;
  width: 80%;
  text-indent: 2em;
}

.title-tag {
  font-size: small;
  margin: 0 156px;
}

.comment-and-info-card {
  margin-top: 20px;
  border-radius: 5px;
  border: 2px solid #F1F2F3;
  box-shadow: 0 3px 12px rgb(0 36 153 / 6%);
}

.starLink {
  font-size: x-large;
}

.play-window-footer {
  margin-top: 30px;
  width: 100%;
  height: auto;
  /*background-color: teal;*/
}

.btn {
  display: flex;
  justify-content: center;
}
</style>
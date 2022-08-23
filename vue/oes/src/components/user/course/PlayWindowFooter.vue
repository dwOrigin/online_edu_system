<template>
  <div class="play-window-footer">
    <div class="star-score">
      <div>
        <el-link :underline="false"
                 type="primary"
                 @click="starClass"
                 class="starLink"
                 icon="el-icon-star-off"><span style="font-size: small">
        {{ infoToDisplay }}
      </span></el-link>
      </div>
      <el-rate
          v-model="course.grade"
          disabled
          show-score
          text-color="#ff9900"
          score-template="{value}">
      </el-rate>
    </div>
    <div class="comment-and-info-card">
      <el-tabs v-model="activeName">
        <el-tab-pane name="intro">
          <div slot="label" class="title-tag">课程介绍</div>
          <div style="margin: 20px">
            {{course.intro}}
          </div>
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
import CourseCommentList from "@/components/user/course/CourseCommentList";

export default {
  name: "PlayWindowFooter",
  components: {
    CourseCommentList
  },
  data() {
    return {
      infoToDisplay: '收藏课程',
      course: {},
      activeName: 'comment'
    };
  },
  methods: {
    //点击收藏
    starClass() {
      //  获取登录信息
      let user = window.localStorage.getItem('user');
      if (user === null) {
        this.$message.info('请先登录或注册');
      } else {
        if (this.infoToDisplay === '收藏成功') {
          this.$message.error({
            message: '你已经收藏该课程',
          });
        } else {
          // let promise = this.$axios({
          //     url: '',
          //     method: '',
          //     data:{
          //       userId: user.id,
          //       courseId: this.course.courseId
          //     }
          // });
          let promise = new Promise((a) => {
            a({
              data: {
                stared: true
              }
            });
          });
          promise.then((res) => {
            if (res.data.stared) {
              this.$message.success('收藏成功');
              this.infoToDisplay = '收藏成功';
            }
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
        }
      }
    }
  },
  mounted() {
    this.$bus.$on('courseChanged', (data) => {
      this.course = data;
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged');
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
</style>
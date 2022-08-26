<template>
  <div class="play-window-footer">
    <div class="star-score">
      <div>
        <el-link :underline="false" type="primary" @click="starClass" class="starLink" :icon="starStateIcon"><span
            style="font-size: small">
            收藏
          </span></el-link>
      </div>
      <el-rate v-model="course.grade" disabled show-score text-color="#ff9900" score-template="{value}">
      </el-rate>
    </div>
    <div class="comment-and-info-card">
      <el-tabs v-model="activeName">
        <el-tab-pane name="intro">
          <div slot="label" class="title-tag">课程介绍</div>
          <div style="margin: 20px; font-weight: lighter; font-size: small; line-height: 33px;">
            {{ course.title }}
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
      stared: false,
      course: {},
      activeName: 'comment',
      user: {}
    };
  },
  computed: {
    starStateIcon() {
      if (this.stared) {
        return 'el-icon-star-on';
      } else {
        return 'el-icon-star-off';
      }
    }
  },
  methods: {
    //点击收藏
    starClass() {
      //test
      if (this.user === null) {
        this.$message.info('请先登录或注册');
      } else {
        if (this.stared) {
          // 修改课程收藏信息
          let promise = this.$axios({
            url: '/records/reduceRecordCourseCollect',
            method: 'get',
            params: {
              userId: this.user.userId,
              courseCollectId: this.course.courseId,
            }
          });
          // let promise = new Promise((a) => {
          //   a({
          //     data: {
          //       stared: false
          //     }
          //   });
          // });
          promise.then((res) => {
            if (res.data.code == '200') {
              this.stared = false;
              this.$message.info("取消收藏");
            }
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
        } else {
          // 修改课程收藏信息
          let promise = this.$axios({
            url: '/records/addRecordCourseCollect',
            method: 'get',
            params: {
              userId: this.user.userId,
              courseCollectId: this.course.courseId,
            }
          });
          // let promise = new Promise((a) => {
          //   a({
          //     data: {
          //       stared: false
          //     }
          //   });
          // });
          promise.then((res) => {
            if (res.data.code == '200') {
              this.stared = true;
              this.$message.success("收藏成功");
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
      let user = window.localStorage.getItem('user');
      this.user = JSON.parse(user);
      this.course = data;
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
<template>
  <div class="play-window-footer">
    <div class="star-score">
      <div>
        <el-link :underline="false" type="primary" @click="starClass" class="starLink" :icon="starStateIcon"><span
            style="font-size: small">
            收藏
          </span></el-link>
        <el-link :underline="false"
                 type="primary"
                 @click="starClass"
                 class="starLink"
                 :icon="starStateIcon"><span style="font-size: small">
        收藏
      </span></el-link>
        <el-link :underline="false"
                 type="primary"
                 @click="likeClass"
                 class="starLink">
          <span style="font-size: small; margin-left: 30px">
            <span style="position: relative; top: 5px">
              <svg v-show="liked" t="1661588476006" class="icon" viewBox="0 0 1024 1024" version="1.1"
                   xmlns="http://www.w3.org/2000/svg" p-id="2098" width="32" height="32"><path
                  d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667z"
                  p-id="2099" fill="#1296db"></path></svg>
              <svg v-show="!liked" t="1661588105531" class="icon" viewBox="0 0 1024 1024" version="1.1"
                   xmlns="http://www.w3.org/2000/svg" p-id="1390" width="32" height="32"><path
                  d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667zM544.256 149.333333c39.253333 0 59.498667 36.48 49.888 84.928-7.573333 38.144-21.984 104.426667-43.221333 198.666667-4.512 20.021333 10.56 39.093333 30.912 39.093333h218.666666c6.101333 0 12.16 1.066667 17.909334 3.168 27.445333 9.984 41.674667 40.554667 31.776 68.266667l-85.568 239.573333C744.981333 838.026667 693.301333 874.666667 635.402667 874.666667H223.498667C194.314667 874.666667 170.666667 850.784 170.666667 821.333333V503.498667c0-17.866667 14.144-32.448 31.829333-32.821334l21.866667-0.469333a221.12 221.12 0 0 0 167.381333-82.56c34.346667-42.602667 59.146667-99.306667 74.869333-169.877333C482.101333 166.336 499.552 149.333333 544.266667 149.333333z"
                  p-id="1391" fill="#1296db"></path></svg>
            </span>
            点赞
          </span>
        </el-link>
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
    },
    //点击点赞
    likeClass() {
      //  获取登录信息
      let user = window.localStorage.getItem('user');
      //test
      if (user === null) {
        this.$message.info('请先登录或注册');
      } else {
        // 修改课程点赞信息
        // let promise = this.$axios({
        //     url: '',
        //     method: '',
        //     data:{
        //       userId: user.id,
        //       courseId: this.course.courseId,
        //       like: !(this.liked)//是否点赞
        //     }
        // });
        let promise = new Promise((a) => {
          a({
            data: {
              liked: false
            }
          });
        });
        promise.then((res) => {
          if (!res.data.liked) {
            //取消点赞
            this.liked = false;
          }else{
            // 点赞
            this.liked = true;
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
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
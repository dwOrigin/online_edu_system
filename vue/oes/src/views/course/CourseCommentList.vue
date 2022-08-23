<template>
  <div class="comment-list">
    <div style="margin-left: 100px">
      <!--      <span style="font-size: x-small;"></span>-->
      <el-rate
          v-model="commentRate"
          show-text>
      </el-rate>
    </div>
    <div class="comment-input-box">
      <div>
        <el-avatar
            :size="70"
            :src="user.avatarUrl">
          <span v-if="user.id === undefined">未登录</span>
          <span v-if="user.avatarUrl === '' && user.id !== undefined">{{ user.name }}</span>
        </el-avatar>
      </div>
      <el-input
          type="textarea"
          class="comment-input-area"
          :rows="3"
          placeholder="请输入一条友善的评论内容"
          v-model="userComment">
      </el-input>
      <el-button type="primary"
                 @click="commitComment"
                 plain class="comment-confirm-btn">发布
      </el-button>
    </div>
    <div><h4>好评率：{{ (goodReview / comments.length) * 100 }}%</h4></div>
    <div class="comment-selector">
      <el-button :plain="filterMethod !== 'all'"
                 type="primary"
                 @click="filterMethod = 'all'"
                 class="selector-tag" style="margin-left: 0">全部评论({{ comments.length }})
      </el-button>
      <el-button
          :plain="filterMethod !== 'good'"
          type="primary"
          @click="filterMethod = 'good'"
          plain class="selector-tag">好评({{ goodReview }})
      </el-button>
      <el-button
          :plain="filterMethod !== 'medium'"
          type="primary"
          @click="filterMethod = 'medium'"
          plain class="selector-tag">中评({{ mediumReview }})
      </el-button>
      <el-button
          :plain="filterMethod !== 'bad'"
          type="primary"
          @click="filterMethod = 'bad'"
          plain class="selector-tag">差评({{ badReview }})
      </el-button>
    </div>
    <div>
      <comment v-for="c in
      commentsToDisplay.slice((curPage-1)*pageSize, (curPage)*pageSize)" :comment="c"></comment>
    </div>
    <div class="comment-pagination-container">
      <el-pagination
          background
          hide-on-single-page
          :current-page.sync="curPage"
          :page-size="pageSize"
          :total="commentsToDisplay.length"
          layout="prev, pager, next">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Comment from "@/components/user/course/Comment";

export default {
  name: "CourseCommentList",
  components: {
    Comment
  },
  data() {
    return {
      userComment: '',
      commentRate: null,
      course: {},
      comments: [],
      user: {},
      commentsToDisplay: [],
      filterMethod: 'all',
      goodReview: 0,
      badReview: 0,
      mediumReview: 0,
      curPage: 1,
      pageSize: 10,
    }
  },
  watch: {
    filterMethod(newV) {
      this.filterComment(newV);
    }
  },
  methods: {
    //统计评论数量
    countReview() {
      this.goodReview = 0;
      this.badReview = 0;
      this.mediumReview = 0;
      this.comments.forEach((item) => {
        if (item.score >= 4) {
          this.goodReview++;
        } else if (item.score <= 2) {
          this.badReview++
        } else {
          this.mediumReview++;
        }
      });
    },
    //过滤评论
    filterComment(type) {
      this.commentsToDisplay = [...this.comments];
      if (type === 'all' || type == null) {
      } else if (type === 'bad') {
        this.commentsToDisplay = this.commentsToDisplay.filter((item) => {
          return item.score <= 2;
        });
      } else if (type === 'good') {
        this.commentsToDisplay = this.commentsToDisplay.filter((item) => {
          return item.score >= 4;
        });
      } else if (type === 'medium') {
        this.commentsToDisplay = this.commentsToDisplay.filter((item) => {
          return item.score > 2 && item.score < 4;
        });
      }
    },
    //提交评论
    commitComment() {
      if (this.user.id === undefined) {
        this.$bus.$emit('OpenLoginDialog');
      } else {
        //提交评论信息
        // let promise = this.$axios({
        //   url: '',
        //   method: '',
        //   data: {
        //     userId: this.user.id,
        //     courseId: this.course.courseId,
        //     comment: this.userComment,
        //     rate: this.commentRate
        //   }
        // });
        let promise = new Promise((a) => {
          a({
            data: {result: false}
          });
        });
        promise.then((res) => {
          if (res.data.result) {
            this.$message.success('评论成功');
            this.userComment = '';
            this.updateComments();
          } else {
            this.$message.error('请勿重复评论');
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
    },
    //更新评论
    updateComments() {
      //获取课程评论
      // let promise = this.$axios({
      //   url: '',
      //   method: '',
      //   data: {
      //     courseId: this.course.courseId
      //   }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            comments: [
              {
                score: 4,
                commentId: 115,
                commenterId: 555,
                content: '这是评论内容',
                time: '2022/05/16 13:00:00',
              },
              {
                score: 1,
                commentId: 115,
                commenterId: 555,
                content: '这是评论内容',
                time: '2022/05/16 13:00:00',
              }
            ]
          }
        });
      });
      promise.then((res) => {
        //测试用
        let c = res.data.comments;
        c = [...c, ...c];
        c = [...c, ...c];
        c = [...c, ...c];
        c = [...c, ...c];
        c = [...c, ...c];
        c = [...c, ...c];
        this.comments = c;
        this.countReview();
        this.filterComment('all');
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.$bus.$on('courseChanged', (c) => {
      this.course = c;
      this.updateComments();
    });
    let u = window.localStorage.getItem('user');
    if (u !== null) {
      this.user = JSON.parse(u);
    }
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged');
  },
  // directives:{
  //   autoClick:{
  //     bind(el){
  //       el.click();
  //       console.log('click');
  //     }
  //   }
  // }
}
</script>


<style scoped>
.comment-confirm-btn {
  width: 80px;
  height: 80px;
}

.comment-input-area {
  width: 70%;
  margin: 0 10px;
}

.comment-input-box {
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.comment-pagination-container {
  margin-top: 30px;
  display: flex;
  justify-content: center;
}

.selector-tag {
  margin: 20px 10px;
}

.comment-list {
  width: 90%;
  margin: 30px auto;
}

</style>
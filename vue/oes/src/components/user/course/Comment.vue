<template>
  <div class="comment">
    <div class="comment-header">
      <div class="comment-avatar" @click="talkto">
        <el-avatar :size="40" :src="commenter.picImg">
          <span v-if="commenter.picImg == '' || commenter.picImg == null">{{  commenter.userName  }}</span>
        </el-avatar>
      </div>
      <div class="comment-info">
        <div style="font-size: 16px;">{{  commenter.userName  }}</div>
        <div class="time-and-score">
          <div style="margin-right: 10px"> {{comment.addtime}}</div>
          <el-rate v-model="comment.praiseCount" disabled text-color="#ff9900" score-template="{value}">
          </el-rate>
        </div>
      </div>
    </div>
    <div class="comment-content">
      <p style="width: 100%">
        {{  comment.content  }}
      </p>
    </div>
  </div>

</template>

<script>
export default {
  name: "Comment",
  data() {
    return {
      commenter: {},
      myComment: this.comment,
      user:{}
    }
  },
  props: {
    comment: {
    }
  },
  watch: {
    myComment: {
      deep: true,
      immediate: true,
      handler: function (newV) {
        this.refreshCommenter(newV.commenterId);
      }
    }
  },
  methods: {
    //更新评论者信息
    refreshCommenter(id) {
      //根据评论者id获取评论者信息
      let promise = this.$axios({
        url: '/user/findOne',
        method: 'get',
        params: {
          id: this.myComment.userId
        }
      });
      // let promise = new Promise((a) => {
      //   a({
      //     data: {user: {
      //         name: '评论者555',
      //         avatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      //       }}
      //   });
      // });
      promise.then((res) => {
        this.commenter = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    talkto() {
      let user = window.localStorage.getItem('user');
      this.user=JSON.parse(user);
      if (this.commenter.userId != this.user.userId) {
        this.$router.push({
          name: 'personal',
          query: {
            select: 'message',
            talk:this.commenter.userId
          }
        });
      }
    }
  }
}
</script>

<style scoped>
.comment-content {
  margin-left: 60px;
  font-size: 16px;
  line-height: 26px;
  color: #0f1419;
  word-break: break-word;
  margin-bottom: 20px;
  margin-top: 20px;
  /*background-color: teal;*/
}

.time-and-score {
  color: #99a9bf;
  font-size: 12px;
  margin-top: 5px;
  display: flex;
  align-items: center;
}

.comment-info {
  font-weight: lighter;
}

.comment-avatar {
  margin-right: 20px;
}

.comment-header {
  display: flex;
  margin-top: 10px;
}

.comment {
  border-bottom: 1px solid #edeff2;
  margin-top: 15px;
}
</style>
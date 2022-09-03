<template>
  <div class="comment">
    <div class="comment-header">
      <div class="comment-avatar">
        <el-avatar :size="40" :src="commenter.picImg">
          <span v-if="commenter.picImg == ''">{{ commenter.name }}</span>
        </el-avatar>
      </div>
      <div class="comment-info">
        <div style="font-size: 16px;">{{ commenter.userName }}</div>
        <div style="float:right;display: inline-block;">
          <i class="el-icon-delete" @click="deleteComment"></i>
          <!-- 需要参数，但现在没有，以后会有的 -->
        </div>
        <div class="time-and-score">
          <div style="margin-right: 10px"> {{comment.addtime}} </div>
          <el-rate v-model="comment.praiseCount" disabled text-color="#ff9900" score-template="{value}">
          </el-rate>
        </div>
      </div>
    </div>
    <div class="comment-content">
      <p style="width: 100%">
        {{ comment.content }}
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
      course: {}
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
      promise.then((res) => {
        this.commenter = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    deleteComment() {
      this.$alert('你将删除这条评论', '提示', {
        confirmButtonText: '确定',
        callback: action => {
          this.request.get('/comment/delete', {
            params: {
              commentId: this.myComment.commentId
            }
          })
            .then((res) => {
              if (res.code == "200") {
                this.$message.success("删除成功")
                this.request.get('/course/getById', {
                  params: {
                    id: this.myComment.totalId
                  }
                })
                  .then((res) => {
                    this.$bus.$emit('courseChanged1',res);
                  })
              } else {
                this.$message.error("删除失败")
              }
            })
        }
      });
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
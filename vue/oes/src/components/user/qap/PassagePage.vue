<template>
  <div
      style="overflow: hidden; margin-bottom: -50px">
    <div class="passage-page">
      <div class="title">{{ passage.title }}</div>
      <div class="time"> {{ passage.time }} 修改</div>
      <div class="content">
        {{ passage.content }}
      </div>
      <div class="footer">
        <div>
          <el-button
              @click="handleClick('like')"
              type="primary" plain>{{ likeBtnContent }}({{ passage.like }})
          </el-button>
        </div>
        <div>
          <el-button
              @click="handleClick('comment')"
              ype="primary" plain>评论文章
          </el-button>
        </div>
      </div>
      <div>
        <div style="margin-top: 10px">
          <answer
              typeM="PassageComment"
              v-for="obj in passage.comments.slice(0, length)" :obj="obj"></answer>
        </div>
      </div>
    </div>
    <el-dialog
        :visible.sync="CommentDialogVisible"
        center
        :show-close="false"
        append-to-body
        modal-append-to-body
        width="30%">
      <el-input
          type="textarea"
          ref="cc"
          :rows="5"
          maxlength="100"
          show-word-limit
          placeholder="请输入你的评论"
          v-model="cc">
      </el-input>
      <span slot="footer" class="dialog-footer">
      <el-button
          plain
          style='padding-left: 30px; padding-right: 30px; margin-right: 50px'
          type="primary" @click="handleClickFooter('ask')">评论
      </el-button>
        <el-button
            plain
            style='padding-left: 30px; padding-right: 30px'
            type="info" @click="handleClickFooter('cancel')">
          取消
        </el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Answer from "@/components/user/qap/Answer";

export default {
  name: "PassagePage",
  components: {
    Answer
  },
  data() {
    return {
      number: 10,
      passage: {
        comments: []
      },
      likeBtnContent: '点赞',
      CommentDialogVisible: false,
      cc: '',
      length: 5,
    };
  },
  methods: {
    loadPassage() {
      let id = this.$route.query.pId;
      //获取文章详细信息
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data:{
      //       pId: id
      //     }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            passage: {
              pId: 8889,
              title: '文章标题',
              time: '2022/12/26',
              like: 1888,
              content: '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容这是' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容' +
                  '这是文章内容这是文章内容这是文章内容这是文章内容这是文章内容文章内容',
              comments: [
                {
                  cId: 10086,//评论id
                  commenterName: '评论者名字',
                  commenterAvatarUrl: 'https://pic4.zhimg.com/v2-d41c2ceaed8f51999522f903672a521f_xs.jpg?source=1940ef5c',
                  content: '这是评论内容',
                  time: '2022/8/71',
                  like: 998
                }
              ]
            }
          }
        });
      });
      promise.then((res) => {
        //@test
        let c = res.data.passage.comments;
        c = [...c, ...c];
        c = [...c, ...c];
        c = [...c, ...c];
        c = [...c, ...c];
        c = [...c, ...c];
        res.data.passage.comments = c;
        this.passage = res.data.passage;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    handleClick(btnName) {
      let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
        return;
      }
      if (btnName === 'like') {
        //点赞文章
        // let promise = this.$axios({
        //   url: '',
        //   method: '',
        //   data: {
        //     pId: this.passage.pId,
        //     uId: JSON.parse(usr).id
        //   }
        // });
        let promise = new Promise((a) => {
          a({
            data: {
              result: false
            }
          });
        });
        promise.then((res) => {
          if (res.data.result) {
            this.passage.like++;
            this.likeBtnContent = '已点赞';
          } else {
            this.$message.error('请勿重复点赞');
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      } else if (btnName === 'comment') {
        this.CommentDialogVisible = true;
      }
    },
    handleClickFooter(btnName) {
      if (btnName === 'cancel') {

      } else if (btnName === 'ask') {
        if (this.cc.length < 10) {
          this.$message.error('评论不能少于10个字');
          return;
        }
        let user = JSON.parse(window.localStorage.getItem('user'));
        //评论文章
        // let promise = this.$axios({
        //   url: '',
        //   method: '',
        //   data: {
        //     comment: this.cc,
        //     userId: user.id,
        //     pId: this.passage.pId
        //   }
        // });
        let promise = new Promise((a) => {
          a({
            data: {
              result: true
            }
          });
        });
        promise.then((res) => {
          let ret = res.data.result;
          if (ret) {
            this.$message.success('评论成功')
            this.loadPassage();
          } else {
            this.$message.error('请勿重复评论');
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
      this.cc = '';
      this.CommentDialogVisible = false;
    }
  },
  mounted() {
    this.loadPassage();
    this.$bus.$on('scrollToBottom', (data) => {
      this.length += 2;
    });
  },
  beforeDestroy() {
    this.$bus.$off('scrollToBottom');
  }
}
</script>

<style scoped>
.content {
  word-wrap: break-word;
  margin: 10px 20px;
}

.footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #CCCCD8;
  font-size: small;
  /*border: 1px solid #ECECED;*/
  padding: 10px;
  border-top: 2px solid #F9F9F9;
  border-bottom: 2px solid #F9F9F9;
}

.paragraph {
  text-indent: 2em;
  margin: 0 0 30px 0;
}

.content {
  padding: 10px 20px;
  line-height: 30px;
}

.time {
  color: #999AAA;
  font-size: small;
  margin: 10px 0;
}

.title {
  font-size: x-large;
  font-weight: bold;
}

.passage-page {
  border: 1px solid #ECECED;
  width: 900px;
  /*background-color: #F0EFE2;*/
  margin: 10px auto 20px auto;
  /*border: 1px solid #DAD8C1;*/

  padding: 20px;
  /*height: 100px;*/
  /*background-color: teal;*/
}
</style>
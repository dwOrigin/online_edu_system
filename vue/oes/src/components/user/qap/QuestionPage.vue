<template>
  <div class="questionPage">
    <div style="box-shadow: 0 10px 10px rgba(0, 0, 0, .12)">
      <div class="header">
        <div class="title">{{question.title}}</div>
        <div class="author">
          <!-- <el-avatar shape="square"
                     style="margin: 0 10px"
                     :size="30" :src="question.askerAvatar">
            <span v-if="question.askerAvatar === ''">
              {{question.askerName}}
            </span>
          </el-avatar> -->
          {{user.userName}}&nbsp;&nbsp;
        </div>
        <div class="content">
          {{question.content}}
        </div>
        <div class="header-footer">
          <el-button
              @click="handleClick"
              size="medium" type="primary" :plain="true" style="width: 100px">
            写回答
          </el-button>
          <div style="color: #8590A6; font-size: x-small">
          <span>
            <i class="el-icon-s-data"></i>
            被浏览 {{question.browseCount}}
          </span>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <span>
            <i class="el-icon-chat-square"></i>
            {{question.replyCount}}条回答
          </span>
          </div>

        </div>

      </div>
    </div>
    <div class="answer-list">
      <div class="answers">
        <div style="width: 95%; margin: 0 auto">
          <div class="space-between">
            <div style="color: #121212; font-size: small; font-weight: bold">{{question.replyCount}} 个回答</div>
            <el-dropdown>
              <div class="sort">
                默认排序
                <i class="el-icon-arrow-down"></i>
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>默认排序</el-dropdown-item>
<!--                i am lazy, fuck your sort!-->
<!--                <el-dropdown-item>时间排序</el-dropdown-item>-->
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <answer v-for="obj in answers" :obj="obj" typeM="Answer"></answer>
        </div>
      </div>
    </div>
    <el-dialog
        :visible.sync="dialogVisible"
        center
        :show-close="false"
        append-to-body
        modal-append-to-body
        width="30%">
      <el-input
          type="textarea"
          :rows="5"
          maxlength="100"
          show-word-limit
          placeholder="请输入你的回答"
          v-model="answer">
      </el-input>
      <span slot="footer" class="dialog-footer">
      <el-button
          plain
          style='padding-left: 30px; padding-right: 30px; margin-right: 50px'
          type="primary" @click="handleClickFooter('answer')">回答
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
  inject: ['reload'],
  name: "QuestionPage",
  components: {
    Answer
  },
  data() {
    return {
      number: 10,
      question: {},
      answers: [],
      dialogVisible: false,
      answer: '',
      user:{}
    };
  },
  methods: {
    handleClickFooter(btnName) {
      if (btnName === 'cancel') {

      } else if (btnName === 'answer') {
        if (this.answer.length < 10) {
          this.$message.error('回答不能少于10个字');
          return;
        }
        let user = JSON.parse(window.localStorage.getItem('user'));
        //回答问题
        let promise = this.$axios({
          url: '/questionscomment',
          method: 'post',
          data: {
            content: this.answer,
            cusId: user.userId,
            questionId: this.question.id
          }
        });
        promise.then((res) => {
          let ret = res.data;
          if (ret) {
            this.$message.success('回答成功');
            this.refreshComment(this.$route.query.qId);
          } else {
            this.$message.error('你已经回答过');
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
      this.answer = '';
      this.dialogVisible = false;
    },
    handleClick() {
      let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
        return;
      }else{
        this.dialogVisible = true;
        console.log('this.dialogVisible = true;');
      }
    },
    refreshQuestion(qId) {
      // 获取问题详细信息
      let promise = this.$axios({
        url: '/questions/id',
        method: 'get',
        params: {
          id: qId
        }
      });
      promise.then((res) => {
        this.question = res.data;
        this.getUserName()
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    refreshComment(qId) {
      // 获取问题答案
      let promise = this.$axios({
        url: '/questionscomment/detail',
        method: 'get',
        params: {
          id: qId
        }
      });
      promise.then((res) => {
        this.answers = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
     getUserName(){
      //获取提问者姓名
      // let promise = this.$axios.get('http://localhost:8081/user/findOne');
      let promise = this.$axios({
        url: '/user/findOne',
        method: 'get',
        params: {
          id:this.question.cusId
        }
      });
       promise.then((res) => {
        this.user = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.refreshQuestion(this.$route.query.qId);
    this.refreshComment(this.$route.query.qId);
  }
}
</script>

<style scoped>
.sort:hover {
  cursor: pointer;
}

.sort {
  font-size: x-small;
  color: #9498BA;

}

.space-between {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 20px;
  padding-bottom: 10px;
  border-bottom: 2px solid #F9F9F9;
}

.answers {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 900px;
  margin: 20px auto 0 auto;
  background-color: #FFFFFF;
}

.answer-list {
  overflow: hidden;
  margin-bottom: -50px;
  padding-bottom: 20px;
  background-color: #F6F6F6;
}

.author {
  font-size: x-small;
  display: flex;
  align-items: center;
  margin: 10px auto 5px 0;
  /*background-color: #F6F6F6;*/
  padding: 10px 0;
  /*border-radius: 5px;*/
  /*border: 1px solid #EBEBEB;*/
}

.header {
  width: 900px;
  padding-top: 50px;
  margin: 0px auto 0px auto;
  padding-bottom: 20px;
}

.header-footer {
  display: flex;
  margin-top: 20px;
  /*height: 30px;*/
  align-items: center;
  justify-content: space-between;
}

.content {
  font-size: x-small;
  line-height: 22px;
  width: 800px;
}

.title {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  font-size: 23px;
  font-weight: bold;
}

.questionPage {
}
</style>
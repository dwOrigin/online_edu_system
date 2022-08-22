<template>
  <div class="qa-recommend">
    <div class="qa-part">
      <el-tabs type="border-card" class="hot-list" v-model="$route.params.select">
        <el-tab-pane name="q">
          <div slot="label" class="title-tag">问题</div>
          <QuestionCard v-for="obj in questionList"
                        :obj="obj"
                        type="question"></QuestionCard>
        </el-tab-pane>
        <el-tab-pane name="p">
          <div slot="label" class="title-tag">文章</div>
          <QuestionCard v-for="obj in passageList"
                        :obj="obj"
                        type="passage"></QuestionCard>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="ask-part">
      <div>
        <el-button
            @click="handleClickMQ"
            type="primary" round plain>
          <i class="el-icon-question"></i>
          我的提问
        </el-button>
      </div>
      <div style="margin-top: 20px">
        <el-button
            @click="handleClickAsk"
            type="primary" round plain>
          <i class="el-icon-edit"></i>
          提出问题
        </el-button>
      </div>
    </div>
    <ask-dialog></ask-dialog>
  </div>
</template>

<script>
import QuestionCard from "@/components/user/qap/QuestionCard";
import AskDialog from "@/components/user/qap/AskDialog";

export default {
  name: "QAPMain",
  components: {
    QuestionCard,
    AskDialog
  },
  data() {
    return {
      questionList: [],
      passageList: [],
    };
  },
  methods: {
    handleClickAsk() {
      let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$message.info('请先登录或注册');
        return;
      }
      this.$bus.$emit('openAskDialog');
    },
    handleClickMQ() {
      this.$router.push({
        name: 'personal',
        query: {
          select: 'question'
        }
      });
      return true;
    },
    refreshQuestion(append) {
      //获取推荐问题
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data:{}
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            questionList: [
              {
                title: '这是一个问题?',
                hottestAnswer: '我是这个问题最好的回答',
                hottestAnswererName: '最好的回答者',
                like: '999万',//赞同数
                answerCnt: 10,
                qId: 888
              }
            ]
          }
        });
      });
      promise.then((res) => {
        if (append === false) {
          this.questionList = res.data.questionList;
          this.questionList = [...this.questionList, ...this.questionList];
          this.questionList = [...this.questionList, ...this.questionList];
          this.questionList = [...this.questionList, ...this.questionList];
        } else {
          this.questionList = [...this.questionList, ...res.data.questionList];
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    refreshPassage(append) {
      //获取热门文章
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data:{}
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            passageList: [
              {
                title: '我是文章标题',
                content: '我是文章内容',
                like: '16万',
                pId: 999
              }
            ]
          }
        });
      });
      promise.then((res) => {
        if (append === false) {
          this.passageList = res.data.passageList;
          this.passageList = [...this.passageList, ...this.passageList];
          this.passageList = [...this.passageList, ...this.passageList];
          this.passageList = [...this.passageList, ...this.passageList];
        } else {
          this.passageList = [...this.passageList, ...res.data.passageList];
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.refreshQuestion(false);
    this.refreshPassage(false);
    this.$bus.$on('scrollToBottom', (data) => {
      //  添加
      if (this.$route.params.select === 'q') {
        this.refreshQuestion(true);
      } else if (this.$route.params.select === 'p') {
        this.refreshPassage(true);
      }
    });
  },
  beforeDestroy() {
    this.$bus.$off('scrollToBottom');
  }
}
</script>

<style scoped>
.hot-list {
  height: max-content;
  overflow: hidden;
  /*background-color: teal;*/
}

.title-tag {
  padding: 0 145px;
}

.qa-part {
  width: 72%;
  border-radius: 5px;
  padding: 5px;
}

.ask-part {
  /*overflow: visible;*/
  width: 27%;
  height: 500px;
  /*background-color: green;*/
  position: sticky;
  top: 100px;
  margin-left: 20px;
}

.qa-recommend {
  width: 1000px;
  margin: 10px auto;
  /*background-color: teal;*/
  display: flex;
  justify-content: space-between;
}
</style>
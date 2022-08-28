<template>
  <div class="my-question">
    <question-record v-for="obj in questionList" :key="obj.id" :obj="obj">
    </question-record>
  </div>
</template>

<script>
import QuestionRecord from "@/components/user/qap/QuestionRecord";

export default {
  name: "MyQuestion",
  components: {
    QuestionRecord
  },
  data() {
    return {
      questionList: {}
    };
  },
  methods: {
    refreshMyQuestion() {
      let usr = JSON.parse(window.localStorage.getItem('user'));
      //获取用户的提问信息
      let promise = this.$axios({
          url: '/questions/getbyuserid',
          method: 'get',
          params:{
            userid: usr.userId
          }
      });
      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       questions: [
      //         {
      //           title: '这是一个问题1?',
      //           hottestAnswer: '我是这个问题最好的回答',
      //           hottestAnswererName: '最好的回答者',
      //           like: '999万',//赞同数
      //           answerCnt: 10,
      //           time: '2022/6/18',//提出时间
      //           qId: 888,
      //         },
      //         {
      //           title: '这是一个问题2?',
      //           hottestAnswer: '我是这个问题最好的回答',
      //           hottestAnswererName: '最好的回答者',
      //           like: '999万',//赞同数
      //           answerCnt: 10,
      //           time: '2022/6/18',//提出时间
      //           qId: 889
      //         },
      //         {
      //           title: '这是一个问题3?',
      //           hottestAnswer: '我是这个问题最好的回答',
      //           hottestAnswererName: '最好的回答者',
      //           like: '999万',//赞同数
      //           time: '2022/6/18',//提出时间
      //           answerCnt: 10,
      //           qId: 8
      //         }
      //       ]
      //     }
      //   });
      // });
      promise.then((res) => {
        this.questionList = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.refreshMyQuestion();
    this.$bus.$on('refreshQ', (data) => {
      this.refreshMyQuestion();
    });
  },
  beforeDestroy() {
    this.$bus.$off('refreshQ');
  }
}
</script>

<style scoped>
.my-question {
  /*min-height: 600px;*/
}
</style>
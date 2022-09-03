<template>
  <div class="question-record">
    <div class="q-info" @click="handleClick">
      <el-link :underline="false" class="question-title">
        {{ question.title }}
      </el-link>
      <div class="time-reply" style="font-size: small; color: #999999">
        <div>{{ question.addTime}}&nbsp;&nbsp;提出</div>
        <div>共{{ question.replyCount }}条回复</div>
      </div>
    </div>
    <div class="delete" @click="handleDelete">
      <el-button>删除提问</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuestionRecord",
  props: {
    obj: {}
  },
  data() {
    return {
      question: this.obj
    };
  },
  methods: {
    handleClick() {
      this.$router.push({
        name: 'questionPage',
        query: {
          qId: this.question.id
        }
      });
    },
    handleDelete() {
      //删除问题
      let promise = this.$axios({
        url: '/questions/delete',
        method: 'get',
        params: {
          id:this.question.id
        }
      });
      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       result: true
      //     }
      //   });
      // });
      promise.then((res) => {
        this.$message.success('删除成功');
        this.$bus.$emit('refreshQ');
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  }
}
</script>

<style scoped>
.time-reply {
  display: flex;
  justify-content: space-between;
}

.question-title {
  font-size: medium;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.q-info {
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: white;
  padding: 10px;
  border-radius: 10px;
  width: 500px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.delete {
  width: 50px;
  margin: auto 0;
  /*background-color: #409EFF;*/
}

.question-record {
  /*position: relative;*/
  /*left: 100px;*/
  width: 700px;
  margin: 20px 0;
  height: 100px;
  display: flex;
  justify-content: space-around;
}

</style>
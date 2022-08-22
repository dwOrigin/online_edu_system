<template>
<div class="question-record">
  <div class="q-info" @click="handleclick">
    <el-link :underline="false" class="question-title">
      胖哥俩肉蟹煲大量使用过期食材，把「隔夜死蟹」当现杀活蟹卖，这对人体有何危害？还有哪些信息值得关注？
    </el-link>
    <div class="time-reply" style="font-size: small; color: #999999">
      <div>2022/08/16提出</div>
      <div>共263条回复</div>
    </div>
  </div>
  <div class="delete" @click="handledelete">
    <el-button>删除提问</el-button>
  </div>
</div>
</template>

<script>
export default {
  name: "QuestionRecord",
   props: {
    type: {
      type: String,
    },
    // obj:{
    //   title: '这是一个问题?',
    //   hottestAnswer: '我是这个问题最好的回答',
    //   hottestAnswererName: '最好的回答者',
    //   like: '999万',//赞同数
    //   answerCnt: 10,
    //   qId: 888
    // }
  },
  data(){
    return {
        dataObj: {
          title: '这是一个问题?',
          hottestAnswer: '我是这个问题最好的回答',
          hottestAnswererName: '最好的回答者',
          like: '999万',//赞同数
          answerCnt: 10,
          qId: 888
        }
    };
  },
  methods:{
    handleclick(){
      this.$router.push({
          name: 'questionPage',
          query:{
            qId: this.dataObj.qId
          }
        });
    },
    handledelete(){
      console.log(2);
    }
  },
  mounted(){
    //后端获取question
  }
}
</script>

<style scoped>
.time-reply{
  display: flex;
  justify-content: space-between;
}
.question-title{
  font-size: medium;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.q-info{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: white;
  padding: 10px;
  border-radius: 10px;
  width: 500px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.delete{
  width: 50px;
  margin: auto 0;
  /*background-color: #409EFF;*/
}
.question-record{
  /*position: relative;*/
  /*left: 100px;*/
  width: 700px;
  margin: 20px 0;
  height: 100px;
  display: flex;
  justify-content: space-around;
}

</style>
<template>
<div id="main"  class="fragment-container">
  <div class="btn">
      <el-button type="primary" round @click="returnQA">返回</el-button>
    </div>   
 <question-page></question-page>
 <!-- 有点空隙，但是也凑活，就这样吧 -->
</div>
</template>

<script>
import QuestionPage from "@/views/qap/QuestionPage.vue";
export default {
  name: "Modify_Lesson_Manage",
  components:{
    QuestionPage,
  },
   methods:{
    returnQA(){
        this.$router.push('/qa_manage')
    }
  }
}
</script>

<style scoped>
#main{
  width: 100%;
}
.btn{
    margin-top: 20px;
    margin-left: 50px;
}
.fragment-container{
  height: 100vh;
  overflow-y: scroll;
  max-width: 100vw;
  margin: 0 auto;
}
</style>
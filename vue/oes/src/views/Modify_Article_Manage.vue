<template>
<div id="main" class="fragment-container">
    <div class="btn">
      <el-button type="primary" round @click="returnAritcle">返回</el-button>
    </div>   
 <passage-page></passage-page>
</div>
</template>

<script>
import PassagePage from "@/views/qap/PassagePage";

export default {
  name: "Modify_Article_Manage",
  components:{
    PassagePage
  },
  methods:{
    returnAritcle(){
        this.$router.push('/article_manage')
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
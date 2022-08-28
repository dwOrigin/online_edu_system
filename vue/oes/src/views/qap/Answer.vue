<template>
  <div class="answer">
    <div style="display: flex; align-items: center">
      <!-- <el-avatar
          :src="dataObj.commenterAvatarUrl"
          shape="square">
        <span v-if="dataObj.commenterAvatarUrl === ''">{{ dataObj.commenterName }}</span>
      </el-avatar> -->
      &nbsp;&nbsp;&nbsp;
      <span style="font-size: x-small; font-weight: bold; color:#4C4444;">{{ user.userName }}</span>
      &nbsp;&nbsp;&nbsp;  
    <div style=" display: inline-block;text-align:right; ">
     <i class="el-icon-delete" @click="deleteComment(dataObj)"></i>
    </div>
    </div>
    <div class="content">
      {{ dataObj.content }}
    </div>
    <div class="footer">
      <div>发布于 {{ dataObj.addTime }}</div>
      <div>
        <el-button
            type="primary" size="small" :plain="true">赞同({{ dataObj.praiseCount }})
        </el-button>
      </div>
    </div>

  </div>
</template>


<script>
export default {
  inject: ['reload'],
  name: "Answer",
  props:{
    obj:{},
    typeM:{}
  },
  data(){
    return {
      dataObj: this.obj,
      type: this.typeM,
      user:{}
    };
  },
  mounted(){
    this.getUserName()
  },
  methods:{
   getUserName(){
      //获取提问者姓名
      let promise = this.$axios({
        url: 'http://localhost:8081/user/findOne',
        method: 'get',
        params: {
          id:this.dataObj.cusId
        }
      });
       promise.then((res) => {
        this.user = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    deleteComment(dataObj){
      this.$axios.get('http://localhost:8081/questionscomment/delete',{
        params:{id:dataObj.id}
    })
    this.reload();
    }
  }
}
</script>

<style scoped>
.footer{
  color: #B8BFBC;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.content{
  font-size: x-small;
  font-weight: lighter;
  margin-top: 20px;
  margin-bottom: 20px;
}
.answer{
  padding-top: 20px;
  font-size: x-small;
  padding-bottom: 20px;
  border-bottom: 2px solid #F9F9F9;
}

</style>
<template>
  <div class="answer">
    <div style="display: flex; align-items: center">
      <el-avatar
          :src="dataObj.commenterAvatarUrl"
          shape="square">
        <span v-if="dataObj.commenterAvatarUrl === ''">{{ dataObj.commenterName }}</span>
      </el-avatar>
      &nbsp;&nbsp;&nbsp;
      <span style="font-size: x-small; font-weight: bold; color:#4C4444;">{{ dataObj.commenterName }}</span>
      &nbsp;&nbsp;&nbsp;  
    <div style=" display: inline-block;text-align:right; ">
     <i class="el-icon-delete" @click="deleteComment"></i>
    </div>
    </div>
    <div class="content">
      {{ dataObj.content }}
    </div>
    <div class="footer">
      <div>发布于 {{ dataObj.time }}</div>
      <div>
        <el-button
            @click="accept"
            type="primary" size="small" :plain="true">{{ likeC }}({{ dataObj.like }})
        </el-button>
      </div>
    </div>

  </div>
</template>


<script>
export default {
  name: "Answer",
  props:{
    obj:{},
    typeM:{}
  },
  data(){
    return {
      dataObj: this.obj,
      type: this.typeM
    };
  },
  methods:{
    deleteComment(){
      this.$alert('你将删除这条评论', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `删除成功`
            });
          }
        });
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
<template>
<div class="comment-list">
  <div><h4>好评率：96%</h4></div>
  <div class="comment-selector">
    <el-button plain class="selector-tag" style="margin-left: 0">全部评论(1100)</el-button>
    <el-button plain class="selector-tag">好评(444)</el-button>
    <el-button plain class="selector-tag">中评(444)</el-button>
    <el-button plain class="selector-tag">差评(444)</el-button>
  </div>
  <div>
    <comment v-for="commentObj in 10" :key="commentObj"></comment>
  </div>
  <div class="comment-pagination-container">
    <el-pagination
        background
        layout="prev, pager, next"
        :total="1000">
    </el-pagination>
  </div>
</div>
</template>

<script>
import Comment from "@/views/course/Comment";
export default {
  name: "CourseCommentList",
  components: {
    Comment
  },
  data(){
    return{
      userComment: ''
    }
  }
}
</script>


<style scoped>
.comment-confirm-btn{
  width: 80px;
  height: 80px;
}
.comment-input-area{
  width: 70%;
  margin: 0 10px;
}
.comment-input-box{
  height: 120px;
  /*background-color: teal;*/
  display: flex;
  align-items: center;
  justify-content: center;
}
.comment-pagination-container{
  margin-top: 30px;
  display: flex;
  justify-content: center;
}
.selector-tag{
  margin: 20px 10px;
}
.comment-list{
  width: 90%;
  margin: 30px auto;
}

</style>
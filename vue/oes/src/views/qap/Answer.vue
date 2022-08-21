<template>
<div class="answer">
  <div style="display: flex; align-items: center;">
    <!-- <div style="display:inline-block"> -->
    <el-avatar
        src="https://pic4.zhimg.com/v2-d41c2ceaed8f51999522f903672a521f_xs.jpg?source=1940ef5c"
        shape="square"></el-avatar>
    &nbsp;&nbsp;&nbsp;  
     <span style="font-size: x-small; font-weight: bold; color:#4C4444;">匿名用户</span>
    <!-- </div> -->
    &nbsp;&nbsp;&nbsp;  
    <div style=" display: inline-block;text-align:right; ">
     <i class="el-icon-delete" @click="deleteComment"></i>
    </div>
    
  </div>
  <div class="content">
    因为那是他们向往的生活，不是我们的，一群玩乐一天就收入百十万的人表演给一群可能一生也存不到百十万的普通人看的所谓向往的生活，在镜头前的做作和表演痕迹明显，早几期的老戏骨们表演出来的戏外家常人生的有趣片断没有了，没什么水花的新人来了反而很做作，明显是秀给大家看，没看点，老成员的千篇一律没新意，没有真正接地气的环节，生活难度毫无挑战，一群人去现成营地野营的感觉，看他们还不如我们自己去。
  </div>
  <div class="footer">
    <div>发布于 2022-08-18 11:06</div>
  </div>

</div>
</template>

<script>
export default {
  name: "Answer",
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
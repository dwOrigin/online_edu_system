<template>
<div>
<ul class="tag-list">
  <li v-for="tag in tagObjList" :key="tag.title">
    <el-link :underline="false" class="tag-item">{{tag.title}}</el-link>
  </li>
  <el-link :underline="false" class="tag-item">个人中心</el-link>
  <el-link :underline="false" class="tag-item" v-if="user == null">登录|注册</el-link>
  <a href="#" class="mini-img-container" v-if="user != null">
<!--    <el-image-->
<!--        style="width: 100%; height: 100%"-->
<!--        src=""-->
<!--        fit="fill"></el-image>-->
    <el-avatar size="small" src="https://tse4-mm.cn.bing.net/th/id/OIP-C.kRklM9DmpC8zqSI4_Bq9mAHaHa?pid=ImgDet&rs=1"></el-avatar>
  </a>


</ul>
</div>
</template>

<script>
export default {
  name: "NavRight",
  data(){
    return{
      tagObjList: [
        {title: '文章', href: '#'},
        {title: '问答', href: '#'},
        {title: '讲师', href: '#'},
      ],
      user: {}
    }
  }
}
</script>

<style scoped>
.mini-img-container {
  /*border-radius: 50%;*/
  /*height: 30px;*/
  /*width: 30px;*/
  /*overflow: hidden;*/
  margin-left: 0;
  margin-right: 5px;
}
.tag-list{
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.tag-item{
  margin: 0 10px;
  font-size: medium;
}
</style>
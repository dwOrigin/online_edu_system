<template>
<div class="star">
  <div class="header">
    <el-input placeholder="搜索已收藏课程" v-model="searchKey" @keyup.enter.native="handleSearch" class="input-with-select">
      <el-button slot="append" icon="el-icon-search" @click="handleSearch"></el-button>
    </el-input>
<!--    <el-button style="margin: 0 50px">清除所有记录</el-button>-->
  </div>
  <div class="content">
    <course-record v-for="obj in number" :key="obj" type="star"></course-record>
  </div>
</div>
</template>

<script>
import CourseRecord from "@/components/user/personal/CourseRecord";
export default {
  name: "Star",
  components:{
    CourseRecord
  },
  data(){
      return {
        searchKey:'',
        number: 10
      };
  },
  created() {
    window.addEventListener('scroll', this.scrollBottom);
  },
  destroyed() {
    window.removeEventListener('scroll', this.scrollBottom) //页面离开后销毁监听事件
  },
  methods: {
    scrollBottom(){
      let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      let clientHeight = document.documentElement.clientHeight;
      let scrollHeight = document.documentElement.scrollHeight;
      if (scrollTop + clientHeight >= scrollHeight) {
        this.number += 3;
      }
    },
    handleSearch() {
      //后端返回List
      this.$bus.$emit('searchInputChanged',this.searchKey);
      this.searchKey='';
    }
  }
}
</script>

<style scoped>
.header{
  display: flex;
}
</style>
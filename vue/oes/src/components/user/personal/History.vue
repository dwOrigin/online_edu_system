<template>
<div class="history">
  <div class="header">
    <el-input placeholder="搜索历史记录" v-model="searchKey" @keyup.enter.native="handleSearch" class="input-with-select">
      <el-button slot="append" icon="el-icon-search" @click="handleSearch"></el-button>
    </el-input>
    <el-button style="margin: 0 50px" @click="deleteall">清除所有记录</el-button>
  </div>
  <div class="content">
    <course-record v-for="obj in number" :key="obj"></course-record>
  </div>
</div>
</template>

<script>
import CourseRecord from "@/components/user/personal/CourseRecord";
export default {
  name: "History",
  data(){
      return {
        number: 10,
        searchKey:""
      };
  },
  components: {
    CourseRecord
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
    },
    deleteall(){
      console.log(0);
    }
  }
}
</script>

<style scoped>
.header{
  display: flex;
  width: 700px;
}
</style>
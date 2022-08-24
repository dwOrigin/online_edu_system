<template>
  <div class="history">
    <div class="header">
      <el-input placeholder="搜索历史记录" v-model="searchKey" class="input-with-select">

      </el-input>
      <el-button style="margin: 0 50px" @click="deleteAll">清除所有记录</el-button>
    </div>
    <div class="content">
      <course-record v-for="obj in listToDisplay"
                     :key="obj.id"
                     :courseP="obj"></course-record>
    </div>
  </div>
</template>

<script>
import CourseRecord from "@/components/user/personal/CourseRecord";

export default {
  name: "History",
  data() {
    return {
      searchKey: "",
      historyList: [],
      listToDisplay: []
    };
  },
  watch:{
    searchKey(newV){
      this.handleSearch(newV);
    }
  },
  components: {
    CourseRecord
  },
  methods: {
    refreshHistory() {
      //获取历史记录
      let usr = window.localStorage.getItem('user');
      usr = JSON.parse(usr);
      let promise = this.$axios({
          url: '/coursehistory/getbyuserid',
          method: 'get',
          params:{
            userid: usr.userId
          }
      });
      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       historyList: [
      //         {
      //           id: 9,
      //           userId: 888,
      //           title: '胡桃',
      //           time: '12小时',//上次观看距今时间
      //           teacherName: '韩愈',
      //           teacherAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      //         },
      //         {
      //           id: 8,
      //           userId: 888,
      //           title: '甘雨',
      //           time: '12小时前',
      //           teacherName: '韩愈',
      //           teacherAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      //         },
      //         {
      //           id: 7,
      //           userId: 888,
      //           title: '尤拉',
      //           time: '12小时',//上次观看距今时间
      //           teacherName: '韩愈',
      //           teacherAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      //         }
      //       ]
      //     }
      //   });
      // });
      promise.then((res) => {
        this.historyList = res.data
        this.listToDisplay = this.historyList;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    handleSearch(key) {
      if(key === ''){
        this.listToDisplay = this.historyList;
      }else{
        let list = this.historyList.filter((item)=>{
          return item.title.indexOf(key) >= 0;
        });
        this.listToDisplay = list;
      }
    },
    deleteAll() {
      let usr = JSON.parse(window.localStorage.getItem('user'));
      //删除用户所有历史记录
      let promise = this.$axios({
        url: '/coursehistory/deletebyuserid',
        method: 'get',
        data: {
          userid: usr.userId
        }
      });
      promise.then((res) => {
        if(res.data.code=="200"){
          this.searchKey = ''
          this.refreshHistory();
          this.$message.success('已删除所有历史记录');
        }else{
          this.$message.error(res.data.message);
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.refreshHistory();
    this.$bus.$on('refreshHistory', (data) => {
      this.refreshHistory();
    });
  },
  beforeDestroy() {
    this.$bus.$off('refreshHistory');
  }
}
</script>

<style scoped>
.header {
  display: flex;
  width: 700px;
}
</style>
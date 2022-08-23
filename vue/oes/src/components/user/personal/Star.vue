<template>
<div class="star">
  <div class="header">
    <el-input placeholder="搜索已收藏课程" v-model="searchKey" class="input-with-select">
    </el-input>
  </div>
  <div class="content">
    <course-record v-for="obj in listToDisplay"
                   :courseP="obj"
                   :key="obj.id" type="star"></course-record>
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
        starList:[],
        listToDisplay: []
      };
  },
  watch:{
    searchKey(newV){
      this.handleSearch(newV);
    }
  },
  methods: {
    refreshStar() {
      //获取收藏记录
      // let usr = window.localStorage.getItem('user');
      // usr = JSON.parse(usr);
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data:{
      //       userId: usr.id
      //     }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            starList: [
              {
                id: 9,
                userId: 888,
                title: '胡桃',
                time: '2022/06/15',//收藏时间
                courseImgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                teacherName: '韩愈',
                teacherAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
              },
              {
                id: 8,
                userId: 888,
                title: '甘雨',
                time: '2022/06/15',
                courseImgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                teacherName: '韩愈',
                teacherAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
              },
              {
                id: 7,
                userId: 888,
                title: '尤拉',
                courseImgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                time: '2022/06/15',
                teacherName: '韩愈',
                teacherAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
              }
            ]
          }
        });
      });
      promise.then((res) => {
        this.starList = res.data.starList;
        this.listToDisplay = [...this.starList];
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    handleSearch(key) {
      if(key === ''){
        this.listToDisplay = this.starList;
      }else{
        let list = this.starList.filter((item)=>{
          return item.title.indexOf(key) >= 0;
        });
        this.listToDisplay = list;
      }
    },
  },
  mounted() {
    this.refreshStar();
    this.$bus.$on('refreshStar', (data) => {
      this.refreshStar();
    });
  },
  beforeDestroy() {
    this.$bus.$off('refreshStar');
  }
}
</script>

<style scoped>
.header{
  display: flex;
}
</style>
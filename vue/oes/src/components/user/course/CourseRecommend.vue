<template>
  <div style="width: 1100px; margin: 10px auto">
    <div class="main">
      <el-row>
        <!--  走马灯-->
        <el-col :span="15" :offset="1">
          <el-carousel height="320px" style="border-radius: 6px">
            <el-carousel-item v-for="item in 4" :key="item">
              <el-image
                  style="width: 100%; height: 100%"
                  src="https://img.alicdn.com/imgextra/i1/6000000007842/O1CN01awUHxQ27ngiY2gNm5_!!6000000007842-0-octopus.jpg"
                  fit="fill"></el-image>
            </el-carousel-item>
          </el-carousel>
        </el-col>
        <!--  个人标签-->
        <el-col class="user-card" :span="6" :offset="1">
          <user-card></user-card>
        </el-col>
      </el-row>
      <el-row class="hot-title">
        <div style="flex-grow: 1">热门课程推荐</div>
        <el-link :underline="false"
                 @click="refreshCourse"
                 type="primary">换一批<i class="el-icon-refresh"></i></el-link>
      </el-row>
      <el-row class="divider"></el-row>
      <el-row class="myHot">
        <course-card
            v-for="course in hotCourseList"
            :course="course"
        >
        </course-card>
      </el-row>
    </div>
  </div>

</template>

<script>
import UserCard from "@/components/user/course/UserCard";
import CourseCard from "@/components/user/course/CourseCard";

export default {
  name: "CourseRecommend",
  components: {
    UserCard,
    CourseCard,
  },
  data() {
    return {
      hotCourseList: []
    };
  },
  methods: {
    //点击换一批调用
    refreshCourse() {
      //获取10门推荐课程
      // let promise = this.$axios({
      //   url: '/course/getrecommend',
      //   method: 'get',
      // });
      let promise = new Promise((a)=>{
        a({data:[
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题1',
                teacher: '僵尸1',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题2',
                teacher: '僵尸2',
                chapterNum: 121,
                studentNum: 108126,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题3',
                teacher: '僵尸3',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题4',
                teacher: '僵尸4',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题5',
                teacher: '僵尸5',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题6',
                teacher: '僵尸6',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题7',
                teacher: '僵尸7',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题8',
                teacher: '僵尸8',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 9999,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题9',
                teacher: '僵尸9',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
              {
                id: 112,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '课成标题10',
                teacher: '僵尸10',
                chapterNum: 1,
                studentNum: 1086,
                likeRate: 10
              },
            ]});
      });
      promise.then((res) => {
        let cl = res.data;
        this.hotCourseList = cl;
      }).catch((err) => {
        this.$message.error("你的网络迷路了");
      });
    }
  },
  mounted() {
    this.refreshCourse();
  }
}
</script>

<style scoped>
.myHot {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

/*.myRefresh:hover {*/
/*  color: red;*/
/*}*/

.hot-title {
  margin-top: 50px;
  display: flex;
  justify-content: space-between;
}

.divider {
  height: 5px;
  width: 100%;
  margin: 5px auto;
  background-color: #99a9bf;
}

.main {
  margin-top: 10px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item {
  background-color: #99a9bf;
}

.user-card {
  height: 320px;
}
</style>
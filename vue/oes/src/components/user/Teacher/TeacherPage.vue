<template>
  <div class="teacher-page">
    <div class="header">
      <el-avatar
          style="margin: 20px"
          :size="70"
          :src="teacher.avatarUrl">
        <span v-if="teacher.avatarUrl === ''">{{ teacher.name }}</span>
      </el-avatar>
      <div style="max-width: 85%">
        <div style="margin-top: 20px">
          {{ teacher.name }}&nbsp;&nbsp;&nbsp;
          <el-tag type="warning" size="mini">{{ teacher.nikeName }}</el-tag>
        </div>
        <div style="font-size: x-small; color: #999999; margin-top: 20px; margin-bottom: 20px">
          {{ teacher.intro }}
        </div>
      </div>
    </div>
    <div class="body">
      <div style="margin: 50px; font-size: large; color: teal; font-style: oblique">Ta的课程</div>
      <div class="result-table" v-show="teacher.courses.length>0">
        <course-card v-for="c in
        teacher.courses.slice((curPage-1)*pageSize, curPage*pageSize)" :course="c">
        </course-card>
      </div>
      <div v-show="teacher.courses.length===0">
        <el-empty description="该讲师没有任何课程"></el-empty>
      </div>
    </div>
    <div class="footer">
      <el-pagination
          background
          hide-on-single-page
          :page-size="pageSize"
          :current-page="curPage"
          layout="prev, pager, next"
          :total="teacher.courses.length">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import CourseCard from "@/components/user/course/CourseCard";
import axios from "axios";

export default {
  name: "TeacherPage",
  components: {
    CourseCard
  },
  data() {
    return {
      teacher: {
        avatarUrl: '',
        name: '',
        nikeName: "",
        intro: '',
        courses: []
      },
      pageSize: 12,
      curPage: 1
    };
  },
  methods: {
    refreshTeacher(id) {
      //通过讲师id获取讲师详细信息
      let promise = this.$axios({
          url: '/teacher/id',
          method: 'get',
          params:{
            id:id
          }
      });
      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       teacher: {
      //         avatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      //         name: 'JEAN',
      //         nikeName: "银牌讲师",
      //         intro: '我我我我问黄ahfdjdhsajkfhkfa我我我我问黄ahfdjdhsajkfhkfa我我我我问黄ahfdjdhsajkfhkfa我我我我问黄ahfdjd' +
      //             '我我我我问黄ahfdjdhsajkfhkfa我我我我问黄ahfdjdhsajkfhkfa我我我我问黄ahfdjdhsajkfhkfa我我我我问黄ahfdjdhsajkfhkfa',
      //         courses: [
      //           {
      //             id: 345,
      //             imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
      //             title: '[量学] 一秒钟一个单词 一小时考过四级! 你值得拥有',
      //             teacher: 'bilbil大学',
      //             chapterNum: 16,
      //             studentNum: 1000,
      //             likeRate: 87
      //           }
      //         ]
      //       }
      //     }
      //   });
      // });
      promise.then((res) => {
        //@test
        this.teacher.avatarUrl = res.data.picPath;
        this.teacher.name=res.data.name;
        this.teacher.nikeName=res.data.education;
        this.teacher.intro=res.data.career;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      let promise1 = this.$axios({
          url: '/course/getbyteacher',
          method: 'get',
          params:{
            teacherid:id
          }
      });
      promise1.then((res1) => {
        //@test
        this.teacher.courses=res1.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.refreshTeacher(this.$route.params.teacherId);
  }
}
</script>

<style scoped>
.footer {
  text-align: center;
  margin-top: 50px;
}

.result-table {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}

.header {
  display: flex;
  align-items: center;
  /*background-color: #409EFF;*/
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}

.teacher-page {
  width: 1000px;
  margin: 30px auto 0 auto;
  /*background-color: teal;*/
}
</style>
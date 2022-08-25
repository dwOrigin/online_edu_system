<template>
  <div class="teacher-center">
    <div class="header">
      <div class="searchInput">
        <el-input placeholder="搜索你喜欢的讲师" @keyup.enter.native="searchTeacher(key)" v-model="key"
          class="input-with-select">
          <el-button slot="append" @click="searchTeacher(key)" icon="el-icon-search"></el-button>
        </el-input>
      </div>
      <div class="space-between">
        <div style="font-weight: bold; color: teal">{{ resultTitle }}</div>
        <div style="font-weight: lighter; color: #99a9bf; font-size: small">共找到
          <span style="font-weight: bold; color: teal">{{ teachers.length }}</span>个搜索结果
        </div>
      </div>
    </div>
    <div class="search-result">
      <teacher-card :teacher="t" v-for="t in
      teachers.slice((curPage - 1) * pageSize, curPage * pageSize)"></teacher-card>
    </div>
    <div class="page">
      <el-pagination background :page-size="pageSize" :current-page="curPage" hide-on-single-page
        @current-change="handleCurrentChange" 
        layout="prev, pager, next" :total="teachers.length">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import TeacherCard from "@/components/user/Teacher/TeacherCard";

export default {
  name: "TeacherCenter",
  components: {
    TeacherCard
  },
  data() {
    return {
      key: '',
      resultTitle: '全部讲师',
      teachers: [],
      pageSize: 15,
      curPage: 1
    };
  },
  methods: {
    searchTeacher(key) {
      if (key == '') {
        let promise = this.$axios({
          url: '/teacher/findAll',
          method: 'get',
        });
        promise.then((res) => {
          this.teachers = res.data;
          //@test
          // if (key == '') {
          //   this.resultTitle = '全部讲师';
          // } else {
          //   this.resultTitle = '" ' + key + ' "搜索结果';
          // }
          this.resultTitle = '全部讲师';
          this.key = '';
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      } else {
        let promise = this.$axios({
          url: '/teacher/searchTeacher',
          method: 'get',
          params: {
            str: key
          }
        });
        promise.then((res) => {
          this.teachers = res.data;
          //@test
          // if (key == '') {
          //   this.resultTitle = '全部讲师';
          // } else {
          //   this.resultTitle = '" ' + key + ' "搜索结果';
          // }
          this.resultTitle = '" ' + key + ' "搜索结果';
          this.key = '';
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });

      }

      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       teachers: [
      //         {
      //           name: 'Jean',
      //           avatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      //           intro: 'Hello, I am Jean from America.Hello, Hello, I am Jean from America.HelloHello, I am Jean from America.HelloI am Jean from America.',
      //           nikeName: '金牌讲师',
      //           teacherId: 8848
      //         }
      //       ]
      //     }
      //   });
      // });
    },
    // 显示第几页
    handleCurrentChange(val) {
      // 改变默认的页数
      this.curPage = val;
      console.log(this.teachers.slice((curPage - 1) * pageSize, curPage * pageSize));
    },
  },
  mounted() {
    this.searchTeacher('');
  }
}
</script>

<style scoped>
.page {
  text-align: center;
}

.search-result {
  display: flex;
  flex-wrap: wrap;
  margin: 40px auto 20px auto;
  /*background-color: teal;*/
  justify-content: left;
}

.searchInput {
  width: 50%;
  margin: 0 auto;
}

.space-between {
  margin-top: 50px;
  display: flex;
  justify-content: space-between;
}

.header {
  margin: 10px auto;
}

.teacher-center {
  width: 1000px;
  margin: 50px auto 0 auto;
}
</style>

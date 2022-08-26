<template>
  <!--  <div style="width: 1100px; margin: 0 auto;">-->
  <div class="main">
    <div class="space-between">
      <div>
        <h5 style="color: #409EFF">搜索结果</h5>
      </div>
      <div style="font-weight: lighter; color: #99a9bf; font-size: small">共找到
        <span style="font-weight: bold; color: teal">{{ coursesToDisplay.length }}</span>个课程
      </div>
    </div>
    <div>
      <el-link :underline="false" ref="default" @click="setSortType('default')" class="sort-tag" style="margin-left: 0">
        综合
      </el-link>
      <el-link :underline="false" ref="likeRate" @click="setSortType('likeRate')" class="sort-tag">点赞数
      </el-link>
      <el-link :underline="false" ref="studentNum" @click="setSortType('studentNum')" class="sort-tag">人气
      </el-link>
    </div>
    <div class="result-table">
      <course-card :course="course" v-for="course in
          coursesToDisplay.slice((curPage - 1) * pageSize, (curPage) * pageSize)">
      </course-card>
    </div>
    <div>
      <el-pagination background :page-size.sync="pageSize" :current-page.sync="curPage"
        @current-change="handleCurrentChange" class="page-controller" layout="prev, pager, next"
        :total="courseList.length">
      </el-pagination>
    </div>

  </div>

</template>

<script>
import CourseCard from "@/components/user/course/CourseCard";

export default {
  name: "CourseSearchResult",
  components: {
    CourseCard
  },
  data() {
    return {
      //原始列表
      courseList: [],
      //排序后要展示的列表
      coursesToDisplay: [],
      //每页大小
      pageSize: 20,
      //排序方式
      sortType: 'default',
      //当前页数
      curPage: 1,
    };
  },
  methods: {
    //页码改变时调用
    handleCurrentChange(v) {
      this.curPage = v;
    },
    //排序方式改变
    setSortType(newV) {
      this.sortList(newV);
      this.curPage = 1;
    },
    //排序结果, 将排序结果展示到页面
    sortList(type) {
      this.coursesToDisplay = [...this.courseList];
      if (type === 'default') {
      } else if (type === 'likeRate') {
        this.coursesToDisplay.sort((a, b) => {
          return b.praiseCount - a.praiseCount;
        });
      } else if (type === 'studentNum') {
        this.coursesToDisplay.sort((a, b) => {
          return b.pageViewcount - a.pageViewcount;
        });
      }
    },
    //刷新搜索结果
    refresh(select, key) {
      if (select === undefined || select === '') {
        //关键词搜索
        select = null;
      }
      if (key === undefined || key === '') {
        //分类搜索
        key = null;
      }
      if (select == null && key == null) {
        let promise = null;
      } else if (select == null && key != null) {
        let promise = this.$axios({
          url: '/course/getbyname',
          method: 'get',
          params: {
            name: key
          }
        });
        promise.then((res) => {
          this.courseList = res.data;
          this.sortList(this.sortType);
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      } else if (select != null && key == null) {
        let promise = this.$axios({
          url: '/course/getByType',
          method: 'get',
          params: {
            type: select
          }
        });
        promise.then((res) => {
          this.courseList = res.data;
          this.sortList(this.sortType);
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      } else {
        let promise = this.$axios({
          url: '/course/gebyboth',
          method: 'get',
          params: {
            select: select,
            key: key,
          }
        });
        promise.then((res) => {
          this.courseList = res.data;
          this.sortList(this.sortType);
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
    }
  },
  mounted() {
    this.$bus.$on('searchInputChanged', (select, key) => {
      this.refresh(select, key);
    });
  },
  beforeDestroy() {
    this.$bus.$off('searchInputChanged');
  }
}
</script>

<style scoped>
.page-controller {
  margin: 30px auto;
  width: 500px;
  display: flex;
  justify-content: center;
}

.result-table {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: left;
}

.sort-tag {
  margin: 20px 20px;
}

.active-tag {
  border: 1px solid #409EFF;
}

.not-active-tag {
  border: none;
}

.space-between {
  display: flex;
  justify-content: space-between;
  margin: 10px auto;
}

.main {
  margin-top: 20px;
}
</style>
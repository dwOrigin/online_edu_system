<template>
  <!--  <div style="width: 1100px; margin: 0 auto;">-->
  <div class="main">
    <div class="space-between">
      <div><h5 style="color: #409EFF">搜索结果</h5></div>
      <div style="font-weight: lighter; color: #99a9bf; font-size: small">共找到
        <span style="font-weight: bold; color: teal">{{ coursesToDisplay.length }}</span>个课程
      </div>
    </div>
    <div>
      <el-link :underline="false"
               ref="default"
               @click="setSortType('default')"
               class="sort-tag" style="margin-left: 0">综合
      </el-link>
      <el-link :underline="false"
               ref="likeRate"
               @click="setSortType('likeRate')"
               class="sort-tag">好评率
      </el-link>
      <el-link :underline="false"
               ref="studentNum"
               @click="setSortType('studentNum')"
               class="sort-tag">人气
      </el-link>
    </div>
    <div class="result-table">
      <course-card
          :course="course"
          v-for="course in
          coursesToDisplay.slice((curPage-1)*pageSize, (curPage)*pageSize)">
      </course-card>
    </div>
    <div>
      <el-pagination
          background
          :page-size.sync="pageSize"
          :current-page.sync="curPage"
          @current-change="handleCurrentChange"
          class="page-controller"
          layout="prev, pager, next"
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
    handleCurrentChange(v){
      this.curPage = v;
    },
    //排序方式改变
    setSortType(newV){
      this.sortList(newV);
      this.curPage = 1;
    },
    //排序结果, 将排序结果展示到页面
    sortList(type) {
      this.coursesToDisplay = [...this.courseList];
      if (type === 'default') {
      } else if (type === 'likeRate') {
        this.coursesToDisplay.sort((a, b) => {
          return b.likeRate - a.likeRate;
        });
      } else if (type === 'studentNum') {
        this.coursesToDisplay.sort((a, b) => {
          return b.studentNum - a.studentNum;
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
      // let promise = this.$axios({
      //    url: '',
      //    method: 'post',
      //    data: {
      //      select: select,
      //      key: key
      //    }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            retList: [
              {
                id: 123,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '[量子速学] 一秒钟一个单词 一小时考过四级! 你值得拥有',
                teacher: 'bilbil大学',
                chapterNum: 16,
                studentNum: 156,
                likeRate: 99
              },
              {
                id: 234,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '[量子速学] 一秒钟一个单词 一小时考过四级! 你值得拥有',
                teacher: 'bilbil大学',
                chapterNum: 16,
                studentNum: 10086,
                likeRate: 100
              },
              {
                id: 345,
                imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
                title: '[量子速学] 一秒钟一个单词 一小时考过四级! 你值得拥有',
                teacher: 'bilbil大学',
                chapterNum: 16,
                studentNum: 1000,
                likeRate: 87
              }
            ]
          }
        });
      });
      promise.then((res) => {
        this.courseList = res.data.retList;
        //测试用, 要删掉
        this.courseList = [...this.courseList, ...this.courseList];
        this.courseList = [...this.courseList, ...this.courseList];
        this.courseList = [...this.courseList, ...this.courseList];
        this.courseList = [...this.courseList, ...this.courseList];
        this.courseList = [...this.courseList, ...this.courseList];
        this.courseList = [...this.courseList, ...this.courseList];
        this.sortList(this.sortType);
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
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

.active-tag{
  border: 1px solid #409EFF;
}

.not-active-tag{
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
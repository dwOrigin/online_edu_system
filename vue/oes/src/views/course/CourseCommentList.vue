<template>
  <div class="comment-list">
      <!-- <el-button type="primary"  v-if="commentable == false" disabled plain class="comment-confirm-btn">发布</el-button> -->
    <div>
      <h4 v-if="comments.length==0">暂无评论</h4>
      <h4 v-if="comments.length!=0">好评率：{{ Math.round((goodReview / comments.length) * 100) }}%</h4>
    </div>
    <div class="comment-selector">
      <el-button :plain="filterMethod !== 'all'" type="primary" @click="filterMethod = 'all'" class="selector-tag"
        style="margin-left: 0">全部评论({{ comments.length }})
      </el-button>
      <el-button :plain="filterMethod !== 'good'" type="primary" @click="filterMethod = 'good'" plain
        class="selector-tag">好评({{ goodReview }})
      </el-button>
      <el-button :plain="filterMethod !== 'medium'" type="primary" @click="filterMethod = 'medium'" plain
        class="selector-tag">中评({{ mediumReview }})
      </el-button>
      <el-button :plain="filterMethod !== 'bad'" type="primary" @click="filterMethod = 'bad'" plain
        class="selector-tag">差评({{ badReview }})
      </el-button>
    </div>
    <div>
      <comment v-for="c in
      commentsToDisplay.slice((curPage - 1) * pageSize, (curPage) * pageSize)" :comment="c"></comment>
    </div>
    <div class="comment-pagination-container">
      <el-pagination background hide-on-single-page :current-page.sync="curPage" :page-size="pageSize"
        :total="commentsToDisplay.length" layout="prev, pager, next">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Comment from "@/views/course/Comment";

export default {
  name: "CourseCommentList",
  components: {
    Comment
  },
  data() {
    return {
      commentable: true,
      userComment: '',
      commentRate: null,
      course: {},
      comments: [],
      user: {},
      commentsToDisplay: [],
      filterMethod: 'all',
      goodReview: 0,
      badReview: 0,
      mediumReview: 0,
      curPage: 1,
      pageSize: 10,
    }
  },
  watch: {
    filterMethod(newV) {
      this.filterComment(newV);
    }
  },
  methods: {
    //统计评论数量
    countReview() {
      this.goodReview = 0;
      this.badReview = 0;
      this.mediumReview = 0;
      this.comments.forEach((item) => {
        if (item.praiseCount >= 4) {
          this.goodReview++;
        } else if (item.praiseCount <= 2) {
          this.badReview++
        } else {
          this.mediumReview++;
        }
      });
    },
    //过滤评论
    filterComment(type) {
      this.commentsToDisplay =this.comments;
      if (type === 'all' || type == null) {
      } else if (type === 'bad') {
        this.commentsToDisplay = this.commentsToDisplay.filter((item) => {
          return item.praiseCount <= 2;
        });
      } else if (type === 'good') {
        this.commentsToDisplay = this.commentsToDisplay.filter((item) => {
          return item.praiseCount >= 4;
        });
      } else if (type === 'medium') {
        this.commentsToDisplay = this.commentsToDisplay.filter((item) => {
          return item.praiseCount > 2 && item.praiseCount < 4;
        });
      }
    },
    //更新评论
    updateComments() {
      //获取课程评论
      let promise1 = this.$axios({
        url: '/comment/showC',
        method: 'get',
        params: {
          courseId: this.course.courseId
        }
      });
      promise1.then((res) => {
        console.log(res.data);
        //测试用
        let c = res.data;
        this.comments = c;
        this.countReview();
        this.filterComment('all');
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.$bus.$on('courseChanged1', (c) => {
      this.course = c;
      this.updateComments();
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged1');
  },
  // directives:{
  //   autoClick:{
  //     bind(el){
  //       el.click();
  //       console.log('click');
  //     }
  //   }
  // }
}
</script>


<style scoped>
.comment-confirm-btn {
  width: 80px;
  height: 80px;
}

.comment-input-area {
  width: 70%;
  margin: 0 10px;
}

.comment-input-box {
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.comment-pagination-container {
  margin-top: 30px;
  display: flex;
  justify-content: center;
}

.selector-tag {
  margin: 20px 10px;
}

.comment-list {
  width: 90%;
  margin: 30px auto;
}
</style>
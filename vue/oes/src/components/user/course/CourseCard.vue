<template>
  <div class="main" @click="pushcourse" ref="card">
    <el-image :style="imgStyle" :src="course.logo" fit="fill">
    </el-image>
    <div>
      <span style="max-height: 40px;
          overflow: hidden;
          font-size: x-small;">
        {{ course.courseName }}
      </span>
      <div style="font-size: xx-small; color: #999999">
        共{{ num }}节 | {{ teacher.name }}
      </div>
      <div style="font-size: xx-small; color: #999999" class="footer">
        <div>
          <span style="color: teal">{{ course.pageViewcount }}</span>人浏览
        </div>
        <div>
          <span style="color: teal">{{ course.praiseCount }}</span>人点赞
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CourseCard",
  data() {
    return {
      num: 1,
      teacher: '',
      imgStyle: {
        width: '100%',
        height: '120px'

      }
    }
  },
  props: {//对接收到的参数进行限制
    direction: {
      type: String,
      default: 'column',
      required: false
    },
    //容纳的课程对象
    course: {
      // default() {
      //   return {
      //     id: 9999,
      //     imgUrl: 'https://10.idqqimg.com/qqcourse_logo_ng/ajNVdqHZLLBJ4V5fI0YdBmgyHpVyILxvWibCt3zJ0HxzI968gMHEW6V748TaRKPaj9BPkEUoHYME/356',
      //     title: '[量子速学] 一秒钟一个单词 一小时考过四级! 你值得拥有',
      //     teacher: 'bilbil大学',
      //     chapterNum: 16,
      //     studentNum: 10086,
      //     likeRate: 100
      //   };
      // }
    }
  },
  methods: {
    pushcourse() {
      this.request.get('/course/pageviewplus',{
        params:{
          id:this.course.courseId
        }
      })
      this.$bus.$emit('courseChanged',this.course)
      this.$router.push({
        name: 'course',
        params: {
          courseId: this.course.courseId
        }
      });
    }

  },
  mounted() {
    //切换方向
    let promise = this.$axios({
      url: '/coursevideo/getnum',
      method: 'get',
      params: {
        courseId: this.course.courseId
      }
    });
    promise.then((res) => {
      //@test
      this.num = res.data;
    }).catch((err) => {
      // this.$message.error('你的网络迷路了');
    });
    let promise1 = this.$axios({
      url: '/teacher/id',
      method: 'get',
      params: {
        id: this.course.teacherId
      }
    });
    promise1.then((res1) => {
      //@test
      this.teacher = res1.data;
    }).catch((err) => {
      // this.$message.error('你的网络迷路了');
    });
    if (this.direction === 'row') {
      this.imgStyle.width = '50%';
      this.$refs.card.style.flexDirection = 'row';
      this.$refs.card.style.justifyContent = 'center';
      this.$refs.card.style.width = '400px';
      this.$refs.card.style.height = '150px';
      this.$refs.card.style.alignItems = 'center';
    }
  }
}
</script>

<style scoped>
.footer {
  display: flex;
  justify-content: space-between;
}

.main {
  width: 200px;
  height: 200px;
  margin: 10px 10px;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
}

.main:hover {
  cursor: pointer;
  box-shadow: 1px 1px 10px #bebec1;
  transform: translateY(-3px) translateZ(100px);
  transition: all 0.3s;
}
</style>
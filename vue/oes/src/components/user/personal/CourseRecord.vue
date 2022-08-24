<template>
  <div @click="$router.push({
    name: 'course',
    params: {
      courseId: course.courseId
    }
  });" class="course-record">
    <el-image class="radius-img" style="width: 200px; height: 120px" ref="img" src=coursedetail.logo fit="fill">
    </el-image>
    <div class="right">
      <div class="center-part">
        <div :underline="false" class="title">
          {{ coursedetail.courseName }}
        </div>
        <div class="center-footer">
          <div v-if="type === 'history'">{{ course.time }}</div>
          <div v-if="type === 'star'">收藏于&nbsp;&nbsp; {{ course.addTime }}</div>
          <div class="teacher">
            <el-avatar :size="30" :src="teacher.picPath">
              <span v-if="teacher.picPath == '' || teacher.picPath == null">{{ teacher.name }}</span>
            </el-avatar>
            &nbsp;&nbsp;&nbsp;{{ teacher.name }}
          </div>
        </div>
      </div>
      <div class="delete">
        <el-button type="info" icon="el-icon-delete" @click.stop="handleDelete" circle></el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CourseRecord",
  props: {
    type: {
      default: 'history',
      type: String
    },
    courseP: {}
  },
  data() {
    return {
      course: this.courseP,
      coursedetail: {
        courseId: 0,
        courseName: "",
        isAvailable: 0,
        addTime: "",
        timeLong: "",
        title: "",
        logo: "",
        type: "",
        pageViewcount: 0,
        praiseCount: 0,
        commentNum: 0,
        teacherId: 0
      },
      teacher: {
        id: 0,
        name: "",
        education: "",
        career: "",
        isStar: 0,
        picPath: "",
        status: 0,
        createTime: null,
        subjectId: 0,
        sort: 0
      }
    }
  },
  methods: {
    handleDelete() {
      if (this.type === 'history') {
        //删除历史记录
        let promise = this.$axios({
          url: '/coursehistory/delete',
          method: 'post',
          data: this.course
        });
        // let promise = new Promise((a)=>{
        //   a({
        //     data:{
        //       result: true
        //     }
        //   });
        // });
        promise.then((res) => {
          if (res.data.code == "200") {
            this.$message.success('删除成功');
            this.$bus.$emit('refreshHistory');
          } else {
            this.$message.error('删除失败');
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
      else
        if (this.type === 'star') {
          //删除收藏记录
          let promise = this.$axios({
            url: '/coursefavorite/delete',
            method: 'post',
            data: this.course
          });
          // let promise = new Promise((a)=>{
          //   a({
          //     data:{
          //       result: true
          //     }
          //   });
          // });
          promise.then((res) => {
            if (res.data.code == "200") {
              this.$message.success('删除成功');
              this.$bus.$emit('refreshStar');
            } else {
              this.$message.error('删除失败');
            }
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
        }
    }
  },
  mounted() {
    let promise = this.$axios({
      url: '/course/getById',
      method: 'get',
      params: {
        id: this.course.courseId
      }
    });
    promise.then((res) => {
      this.coursedetail = res.data;
      let promise1 = this.$axios({
        url: '/teacher/id',
        method: 'get',
        params: {
          id: this.coursedetail.teacherId
        }
      });
      promise1.then((res1) => {
        this.teacher = res1.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }).catch((err) => {
      this.$message.error('你的网络迷路了');
    });
    // this.request.get("/course/getById", {
    //   param: {
    //     id: this.course.courseId
    //   }
    // })
    //   .then((res) => {
    //     this.coursedetail = res;
    //   })
    // console.log(this.coursedetail);
    // this.request.get("/teacher/id", {
    //   param: {
    //     id: this.coursedetail.teacherId
    //   }
    // })
    //   .then((res) => {
    //     this.teacher = res;
    //   })
  }
}
</script>

<style scoped>
.course-record:hover {
  cursor: pointer;
  transform: perspective(800px) translate3d(0, 0, 10px);
  transition: all 0.2s;
  /*border: 1px solid #409eff;*/
}

.right {
  display: flex;
  /*border-bottom: 1px solid #B3B5C0;*/
  margin: 0 20px;
}

.delete {
  margin: auto 100px;
}

.teacher {
  display: flex;
  align-items: center;
}

.center-footer {
  display: flex;
  justify-content: space-between;
  color: #A0ACBA;
  align-items: center;
  font-size: x-small;
}

.title:hover {
  color: #409EFF;
  cursor: pointer;
}

.title {
  color: #222;
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.center-part {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  flex-grow: 0;
  flex-basis: 310px;
  flex-shrink: 0;
  margin: 0;
  padding-bottom: 5px;
}

.radius-img {
  border-radius: 10px;
  flex-grow: 0;
  flex-shrink: 0;
}

.course-record {
  display: flex;
  width: 700px;
  margin: 25px 0;
}
</style>
<template>
  <div @click="openTeacher" class="teacher-header">
    <div class="large-avatar">
      <a href="#">
        <el-avatar :size="60" :src="teacher.picPath">
          <span v-if="teacher.picPath == '' || teacher.picPath == null">{{ teacher.name }}</span>
        </el-avatar>
      </a>
    </div>
    <div>
      <div style="font-weight: lighter; margin: 20px 0 5px  0">
        {{ teacher.name }}
        &nbsp;
        <el-tag type="warning" size="mini">{{ teacher.education }}</el-tag>
      </div>
      <div style="font-size: x-small; color: #99a9bf;">
        {{ teacher.career }}
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PlayTeacherHeader",
  data() {
    return {
      teacher: {},
      course: {}
    };
  },
  watch: {
    course(newV) {
      // while (this.course.teacherId ==  null);
      //获取讲师简略信息
      let promise = this.$axios({
        url: '/teacher/id',
        method: 'get',
        params: {
          id: newV.teacherId
        }
      });
      promise.then((res) => {
        this.teacher = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  methods: {
    // 路由到讲师界面
    openTeacher() {
      this.$router.push({
        name: 'teacherPage',
        params: {
          teacherId: this.teacher.id
        }
      });
    }
  },
  mounted() {
    this.$bus.$on('courseChanged1', (data) => {
      this.course = data;
    });
  },
  beforeDestroy() {
    this.$bus.$off('courseChanged1');
  }
}
</script>

<style scoped>
.teacher-header:hover {
  cursor: pointer;
}

.large-avatar {
  margin: 10px;
}

.teacher-header {
  display: flex;
  border-radius: 5px;
  border: 1px solid #409EFF;
}
</style>
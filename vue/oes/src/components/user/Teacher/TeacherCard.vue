<template>
  <div
      @click="toTeacherPage(t.id)"
      class="teacher-card">
    <div>
      <el-avatar
          :size="60"
          style="margin: auto 20px"
          :src="t.avatarUrl">
        <span v-if="t.avatarUrl === ''">{{ t.name }}</span>
      </el-avatar>
    </div>
    <div>
      <div>{{ t.name }}&nbsp;
        <el-tag type="warning" size="mini">{{ t.nikeName }}</el-tag>
      </div>
      <div style="font-size: xx-small; margin-top: 10px; display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;
            overflow: hidden;"
      >{{ t.intro }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherCard",
  props: {
    teacher: {}
  },
  data() {
    return {
      t: this.teacher
    };
  },
  methods: {
    toTeacherPage(id) {
      this.$router.push({
        name: 'teacherPage',
        params: {
          teacherId: id
        }
      });
    }
  }
}
</script>

<style scoped>
.teacher-card:hover {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transform: perspective(800px) translateZ(10px);
  transition: 0.3s;
  cursor: pointer;
}

.teacher-card {
  width: 300px;
  /*background-color: #409EFF;*/
  height: 100px;
  background-color: #F5F5F5;
  margin: 10px;
  display: flex;
  align-items: center;
}
</style>
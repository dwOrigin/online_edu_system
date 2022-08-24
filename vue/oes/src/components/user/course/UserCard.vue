<template>
  <div class="main">
    <!--    根据用户是否登录切换展示内容-->
    <div v-if="user == null" class="default">
      <div class="default-item" style='font-weight: lighter; font-size: small; margin: 10px 0'>
        跟进你的学习进度
      </div>
      <div class="default-item">
        <i class="el-icon-s-custom myIcon"></i>
      </div>
      <div class="default-item">
        <el-button type="primary" @click="handleClickLogIn" round style="width: 150px">登录
        </el-button>
      </div>
    </div>
    <div v-if="user != null" class="default">
      <div class="login-item">
        <a href="#" @click="$router.push({
          name: 'personal',
          query: {
            select: ''
          }
        });" class="mini-img-container">
          <el-avatar size="medium" :src="user.picImg">
            <span v-if="user.picImg === null">{{ user.userName }}</span>
          </el-avatar>
        </a>
        <el-link :underline="false" type="success" class="line-text-ellipsis"
          @click="$router.push({ name: 'personal', query: { select: '' } });"
          style="font-size: medium; max-width: 100px; min-width: 100px; margin: 0 10px">
          {{ user.userName }}
        </el-link>
        <el-link :underline="false" type="info" @click="exit">退出</el-link>
      </div>
      <div class="course" v-for="course in user.history3" :key="course.courseName">
        <el-link :underline="false">{{ course.courseName }}&nbsp;&nbsp;[{{ getTimeInterval(course.lastTime) }}前看过]
        </el-link>
      </div>
      <div class="divider"></div>
      <div class="login-card-footer" style="width: 100%">
        <div style="margin: 0 10px">
          <el-link :underline="false" @click="$router.push({ name: 'personal', query: { select: 'history' } });"
            style="font-size: medium">{{ historyNum }}门课程</el-link>
        </div>
        <div style="margin: 0 10px">
          <el-link :underline="false" @click="$router.push({ name: 'personal', query: { select: 'star' } });"
            style="font-size: medium">{{ starCourseNum }}门收藏</el-link>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import LoginDialog from "@/components/user/course/LoginDialog";

export default {
  name: "UserCard",
  components: {
    LoginDialog
  },
  data() {
    return {
      //登录信息
      user: null,
      historyNum: 0,
      starCourseNum: 0,
    }
  },
  methods: {
    //获取时间间隔字符串
    getTimeInterval(oldTime) {
      //@不知道哪里有bug, 时间总是快1个月
      return '不久';
      let diff = (new Date().getTime() - oldDate.getTime()) / 1000;
      if (diff / 86400 >= 30) {
        return '一个月';
      } else if (diff / 86400 < 30 && diff / 86400 >= 1) {
        return (diff / 86400).toString() + '天';
      } else if ((diff % 86400) / 3600 >= 1) {
        return ((diff % 86400) / 3600).toString() + '小时';
      } else {
        return '不久';
      }
    },
    //点击退出登录
    exit() {
      window.localStorage.removeItem('user');
      this.$message.info('已退出登录');
      this.$bus.$emit('AuthorizationChanged');
    },
    //未登录时点击的登陆按钮
    handleClickLogIn() {
      if (this.user !== null) {
        this.$message.info('请勿重复登录');
        return;
      }
      //激活登录窗口
      this.$bus.$emit('OpenLoginDialog');
    },
    // 更新登录信息
    refreshAuthorization() {
      let user = window.localStorage.getItem('user');
      if (user != null) {
        user = JSON.parse(user);
        this.user = user;
        this.request
          .get('http://localhost:8081/coursefavorite/getbyuserid', {
            params: {
              userid: this.user.userId
            }
          })
          .then((res) => {
            this.starCourseNum = res.length;
          })
          this.request
          .get('http://localhost:8081/coursehistory/getbyuserid', {
            params: {
              userid: this.user.userId
            }
          })
          .then((res) => {
            this.historyNum = res.length;
          })
      } else {
        this.user = null;
      }
    }
  },
  mounted() {
    this.refreshAuthorization();
    //登录信息改变
    this.$bus.$on('AuthorizationChanged', () => {
      this.refreshAuthorization();
    });
  },
  beforeDestroy() {
    this.$bus.$off('Authorization-Changed');
  }
}
</script>

<style scoped>
.login-card-footer {
  display: flex;
  margin-top: 10px;
  justify-content: space-between;
}

.divider {
  height: 2px;
  width: 100%;
  margin-top: 30px;
  background-color: teal;
}

.course {
  margin-top: 15px;
  font-size: medium;
}

.line-text-ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.mini-img-container {
  /*border-radius: 50%;*/
  /*height: 40px;*/
  /*width: 40px;*/
  /*overflow: hidden;*/
  margin-left: 0;
  margin-right: 5px;
}

.login-item {
  display: flex;
  justify-content: flex-start;
  margin: 5px 0;
}

.myIcon {
  font-size: 150px;
  color: teal;
}

.main {
  display: flex;
  height: 100%;
  align-items: center;
  justify-content: center;
}

.default {
  box-shadow: 1px 1px 10px #e2dcdc;
  display: flex;
  flex-shrink: 1;
  flex-direction: column;
  align-items: center;
  width: 80%;
  height: 80%;
  border-radius: 8px;
}

.default-item {
  flex-shrink: 1;
}
</style>
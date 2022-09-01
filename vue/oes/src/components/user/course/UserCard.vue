<template>
  <div class="main">
    <!--    根据用户是否登录切换展示内容-->
    <div v-if="user == null" class="default">
      <div class="default-item" style='font-weight: lighter; font-size: small; margin-top: 20px'>
        跟进你的学习进度
      </div>
      <div class="default-item">
        <i class="el-icon-s-custom myIcon"></i>
      </div>
      <div class="default-item" style="margin-bottom: 30px">
        <el-button type="primary" @click="handleClickLogIn" round style="width: 150px">登录 / 注册
        </el-button>
      </div>
    </div>
    <div v-if="user != null" class="default">
      <div class="login-item" style="margin-top: 20px">
        <a href="#" @click="$router.push({
          name: 'personal',
          query: {
            select: ''
          }
        });" class="mini-img-container">
          <el-avatar size="medium" :src="user.picImg">
            <span v-if="user.picImg == null||user.picImg==''">{{ user.userName }}</span>
          </el-avatar>
        </a>
        <el-link :underline="false" type="success" class="line-text-ellipsis"
          @click="$router.push({ name: 'personal', query: { select: '' } });"
          style="font-size: medium;  min-width: 100px; margin: 0 10px">
          {{ user.userName }}
        </el-link>
        <el-link :underline="false" type="info" @click="exit">退出</el-link>
      </div>
      <div style="align-self: flex-start" v-show="history.name.length > 0">
        <div class="course">
          <el-link :underline="false" @click="$router.push({ name: 'personal', query: { select: 'history' } });">
            {{ history.name[0] }}&nbsp;&nbsp;{{ history.time[0] }}
          </el-link>
        </div>
        <div class="course">
          <el-link :underline="false" @click="$router.push({ name: 'personal', query: { select: 'history' } });">
            {{ history.name[1] }}&nbsp;&nbsp;{{ history.time[1] }}
          </el-link>
        </div>
        <div class="course">
          <el-link :underline="false" @click="$router.push({ name: 'personal', query: { select: 'history' } });">
            {{ history.name[2] }}&nbsp;&nbsp;{{ history.time[2] }}
          </el-link>
        </div>
      </div>
      <div style="align-self: center; min-height: 120px" v-show="history.name.length === 0">
        <el-empty
            style="height: 100px"
            :image-size="40"
            description="无历史观看记录">
        </el-empty>
      </div>
      <div class="divider"></div>
      <div class="login-card-footer" style="width: 100%; margin-bottom: 10px">
        <div>
          <el-link :underline="false" @click="$router.push({ name: 'personal', query: { select: 'history' } });"
            style="font-size: medium; margin-left: 10px">{{ historyNum }}门课程</el-link>
        </div>
        <div>
          <el-link :underline="false" @click="$router.push({ name: 'personal', query: { select: 'star' } });"
            style="font-size: medium; margin-right: 10px">{{ starCourseNum }}门收藏</el-link>
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
      history:{
        name:[""],
        time:[""]
      }
    }
  },
  methods: {
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
          .get('/coursefavorite/getbyuserid', {
            params: {
              userid: this.user.userId
            }
          })
          .then((res) => {
            this.starCourseNum = res.length;
          })
          this.request
          .get('/coursehistory/getNumberByUserId', {
            params: {
              userId: this.user.userId
            }
          })
          .then((res) => {
            this.historyNum = res;
          })
          this.request
          .get('/coursehistory/getByUserId',{
            params:{
              id:this.user.userId
            }
          })
          .then((res)=>{
            this.history.name=res.reverse();
          })
           this.request
          .get('/coursehistory/getByUserIdT',{
            params:{
              id:this.user.userId
            }
          })
          .then((res)=>{
            this.history.time=res;
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
  height: 1px;
  width: 100%;
  margin-top: 30px;
  background-color: teal;
}

.course {
  margin: 10px 0 0 30px;
  align-self: flex-start;
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
  margin: 0;
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
  justify-content: space-between;
  align-items: center;
  width: 80%;
  height: 90%;
  border-radius: 8px;
}

.default-item {
  flex-shrink: 1;
}
</style>
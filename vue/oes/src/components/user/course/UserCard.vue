<template>
  <div class="main">
    <div v-if="user==null" class="default">
      <div class="default-item"
           style='font-weight: lighter; font-size: small; margin: 10px 0'>
        跟进你的学习进度
      </div>
      <div class="default-item">
        <i class="el-icon-s-custom myIcon"></i>
      </div>
      <div class="default-item">
        <el-button type="primary"
                   @click="handleClickLogIn"
                   round style="width: 150px">登录
        </el-button>
      </div>
    </div>
    <div v-if="user!=null" class="default">
      <div class="login-item">
        <a href="#" class="mini-img-container">
          <!--          <el-image-->
          <!--              style="width: 100%; height: 100%"-->
          <!--              src="https://tse4-mm.cn.bing.net/th/id/OIP-C.kRklM9DmpC8zqSI4_Bq9mAHaHa?pid=ImgDet&rs=1"-->
          <!--              fit="fill"></el-image>-->
          <el-avatar size="medium"
                     src="https://tse4-mm.cn.bing.net/th/id/OIP-C.kRklM9DmpC8zqSI4_Bq9mAHaHa?pid=ImgDet&rs=1"></el-avatar>
        </a>
        <el-link :underline="false" type="success"
                 class="line-text-ellipsis"
                 style="font-size: medium; max-width: 100px; min-width: 100px; margin: 0 10px">
          燕子
        </el-link>
        <el-link :underline="false" type="info">退出</el-link>
      </div>
      <div class="course">
        <el-link :underline="false">课程A&nbsp&nbsp[11小时前看过]</el-link>
      </div>
      <div class="course">
        <el-link :underline="false">课程A&nbsp&nbsp[11小时前看过]</el-link>
      </div>
      <div class="course">
        <el-link :underline="false">课程A&nbsp&nbsp[11小时前看过]</el-link>
      </div>
      <div class="divider"></div>
      <div class="login-card-footer" style="width: 100%">
        <div style="margin: 0 10px">
          <el-link :underline="false" style="font-size: medium">5门课程</el-link>
        </div>
        <div style="margin: 0 10px">
          <el-link :underline="false" style="font-size: medium">6门收藏</el-link>
        </div>
      </div>
    </div>
    <login-dialog></login-dialog>
  </div>
</template>

<script>
import LoginDialog from "@/components/user/course/LoginDialog";
export default {
  name: "UserCard",
  components:{
    LoginDialog
  },
  data() {
    return {
      user: null,
    }
  },
  methods: {
    handleClickLogIn() {
      this.$bus.$emit('userCardMessage', null);
    }
  },
  mounted() {
    this.$bus.$on('loginDialogMessage', (data)=>{
      console.log(data);
    });
  },
  beforeDestroy() {
    this.$bus.off('loginDialogMessage');
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
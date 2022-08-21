<template>
  <div class="p-center">
    <div class="inner">
      <div class="nav">
        <el-menu
            class="el-menu-vertical-demo"
            :default-active="this.$router.path"           
            router>
          <el-menu-item index="1"
          :route="{ path: '/home/personal/pinfo', query: { select:user } }">
            <i class="el-icon-user"></i>
            <span slot="title">
              个人信息
            </span>
          </el-menu-item>
          <el-menu-item index="2"
          :route="{ path: '/home/personal/history', query: { select:user } }">
            <i class="el-icon-document"></i>
            <span slot="title">历史观看</span>
          </el-menu-item>
          <el-menu-item index="3"
          :route="{ path: '/home/personal/star', query: { select:user } }">
            <i class="el-icon-star-off"></i>
            <span slot="title">收藏</span>
          </el-menu-item>
          <el-menu-item index="4"
          :route="{ path: '/home/personal/message', query: { select:user } }">
            <i class="el-icon-chat-dot-square"></i>
            <span slot="title">
              消息
              <el-badge class="mark" :value="122" style="margin-bottom: 10px" :max="99"/>
            </span>
          </el-menu-item>
          <el-menu-item index="5"
          :route="{ path: '/home/personal/question', query: { select:user } }">
            <i class="el-icon-question"></i>
            <span slot="title">我的提问</span>
          </el-menu-item>
          <el-menu-item index="/home/recommend" @click="exit">
            <i class="el-icon-back"></i>
            <span slot="title">退出登录</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div class="rv">
<!--        <div><span style="font-size: 22px;-->
<!--                    line-height: 1.3;-->
<!--                     margin-bottom: 20px;">历史观看</span></div>-->
        <div style="margin-top: 20px;">
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "PersonalCenter",
  components: {

  },
  data(){
    return{
      user:{}
    }
  },
  methods:{
    exit(){
      window.localStorage.removeItem('user');
      this.$bus.$emit('AuthorizationChanged');
    },
  },
  mounted(){
    this.user=this.$route.query.select;
    console.log(this.user)
  }
}
</script>

<style scoped>
.inner{
  width: 1000px;
  margin: 0 auto;
  display: flex;
}
.nav {
  border: 1px solid #E0E0E0;
  width: 200px;
  margin-left: 50px;
  margin-right: 40px;
  height: max-content;
  position: sticky;
  top: 25%;
}

.rv {
  width: 550px;
}

.p-center {
  background-color: #f4f4f4;
  padding-top: 50px;
  margin-bottom: -50px;
}
</style>
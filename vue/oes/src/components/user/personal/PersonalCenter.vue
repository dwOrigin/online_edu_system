<template>
  <div class="p-center">
    <div class="inner">
      <div class="nav">
        <el-menu class="el-menu-vertical-demo" :default-active=this.url router>
          <el-menu-item index='/home/personal/pinfo'>
            <i class="el-icon-user"></i>
            <span slot="title">
              个人信息
            </span>
          </el-menu-item>
          <el-menu-item index='/home/personal/history'>
            <i class="el-icon-document"></i>
            <span slot="title">历史观看</span>
          </el-menu-item>
          <el-menu-item index='/home/personal/star'>
            <i class="el-icon-star-off"></i>
            <span slot="title">收藏</span>
          </el-menu-item>
          <el-menu-item index='/home/personal/message' @click="pushm">
            <i class="el-icon-chat-dot-square"></i>
            <span slot="title">
              消息
              <el-badge v-show="msgUnCheckCnt > 0" class="mark" :value="msgUnCheckCnt" style="margin-bottom: 10px"
                :max="99" />
            </span>
          </el-menu-item>
          <el-menu-item index='/home/personal/question'>
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
        <div style="margin-top: 20px; min-height: 550px">
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "PersonalCenter",
  components: {},
  data() {
    return {
      url: "/home/personal/",
      user: {},
      msgUnCheckCnt: 0
    }
  },
  methods: {
    pushm() {
      this.$bus.$emit('clearUnCheckedCnt', -1);
    },
    exit() {
      window.localStorage.removeItem('user');
      this.$bus.$emit('AuthorizationChanged');
    },
    getMsgUnCheckCnt() {
      //获取用户未读消息数
      this.msgUnCheckCnt = 0;
      let promise = this.$axios({
        url: '/msgsystem/getbyid',
        method: 'get',
        params: {
          id: this.user.userId
        }
      });
      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       cnt: 10
      //     }
      //   });
      // });
      promise.then((res) => {
        this.msgUnCheckCnt = this.msgUnCheckCnt + res.data.length;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });

      let promise1 = this.$axios({
        url: '/msgreceive/getbyid',
        method: 'get',
        params: {
          id: this.user.userId
        }
      });
      promise1.then((res1) => {
        // console.log(this.msgUnCheckCnt);
        // console.log(res1.data.length);
        this.msgUnCheckCnt = this.msgUnCheckCnt + res1.data.length;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });

    }
  },
  mounted() {
    let user = window.localStorage.getItem('user');
    this.user = JSON.parse(user);
    this.$bus.$on('clearUnCheckedCnt', (data) => {
      this.getMsgUnCheckCnt();
    });
    this.getMsgUnCheckCnt();
    let url = this.$route.query.select;
    let talkid = this.$route.query.talk;
    if (talkid != undefined) {
      console.log(talkid);
      this.$router.push({
        path: '/home/personal/message',
        query: {
          select: talkid
        }
      })
    }else
    if (url !== '' && url !== undefined) {
      this.url = this.url + url;
      this.$router.push(this.url);
    } else {
      this.url = this.url + "pinfo";
      this.$router.push(this.url);
    }
  },
  beforeDestroy() {
    this.$bus.$off('clearUnCheckedCnt');
  }
}
</script>

<style scoped>
.inner {
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
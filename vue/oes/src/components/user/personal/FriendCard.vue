<template>
  <div class="friend-card" @click="changeChatWindow">
    <el-avatar v-if="type == 'receive'" style="margin: 0 10px 0 20px" :src="friend.picImg">
      <span v-if="friend.picImg == '' || friend.picImg == null">{{ friend.userName }}</span>
    </el-avatar>
    <el-avatar v-if="type == 'system'" style="margin: 0 10px 0 20px">
      <span>系</span>
    </el-avatar>
    <span v-if="type == 'receive'" style="font-weight: lighter; font-size: x-small">
      {{ friend.userName }}
    </span>
    <span v-if="type == 'system'" style="font-weight: lighter; font-size: x-small">
      系统消息
    </span>
    <el-badge v-if="unCheckedCnt !== 0" is-dot style="margin-left: 5px"></el-badge>

    <!--    <el-button type="info"-->
    <!--               size="mini"-->
    <!--               @click-->
    <!--               class="appear-on-hover"-->
    <!--               icon="el-icon-close" circle></el-button>-->
  </div>
</template>

<script>
export default {
  name: "FriendCard",
  props: {
    obj: {},
    typer: ''
  },
  data() {
    return {
      type: this.typer,
      friend: this.obj,
      unCheckedCnt: 0,
      user: {}
    };
  },
  methods: {
    changeChatWindow() {
      //将用户和特定好友的聊天记录全部标为已读
      // let usr = JSON.parse(window.localStorage.getItem('user'));
      if (this.type == 'system') {
        let promise = this.$axios({
          url: '/msgsystem/readall',
          method: 'get',
          params: {
            userId: this.user.userId
          }
        });
        promise.then((res) => {
          if (this.unCheckedCnt != 0) {
            this.$bus.$emit('clearUnCheckedCnt', -1);
          }
          this.unCheckedCnt = 0;
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
        this.$bus.$emit('changeChatWindow', -1);
      } else {
        let promise = this.$axios({
          url: '/msgreceive/readone',
          method: 'get',
          params: {
            cusId:this.friend.userId,
            userId: this.user.userId
          }
        });
        promise.then((res) => {
          if (this.unCheckedCnt !== 0) {
            this.$bus.$emit('clearUnCheckedCnt', this.friend.userId);
          }
          this.unCheckedCnt = 0;
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
        this.$bus.$emit('changeChatWindow', this.friend.userId);
      }
      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       result: true
      //     }
      //   });
      // });
    }
  },
  mounted() {
    let user = window.localStorage.getItem('user');
    this.user = JSON.parse(user);
    if (this.type == 'receive') {
      let promise1 = this.$axios({
        url: '/msgreceive/getbyid',
        method: 'get',
        params: {
          id: this.user.userId
        }
      });
      promise1.then((res1) => {
        this.unCheckedCnt = res1.data.length;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    } else {
      this.request.get('/msgsystem/getbyid', {
        params: {
          id: this.user.userId
        }
      })
        .then((res) => {
          console.log(res)
          this.unCheckedCnt = res.length;
        })
    }
  }
}
</script>

<style scoped>
.friend-card:hover .appear-on-hover {
  left: 20px;
}

.appear-on-hover {
  position: relative;
  left: 100px;
  transition: all 0.4s;
}

.friend-card:hover {
  background-color: #e4e5e6;
}

.friend-card {
  overflow: hidden;
  display: flex;
  align-items: center;
  flex-grow: 0;
  flex-shrink: 0;
  height: 80px;
}
</style>
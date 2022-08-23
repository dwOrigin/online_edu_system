<template>
  <div class="friend-card" @click="changeChatWindow">
    <el-avatar
        style="margin: 0 10px 0 20px"
        :src="friend.friendAvatar">
      <span v-if="friend.friendAvatar === ''">{{ friend.friendName }}</span>
    </el-avatar>
    <span style="font-weight: lighter; font-size: x-small">
      {{ friend.friendName }}
  </span>
    <el-badge v-if="friend.unCheckedCnt !== 0" is-dot style="margin-left: 5px"></el-badge>

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
    obj: {}
  },
  data() {
    return {
      friend: this.obj
    };
  },
  methods: {
    changeChatWindow() {
      //将用户和特定好友的聊天记录全部标为已读
      // let usr = JSON.parse(window.localStorage.getItem('user'));
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data:{
      //       userId: usr.id,
      //       friendId: this.friend.friendId
      //     }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            result: true
          }
        });
      });
      promise.then((res) => {
        if (this.friend.unCheckedCnt !== 0) {
          this.$bus.$emit('clearUnCheckedCnt', this.friend.friendId);
        }
        this.friend.unCheckedCnt = 0;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      this.$bus.$emit('changeChatWindow', this.friend.friendId);
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
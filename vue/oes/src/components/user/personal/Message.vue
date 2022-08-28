<template>
  <div class="message">
    <div class="time">
      {{ message.addTime }}
    </div>
    <div ref="mb">
      <el-avatar v-if="type == 'system'" style="margin: 0 10px 0 20px">
        <span>系</span>
      </el-avatar>
      <el-avatar v-if="type=='receive'" :size="40" style="margin: 0 10px; order: 1" :src="sender.picImg">
        <span v-if="sender.picImg == '' || sender.picImg == null">
          {{ sender.userName }}
        </span>
      </el-avatar>
      <div style="order: 2" class="message-content" ref="mc">
        {{ message.content }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Message",
  props: {
    msg: {},
    typer: ''
  },
  data() {
    return {
      message: this.msg,
      user: {},
      sender: {},
      type: this.typer,
    };
  },
  mounted() {
    this.request.get('/user/findOne', {
      params: {
        id: this.message.cusId
      }
    })
      .then((res) => {
        this.sender = res;
      })
    this.user = JSON.parse(window.localStorage.getItem('user'));
    if (this.type == "system") {
      this.$refs.mc.className = 'message-content';
      this.$refs.mb.className = 'message-box-left';
    } else {
      if (this.user.id === this.msg.cusId) {
        this.$refs.mc.className = 'message-content-self';
        this.$refs.mb.className = 'message-box-right';
      } else {
        this.$refs.mc.className = 'message-content';
        this.$refs.mb.className = 'message-box-left';
      }
    }
  }
}
</script>

<style scoped>
.message-content-self {
  width: 70%;
  background-color: #409eff;
  padding: 10px 20px;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  border-top-left-radius: 15px;
}

.message-content {
  width: 70%;
  background-color: #F4F5F7;
  padding: 10px 20px;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  border-top-right-radius: 15px;
}

.message-box-left {
  display: flex;
  justify-content: left;
}

.message-box-right {
  display: flex;
  flex-direction: row-reverse;
}

.time {
  text-align: center;
  color: #BFBFC0;
  font-size: x-small;
  margin: 20px 0;
}
</style>
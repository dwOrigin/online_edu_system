<template>
<div class="message">
  <div class="time">
    {{message.time}}
  </div>
  <div ref="mb">
    <el-avatar
        :size="40"
        style="margin: 0 10px; order: 1"
        :src="message.senderAvatarUrl">
      <span v-if="message.senderAvatarUrl === ''">
        {{message.senderName}}
      </span>
    </el-avatar>
    <div
        style="order: 2"
        class="message-content" ref="mc">
      {{message.content}}
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: "Message",
  props:{
    msg:{},
  },
  data(){
      return {
          message: this.msg,
          user: {}
      };
  },
  mounted() {
    this.user = JSON.parse(window.localStorage.getItem('user'));
    if(this.user.id === this.msg.senderId){
      this.$refs.mc.className = 'message-content-self';
      this.$refs.mb.className = 'message-box-right';
    }else{
      this.$refs.mc.className = 'message-content';
      this.$refs.mb.className = 'message-box-left';
    }
  }
}
</script>

<style scoped>
.message-content-self{
  width: 70%;
  background-color:#409eff;
  padding: 10px 20px;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  border-top-left-radius: 15px;
}
.message-content{
  width: 70%;
  background-color: #F4F5F7;
  padding: 10px 20px;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  border-top-right-radius: 15px;
}
.message-box-left{
  display: flex;
  justify-content: left;
}
.message-box-right{
  display: flex;
  flex-direction: row-reverse;
}
.time{
  text-align: center;
  color: #BFBFC0;
  font-size: x-small;
  margin: 20px 0;
}

</style>
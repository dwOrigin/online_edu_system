<template>
<div class="message-center">
  <div class="contact-list">
    <div class="friends-header">
      <span style="margin-left: 50px; color: #6A6A6A; font-size: x-small">近期消息</span>
    </div>
    <div class="friends-list">
      <friend-card v-for="obj in 5" :key="obj"></friend-card>
    </div>
  </div>
  <div class="chat-window">
    <div class="chat-header">
      <span style="color: #353636; font-size: small;">系统消息</span>
    </div>
    <div class="chat-content">
      <message v-for="obj in 10" :key="obj"></message>
    </div>
    <div class="chat-input">
      <el-input
          type="text"
          placeholder="请输入内容"
          v-model="text"
          maxlength="50"
          show-word-limit
      >
        <template slot="append">
          <el-button>发送</el-button>
        </template>
      </el-input>
    </div>
  </div>
</div>
</template>

<script>
import FriendCard from "@/components/user/personal/FriendCard";
import Message from "@/components/user/personal/Message";
export default {
  name: "MessageCenter",
  components:{
    FriendCard,
    Message
  },
  data(){
      return {
        text: ''
      };
  }
}
</script>

<style scoped>
.chat-content::-webkit-scrollbar-thumb {
  border-radius: 2px;
  -webkit-box-shadow: inset 0 0 6px #999999;
}
.chat-content::-webkit-scrollbar{
  width: 5px;
  border-radius: 5px;
}
.friends-list:hover::-webkit-scrollbar-thumb{
  -webkit-box-shadow: inset 0 0 6px #999999;
}
.friends-list::-webkit-scrollbar-thumb {
  border-radius: 2px;
  -webkit-box-shadow: inset 0 0 6px white;
}
.friends-list::-webkit-scrollbar{
  width: 5px;
  border-radius: 5px;
}
.friends-list{
  display: flex;
  flex-direction: column;
  overflow: auto;
}
.chat-input{
  /*height: 100px;*/
  /*background-color:palegreen;*/
  flex-grow: 0;
  flex-shrink: 0;
}
.chat-content{
  display: flex;
  flex-direction: column;
  overflow: auto;
  flex-grow: 1;
}
.chat-header{
  border-bottom: 2px solid #F7F7F8;
  display: flex;
  height: 26px;
  justify-content: center;
  flex-shrink: 0;
  flex-grow: 0;
}
.friends-list{
  border-right: 2px solid #F7F7F8;
  width: 100%;
  /*background-color: teal;*/
  /*flex-grow: 1;*/
}
.friends-header{
  border-bottom: 2px solid #F7F7F8;
  border-right: 2px solid #F7F7F8;
  width: 100%;
  /*height: 30px;*/
  flex-grow: 0;
  flex-shrink: 0;
}
.chat-window{
  height: 500px;
  width: 70%;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  /*background-color: green;*/
}
.contact-list{
  display: flex;
  flex-direction: column;
  width: 200px;
  height: 500px;
  /*background-color: #409EFF;*/
}
.message-center{
  display: flex;
  width: 700px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-bottom: 100px;
  border-radius: 10px;
}
</style>
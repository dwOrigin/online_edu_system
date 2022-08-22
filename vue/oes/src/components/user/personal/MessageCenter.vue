<template>
  <div class="message-center">
    <div class="contact-list">
      <div class="friends-header">
        <span style="margin-left: 50px; color: #6A6A6A; font-size: x-small">近期消息</span>
      </div>
      <div class="friends-list">
        <friend-card v-for="obj in allMessages" :key="obj.friendId" :obj="obj"></friend-card>
      </div>
    </div>
    <div class="chat-window">
      <div class="chat-header">
        <span style="color: #353636; font-size: small;">{{ curChatObj.friendName }}</span>
      </div>
      <div class="chat-content">
        <message v-for="obj in curChatObj.history" :key="obj.mId"
                 :msg="obj"></message>
      </div>
      <div class="chat-input">
        <el-input
            type="text"
            placeholder="请输入内容"
            v-model="text"
            maxlength="50"
            @keyup.enter.native="sendMsg"
            show-word-limit
        >
          <template slot="append">
            <el-button @click="sendMsg">发送</el-button>
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
  components: {
    FriendCard,
    Message
  },
  data() {
    return {
      text: '',
      allMessages: [],
      curChatObj: {history: [], friendName: ''},
      user: {id: 888}
    };
  },
  methods: {
    getUserMessage() {
      //获取特定用户id的所有通信消息
      // let promise = this.$axios({
      //   url: '',
      //   method: '',
      //   data: {
      //     userId: this.user.id
      //   }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            messageList: [
              {
                friendId: 888,//好友的id
                friendName: '留云借风',
                friendAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
                unCheckedCnt: 1,//未读信息数
                history: [
                  {
                    mId: 81,//消息id
                    content: 'hello, my mother-in-law!',//消息内容
                    time: '2022/12/25 16:00:00',//发送时间
                    senderId: 1314520,
                    senderName: '留云借风',
                    senderAvatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
                  },
                  {
                    mId: 8,//消息id
                    content: 'hello, my mother-in-law!',//消息内容
                    time: '2022/12/25 16:00:00',
                    senderId: 888,
                    senderName: '留云借风',
                    senderAvatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
                  }
                ]
              },
              {
                friendId: 889,//好友的id
                friendName: '留云借风',
                friendAvatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
                unCheckedCnt: 1,//未读信息数
                history: [
                  {
                    mId: 83,//消息id
                    content: 'hello, my mother-in-law!',//消息内容
                    time: '2022/12/25 16:00:00',
                    senderId: 888,
                    senderName: '留云借风',
                    senderAvatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
                  },
                  {
                    mId: 88,//消息id
                    content: 'hello, my mother-in-law!',//消息内容
                    time: '2022/12/25 16:00:00',
                    senderId: 888,
                    senderName: '留云借风',
                    senderAvatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
                  }
                ]
              }
            ]
          }
        });
      });
      promise.then((res) => {
        this.allMessages = res.data.messageList;
        this.curChatObj = this.allMessages[0];
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    sendMsg() {
      if(this.text.length === 0){
        return;
      }
      let rId = this.curChatObj.friendId;
      let sId = this.user.id;
      //发送消息到rId的用户
      // let promise = this.$axios({
      //   url: '',
      //   method: '',
      //   data: {
      //     sId: sId,
      //     rId: rId,
      //     content: this.text,
      //   }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            result: true,
            msg: {//发送的消息
              mId: 8555,//消息id
              content: 'hello, my mother-in-law!!!!!!!',//消息内容
              time: '2022/12/25 16:00:00',//发送时间
              senderId: 1314520,
              senderName: '留云借风',
              senderAvatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
            }
          }
        });
      });
      promise.then((res) => {
        if(res.data.result){
          this.curChatObj.history.push(res.data.msg);
          this.text = '';
        }else{
          this.$message.error('消息发送失败');
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    }
  },
  mounted() {
    this.user = JSON.parse(window.localStorage.getItem('user'));
    this.getUserMessage();
    this.$bus.$on('clearUnCheckedCnt', (id) => {
      this.allMessages.forEach((item) => {
        if (item.friendId === id) {
          item.unCheckedCnt = 0;
        }
      });
    });
    this.$bus.$on('changeChatWindow', (id) => {
      this.allMessages.forEach((item) => {
        if (item.friendId === id) {
          this.curChatObj = item;
          this.text = '';
        }
      });
    });
  },
  beforeDestroy() {
    this.$bus.$off('clearUnCheckedCnt');
    this.$bus.$off('changeChatWindow');
  }
}
</script>

<style scoped>
.chat-content::-webkit-scrollbar-thumb {
  border-radius: 2px;
  -webkit-box-shadow: inset 0 0 6px #999999;
}

.chat-content::-webkit-scrollbar {
  width: 5px;
  border-radius: 5px;
}

.friends-list:hover::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px #999999;
}

.friends-list::-webkit-scrollbar-thumb {
  border-radius: 2px;
  -webkit-box-shadow: inset 0 0 6px white;
}

.friends-list::-webkit-scrollbar {
  width: 5px;
  border-radius: 5px;
}

.friends-list {
  display: flex;
  min-height: 100%;
  flex-direction: column;
  overflow: auto;
}

.chat-input {
  /*height: 100px;*/
  /*background-color:palegreen;*/
  flex-grow: 0;
  flex-shrink: 0;
}

.chat-content {
  display: flex;
  flex-direction: column;
  overflow: auto;
  flex-grow: 1;
}

.chat-header {
  border-bottom: 2px solid #F7F7F8;
  display: flex;
  height: 26px;
  justify-content: center;
  flex-shrink: 0;
  flex-grow: 0;
}

.friends-list {
  border-right: 2px solid #F7F7F8;
  width: 100%;
  /*background-color: teal;*/
  /*flex-grow: 1;*/
}

.friends-header {
  border-bottom: 2px solid #F7F7F8;
  border-right: 2px solid #F7F7F8;
  width: 100%;
  /*height: 30px;*/
  flex-grow: 0;
  flex-shrink: 0;
}

.chat-window {
  height: 500px;
  width: 70%;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  /*background-color: green;*/
}

.contact-list {
  display: flex;
  flex-direction: column;
  width: 200px;
  height: 500px;
  /*background-color: #409EFF;*/
}

.message-center {
  display: flex;
  width: 700px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-bottom: 100px;
  border-radius: 10px;
}
</style>
<template>
  <div class="message-center">
    <div class="contact-list">
      <div class="friends-header">
        <span style="margin-left: 50px; color: #6A6A6A; font-size: x-small">近期消息</span>
      </div>
      <div class="friends-list">
        <friend-card typer="system"></friend-card>
        <friend-card typer="receive" v-for="obj in allfriends" :key="obj.userId" :obj="obj"></friend-card>
      </div>
    </div>
    <div class="chat-window">
      <div class="chat-header">
        <span style="color: #353636; font-size: medium;">{{  name  }}</span>
      </div>
      <div class="chat-content">
        <message v-for="obj in curChatObj" :key="obj" :msg="obj" :typer="type"></message>
      </div>
      <div class="chat-input" v-if="type != 'system'">
        <el-input type="text" placeholder="请输入内容" v-model="text" maxlength="50" @keyup.enter.native="sendMsg"
          show-word-limit>
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
      SysMessages: [],
      allMessages: [],
      curChatObj: {},
      curoid: {},
      name: '系统消息',
      user: {},
      type: 'system',
      allfriends: {},
    };
  },
  methods: {
    getUserMessage() {
      //获取特定用户id的所有通信消息
      let promise = this.$axios({
        url: '/msgsystem/getallbyid',
        method: 'get',
        params: {
          id: this.user.userId
        }
      });
      promise.then((res) => {
        this.SysMessages = res.data;
        this.curChatObj = this.SysMessages;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      // let promise1 = this.$axios({
      //   url: '/msgreceive/getallbyid',
      //   method: 'get',
      //   params: {
      //     id: this.user.userId
      //   }
      // });
      // promise1.then((res1) => {
      //   this.allMessages = res1.data;
      // }).catch((err) => {
      //   this.$message.error('你的网络迷路了');
      // });
    },
    sendMsg() {
      if (this.text.length == 0) {
        return;
      }
      let promise = this.$axios({
        url: '/msgreceive/add',
        method: 'get',
        params: {
          cusId: this.user.userId,
          receiveId: this.curoid,
          content: this.text,
        }
      });
      // let promise = new Promise((a) => {
      //   a({
      //     data: {
      //       result: true,
      //       msg: {//发送的消息
      //         mId: 8555,//消息id
      //         content: 'hello, my mother-in-law!!!!!!!',//消息内容
      //         time: '2022/12/25 16:00:00',//发送时间
      //         senderId: 1314520,
      //         senderName: '留云借风',
      //         senderAvatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      //       }
      //     }
      //   });
      // });
      promise.then((res) => {
        if (res.data) {
          this.text = '';
          this.$bus.$emit('changeChatWindow', this.curoid);
        } else {
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
    let talkid = this.$route.query.select;
    console.log("===========>" + talkid);
    if (talkid != undefined) {
      console.log("----->" + talkid);
      this.request.get('/user/findOne', {
        params: {
          id: talkid
        }
      })
        .then((res) => {
          console.log("--------------------------------------------------")
          this.request.get('/msgreceive/talknew', {
            params: {
              talktoId: res.userId,
              userId: this.user.userId
            }
          })
            .then((res1) => {
              this.allfriends = res1;
            })
        })
    } else {
      console.log("---------->>>>")
      this.request.get('/msgreceive/getConnectUser', {
        params: {
          userId: this.user.userId
        }
      })
        .then((res) => {
          this.allfriends = res;
        })
    }
    // if (this.$route.query != undefined||this.$route.query!=null||this.$route.query!='') {
    // } else {
    // }
    // this.$bus.$on('clearUnCheckedCnt', (id) => {
    //   this.allMessages.forEach((item) => {
    //     if (item.friendId === id) {
    //       item.unCheckedCnt = 0;
    //     }
    //   });
    // });
    this.$bus.$on('changeChatWindow', (id) => {
      if (id == -1) {
        this.curChatObj = this.SysMessages;
        this.name = "系统消息"
        this.type = "system"
      } else {
        this.curoid = id;
        this.request.get('/msgreceive/getboth', {
          params: {
            cusId: id,
            userId: this.user.userId
          }
        })
          .then((res) => {
            this.curChatObj = res;
            this.type = "receive"
          })
        this.request.get('/user/findOne', {
          params: {
            id: id
          }
        })
          .then((res) => {
            this.name = res.userName
          })
      }
    });
  },
  beforeDestroy() {
    // this.$bus.$off('clearUnCheckedCnt');
    this.$bus.$off('changeChatWindow');
    this.$bus.$off('talk');
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
<template>
  <div class="answer">
    <div style="display: flex; align-items: center">
      <!-- <el-avatar
          :src="dataObj.commenterAvatarUrl"
          shape="square">
        <span v-if="dataObj.commenterAvatarUrl === ''">{{ dataObj.commenterName }}</span>
      </el-avatar> -->
      &nbsp;&nbsp;&nbsp;
      <span style="font-size: x-small; font-weight: bold; color:#4C4444;">{{ user.userName }}</span>
    </div>
    <div class="content">
      {{ dataObj.content }}
    </div>
    <div class="footer">
      <div>发布于 {{ dataObj.addTime }}</div>
      <div>
        <el-button
            @click="accept"
            type="primary" size="small" :plain="true">{{ likeC }}({{ dataObj.praiseCount }})
        </el-button>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  inject: ['reload'],
  name: "Answer",
  props: {
    obj: {},
    typeM: {}
  },
  data() {
    return {
      dataObj: this.obj,
      likeC: '赞同',
      type: this.typeM,
      user:{}
    };
  },
  mounted(){
    this.getUserName()
  },
  methods: {
    refresh(){
      this.$parent.refreshQuestion(this.$route.query.qId);
      this.$parent.refreshComment(this.$route.query.qId);
    },
    getUserName(){
      //获取提问者姓名
      let promise = this.$axios({
        url: 'http://localhost:8081/user/findOne',
        method: 'get',
        params: {
          id:this.dataObj.cusId
        }
      });
       promise.then((res) => {
        this.user = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    accept() {
      let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
      } else {
        usr = JSON.parse(usr);

        if (this.type === 'PassageComment') {
          //点赞文章评论
          // let promise = this.$axios({
          //   url: 'http://localhost:8081/questionscomment/addPraise',
          //   method: 'get',
          //   data:{
          //     cusId: usr.userId,
          //     id: this.dataObj.id
          //   }
          // });
          let promise = new Promise((a) => {
            a({
              data: {
                result: true
              }
            });
          });
          promise.then((res) => {
            let ret = res.data;
            if (ret) {
              this.likeC = '已赞同';
              this.dataObj.like++;
            } else {
              this.$message.error('请勿重复操作');
            }
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
        } else if (this.type === 'Answer') {
          //赞同回答
          let promise = this.$axios({
            url: 'http://localhost:8081/questionscomment/addPraise',
            method: 'get',
            params: {
                  // cusId: usr.userId,
                  id: this.dataObj.id
            }
          });
          promise.then((res) => {
            let ret = res.data;
            if (ret) {
              this.likeC = '已赞同';
              this.dataObj.praiseCount++;
            } else {
              this.$message.error('请勿重复操作');
            }
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
        }
      }
    }
  }
}
</script>

<style scoped>
.footer {
  color: #B8BFBC;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.content {
  font-size: x-small;
  font-weight: lighter;
  margin-top: 20px;
  margin-bottom: 20px;
}

.answer {
  padding-top: 20px;
  font-size: x-small;
  padding-bottom: 20px;
  border-bottom: 2px solid #F9F9F9;
}

</style>
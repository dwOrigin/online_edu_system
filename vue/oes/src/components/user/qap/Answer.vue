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
      <div>发布于 {{ dataObj.addtime.split('T')[0] }}&nbsp;{{ dataObj.addtime.split('T')[1].split(':')[0] }}:{{ dataObj.addtime.split('T')[1].split(':')[1] }}</div>
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
      user:{},
      isPraise:''
    };
  },
  mounted(){
    this.getUserName();
    this.getPraise();
  },
  methods: {
     getUserName(){
      //获取回答者姓名
      if(this.type === 'PassageComment'){
        let promise = this.$axios({
        url: 'http://localhost:8081/user/findOne',
        method: 'get',
        params: {
          id:this.dataObj.userId
        }
      });
       promise.then((res) => {
        this.user = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      }else if(this.type=='Answer'){

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
      }
    },
    getPraise(){
      let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
      } else {
        usr = JSON.parse(usr);
        if (this.type === 'PassageComment'){
          let promise=this.$axios({
          url:'http://localhost:8081/records/orLikedArticleCom',
          method:'get',
          params:{
            userId:usr.userId,
            articleCommentId:this.dataObj.commentId
          }
        });
         promise.then((res) => {
        this.isPraise = res.data;
        console.log('这是getpraise'+res.data);
        if(this.isPraise=='1'){
          this.likeC = '已赞同';
        }
        else if(this.isPraise=='2'){
          this.likeC='赞同'
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
        }else if(this.type=='Answer'){
           let promise=this.$axios({
          url:'http://localhost:8081/records/orLikedCom',
          method:'get',
          params:{
            userId:usr.userId,
            commentId:this.dataObj.id
          }
        });
         promise.then((res) => {
        this.isPraise = res.data;
        console.log('这是getpraise'+res.data);
        if(this.isPraise=='1'){
          this.likeC = '已赞同';
        }
        else if(this.isPraise=='2'){
          this.likeC='赞同'
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
        }      
      }
    },
    accept() {
      let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
      } else {
        usr = JSON.parse(usr);
        if (this.type === 'PassageComment') {
        //   修改文章评论的点赞状态
        let promise=this.$axios({
          url:'http://localhost:8081/records/orLikedArticleCom',
          method:'get',
          params:{
            userId:usr.userId,
            articleCommentId:this.dataObj.commentId
          }
        });
         promise.then((res) => {
        this.isPraise = res.data;
          if(this.isPraise=='2'){
            this.$axios({
            url: 'http://localhost:8081/comment/praise',
            method: 'get',
            params: {
                  id: this.dataObj.commentId,
            }
          });
            let promise = this.$axios({
            url: 'http://localhost:8081/records/addRecordArticleComment',
            method: 'get',
            params: {
                  userId: usr.userId,
                  articleCommentId: this.dataObj.commentId,
            }
          });
          promise.then((res) => {
              // this.reload();
              this.likeC = '已赞同';
              this.dataObj.praiseCount++;
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
          }else if(this.isPraise=='1'){
            this.$axios({
            url: 'http://localhost:8081/comment/cancelPrise',
            method: 'get',
            params: {
                  commentId: this.dataObj.commentId,
            }
          });
             let promise = this.$axios({
            url: 'http://localhost:8081/records/reduceRecordArticleComment',
            method: 'get',
            params: {
                  userId: usr.userId,
                  articleCommentId: this.dataObj.commentId,
            }
          });
          promise.then((res) => {
            this.likeC = '赞同';
              this.dataObj.praiseCount--;
              // this.reload();
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
          }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });         
        } else if (this.type === 'Answer') {
          //修改回答的赞同状态
          let promise=this.$axios({
          url:'http://localhost:8081/records/orLikedCom',
          method:'get',
          params:{
            userId:usr.userId,
            commentId:this.dataObj.id
          }
        });
         promise.then((res) => {
        this.isPraise = res.data;
          console.log('这是点赞里的'+this.isPraise);
          if(this.isPraise=='2'){
            this.$axios({
            url: 'http://localhost:8081/questionscomment/addPraise',
            method: 'get',
            params: {
                  id: this.dataObj.id,
            }
          });
            let promise = this.$axios({
            url: 'http://localhost:8081/records/addRecordCom',
            method: 'get',
            params: {
                  userId: usr.userId,
                  commentId: this.dataObj.id,
                  // like: this.likeC === '赞同'//修改后的点赞状态true or false
            }
          });
          promise.then((res) => {
              // this.reload();
              this.likeC = '已赞同';
              this.dataObj.praiseCount++;
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
          }else if(this.isPraise=='1'){
            this.$axios({
            url: 'http://localhost:8081/questionscomment/reducePraise',
            method: 'get',
            params: {
                  id: this.dataObj.id,
            }
          });
             let promise = this.$axios({
            url: 'http://localhost:8081/records/reduceRecordCom',
            method: 'get',
            params: {
                  userId: usr.userId,
                  commentId: this.dataObj.id,
                  // like: this.likeC === '赞同'//修改后的点赞状态true or false
            }
          });
          promise.then((res) => {
            this.likeC = '赞同';
              this.dataObj.praiseCount--;
              // this.reload();
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
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

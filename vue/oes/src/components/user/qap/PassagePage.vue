<template>
  <div
      style="overflow: hidden; margin-bottom: -50px">
    <div class="passage-page">
      <div class="title">{{ passage.title }}</div>
      <div class="time"> {{ passage.createTime }} 修改</div>
      <div class="content">
        <!-- {{ passage.summary }} -->
        <!-- 等下想怎么展示md文件 -->
         <mavon-editor
            class="md"
            :value="htmlContent" 
            :subfield="prop.subfield" 
            :defaultOpen="prop.defaultOpen"
            :toolbarsFlag="prop.toolbarsFlag"
            :editable="prop.editable"
            :scrollStyle="prop.scrollStyle"
          />
      </div>
      <div class="footer">
        <div>
          <el-button
              @click="handleClick()"
              type="primary" plain>{{ likeBtnContent }}({{ passage.praiseCount }})
          </el-button>
        </div>
        <div>
          <el-button
              @click="handleComment"
              ype="primary" plain>评论文章
          </el-button>
        </div>
      </div>
      <div>
        <div style="margin-top: 10px">
          <answer
              typeM="PassageComment" v-if="update"
              v-for="obj in answers" :obj="obj"></answer>
        </div>
      </div>
    </div>
    <el-dialog
        :visible.sync="CommentDialogVisible"
        center
        :show-close="false"
        append-to-body
        modal-append-to-body
        width="30%">
      <el-input
          type="textarea"
          ref="cc"
          :rows="5"
          maxlength="100"
          show-word-limit
          placeholder="请输入你的评论"
          v-model="cc">
      </el-input>
      <span slot="footer" class="dialog-footer">
      <el-button
          plain
          style='padding-left: 30px; padding-right: 30px; margin-right: 50px'
          type="primary" @click="handleClickFooter('ask')">评论
      </el-button>
        <el-button
            plain
            style='padding-left: 30px; padding-right: 30px'
            type="info" @click="handleClickFooter('cancel')">
          取消
        </el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Answer from "@/components/user/qap/Answer";
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  name: "PassagePage",
  components: {
    Answer,
    mavonEditor
  },
  data() {
    return {
      // number: 10,
      passage: {},
      answers:[],
      likeBtnContent: '点赞',
      CommentDialogVisible: false,
      cc: '',
      isPraise:'',
      htmlContent:'',
      update:true
      // length: 5,
    };
  },
  computed: {
  // 解析器配置
    prop () {
      let data = {
        subfield: false,// 单双栏模式
        defaultOpen: 'preview',//edit： 默认展示编辑区域 ， preview： 默认展示预览区域 
        editable: false,    // 是否允许编辑
        toolbarsFlag: false,
        scrollStyle: true
      }
      return data
    }
  },
  methods: {
    // getInfo(){
    //    this.htmlContent=data
    // },
     refreshComment(qId) {
      // 获取问题答案
      let promise = this.$axios({
        url: 'http://localhost:8081/comment/showA',
        method: 'get',
        params: {
          articleId: qId
        }
      });
      promise.then((res) => {
        this.answers = res.data;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    loadPassage() {
      let id = this.$route.query.pId;
      //获取文章详细信息
      let promise = this.$axios({
          url: 'http://localhost:8081/article/getbyid',
          method: 'get',
          params:{
            id: id
          }
      });
      promise.then((res) => {
        this.passage = res.data;
        console.log(this.passage.createTime);
        this.htmlContent=res.data.summary;
        let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
      } else {
        usr = JSON.parse(usr);
        let promise = this.$axios({
          url: 'http://localhost:8081/records/orLikedArt',
          method: 'get',
          params:{
            articleId: id,
            userId:usr.userId
          }
      });
       promise.then((res) => {
        this.isPraise = res.data;
        if(this.isPraise=='1'){
          this.likeBtnContent = '已喜欢';
        }
        else if(this.isPraise=='2'){
          this.likeBtnContent='喜欢'
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
      }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    handleClick() {
      let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
        return;
      }
       usr = JSON.parse(usr);
       let promise = this.$axios({
          url: 'http://localhost:8081/records/orLikedArt',
          method: 'get',
          params:{
            articleId: this.passage.articleId,
            userId:usr.userId
          }
      });
       promise.then((res) => {
        this.isPraise = res.data;
        if (this.isPraise == '2') {
        //点赞文章
        let promise = this.$axios({
          url: 'http://localhost:8081/records/addRecordArt',
          method: 'get',
          params: {
            articleId: this.passage.articleId,
            userId: usr.userId
          }
        });
        promise.then((res) => {
            this.passage.praiseCount++;
            this.likeBtnContent = '已喜欢';
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      } else if (this.isPraise == '1') {
         let promise = this.$axios({
            url: 'http://localhost:8081/records/reduceRecordArt',
            method: 'get',
            params: {
                  userId: usr.userId,
                  articleId: this.passage.articleId,
            }
          });
          promise.then((res) => {
            this.passage.praiseCount--;
            this.likeBtnContent = '喜欢';
          }).catch((err) => {
            this.$message.error('你的网络迷路了');
          });
          }     
       });
    },
    handleComment(){
      this.CommentDialogVisible = true;
    },
    handleClickFooter(btnName) {
      if (btnName === 'cancel') {

      } else if (btnName === 'ask') {
        let usr = window.localStorage.getItem('user');
      if (usr === null) {
        this.$bus.$emit('OpenLoginDialog');
        return;
      }
       usr = JSON.parse(usr);
        if (this.cc.length < 10) {
          this.$message.error('评论不能少于10个字');
          return;
        }
        let user = JSON.parse(window.localStorage.getItem('user'));
        //评论文章
        let promise = this.$axios({
          url: 'http://localhost:8081/comment/sendArticle',
          method: 'get',
          params: {
            commentContent: this.cc,
            userId: user.userId,
            articleId: this.passage.articleId
          }
        });
        promise.then((res) => {
          let ret = res.data;
          if (ret) {
            this.$message.success('评论成功')
            this.refreshComment(this.$route.query.pId);
            // this.loadPassage();
             this.update = false
            // 在组件移除后，重新渲染组件
            // this.$nextTick可实现在DOM 状态更新后，执行传入的方法。
            this.$nextTick(() => {
                this.update = true
            })
          } else {
            this.$message.error('请勿重复评论');
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
      this.cc = '';
      this.CommentDialogVisible = false;
    }
  },
  mounted() {
    this.loadPassage();
    this.refreshComment(this.$route.query.pId);
    // this.getInfo();
  },
}
</script>

<style scoped>
.content {
  word-wrap: break-word;
  margin: 10px 20px;
}

.footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #CCCCD8;
  font-size: small;
  /*border: 1px solid #ECECED;*/
  padding: 10px;
  border-top: 2px solid #F9F9F9;
  border-bottom: 2px solid #F9F9F9;
}

.paragraph {
  text-indent: 2em;
  margin: 0 0 30px 0;
}

.content {
  padding: 10px 20px;
  line-height: 30px;
}

.time {
  color: #999AAA;
  font-size: small;
  margin: 10px 0;
}

.title {
  font-size: x-large;
  font-weight: bold;
}

.passage-page {
  border: 1px solid #ECECED;
  width: 900px;
  /*background-color: #F0EFE2;*/
  margin: 10px auto 20px auto;
  /*border: 1px solid #DAD8C1;*/

  padding: 20px;
  /*height: 100px;*/
  /*background-color: teal;*/
}
</style>
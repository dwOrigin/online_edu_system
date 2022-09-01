<template>
  <div
      style="overflow: hidden; margin-bottom: -50px">
    <div class="passage-page">
      <div class="title">{{ passage.title }}</div>
      <div class="time"> {{ passage.createTime}} </div>
      <div class="content">
        <!-- {{ passage.summary }} -->
        <!-- 等下想怎么展示md文件 -->
        <!-- 要双向绑定 -->
        <mavon-editor
            class="md"
            v-model="htmlContent"
            :subfield="prop.subfield"
            :defaultOpen="prop.defaultOpen"
            :toolbarsFlag="prop.toolbarsFlag"
            :editable="prop.editable"
            :scrollStyle="prop.scrollStyle"
        />
      </div>
      <div class="footer">
        <div  style="float:left">
          <el-button
              type="primary" plain>喜欢({{ passage.praiseCount }})
          </el-button>
        </div>
        <div style="float:right">
          <el-button  type="primary" round @click="updateArticle">确认修改</el-button>
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
  </div>
</template>

<script>
import Answer from "@/views/qap/Answer";
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
      passage: {},
      answers:[],
      htmlContent:'',
      update:true
    };
  },
  computed: {
    // 解析器配置
    prop () {
      let data = {
        subfield: false,// 单双栏模式
        defaultOpen: 'edit',//edit： 默认展示编辑区域 ， preview： 默认展示预览区域
        editable: true,    // 是否允许编辑
        toolbarsFlag: true,
        scrollStyle: true
      }
      return data
    }
  },
  methods: {
    updateArticle(){
      console.log(this.htmlContent);
      let promise=this.$axios({
        url: '/article/update',
        method: 'post',
        params: {
          articleId: this.$route.query.pId,
          content:this.htmlContent
        }
      });
      promise.then((res) => {
        this.$message.success('修改成功');
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    refreshComment(qId) {
      // 获取问题答案
      let promise = this.$axios({
        url: '/comment/showA',
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
        url: '/article/getbyid',
        method: 'get',
        params:{
          id: id
        }
      });
      promise.then((res) => {
        this.passage = res.data;
        this.htmlContent=res.data.summary;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
  },
  mounted() {
    this.loadPassage();
    this.refreshComment(this.$route.query.pId);
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
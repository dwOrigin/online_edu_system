<template>
  <div
      @click="clickedM"
      class="card">
    <el-link :underline="false" style="font-size: 18px; font-weight: bold">
      <span v-if="type==='question'">{{dataObj.title}}</span>
      <span v-if="type==='passage'">{{dataObj.title}}</span>
    </el-link>
    <div class="body">
      <el-link :underline="false" type="info">
        <p class="answer-preview" v-if="type==='question'">
          {{dataObj.hottestAnswererName}}:&nbsp;&nbsp;&nbsp;{{dataObj.hottestAnswer}}
        </p>
        <p class="answer-preview" v-if="type==='passage'">
          {{dataObj.content}}
        </p>
      </el-link>
    </div>
    <div class="question-card-footer">
      <el-tag v-if="type==='question'">
        &nbsp;&nbsp;{{dataObj.praiseCount}}赞同&nbsp;&nbsp;
      </el-tag>
      <el-tag v-if="type==='passage'">
        &nbsp;&nbsp;{{dataObj.praiseCount}}点赞&nbsp;&nbsp;
      </el-tag>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <el-tag type="success" v-if="type==='question'">&nbsp;&nbsp;{{dataObj.replyCount}}条答案</el-tag>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuestionCard",
  props: {
    type: {
      type: String,
    },
    obj:{}
  },
  data(){
      return {
        dataObj: this.obj
      };
  },
  methods:{
    clickedM(){
      if(this.type === 'question'){
        this.$router.push({
          name: 'questionPage',
          query:{
            qId: this.dataObj.qId
          }
        });
      }else if(this.type === 'passage'){
        this.$router.push({
          name: 'passagePage',
          query:{
            pId: this.dataObj.pId
          }
        });
      }
    }
  }
}
</script>

<style scoped>
.card:hover{
  cursor: pointer;
}
.card{
  border-bottom: 1px solid #9499A0;
  margin-top: 15px;
}
.body{
  margin: 10px auto;
}

.question-card-footer {
  height: 50px;
  margin-top: 6px;
}

.answer-preview {
  color: black;
  font-size: 15px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
</style>
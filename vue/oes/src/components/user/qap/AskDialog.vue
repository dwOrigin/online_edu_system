<template>
  <el-dialog
      :visible.sync="dialogVisible"
      center
      :show-close="false"
      append-to-body
      modal-append-to-body
      width="30%">
    <div style="margin: 0 auto 20px auto; display: flex; justify-content: center">
      <el-input
          style="margin-right: 10px"
          maxlength="30"
          v-model="qTitle" placeholder="输入你的问题标题">
      </el-input>
      <el-select v-model="category"
                 placeholder="问题分类">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.value"
            :value="item.value">
        </el-option>
      </el-select>
    </div>
    <el-input
        type="textarea"
        ref="qC"
        :rows="6"
        maxlength="100"
        show-word-limit
        style="margin-bottom: 0"
        placeholder="输入问题描述"
        v-model="qContent">
    </el-input>
    <span slot="footer" class="dialog-footer">
    <el-button
        plain
        style='padding-left: 30px; padding-right: 30px; margin-right: 50px'
        type="primary" @click="handleClickFooter('ask')">提问</el-button>
          <el-button
              plain
              style='padding-left: 30px; padding-right: 30px'
              type="info" @click="handleClickFooter('cancel')">取消</el-button>
  </span>
  </el-dialog>
</template>

<script>
import axios from "axios";

export default {
  name: "AskDialog",
  data() {
    return {
      dialogVisible: false,
      qContent: '',
      qTitle: '',
      category: '',
      options:[{value: '闲聊'}, {value: '知识'}],
      question:{
        cusId:'',
        title:'',
        content:'',
        type:'',
        status:1,
      }
    };
  },
  methods: {
    handleClickFooter(btnName) {
      if(btnName === 'cancel'){

      }else if (btnName === 'ask' || this.qTitle.length < 10){
        if(this.qContent.length < 10){
          this.$message.error('问题标题或描述不能少于10个字');
          return;
        }
        if(this.category === ''){
          this.$message.error('请选择问题分类');
          return;
        }
        let user = JSON.parse(window.localStorage.getItem('user'));
        //提问
        this.question.content=this.qContent;
        this.question.cusId=user.userId;
        this.question.title=this.qTitle;
        this.question.type=this.category;
        this.request.post('/questions',this.question)
        .then((res)=>{
          if(res){
            this.$message.success("提问成功");
            this.$parent.refreshQuestion();
            // this.$bus.$emit('refreshQuestion');
          }else{
            this.$message.error("提问失败");
          }
        })
        // let promise = new Promise((a)=>{
        //     a({
        //         data:{
        //           passed: true
        //         }
        //     });
        // });
      }
      this.qContent = '';
      this.qTitle = '';
      this.category = '';
      this.dialogVisible = false;
    }
  },
  mounted() {
    this.$bus.$on('openAskDialog', (data) => {
        this.dialogVisible = true;
    });
  },
  beforeDestroy() {
    this.$bus.$off('openAskDialog');
  }
}
</script>

<style scoped>

</style>
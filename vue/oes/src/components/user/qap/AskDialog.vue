<template>
  <el-dialog
      :visible.sync="dialogVisible"
      center
      :show-close="false"
      append-to-body
      modal-append-to-body
      width="30%">
    <el-input
        type="textarea"
        ref="qC"
        :rows="6"
        maxlength="100"
        show-word-limit
        placeholder="请输入你的问题"
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
      questions:{
        cusId:'',

      }
    };
  },
  methods: {
    handleClickFooter(btnName) {
      if(btnName === 'cancel'){

      }else if (btnName === 'ask'){
        if(this.qContent.length < 10){
          this.$message.error('问题不能少于10个字');
          return;
        }
        let user = JSON.parse(window.localStorage.getItem('user'));
        提问
        let promise = this.$axios({
            url: '/questions',
            method: 'post',
            params:{
              qContent: this.qContent,
              userId: user.id
            }
        });
        promise.then((res)=>{
          let ret = res.data.passed;
          if(ret){
            this.$message.success('提问成功');
          }else{
            this.$message.error('提问失败');
          }
        }).catch((err)=>{
            this.$message.error('你的网络迷路了');
        });
      }
      this.qContent = '';
      this.dialogVisible = false;
      this.reload();
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
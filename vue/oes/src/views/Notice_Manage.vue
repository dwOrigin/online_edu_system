<template>
   <div class="homeBox">
   <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu
      default-active="6"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose">
      <el-menu-item index="1" @click="gotoMember">
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="2" @click="gotoTeacher">
        <span slot="title">讲师管理</span>
      </el-menu-item>
      <el-menu-item index="3" @click="gotoLesson">
        <span slot="title">课程管理</span>
      </el-menu-item>
      <el-menu-item index="4" @click="gotoArticle">
        <span slot="title">文章管理</span>
      </el-menu-item>
      <el-menu-item index="5" @click="gotoQA">
        <span slot="title">问答管理</span>
      </el-menu-item>
      <el-menu-item index="6" @click="gotoNotice">
        <span slot="title">通知管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
  <el-container>
    <el-header  style="text-align: left; font-size: 25px">
      <span>管理员</span>
    </el-header>
    <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="用户类型" prop="region">
    <el-select v-model="ruleForm.region" placeholder="请选择用户类型">
      <el-option label="用户一" value="shanghai"></el-option>
      <el-option label="用户二" value="beijing"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="消息内容" prop="desc">
    <el-input type="textarea" v-model="ruleForm.desc" :autosize="{minRows: 8}" ></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">发送</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
    </el-main>
  </el-container>
</el-container>
</div>
</template>

<script>
export default{
    activated: function() {
 this.getCase()
 },
  data() {
      return {
       ruleForm: {
          region: '',
          desc: ''
        },
        rules: {
          region: [
            { required: true, message: '请选择活动区域', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写活动形式', trigger: 'blur' }
          ]
        }
      };
      
  },
     methods:{
        gotoMember(){
            this.$router.push('/member_manage')
        },
        gotoTeacher(){
            this.$router.push('/teacher_manage')
        },
        gotoLesson(){
            this.$router.push('/lesson_manage')
        },
        gotoArticle(){
            this.$router.push('/article_manage')
        },
        gotoQA(){
            this.$router.push('/qa_manage')
        },
        gotoNotice(){
            this.$router.push('/notice_manage')
        },
          submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('发送成功!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
     }
}
</script>

<style>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
  html,body,#app,.homeBox,.el-container{
    padding:0px;
    margin:0px;
    height:100%;
  }
</style>
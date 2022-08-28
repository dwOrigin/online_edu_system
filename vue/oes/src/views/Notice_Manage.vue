<template>
  <div class="homeBox">
    <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="6" class="el-menu-vertical-demo">
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
           <el-menu-item index="7" @click="gotoHome">
            <span slot="title">退出</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="text-align: left; font-size: 25px">
          <span>管理员</span>
        </el-header>
        <el-main>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户类型" prop="type">
              <el-select v-model="ruleForm.type" placeholder="请选择用户类型">
                <el-option label="全体用户" value="全体用户"></el-option>
                <el-option v-for="user in users" :key=user.userId :label=user.userName :value=user.userId>
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="消息内容" prop="content">
              <el-input type="textarea" v-model="ruleForm.content" :autosize="{ minRows: 8 }"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">发送</el-button>
              <el-button @click="resetForm">重置</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  activated: function () {
    this.getCase()
  },
  data() {
    return {
      ruleForm: {
        type:'',
        content:'',
      },
      msgsystem:{
        content:'',
        acceptId:'',
      },
      users: {
        userId: '',
        userName: '',
        showName: '',
        mobile: '',
        email: '',
        lastSystemTime: '',
        isAvailable: '',
        password: '',
        sex: '',
        age: '',
        createTime: '',
        piclmg: '',
        msgNum: '',
        sysMsgNum: '',
      },
      rules: {
        region: [
          { required: true, message: '请选择用户类型', trigger: 'change' }
        ],
        desc: [
          { required: true, message: '请填写消息内容', trigger: 'blur' }
        ]
      }
    };

  },
  methods: {
    gotoMember() {
      this.$router.push('/member_manage')
    },
    gotoTeacher() {
      this.$router.push('/teacher_manage')
    },
    gotoLesson() {
      this.$router.push('/lesson_manage')
    },
    gotoArticle() {
      this.$router.push('/article_manage')
    },
    gotoQA() {
      this.$router.push('/qa_manage')
    },
    gotoNotice() {
      this.$router.push('/notice_manage')
    },
    gotoHome(){
      this.$router.push('/home')
    },
    submitForm() {
      if(this.ruleForm.type=="全体用户"){
        this.request
          .post("/msgsystem/sendAllMsg",this.ruleForm.content)
          .then((res)=>{
            if(res.code=="200"){
              this.$message.success("发送成功");
            }else{
              this.$message.error("发送失败");
            }
          })
      }else{
        this.msgsystem.content=this.ruleForm.content;
        this.msgsystem.acceptId=this.ruleForm.type;
        this.request
          .post("/msgsystem/add",this.msgsystem)
          .then((res)=>{
            if(res.code=="200"){
              this.$message.success("发送成功");
            }else{
              this.$message.error("发送失败");
            }
          })
      }
      this.resetForm();
    },
    resetForm() {
      this.ruleForm.type='';
      this.ruleForm.content='';
    }
  },
  mounted() {
    this.request
      .get("/user")
      .then((res) => {
        this.users = res;
      })
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

html,
body,
#app,
.homeBox,
.el-container {
  padding: 0px;
  margin: 0px;
  height: 100%;
}
</style>
<template>
    <div class="homeBox">
   <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu
      default-active="4"
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
        <el-form ref="form" :model="form" label-width="80px" enctype="multipart/form-data">
        <el-row>
            <el-col :span="10">
               <el-form-item label="文章标题" prop="title">
     <el-input v-model="form.title"></el-input>
  </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="10">
              <el-form-item label="关键词" prop="keyWord">
    <el-input v-model="form.keyWord"></el-input>
  </el-form-item>
            </el-col>
            <el-col :span="10">
                 <el-form-item label="文章作者" prop="author">
    <el-input v-model="form.author"></el-input>
  </el-form-item>
            </el-col>
        </el-row>
        <el-row>           
             <el-form-item label="文章来源" prop="source">
    <el-input v-model="form.source"></el-input>
  </el-form-item>
        </el-row>
  <el-form-item label="文章类型" prop="articleType">
    <el-select v-model="form.articleType" placeholder="请选择文章类型">
      <el-option label="科技" value="科技"></el-option>
      <el-option label="文学" value="文学"></el-option>
      <el-option label="闲聊" value="闲聊"></el-option>
    </el-select>
  </el-form-item>
    <mavon-editor ref="md" v-model="form.summary" :ishljs="true" @imgAdd="imgAdd"/> 
   <!-- imgAdd监听图片上传 save监听图片保存 value保存整个markdown文件内容 -->
   <el-form-item>  </el-form-item>
  <el-form-item>
       <el-button type="primary" @click="onSubmit('form')">发布文章</el-button>
    <el-button @click="resetForm('form')">重置</el-button> 
   <!-- 居中不了，去死吧 -->
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
       form: {
          aritcleId:'',
          title:'',
          summary:'',
          keyWord:'',
          imageUrl:'',
          source:'',
          author:'',
          articleType:'',
        },
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
         handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      onSubmit(formName) {
           this.request.post('http://localhost:8081/article', this.form)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.data);
          } else {
            this.$message.error(res.data);
          }
        });
        this.resetForm(formName);
      },
       resetForm(formName) {
        this.$refs[formName].resetFields();
        this.form.summary='';
      },
      //保存图片到后台
     // 绑定@imgAdd event
imgAdd(pos, $file) {
  let $vm = this.$refs.md
  // 第一步.将图片上传到服务器.
  let formData = new FormData();
  formData.append('file', $file);
  this.$axios({
    url: 'http://localhost:8081/file/upload?filetype=picture',
    method: 'post',
    params:{file:formData} ,
    headers: {'Content-Type': 'multipart/form-data'},
  }).then((res) => {
    // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
    $vm.$img2Url(pos, res.data);
  })
},
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
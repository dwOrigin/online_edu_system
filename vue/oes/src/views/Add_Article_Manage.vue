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
        <el-form ref="form" :model="form" label-width="80px">
        <el-row>
            <el-col span="10">
                 <el-form-item label="文章ID" prop="articleId">
    <el-input v-model="form.articleId"></el-input>
  </el-form-item>
            </el-col>
            <el-col span="10">
               <el-form-item label="文章标题" prop="title">
     <el-input v-model="form.title"></el-input>
  </el-form-item>
            </el-col>
        </el-row>
        <el-row>
         <el-form-item label="文章概要" prop="summary">
    <el-input type="textarea" v-model="form.summary"></el-input>
  </el-form-item>
        </el-row>
        <el-row>
            <el-col span="10">
              <el-form-item label="关键词" prop="keyWord">
    <el-input v-model="form.keyWord"></el-input>
  </el-form-item>
            </el-col>
            <el-col span="10">
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
      <el-option label="美女" value="shanghai"></el-option>
      <el-option label="高中生" value="beijing"></el-option>
    </el-select>
  </el-form-item>
   <mavon-editor v-model="value" :ishljs = "true" ref=md @save="save" @imgAdd="imgAdd" />   
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
        value:''
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
         onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('发布成功!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        this.resetForm(formName);
      },
       resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      save(){ 
         //传递name(文件名),typeId(文件所属类别),value(md文件内容）
         var result=postMd("555","java",this.value);
         console.log(result);
         this.dialogFormVisible=false
         
      },

      //保存图片到后台
      imgAdd(pos, $file){
        var _this = this;
        // 第一步.将图片上传到服务器.
        var formdata = new FormData();
        formdata.append('image', $file);
        uploadFile(formdata).then(resp=> {
         var url = resp.data; //取出上传成功后的url
          if (resp.status == 200) {
          //  将后端返回的url放在md中图片的指定位置
          console.log(url)
           _this.$refs.md.$img2Url(pos, url)
          } else {
            _this.$message({type: resp.status, message: resp.statusText});
          }
        });
      },
     }
    
}
//上传md文件
export function postMd(name,typeId,content){
   return  axios.post('/saveMd', {
                        name: name,
                        typeId: typeId,
                        content: content
                     })
                     .then(function (response) {
                        console.log(response);
                     })
                     .catch(function (error) {
                        console.log(error);
                     });
}

//上传图片
export const uploadFile = (params) => {
   return axios({
     method: 'post',
     url: `/uploadFile`,
     data: params,
     headers: {
       'Content-Type': 'multipart/form-data'
     }
   });
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
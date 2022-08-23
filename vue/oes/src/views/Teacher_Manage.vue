<template>
<div class="homeBox">
   <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu
      default-active="2"
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
    <el-header >
      <span style="text-align: left; font-size: 25px">管理员</span>
       <el-button type="primary" style="float: right" round @click="addTeacher">添加讲师</el-button>
        <el-dialog title="详细信息" :visible.sync="addNewVisible" append-to-body>
  <el-form :model="newform">
    <el-form-item prop="id" label="教师ID" :label-width="formLabelWidth">
      <el-input v-model="newform.id" ></el-input>
    </el-form-item>
   <el-form-item prop="name" label="名称" :label-width="formLabelWidth">
      <el-input v-model="newform.name" ></el-input>
    </el-form-item>
    <el-form-item prop="education" label="所属机构" :label-width="formLabelWidth">
      <el-input v-model="newform.education"></el-input>
    </el-form-item>
    <el-form-item prop="career" label="简介" :label-width="formLabelWidth">
      <el-input v-model="newform.career"></el-input>
      <!-- 前面限制宽度了，所以别写太长好了，很难改 -->
    </el-form-item>
    <el-form-item prop="isStar" label="是否推荐" :label-width="formLabelWidth">
      <el-radio v-model="newform.isStar" :label="1">是</el-radio>
      <el-radio v-model="newform.isStar" :label="0">否</el-radio>
    </el-form-item>
     <el-form-item prop="picPath" label="头像" :label-width="formLabelWidth">
      <el-input v-model="newform.picPath"></el-input>
    </el-form-item>
    <el-form-item prop="status" label="状态" :label-width="formLabelWidth">
      <el-radio v-model="newform.status" :label="1">在线</el-radio>
      <el-radio v-model="newform.status" :label="0">不在线</el-radio>
    </el-form-item>
    <el-form-item prop="createTime" label="创建时间" :label-width="formLabelWidth">
      <el-input v-model="newform.createTime"></el-input>
    </el-form-item>
    <el-form-item prop="subjectId" label="分类ID" :label-width="formLabelWidth">
      <el-input v-model="newform.subjectId"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="addNewVisible = false">取 消</el-button>
    <el-button type="primary" @click="addTeachermsg();addNewVisible = false;">确 定</el-button>
  </div>
  </el-dialog>
    </el-header>
    <el-main>
        <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      fixed
      prop="id"
      label="教师ID"
      width="120">
    </el-table-column>
    <el-table-column
      prop="name"
      label="名称"
      width="120">
    </el-table-column>
    <el-table-column
      prop="education"
      label="所属机构"
      width="120">
    </el-table-column>
    <el-table-column
      prop="isStar"
      label="是否推荐"
      width="120">
       <template slot-scope="scope">
        <!-- 取到当前单元格 -->
        <span v-if="scope.row.isAvailable==0">否</span>
        <span v-else>是</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="status"
      label="状态"
      width="120">
       <template slot-scope="scope">
        <!-- 取到当前单元格 -->
        <span v-if="scope.row.isAvailable==0">不在线</span>
        <span v-else>在线</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="createTime"
      label="创建时间"
      width="120">
    </el-table-column>
    <el-table-column
      prop="subjectId"
      label="分类ID"
      width="120">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
         <el-dialog title="详细信息" :visible.sync="dialogFormVisible" append-to-body>
  <el-form :model="form">
    <el-form-item prop="id" label="教师ID" :label-width="formLabelWidth">
      <el-input v-model="form.id" :disabled="true"></el-input>
    </el-form-item>
   <el-form-item prop="name" label="名称" :label-width="formLabelWidth">
      <el-input v-model="form.name" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item prop="education" label="所属机构" :label-width="formLabelWidth">
      <el-input v-model="form.education"></el-input>
    </el-form-item>
    <el-form-item prop="career" label="简介" :label-width="formLabelWidth">
      <el-input v-model="form.career"></el-input>
      <!-- 前面限制宽度了，所以别写太长好了，很难改 -->
    </el-form-item>
    <el-form-item prop="isStar" label="是否推荐" :label-width="formLabelWidth">
      <el-radio v-model="form.isStar" :label="1">是</el-radio>
      <el-radio v-model="form.isStar" :label="0">否</el-radio>
    </el-form-item>
     <el-form-item prop="picPath" label="头像" :label-width="formLabelWidth">
      <el-input v-model="form.picPath"></el-input>
    </el-form-item>
    <el-form-item prop="status" label="状态" :label-width="formLabelWidth">
      <el-radio v-model="form.status" :label="1">在线</el-radio>
      <el-radio v-model="form.status" :label="0">不在线</el-radio>
    </el-form-item>
    <el-form-item prop="createTime" label="创建时间" :label-width="formLabelWidth">
      <el-input v-model="form.createTime" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item prop="subjectId" label="分类ID" :label-width="formLabelWidth">
      <el-input v-model="form.subjectId"></el-input>
    </el-form-item>
    <el-form-item prop="sort" label="排序" :label-width="formLabelWidth">
      <el-input v-model="form.sort" :disabled="true"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="changeUsermsg();dialogFormVisible = false;">确 定</el-button>
  </div>
  </el-dialog>
        <el-button @click="deleteMember(scope.row)" type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
    </el-main>
   <div style='text-align:center'>
        <el-footer class="block">
    <span class="demonstration"></span>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
     
      :page-size="100"
      layout="prev, pager, next, jumper"
      :total="1000">
    </el-pagination>
  </el-footer>
    </div>
  </el-container>
</el-container>
</div>
   
</template>

<script>
export default{
    inject:['reload'],
  name:'Teacher_Manage',
  activated: function() {
 this.getCase()
 },
 mounted(){
  this.fetchData()
 },
     data(){
        return{
            tableData: [],
         dialogFormVisible: false,
         addNewVisible:false,
        form: {
          id:'',
          name:'',
          education:'',
          career:'',
          isStar:'',
          picPath:'',
          status:'',
          createTime:'',
          subjectId:'',
          sort:''
        },
        newform:{
             id:'',
          name:'',
          education:'',
          career:'',
          isStar:'',
          picPath:'',
          status:'',
          createTime:'',
          subjectId:'',
          sort:''
        },
        formLabelWidth: '120px',
        }
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
        handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClick(row) {
        console.log(row);
      },
      handleClick(row) {
        console.log(row);
        this.form=row;
        this.dialogFormVisible=true;
        //现在改了内容点取消也会被改
        //   this.templateList = extendCopy(row);
        //   // 对象进行深拷贝，否则“编辑”框内修改内容(e)会影响到页面
        //   console.log(this.templateList);

        //   function extendCopy(p) {
        //     var c = {};
        //     for (var i in p) {
        //       c[i] = p[i];
        //     }
        //     c.uber = p;
        //     return c;
        //   }
      },
      addTeacher(){
        this.addNewVisible=true;
      },
      deleteMember(row){
        console.log(row);
        this.$confirm('是否确认删除该讲师?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
         this.request.post('http://localhost:8081/teacher/removeTeacher', row)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.message);
          } else {
            this.$message.error(res.message);
          }
        })
      //    this.$axios.post('http://localhost:8081/teacher/removeTeacher', {
      //   id: row.id
      // })
      //   .then(function (response) {
      //     console.log(response);
      //   })
      //   .catch(function (error) {
      //     console.log(error);
      //   });
      this.reload();
      },
      changeUsermsg(){
         this.request.post('http://localhost:8081/teacher/updateTeacher', this.form)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.message);
          } else {
            this.$message.error(res.message);
          }
        })
       this.reload();
      },
      addTeachermsg(){
        this.request.post('http://localhost:8081/teacher/updateTeacher', this.newform)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.message);
          } else {
            this.$message.error(res.message);
          }
        })
       this.reload();
      },
       fetchData(){
        this.$axios.get('http://localhost:8081/teacher').then(
          response=>{
            this.tableData=response.data;
          },
          response=>{
            console.log("error");
            alert("请求失败");
          }
        );
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
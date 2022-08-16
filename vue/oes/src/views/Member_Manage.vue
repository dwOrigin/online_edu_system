<template>
<div class="homeBox">
  <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu
      default-active="1"
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
    <el-header style="text-align: left; font-size: 25px">
      <span>管理员</span>
    </el-header>
    
    <el-main>
     <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      fixed
      prop="userId"
      label="用户ID"
      width="120">
    </el-table-column>
     <el-table-column
      prop="userName"
      label="账号"
      width="120">
    </el-table-column>
     <el-table-column
      prop="showName"
      label="显示名称"
      width="120">
    </el-table-column>
    <el-table-column
      prop="mobile"
      label="手机"
      width="120">
    </el-table-column>
     <el-table-column
      prop="email"
      label="邮箱"
      width="150">
    </el-table-column>
    <el-table-column
      prop="lastSystemTime"
      label="上次登录时间"
      width="120">
    </el-table-column>
    <el-table-column
      prop="isAvailable"
      label="是否可用"
      width="80">
      <template slot-scope="scope">
        <!-- 取到当前单元格 -->
        <span v-if="scope.row.isAvailable==0">否</span>
        <span v-else>是</span>
      </template>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        <el-dialog title="详细信息" :visible.sync="dialogFormVisible" append-to-body>
  <el-form :model="form">
    <el-form-item prop="userId" label="用户ID" :label-width="formLabelWidth">
      <el-input v-model="form.userId" :disabled="true"></el-input>
    </el-form-item>
   <el-form-item prop="userName" label="账号" :label-width="formLabelWidth">
      <el-input v-model="form.userName" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item prop="showName" label="显示名称" :label-width="formLabelWidth">
      <el-input v-model="form.showName"></el-input>
    </el-form-item>
    <el-form-item prop="mobile" label="手机" :label-width="formLabelWidth">
      <el-input v-model="form.mobile"></el-input>
    </el-form-item>
    <el-form-item prop="email" label="邮箱" :label-width="formLabelWidth">
      <el-input v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item prop="password" label="密码" :label-width="formLabelWidth">
      <el-input v-model="form.password"></el-input>
    </el-form-item>
    <el-form-item prop="sex" label="性别" :label-width="formLabelWidth">
      <el-radio v-model="form.sex" label="1">男</el-radio>
      <el-radio v-model="form.sex" label="2">女</el-radio>
    </el-form-item>
    <el-form-item prop="age" label="年龄" :label-width="formLabelWidth">
      <el-input v-model="form.age"></el-input>
    </el-form-item>
    <el-form-item prop="createTime" label="注册时间" :label-width="formLabelWidth">
      <el-input v-model="form.createTime" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item prop="isAvailable" label="是否可用" :label-width="formLabelWidth">
      <el-radio v-model="form.isAvailable" label="1">是</el-radio>
      <el-radio v-model="form.isAvailable" label="0">否</el-radio>
    </el-form-item>
    <el-form-item prop="piclmg" label="图片链接" :label-width="formLabelWidth">
      <el-input v-model="form.piclmg"></el-input>
    </el-form-item>
    <el-form-item prop="msgNum" label="消息数量" :label-width="formLabelWidth">
      <el-input v-model="form.msgNum"></el-input>
    </el-form-item>
    <el-form-item prop="sysMsgNum" label="系统消息数量" :label-width="formLabelWidth">
      <el-input v-model="form.sysMsgNum"></el-input>
    </el-form-item>
    <el-form-item prop="lastSystemTime" label="上次登录时间" :label-width="formLabelWidth">
      <el-input v-model="form.lastSystemTime" :disabled="true"></el-input>
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
      :current-page.sync="currentPage3"
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
export default {
    activated: function() {
 this.getCase()
 },
   data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: [{
          userId:'71120207',
          userName:'张柯宁',
          showName:'Bo',
          mobile:'13722789359',
          email:'1303322505@qq.com',
          lastSystemTime:'2022-8-14 15:13',
          isAvailable:'1',
          password:'71120207',
          sex:'2',
          age:'20',
          createTime:'2002-10-9 11:22',
          piclmg:'vdihfoidjcfd',
          msgNum:'2',
          sysMsgNum:'1',
        }, {
          userId:'20050923',
          userName:'刘耀文',
          showName:'脑壳要爆炸',
          mobile:'13722789359',
          email:'1303322505@qq.com',
          lastSystemTime:'2022-8-12 15:13',
          isAvailable:'1',
          password:'20050923',
          sex:'1',
          age:'17',
          createTime:'2005-9-23 11:22',
          piclmg:'vdihfoidjcfd',
          msgNum:'4',
          sysMsgNum:'99',
        }, {
          userId:'20061124',
          userName:'余宇涵',
          showName:'。。。。。',
          mobile:'13722789359',
          email:'1303322505@qq.com',
          lastSystemTime:'2022-8-13 15:13',
          isAvailable:'1',
          password:'20061124',
          sex:'1',
          age:'16',
          createTime:'2006-11-23 11:22',
          piclmg:'vdihfoidjcfd',
          msgNum:'4',
          sysMsgNum:'99',
        }, {
          userId:'19970805',
          userName:'王一博',
          showName:'uniqyibo',
          mobile:'13722789359',
          email:'1303322505@qq.com',
          lastSystemTime:'2022-8-14 15:13',
          isAvailable:'0',
          password:'19970805',
          sex:'1',
          age:'25',
          createTime:'1997-8-5 11:22',
          piclmg:'vdihfoidjcfd',
          msgNum:'43',
          sysMsgNum:'99',
        },{
          userId:'19961029',
          userName:'陈哲远',
          showName:'男团门面',
          mobile:'13722789359',
          email:'1303322505@qq.com',
          lastSystemTime:'2022-8-14 15:13',
          isAvailable:'1',
          password:'19961029',
          sex:'1',
          age:'26',
          createTime:'1996-10-29 11:22',
          piclmg:'vdihfoidjcfd',
          msgNum:'5',
          sysMsgNum:'99',
        },{
          userId:'19950808',
          userName:'崔胜澈',
          showName:'S.Coups',
          mobile:'13722789359',
          email:'1303322505@qq.com',
          lastSystemTime:'2022-8-14 08:08',
          isAvailable:'1',
          password:'19950808',
          sex:'1',
          age:'27',
          createTime:'1995-8-8 11:22',
          piclmg:'vdihfoidjcfd',
          msgNum:'40',
          sysMsgNum:'9',
        }
        ],        
        dialogFormVisible: false,
        form: {
          userId: '',
          userName: '',
          showName: '',
          mobile: '',
          email:'',
          password:'',
          sex:'',
          age:'',
          createTime:'',
          isAvailable:'',
          piclmg:'',
          msgNum:'',
          sysMsgNum:'',
          lastSystemTime:''
        },
        formLabelWidth: '120px',
        currentPage1: 5,
        currentPage2: 5,
        currentPage3: 5,
        currentPage4: 4
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
      deleteMember(row){
        console.log(row);
        this.$confirm('是否确认删除该用户?', '提示', {
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
      },
      changeUsermsg(){
        this.$notify({
          title: '成功',
          message: '信息修改成功',
          type: 'success'
        });
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


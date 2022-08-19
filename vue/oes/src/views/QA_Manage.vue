<template>
<div class="homeBox">
   <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu
      default-active="5"
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
      prop="id"
      label="问答ID"
      width="120">
    </el-table-column>
    <el-table-column
      prop="cusId"
      label="提问者ID"
      width="120">
    </el-table-column>
    <el-table-column
      prop="title"
      label="标题"
      width="120">
    </el-table-column>
    <el-table-column
      prop="status"
      label="状态"
      width="120">
      <template slot-scope="scope">
        <!-- 取到当前单元格 -->
        <span v-if="scope.row.isAvailable==0">未回答</span>
        <span v-else>已回答</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="replyCount"
      label="回复数量"
      width="120  ">
    </el-table-column>
    <el-table-column
      prop="browseCount"
      label="浏览数量"
      width="120">
    </el-table-column>
   <el-table-column
      prop="type"
      label="类型"
      width="100"
      :filters="[{ text: '美女', value: '美女' }, { text: '高中生', value: '高中生' },{text:'帅哥',value:'帅哥'}]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          close-transition>{{scope.row.type}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        <el-button  @click="deleteMember(scope.row)" type="text" size="small">删除</el-button>
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
export default{
    activated: function() {
 this.getCase()
 },
  data() {
      return {
        tableData: [{
          id:'1',
          cusId:'1',
          title:'张柯宁',
          status:'1',
          type:'美女',
          replyCount:'3',
          browseCount:'5'
        }, {
          id:'2',
          cusId:'2',
          title:'刘耀文',
          status:'1',
          type:'高中生',
          replyCount:'3',
          browseCount:'5'
        },{
         id:'3',
          cusId:'3',
          title:'陈哲远',
          status:'1',
          type:'帅哥',
          replyCount:'3',
          browseCount:'5'
        }
        ]
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
        this.$router.push('modify_qa_manage')
      },
       deleteMember(row){
        console.log(row);
        this.$confirm('是否确认删除该课程?', '提示', {
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
       formatter(row, column) {
        return         
            row.id,
            row.cusId,
            row.title,
            row.status,
            row.replyCount,
            row.browseCount
          
      },
      filterTag(value, row) {
        return row.type === value;
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
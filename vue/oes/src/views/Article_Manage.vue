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
    <el-header>
       <span style="text-align: left; font-size: 25px">管理员</span>
       <el-button type="primary" style="float: right" round @click="addArticle">添加文章</el-button>
    </el-header>
    <el-main>
         <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      fixed
      prop="articleId"
      label="文章ID"
      width="100">
    </el-table-column>
    <el-table-column
      prop="title"
      label="标题"
      width="160">
    </el-table-column>
    <el-table-column
      prop="summary"
      label="文章概要"
      width="120">
    </el-table-column>
    <el-table-column
      prop="keyWord"
      label="关键词"
      width="120  ">
    </el-table-column>
    <el-table-column
      prop="createTime"
      label="创建时间"
      width="120">
    </el-table-column>
    <el-table-column
      prop="clickNum"
      label="点击数量"
      width="120">
    </el-table-column>
     <el-table-column
      prop="articleType"
      label="文章类型"
      width="100"
      :filters="[{ text: '美女', value: '美女' }, { text: '高中生', value: '高中生' },{text:'帅哥',value:'帅哥'}]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          close-transition>{{scope.row.articleType}}</el-tag>
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
    inject:['reload'],
  name:'Article_Manage',
  activated: function() {
 this.getCase()
 },
 mounted(){
  this.fetchData()
 },
  data() {
      return {
        tableData: [{
          articleId:'1',
          title:'张柯宁',
          summary:'sdfvdgeds',
          keyWord:'美女',
          articleType:'美女',
          createTime:'2022-8-15 19:06',
          clickNum:'111'
        }, {
          articleId:'2',
          title:'刘耀文',
          summary:'sdfvdgeds',
          keyWord:'高中生',
          articleType:'高中生',
          createTime:'2022-8-15 19:06',
          clickNum:'444'
        },{
          articleId:'3',
          title:'陈哲远',
          summary:'sdfvdgeds',
          keyWord:'帅哥',
          articleType:'帅哥',
          createTime:'2022-8-15 19:06',
          clickNum:'1029'
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
        this.$router.push('modify_article_manage')
      },
      formatter(row, column) {
        return         
            row.articleId,
            row.title,
            row.summary,
            row.keyWord,
            row.createTime,
            row.clickNum
          
      },
      filterTag(value, row) {
        return row.articleType === value;
      },
       deleteMember(row){
        console.log(row);
        this.$confirm('是否确认删除该文章?', '提示', {
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
      addArticle(){
        this.$router.push('/add_article_manage')
      },
       fetchData(){
        this.$axios.get("http://localhost:8081/article").then(
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
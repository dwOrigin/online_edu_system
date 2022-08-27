<template>
  <div class="homeBox">
    <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="5" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
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
          <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%">
            <el-table-column fixed prop="id" label="问答ID" width="120">
            </el-table-column>
            <el-table-column prop="cusId" label="提问者ID" width="120">
            </el-table-column>
            <el-table-column prop="title" label="标题" width="120">
            </el-table-column>
            <el-table-column prop="status" label="状态" width="120">
              <template slot-scope="scope">
                <!-- 取到当前单元格 -->
                <span v-if="scope.row.isAvailable == 0">未回答</span>
                <span v-else>已回答</span>
              </template>
            </el-table-column>
            <el-table-column prop="replyCount" label="回复数量" width="120  ">
            </el-table-column>
            <el-table-column prop="browseCount" label="浏览数量" width="120">
            </el-table-column>
            <el-table-column prop="type" label="类型" width="100"
              :filters="[{ text: '美女', value: '美女' }, { text: '高中生', value: '高中生' }, { text: '帅哥', value: '帅哥' }]"
              :filter-method="filterTag" filter-placement="bottom-end">
              <template slot-scope="scope">
                <el-tag close-transition>{{ scope.row.type }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button @click="deleteMember(scope.row)" type="text" size="small">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
        <div style='text-align:center'>
          <el-footer class="block">
            <span class="demonstration"></span>
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="pageSizes"
              :current-page.sync="currentPage" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
              :total="totalCount">
            </el-pagination>
          </el-footer>
        </div>
      </el-container>
    </el-container>
  </div>

</template>

<script>
export default {
  inject: ['reload'],
  name: 'QA_Manage',
  activated: function () {
    this.getCase()
  },
  mounted() {
    this.fetchData()
  },
  data() {
    return {
      currentPage: 1,
      pageSize: 5,
      totalCount: 1,
      pageSizes: [5, 10],
      tableData: [
      ]
    }
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
    gotoHome() {
      this.$router.push('/home')
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.currentPage = 1;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
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
      this.$router.push({
        name: 'modify_qa_manage',
        query: { qId: row.id }
      })
    },
    deleteMember(row) {
      console.log(row);
      this.$confirm('是否确认删除该问答?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('/questions/delete', {
          params: {
            id: row.id
          }
        })
          .then(() => {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
            this.reload();
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
      /*this.$axios.delete('http://localhost:8081/questions/${row.id}',
    //   {
    //   data:{
    //   id: row.id
    //   }
    // }
    )
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });*/
      // this.reload();
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
    fetchData() {
      this.$axios.get('/questions').then(
        response => {
          this.tableData = response.data;
          this.totalCount = response.data.length;
        },
        response => {
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

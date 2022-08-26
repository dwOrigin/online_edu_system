<template>
  <div class="homeBox">
    <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
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
          <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"  style="width: 100%">
            <el-table-column fixed prop="userId" label="用户ID" width="120">
            </el-table-column>
            <el-table-column prop="userName" label="账号" width="120">
            </el-table-column>
            <el-table-column prop="showName" label="显示名称" width="120">
            </el-table-column>
            <el-table-column prop="mobile" label="手机" width="120">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="150">
            </el-table-column>
            <el-table-column prop="lastSystemTime" label="上次登录时间" width="150">
            </el-table-column>
            <el-table-column prop="isAvailable" label="是否可用" width="80">
              <template slot-scope="scope">
                <!-- 取到当前单元格 -->
                <span v-if="scope.row.isAvailable == 0">否</span>
                <span v-else>是</span>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-dialog title="详细信息" :visible.sync="dialogFormVisible" append-to-body>
                  <el-form :model="form" id="form">
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
                      <el-radio v-model="form.sex" :label="1">男</el-radio>
                      <el-radio v-model="form.sex" :label="2">女</el-radio>
                    </el-form-item>
                    <el-form-item prop="age" label="年龄" :label-width="formLabelWidth">
                      <el-input v-model="form.age"></el-input>
                    </el-form-item>
                    <el-form-item prop="createTime" label="注册时间" :label-width="formLabelWidth">
                      <el-input v-model="form.createTime" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item prop="isAvailable" label="是否可用" :label-width="formLabelWidth">
                      <el-radio v-model="form.isAvailable" :label="1">是</el-radio>
                      <el-radio v-model="form.isAvailable" :label="0">否</el-radio>
                    </el-form-item>
                    <el-form-item prop="picImg" label="图片链接" :label-width="formLabelWidth">
                      <el-input v-model="form.picImg"></el-input>
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
                    <el-button @click="notChange">取 消</el-button>
                    <el-button type="primary" @click="changeUsermsg(); dialogFormVisible = false;">确 定</el-button>
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
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="pageSizes"
              :current-page.sync="currentPage" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="totalCount">
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
  name: 'Member_Manage',
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
      totalCount:1,
      pageSizes:[5,10],
      tableData: [],
      dialogFormVisible: false,
      form: {
        userId: '',
        userName: '',
        showName: '',
        mobile: '',
        email: '',
        password: '',
        sex: '',
        age: '',
        createTime: '',
        isAvailable: '',
        picImg: '',
        msgNum: '',
        sysMsgNum: '',
        lastSystemTime: ''
      },
      formLabelWidth: '120px',
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
    gotoHome(){
      this.$router.push('/home')
    },
    handleSizeChange(val) {
      this.pageSize=val;
      this.currentPage=1;
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage=val;
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
      this.form = row;
      this.dialogFormVisible = true;
      //点取消后直接刷新，从数据库里取值，解决了，我天，我也太聪明了吧哈哈哈
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

    deleteMember(row) {
      console.log(row);
      this.$confirm('是否确认删除该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/user/deleteUser', {
          userId: row.userId
        }).then(response=>{
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
      this.reload();
    },

    changeUsermsg() {
      console.log(this.form.userId);
      this.request.post('/user/updateUser', this.form)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.message);
          } else {
            this.$message.error(res.message);
          }
        });
        this.reload();
    },
    fetchData() {
      this.$axios.get("/user").then(
        response => {
          this.tableData = response.data;
          this.totalCount=response.data.length;
        },
        response => {
          console.log("error");
          alert("请求失败");
        }
      );
    },
    notChange(){
      this.dialogFormVisible = false;
      this.reload();
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


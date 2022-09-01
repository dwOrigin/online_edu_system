<template>
  <div class="homeBox">
    <el-container style="height: 100% ; border: 1px solid #eee;direction='vertical'">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
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
        <el-header>
          <span style="text-align: left; font-size: 25px">管理员</span>
          <el-button type="primary" style="float: right; margin:10px 10px " round @click="addTeacher">添加讲师</el-button>
          <el-dialog title="详细信息" :visible.sync="addNewVisible" append-to-body>
            <el-form :model="newform">
              <el-form-item prop="name" label="名称" :label-width="formLabelWidth">
                <el-input v-model="newform.name"></el-input>
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
                <el-avatar shape="square" :size="60" :src="newform.picPath"></el-avatar>
                  <el-link @click="changeAvatar" :underline="false" type="primary" style="margin: 0 30px">上传
                  </el-link>
                <!-- <el-input v-model="newform.picPath"></el-input> -->
              </el-form-item>
              <el-dialog :visible.sync="avatar" append-to-body :close-on-click-modal="false" lock-scroll top="25vh" center
              width="max-content" modal-append-to-body>
              <el-upload class="avatar-uploader" action="http://120.46.178.233:8081/file/upload" :drag="true"
                :show-file-list="false" :data="{ filetype: 'picture' }" :on-success="handleAvatarSuccess">
                <img v-if="form.logo" :src="form.logo" class="avatar"  this.avatar=false>
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-dialog>

              <el-form-item prop="status" label="状态" :label-width="formLabelWidth">
                <el-radio v-model="newform.status" :label="1">在线</el-radio>
                <el-radio v-model="newform.status" :label="0">不在线</el-radio>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="notAdd();addNewVisible = false">取 消</el-button>
              <el-button type="primary" @click="addTeachermsg(); addNewVisible = false;">确 定</el-button>
            </div>
          </el-dialog>
        </el-header>
        <el-main>
          <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" style="width: 100%">
            <el-table-column fixed prop="id" label="教师ID" width="120">
            </el-table-column>
            <el-table-column prop="name" label="名称" width="150">
            </el-table-column>
            <el-table-column prop="education" label="所属机构" width="170">
            </el-table-column>
            <el-table-column prop="career" label="简介" width="250">
            </el-table-column>
            <el-table-column prop="isStar" label="是否推荐" width="120">
              <template slot-scope="scope">
                <!-- 取到当前单元格 -->
                <span v-if="scope.row.isAvailable == 0">否</span>
                <span v-else>是</span>
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态" width="150">
              <template slot-scope="scope">
                <!-- 取到当前单元格 -->
                <span v-if="scope.row.isAvailable == 0">不在线</span>
                <span v-else>在线</span>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="250">
            </el-table-column>
            <el-table-column label="操作" width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-dialog title="详细信息" :visible.sync="dialogFormVisible" append-to-body>
                  <el-form :model="form">
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
                      <el-avatar shape="square" :size="60" :src="form.picPath"></el-avatar>
                      <el-link @click="changeAvatar2" :underline="false" type="primary" style="margin: 0 30px">上传
                      </el-link>
                      <!-- <el-input v-model="newform.picPath"></el-input> -->
                    </el-form-item>
                    <el-dialog :visible.sync="avatar2" append-to-body :close-on-click-modal="false" lock-scroll top="25vh" center
                               width="max-content" modal-append-to-body>
                      <el-upload class="avatar-uploader" action="http://120.46.178.233:8081/file/upload" :drag="true"
                                 :show-file-list="false" :data="{ filetype: 'picture' }" :on-success="handleAvatarSuccess2">
                        <img v-if="form.picPath" :src="form.picPath" class="avatar"  this.avatar2=false>
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                      </el-upload>
                    </el-dialog>
                    <el-form-item prop="status" label="状态" :label-width="formLabelWidth">
                      <el-radio v-model="form.status" :label="1">在线</el-radio>
                      <el-radio v-model="form.status" :label="0">不在线</el-radio>
                    </el-form-item>
                    <el-form-item prop="createTime" label="创建时间" :label-width="formLabelWidth">
                      <el-input v-model="form.createTime" :disabled="true"></el-input>
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
  name: 'Teacher_Manage',
  activated: function () {
    this.getCase()
  },
  mounted() {
    this.fetchData()
  },
  data() {
    return {
      avatar: false,
      avatar2:false,
      tableData: [],
      currentPage: 1,
      pageSize: 7,
      totalCount: 1,
      pageSizes: [5, 7,10],
      dialogFormVisible: false,
      addNewVisible: false,
      form: {
        id: '',
        name: '',
        education: '',
        career: '',
        isStar: '',
        picPath: '',
        status: '',
        createTime: '',
      },
      newform: {
        name: '',
        education: '',
        career: '',
        isStar: '',
        picPath: '',
        status: '',
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
    /* handleClick(row) {
    *和下面的重复了
       console.log(row);
     },*/
    handleClick(row) {
      console.log(row);
      this.form = row;
      this.dialogFormVisible = true;
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
    changeAvatar() {
      this.avatar = true;
    },
    handleAvatarSuccess(res, file) {
      this.newform.picPath = res;

    },
    changeAvatar2() {
      this.avatar2 = true;
    },
    handleAvatarSuccess2(res, file) {
      this.form.picPath = res;

    },
    addTeacher() {
      this.addNewVisible = true;
    },
    deleteMember(row) {
      console.log(row);
      this.$confirm('是否确认删除该讲师?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log(row.id);
        this.$axios.get('/teacher/removeTeacher', {
          params: {
            teacherId: row.id
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
    },
    changeUsermsg() {
      this.request.post('/teacher/updateTeacher', this.form)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.message);
          } else {
            this.$message.error(res.message);
          }
        })
      this.reload();
    },
    notAdd(){
      this.newform.name= '';
      this.newform.education= '';
      this.newform.career= '';
      this.newform.isStar='';
      this.newform.picPath= '';
      this.newform.status= '';
    },
    addTeachermsg() {
      this.request.post('/teacher/addTeacher', this.newform)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.message);
            this.reload();
          } else {
            this.$message.error(res.message);
          }
        })

    },
    //不用手写时间
    fetchData() {
      this.$axios.get('/teacher/findAll').then(
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

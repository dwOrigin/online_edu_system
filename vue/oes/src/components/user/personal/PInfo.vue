<template>
  <div class="p-info">
    <el-descriptions border direction="vertical" :column="1" style="font-size: medium">
      <el-descriptions-item label="用户名">
        {{ user.userName }}
        <el-link @click="changeName" :underline="false" type="primary" style="margin: 0 10px">修改
        </el-link>
      </el-descriptions-item>
      <el-descriptions-item label="手机号">
        {{ user.mobile }}
        <el-link @click="changePhone" :underline="false" type="primary" style="margin: 0 10px">修改
        </el-link>
      </el-descriptions-item>
      <el-descriptions-item label="密码">
        **************
        <el-link @click="changePwd" :underline="false" type="primary" style="margin: 0 10px">修改
        </el-link>
      </el-descriptions-item>
      <el-descriptions-item label="头像">
        <el-avatar shape="square" :size="60" :src="user.picImg"></el-avatar>
        <el-link @click="changeAvatar" :underline="false" type="primary" style="margin: 0 30px">修改
        </el-link>
      </el-descriptions-item>
    </el-descriptions>
    <el-dialog :visible.sync="visible.name" append-to-body lock-scroll top="25vh" center modal-append-to-body
      width="25%">
      <el-input v-model="newUser.name" placeholder="请输入新的用户名"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button plain type="primary" @click="changeNameConfirm">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog :visible.sync="visible.phone" append-to-body :close-on-click-modal="false" lock-scroll top="25vh" center
      modal-append-to-body width="25%">
      <el-input v-model="newUser.phone" style="margin: 20px 0" :disabled="codeIsRight" placeholder="请输入新的手机号">
        <template slot="append">
          <el-button plain v-if="sendCoolDown === 0" type="primary" @click="sendCode">发送验证码
          </el-button>
          <span v-if="sendCoolDown !== 0">{{ sendCoolDown }}s</span>
        </template>
      </el-input>
      <el-input v-model="newUser.code" style="margin: 20px 0" placeholder="请输入验证码">
        <template slot="append">
          <el-button plain v-if="!codeIsRight" type="primary" @click="vCode">
            验证
          </el-button>
          <span v-if="codeIsRight" style="color: palegreen">验证成功</span>
        </template>
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button plain type="primary" @click="changePhoneConfirm">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog :visible.sync="visible.pwd" append-to-body :close-on-click-modal="false" lock-scroll top="25vh" center
      modal-append-to-body width="25%">
      <el-input v-model="newUser.pwd" style="margin: 20px 0" show-password placeholder="请输入新的密码">
      </el-input>
      <el-input v-model="newUser.pwdR" style="margin: 20px 0" show-password placeholder="请再次输入新密码">
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button plain type="primary" @click="changePwdConfirm">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog :visible.sync="visible.avatar" append-to-body :close-on-click-modal="false" lock-scroll top="25vh" center
      width="max-content" modal-append-to-body>
      <el-upload class="avatar-uploader" action="http://localhost:8081/file/upload" :drag="true" :show-file-list="false"
        :data="{ filetype: 'picture' }" :on-success="handleAvatarSuccess">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>


export default {
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  //不对格式进行检查了, i am tired
  name: "PInfo",
  data() {
    return {
      user: {},
      phoneDisable: false,
      visible: {
        name: false,
        phone: false,
        pwd: false,
        avatar: false
      },
      sendCoolDown: 0,
      codeIsRight: false,
      imageUrl: '',
      newUser: {
        name: '',
        phone: '',
        code: '',
        codeR: '',
        pwd: '',
        pwdR: '',
      }
    }
  },
  mounted() {
    let user = window.localStorage.getItem('user');
    this.user = JSON.parse(user);
  },
  methods: {
    vCode() {
      if (this.newUser.code == this.newUser.codeR) {
        this.codeIsRight = true;
      } else {
        console.log(this.newUser.codeR);
        console.log(this.newUser.code);
        this.$message.error('验证失败');
      }
    },
    changeName() {
      this.visible.name = true;
    },
    changeNameConfirm() {
      //不进行过于复杂的验证
      if (this.newUser.name.length < 8) {
        this.$message.error('用户名至少8位');
        return;
      }
      if (this.newUser.name === this.user.userName) {
        this.$message.error('与旧用户名相同');
      } else {
        //修改用户名
        this.user.userName = this.newUser.name;
        let promise = this.$axios({
          url: 'http://localhost:8081/user/updateUser',
          method: 'post',
          data: this.user
        });
        promise.then((res) => {
          if (res.data.code == "200") {
            this.reloadUser();
            this.newUser.name = '';
            this.visible.name = false;
            this.$message.success('用户名修改成功');
          } else {
            this.$message.error(res.data.message);
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
    },
    sendCode() {
      //发送手机号验证码
      let promise = this.$axios({
        url: 'http://localhost:8081/user/sendCode',
        method: 'get',
        params: {
          mobile: this.newUser.phone
        }
      });
      promise.then((res) => {
        this.$message.success('已发送验证码');
        this.newUser.codeR = res.data;
        console.log(res.data);
        this.sendCoolDown = 60;
        let timer = setInterval(() => {
          if (this.sendCoolDown === 0) {
            clearInterval(timer);
          } else {
            this.sendCoolDown--;
          }
        }, 1000);
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    changePhone() {
      this.visible.phone = true;
    },
    changePhoneConfirm() {
      if (!this.codeIsRight) {
        this.$message.error('请先验证手机号');
        return;
      }
      //修改手机号
      this.user.mobile = this.newUser.phone;
      let promise = this.$axios({
        url: 'http://localhost:8081/user/updateUser',
        method: 'post',
        data: this.user
      });
      promise.then((res) => {
        if (res.data.code == "200") {
          this.$message.success('手机号修改成功');
          this.reloadUser();
          this.newUser.phone = '';
          this.newUser.code = '';
          this.newUser.codeR = '';
          this.codeIsRight = false;
          this.sendCoolDown = 0;
          this.visible.phone = false;
        } else {
          this.$message.error('手机号已被注册');
          // this.newUser.phone = '';
          this.newUser.code = '';
          this.newUser.codeR = '';
          this.codeIsRight = false;
          this.sendCoolDown = 0;
        }
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
    changePwd() {
      this.visible.pwd = true;
    },
    changePwdConfirm() {
      if (this.newUser.pwdR !== this.newUser.pwd) {
        this.$message.error('两次密码不同');
        return;
      } else if (this.newUser.pwd.length < 8) {
        this.$message.error('密码至少8位');
        return;
      }
      else {
        //修改密码
        this.user.password = this.newUser.pwd;
        let promise = this.$axios({
          url: 'http://localhost:8081/user/updateUser',
          method: 'post',
          data: this.user
        });
        promise.then((res) => {
          if (res.data.code == "200") {
            this.$message.success('修改密码成功');
          } else {
            this.$message.error('修改密码失败');
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
        this.newUser.pwdR = '';
        this.newUser.pwd = '';
        this.visible.pwd = false
      }
    },
    changeAvatar() {
      this.visible.avatar = true;
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = res;
      let lasturl = this.user.picImg;
      this.request.get("http://localhost:8081/file/removeFile", {
        params: {
          url: lasturl
        }
      }).then((res) => {
        console.log(res);
      })
      this.user.picImg = this.imageUrl;
      this.request.post("http://localhost:8081/user/updateUser", this.user)
        .then((res) => {
          if (res.code == "200") {
            this.$message.success(res.message);
            this.reloadUser();
          } else {
            this.$message.error(res.message);
          }
        })
    },
    reloadUser() {
      //获取用户信息(同登录)
      let promise = this.$axios({
        url: 'http://localhost:8081/user/id',
        method: 'get',
        params: {
          id: this.user.userId
        }
      });
      promise.then((res) => {
        let nUser = res.data;
        window.localStorage.removeItem('user');
        window.localStorage.setItem('user', JSON.stringify(nUser));
        this.user = nUser;
      }).catch((err) => {
        this.$message.error('你的网络迷路了');
      });
    },
  }
}

</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
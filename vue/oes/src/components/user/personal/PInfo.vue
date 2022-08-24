<template>
  <div class="p-info">
    <el-descriptions border direction="vertical" :column="1" style="font-size: medium">
      <el-descriptions-item label="用户名">
        {{ user.userName }}
        <el-link
            @click="changeName"
            :underline="false" type="primary" style="margin: 0 10px">修改
        </el-link>
      </el-descriptions-item>
      <el-descriptions-item label="手机号">
        {{ user.mobile }}
        <el-link
            @click="changePhone"
            :underline="false" type="primary" style="margin: 0 10px">修改
        </el-link>
      </el-descriptions-item>
      <el-descriptions-item label="密码">
        **************
        <el-link
            @click="changePwd"
            :underline="false" type="primary" style="margin: 0 10px">修改
        </el-link>
      </el-descriptions-item>
      <el-descriptions-item label="头像">
        <el-avatar shape="square" :size="60" :src="user.picImg"></el-avatar>
        <el-link
            @click="changeAvatar"
            :underline="false" type="primary" style="margin: 0 30px">修改
        </el-link>
      </el-descriptions-item>
    </el-descriptions>
    <el-dialog
        :visible.sync="visible.name"
        append-to-body
        lock-scroll
        top="25vh"
        center
        modal-append-to-body
        width="25%">
      <el-input v-model="newUser.name" placeholder="请输入新的用户名"></el-input>
      <span slot="footer" class="dialog-footer">
      <el-button
          plain
          type="primary" @click="changeNameConfirm">确 定</el-button>
    </span>
    </el-dialog>
    <el-dialog
        :visible.sync="visible.phone"
        append-to-body
        :close-on-click-modal="false"
        lock-scroll
        top="25vh"
        center
        modal-append-to-body
        width="25%">
      <el-input v-model="newUser.phone"
                style="margin: 20px 0"
                :disabled="codeIsRight"
                placeholder="请输入新的手机号">
        <template slot="append">
          <el-button
              plain
              v-if="sendCoolDown === 0"
              type="primary" @click="sendCode">发送验证码
          </el-button>
          <span v-if="sendCoolDown !== 0">{{ sendCoolDown }}s</span>
        </template>
      </el-input>
      <el-input v-model="newUser.code"
                style="margin: 20px 0"
                placeholder="请输入验证码">
        <template slot="append">
          <el-button
              plain
              v-if="!codeIsRight"
              type="primary" @click="vCode">
            验证
          </el-button>
          <span v-if="codeIsRight" style="color: palegreen">验证成功</span>
        </template>
      </el-input>
      <span slot="footer" class="dialog-footer">
      <el-button
          plain
          type="primary" @click="changePhoneConfirm">确 定</el-button>
    </span>
    </el-dialog>
    <el-dialog
        :visible.sync="visible.pwd"
        append-to-body
        :close-on-click-modal="false"
        lock-scroll
        top="25vh"
        center
        modal-append-to-body
        width="25%">
      <el-input v-model="newUser.pwd"
                style="margin: 20px 0"
                show-password
                placeholder="请输入新的密码">
      </el-input>
      <el-input v-model="newUser.pwdR"
                style="margin: 20px 0"
                show-password
                placeholder="请再次输入新密码">
      </el-input>
      <span slot="footer" class="dialog-footer">
      <el-button
          plain
          type="primary" @click="changePwdConfirm">确 定</el-button>
    </span>
    </el-dialog>
    <el-dialog
        :visible.sync="visible.avatar"
        append-to-body
        :close-on-click-modal="false"
        lock-scroll
        top="25vh"
        center
        width="max-content"
        modal-append-to-body>
        <el-upload
            class="avatar-uploader"
            action=""
            :drag="true"
            :show-file-list="false"
            :on-error="handleAvatarError"
            :on-success="handleAvatarSuccess"
            :data="{userId: user.id}"
            :before-upload="beforeAvatarUpload">
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
      if (this.newUser.code === this.newUser.codeR) {
        this.codeIsRight = true;
      } else {
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
      if (this.newUser.name === this.user.name) {
        this.$message.error('与旧用户名相同');
      } else {
        //修改用户名
        // let promise = this.$axios({
        //     url: '',
        //     method: '',
        //     data:{
        //       userId: this.user.id,
        //       newName: this.newUser.name
        //     }
        // });
        let promise = new Promise((a) => {
          a({
            data: {
              result: true,
              info: '用户名冲突'
            }
          });
        });
        promise.then((res) => {
          if (res.data.result) {
            this.reloadUser();
            this.newUser.name = '';
            this.visible.name = false;
            this.$message.success('用户名修改成功');
          } else {
            this.$message.error(res.data.info);
          }
        }).catch((err) => {
          this.$message.error('你的网络迷路了');
        });
      }
    },
    sendCode() {
      //发送手机号验证码
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data: {
      //       phone: this.newUser.phone
      //     }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            code: '8888'
          }
        });
      });
      promise.then((res) => {
        this.$message.success('已发送验证码');
        this.newUser.codeR = res.data.code;
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
      if(!this.codeIsRight){
        this.$message.error('请先验证手机号');
        return;
      }
      //修改手机号
      // let promise = this.$axios({
      //   url: '',
      //   method: '',
      //   data: {
      //     id: this.user.id,
      //     newPhone: this.newUser.phone
      //   }
      // });
      let promise = new Promise((a) => {
        a({
          data: {
            result: false
          }
        });
      });
      promise.then((res) => {
        if(res.data.result){
          this.$message.success('手机号修改成功');
          this.reloadUser();
          this.newUser.phone = '';
          this.newUser.code = '';
          this.newUser.codeR = '';
          this.codeIsRight = false;
          this.sendCoolDown = 0;
          this.visible.phone = false;
        }else{
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
    changePwdConfirm(){
      if(this.newUser.pwdR !== this.newUser.pwd){
        this.$message.error('两次密码不同');
        return;
      }else if(this.newUser.pwd.length < 8){
        this.$message.error('密码至少8位');
        return;
      }
      else{
        //修改密码
        // let promise = this.$axios({
        //     url: '',
        //     method: '',
        //     data:{
        //       userId: this.user.id,
        //       newPwd: this.newUser.pwd
        //     }
        // });
        let promise = new Promise((a)=>{
            a({
                data:{
                  result: true
                }
            });
        });
        promise.then((res)=>{
          if(res.data.result){
            this.$message.success('修改密码成功');
          }else{
            this.$message.error('修改密码失败');
          }
        }).catch((err)=>{
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
    reloadUser() {
      //获取用户信息(同登录)
      // let promise = this.$axios({
      //     url: '',
      //     method: '',
      //     data:{
      //       userId: this.user.id
      //     }
      // });
      let promise = new Promise((a)=>{
          a({
              data:{
                user:{}//返回对象必要的属性同登录请求
              }
          });
      });
      promise.then((res)=>{
        let nUser = res.data.user;
        window.localStorage.removeItem('user');
        window.localStorage.setItem('user', JSON.stringify(nUser));
        this.user = nUser;
      }).catch((err)=>{
          this.$message.error('你的网络迷路了');
      });
    },
    handleAvatarSuccess(res, file) {
      this.$message.success('修改头像成功');
      this.reloadUser();
      this.visible.avatar = false;
    },
    handleAvatarError(){
      this.$message.error('未知原因导致头像修改失败!');
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isLt2M;
    }
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
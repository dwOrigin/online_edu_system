<template>
  <el-dialog
      class="dialog"
      :visible.sync="isVisible"
      append-to-body
      :close-on-click-modal="false"
      :show-close="false"
      :center="true"
      :before-close="beforeClose"
      top="10vh"
      width="400px">
      <span>
          <el-tabs v-model="active">

            <el-tab-pane name="logIn">
              <span slot="label">
                <span class="tag">登录</span>
              </span>
              <el-form
                    ref="loginForm"
                    status-icon
                    :rules="RulesL"
                    class="form"
                    :model="loginForm">
                  <el-form-item prop="username">
                    <el-input
                        class="inputBox"
                        prefix-icon="el-icon-user"
                        v-model="loginForm.username" placeholder="用户名">
                    </el-input>
                  </el-form-item>
                  <el-form-item prop="password">
                    <el-input
                        class="inputBox"
                        show-password
                        prefix-icon="el-icon-lock"
                        v-model="loginForm.password" placeholder="密码">
                    </el-input>
                  </el-form-item>
                  <div>
                    <span v-show="!antiRobotPassed">滑动到底进行人机验证</span>
                    <el-slider
                        v-show="!antiRobotPassed"
                        :max="1000"
                        :min="0"
                        :show-tooltip="false"
                        @change="sliderChanged"
                        v-model="sliderValue"></el-slider>
                  </div>
                </el-form>
              <div style="display: flex; justify-content: center; margin-top: 20px">
                <el-button type="primary"
                 @click="handleFooterClick('login')"
                 plain
                 size="medium"
                 class="footer-btn"
                 :disabled="!antiRobotPassed"
                 round>
        <span class="btn-content">登&nbsp;&nbsp;录</span>
      </el-button>
                <el-button
          round
          size="medium"
          class="footer-btn"
          @click="handleFooterClick('cancel')">
        <span class="btn-content">取&nbsp;&nbsp;消</span>
      </el-button>
              </div>

            </el-tab-pane>

            <el-tab-pane name="register">
              <span slot="label">
                <span class="tag">注册</span>
              </span>
              <el-form
                  ref="registerForm"
                  status-icon
                  :rules="RulesR"
                  class="form"
                  :model="registerForm">
                  <el-form-item prop="username">
                    <el-input
                        class="inputBox"
                        prefix-icon="el-icon-user"
                        v-model="registerForm.username" placeholder="请输入用户名">
                    </el-input>
                  </el-form-item>
                  <el-form-item prop="password">
                    <el-input
                        class="inputBox"
                        show-password
                        prefix-icon="el-icon-lock"
                        v-model="registerForm.password" placeholder="请输入密码">
                    </el-input>
                  </el-form-item>
                <el-form-item prop="passwordR">
                    <el-input
                        class="inputBox"
                        show-password
                        prefix-icon="el-icon-lock"
                        v-model="registerForm.passwordR" placeholder="请再次输入密码">
                    </el-input>
                  </el-form-item>
                <el-form-item prop="phone">
                    <el-input
                        class="inputBox"
                        :disabled="codeIsRight"
                        prefix-icon="el-icon-phone-outline"
                        v-model="registerForm.phone" placeholder="手机号">
                      <template slot="append">
                        <i v-show="sendCodeInterval === 0" class="el-icon-s-promotion sendCode"
                           @click="sendCode"
                           style="font-size: large"></i>
                        <span v-show="sendCodeInterval !== 0">{{sendCodeInterval}}S</span>
                      </template>
                    </el-input>
                  </el-form-item>
                <el-form-item prop="code">
                    <el-input
                        class="inputBox"
                        v-model="registerForm.code" placeholder="验证码">
                      <template slot="append">
                        <div v-show="!codeIsRight"
                            @click="validateCode"
                            class="validateBtn">验证</div>
                        <div v-show="codeIsRight" style="color: palegreen">验证成功</div>
                      </template>
                    </el-input>
                  </el-form-item>
                <div>
                  <el-button type="primary"
                     @click="handleFooterClick('register')"
                     plain
                     size="medium"
                     class="footer-btn"
                     :disabled="!codeIsRight"
                     round>
                    <span class="btn-content">注&nbsp;&nbsp;册</span>
                  </el-button>
                  <el-button
                     @click="handleFooterClick('cancel')"
                     plain
                     size="medium"
                     class="footer-btn"
                     round>
                    <span class="btn-content">取&nbsp;&nbsp;消</span>
                  </el-button>
                </div>
                </el-form>
            </el-tab-pane>

          </el-tabs>
      </span>
  </el-dialog>
</template>

<script>
export default {
  name: "LoginDialog",
  data() {
    let UNRuleL = (rule, value, callback) => {
      if (value == null || value.length === 0) {
        callback(new Error('请输入用户名'));
      } else {
        callback();
      }
    };
    let PWDRuleL = (rule, value, callback) => {
      if (value == null || value.length === 0) {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    let UNRuleR = (rule, value, callback) => {
      if(value === null || value.length === 0){
        callback(new Error('用户名不能为空'));
      }else if(!/\w{8,}/.test(value)){
        callback(new Error('用户名为8位及以上数字或英文'));
      }else{
        callback();
      }
    };
    let PWDRuleR = (rule, value, callback)=>{
      if(value === null || value.length === 0){
        callback(new Error('密码不能为空'));
      }else if(!/.{8,}/.test(value)){
        callback(new Error('密码至少8位'));
      }else{
        callback();
      }
    };
    let PWDRRuleR = (rule, value, callback)=>{
      if(value !== this.registerForm.password){
        if(this.registerForm.password === ''){
          callback(new Error('请先输入密码'));
        }else{
          callback(new Error('两次密码不相同'));
        }
      }else{
        callback();
      }
    };
    let PhoneRuleR = (rule, value, callback)=>{
        if(value === null || value.length === 0){
          callback(new Error('请输入手机号'));
        }else if(!/^1[3456789]\d{9}$/.test(value)){
          callback('手机号格式错误');
        }else{
          callback();
        }
    };
    return {
      isVisible: false,
      //窗口处在登录还是注册
      active: 'logIn',
      //登录表单
      loginForm: {
        username: '',
        password: ''
      },
      //发送验证码剩余间隔
      sendCodeInterval: 0,
      //验证码校验结果
      codeIsRight: false,
      //注册表单
      registerForm: {
        username: '',
        password: '',
        passwordR: '',
        phone: '',
        code: '',
        codeReceived: null
      },
      //登录表单时校验规则
      RulesL: {
        username: [{validator: UNRuleL, trigger: 'blur'}],
        password: [{validator: PWDRuleL, trigger: 'blur'}]
      },
      //注册验证规则
      RulesR: {
        username: [{validator: UNRuleR, trigger: 'blur'}],
        password: [{validator: PWDRuleR, trigger: 'blur'}],
        passwordR: [{validator: PWDRRuleR, trigger: 'blur'}],
        phone: [{validator: PhoneRuleR, trigger: 'blur'}],
      },
      //值为1000时反人机验证成功
      sliderValue: 0,
      //反人机验证是否通过
      antiRobotPassed: false
    }
  },
  methods: {
    //点击发送验证码时调用
    sendCode(){
      let phone = this.registerForm.phone;
      if(!/^1[3456789]\d{9}$/.test(phone)){
        this.$message.error('手机号格式错误');
      }else{
        //获取验证码
        // let promise = this.$axios({
        //   url: '',
        //   method: '',
        //   data:{
        //     'phone': phone
        //   }
        // });
        let promise = new Promise((a)=>{a({data:{code: 8888}})});
        promise.then((res)=>{
          this.registerForm.codeReceived = res.data.code.toString();
          this.sendCodeInterval = 60;
          let id = setInterval(()=>{
            this.sendCodeInterval--;
            if(this.sendCodeInterval === 0){
              clearInterval(id);
            }
          }, 1000);
        }).catch((err)=>{
          this.$message.error('你的网络迷路了');
        });
      }
    },
    //点击验证验证码时调用
    validateCode(){
      if(this.registerForm.code === this.registerForm.codeReceived && this.registerForm.codeReceived !== null){
        this.codeIsRight = true;
        //防止用户验证成功后修改电话号码
      }else{
        this.$message.error('验证码错误');
      }
    },
    //重置登录窗口所有状态
    resetAllStatus() {
      setTimeout(()=>{
        this.sliderValue = 0;
        this.antiRobotPassed = false;
        this.$refs.loginForm.resetFields();
        this.active = "logIn";
        this.codeIsRight = false;
        this.$refs.registerForm.resetFields();
      }, 50);
    },
    //滚动条改变时检测是否滑倒到底
    sliderChanged() {
      if (this.sliderValue === 1000) {
        this.antiRobotPassed = true;
      }
    },
    //登录窗口关闭时调用
    beforeClose(done) {
      this.resetAllStatus();
      done();
    },
    //  点击登录/取消/注册时调用
    handleFooterClick(choice) {
      if (choice === 'cancel') {
        this.isVisible = false;
        this.resetAllStatus();
      } else if (choice === 'login') {
        //  验证表单
        this.$refs['loginForm'].validate((valid) => {
          if (valid) {
            //用户登录验证
            // let promise = this.$axios({
            //   method: 'post',
            //   url: '/',
            //   data: this.loginForm
            // });

            //测试
            let promise = new Promise((a, r) => {
              a({
                data: {
                  pass: true,
                  user: {
                    id: 1314520,
                    name: '燕子',
                    //头像地址
                    avatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
                    //  历史记录前三条
                    history3: [
                      {courseName: '英语3级', lastTime: new Date(2022, 8, 18).toLocaleTimeString()},
                      {courseName: '英语4级', lastTime:  new Date(2022, 7, 30).toLocaleTimeString()},
                      {courseName: '英语5级', lastTime:  new Date(2021, 8, 18).toLocaleTimeString()},
                    ],
                    //  已收藏课程数量
                    starCourseNum: 6,
                  //  所有看过的课程数
                    historyNum: 5
                  }
                }
              });
            });

            promise.then((res) => {
              let ret = res.data;
              //用户名和密码正确
              if (ret.pass) {
                let user = ret.user;
                window.localStorage.setItem('user', JSON.stringify(user));
                this.isVisible = false;
                this.resetAllStatus();
                this.$message.success('登录成功');
                this.$bus.$emit('AuthorizationChanged');
              } else {//用户名或密码错误
                this.antiRobotPassed = false;
                this.sliderValue = 0;
                this.$message.error('用户名或密码错误');
              }
            }).catch((err) => {
              this.$message.error('网络连接失败');
            });
          } else {
            return false;
          }
        });
      }else if (choice === 'register'){
        this.$refs.registerForm.validate((valid)=>{
          if(valid){
            //用户注册
            // let promise = this.$axios({
            //     url: '',
            //     method: 'post',
            //     data: this.registerForm
            // });
            let promise = new Promise((a)=>{a({data:{success: true, info: '手机号注册过'}});});
            promise.then((res)=>{
              if(res.data.success){
                this.$message.success('注册成功');
                this.resetAllStatus();
              }else{
                this.$message.error(res.data.info);
              }
            }).catch((err)=>{
              this.$message.error('你的网络迷路了');
            });
          }else{
            return false;
          }
        })
      }
    }
  },
  mounted() {
    this.$bus.$on('OpenLoginDialog', () => {
      //查看是否已经登录
      if (window.localStorage.getItem('user') !== null) {
        this.$message.warning('你已经登录, 请勿重复登录!');
      } else {
        this.isVisible = true;
      }
    });
  },
  beforeDestroy() {
    this.$bus.$off('OpenLoginDialog');
  }
}
</script>

<style scoped>
.validateBtn:hover{
  color: #409EFF;
  cursor: pointer;
}
.validateBtn{
  width: 100%;
}
.sendCode:hover{
  cursor: pointer;
  color: #409EFF;
}
.inputBox {
  margin: 0 auto;
}

.btn-content {
  margin: 0 20px;
}

.footer-btn {
  margin: 0 10px;
}

.tag {
  margin: 0 57px;
  font-size: medium;
}

.form {
  width: 80%;
  padding-top: 30px;
  margin: 0 auto;
}

</style>
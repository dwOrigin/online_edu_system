<template>
  <el-dialog
      :visible.sync="visible"
      append-to-body
      :show-close="false"
      :center="true"
      :destroy-on-close="true"
      top="25vh"
      width="30vw">
      <span>
        <el-form
            style="background-color: teal"
            ref="loginForm"
            :model="loginForm"
        >
          <el-form-item>
            <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
          </el-form-item>
        </el-form>
      </span>
    <span slot="footer">
        <el-button type="primary" @click="visible = false">登录</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </span>
  </el-dialog>
</template>

<script>
export default {
  name: "LoginDialog",
  data() {
    return {
      visible: false,
      loginForm: {
        username: null,
      }
    }
  },
  watch: {
    visible(newV) {
      this.$bus.$emit('loginDialogMessage', newV);
    }
  }
  ,
  methods: {},
  mounted() {
    this.$bus.$on('userCardMessage', (data) => {
      this.visible = true;
    });
  },
  beforeDestroy() {
    this.$bus.off('userCardMessage');
  }
}
</script>

<style scoped>

</style>
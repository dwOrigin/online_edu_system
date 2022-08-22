<template>
<div class="p-info">
  <el-descriptions border direction="vertical" :column="1" style="font-size: medium">
    <el-descriptions-item label="用户名">
      {{user.name}}
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>
    </el-descriptions-item>
    <el-descriptions-item label="手机号">
      {{user.phonenumber}}
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>
    </el-descriptions-item>
    <el-descriptions-item label="邮箱">
      {{user.email}}
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>

    </el-descriptions-item>
    <el-descriptions-item label="密码">
      {{user.password}}
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>
    </el-descriptions-item>
  </el-descriptions>
</div>
</template>

<script>
export default {
  name: "PInfo",
  data(){
    return{
      user:{}
    }
  },
  mounted(){
    let user = window.localStorage.getItem('user');
    this.user=JSON.parse(user);
  }
}

</script>

<style scoped>

</style>
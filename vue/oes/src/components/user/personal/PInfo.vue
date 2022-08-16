<template>
<div class="p-info">
  <el-descriptions border direction="vertical" :column="1" style="font-size: medium">
    <el-descriptions-item label="用户名">
      燕子去时
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>
    </el-descriptions-item>
    <el-descriptions-item label="手机号">
      18100000000
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>
    </el-descriptions-item>
    <el-descriptions-item label="邮箱">
      icanotservivewithoutu@yanzi.com
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>

    </el-descriptions-item>
    <el-descriptions-item label="密码">
      *****************
      <el-link :underline="false" type="primary" style="margin: 0 10px">修改</el-link>
    </el-descriptions-item>
    <el-descriptions-item label="个人简介">
      i am a feiwu!
    </el-descriptions-item>
  </el-descriptions>
</div>
</template>

<script>
export default {
  name: "PInfo"
}
</script>

<style scoped>

</style>
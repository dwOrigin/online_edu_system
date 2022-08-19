<template>
  <div style="margin: 40px 0 40px 0">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ name: 'home' }">所有课程</el-breadcrumb-item>
      <el-breadcrumb-item>语言</el-breadcrumb-item>
      <el-breadcrumb-item>速过四级</el-breadcrumb-item>
      <el-breadcrumb-item>[文老师带你学英语]2022版</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: "BreadCrumb"
}
</script>

<style scoped>

</style>
<template>
<div id="main">
      <router-view></router-view>
      <login-dialog></login-dialog>
</div>
</template>

<script>
import LoginDialog from "@/components/user/course/LoginDialog";
export default {
  name: "HomeMain",
  components:{
    LoginDialog
  },
  mounted() {
    this.request.get('/course/sortCourses');
  }
}
</script>

<style scoped>
#main{
  width: 100%;
}
</style>
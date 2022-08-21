<template>
<div>
<ul class="tag-list">
  <li v-for="tag in tagObjList" :key="tag.title">
    <el-link :underline="false"
             @click="$router.push({
                name: tag.routerName,
                query: tag.query
             })"
             class="tag-item">{{tag.title}}</el-link>
  </li>
  <el-link :underline="false"
           @click="$router.push({
              name: 'personal',
              query:{select:user}
           });"
           class="tag-item">个人中心</el-link>
  <el-link :underline="false"
           @click="$bus.$emit('OpenLoginDialog')"
           class="tag-item" v-if="user == null">登录|注册</el-link>
  <a href="#"
     @click="$router.push({
              name: 'personal',
              query:{select: ''}
           });"
     class="mini-img-container" v-if="user != null">
    <el-avatar size="small" :src="user.avatarUrl">
      <span v-if="user.avatarUrl.length === 0">
        {{user.name}}
      </span>
    </el-avatar>
  </a>
</ul>
</div>
</template>

<script>
export default {
  name: "NavRight",
  data(){
    return{
      tagObjList: [
        {title: '文章', routerName: 'qap', query:{select: 'p'}},
        {title: '问答', routerName: 'qap', query:{select: 'q'}},
        {title: '讲师', routerName: 'teacher', query:{}},
      ],
      user: null
    }
  },
  methods: {
  //  更新的登陆信息
    refreshAuthorization() {
      let user = window.localStorage.getItem('user');
      console.log(user);
      if (user != null) {
        user = JSON.parse(user);
        this.user = user;
      } else {
        this.user = null;
      }
    }
  },
  mounted() {
    this.refreshAuthorization();
    this.$bus.$on('AuthorizationChanged', ()=>{
      this.refreshAuthorization();
    });
  },
  beforeDestroy() {
    this.$bus.$off('AuthorizationChanged');
  }
}
</script>

<style scoped>
.mini-img-container {
  /*border-radius: 50%;*/
  /*height: 30px;*/
  /*width: 30px;*/
  /*overflow: hidden;*/
  margin-left: 0;
  margin-right: 5px;
}
.tag-list{
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.tag-item{
  margin: 0 10px;
  font-size: medium;
}
</style>
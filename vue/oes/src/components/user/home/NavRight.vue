<template>
  <div>
    <ul class="tag-list">
      <li v-for="tag in tagObjList" :key="tag.title">
        <el-link :underline="false"
                 @click="$router.push({
                name: tag.routerName,
                params: tag.paramsM,
                query:tag.query
             })"
                 class="tag-item">{{ tag.title }}
        </el-link>
      </li>
      <el-link :underline="false"
               @click="$router.push({
              name: 'personal',
              query:{select:''}
           });"
               class="tag-item">个人中心
      </el-link>
      <el-link :underline="false"
               @click="$bus.$emit('OpenLoginDialog')"
               class="tag-item" v-if="user == null">登录|注册
      </el-link>
      <!--  <a href="#"-->
      <!--     class="mini-img-container" v-if="user != null">-->
      <!--    <el-avatar size="small" :src="user.picImg">-->
      <!--      <span v-if="user.picImg==null||user.picImg==''" >-->
      <!--        {{user.userName}}-->
      <!--      </span>-->
      <!--    </el-avatar>-->

      <!--  </a>-->
      <el-dropdown @command="exit" v-if="user != null">
        <el-avatar class="linkToP"
                   :size="40"
                   @click.native="$router.push({
                      name: 'personal',
                      query:{select:''}
                   })"
                   :src="user.picImg">
      <span v-if="user.picImg==null||user.picImg===''">
        {{ user.userName }}
      </span>
        </el-avatar>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item icon="el-icon-back">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </ul>
  </div>
</template>

<script>
export default {
  name: "NavRight",
  data() {
    return {
      tagObjList: [
        //  使用params避免重复路由问题(尝试避免, 但失败了)
        {title: '文章', routerName: 'qap', paramsM: {select: 'p'}},
        {title: '问答', routerName: 'qap', paramsM: {select: 'q'}},
        {title: '讲师', routerName: 'teacher', query: {}},
      ],
      user: null
    }
  },
  methods: {
    //  更新的登陆信息
    refreshAuthorization() {
      let user = window.localStorage.getItem('user');
      if (user != null) {
        user = JSON.parse(user);
        this.user = user;
      } else {
        this.user = null;
      }
    },
    //  退出登录
    exit() {
      window.localStorage.removeItem('user');
      this.$bus.$emit('AuthorizationChanged');
      this.$router.push('/home/recommend');
    }
  },
  mounted() {
    this.refreshAuthorization();
    this.$bus.$on('AuthorizationChanged', () => {
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

.tag-list {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.tag-item {
  margin: 0 10px;
  font-size: medium;
}

.linkToP:hover {
  cursor: pointer;
}
</style>